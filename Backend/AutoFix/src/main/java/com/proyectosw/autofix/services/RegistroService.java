package com.proyectosw.autofix.services;

import com.proyectosw.autofix.dtos.Detalle;
import com.proyectosw.autofix.entities.RegistroEntity;
import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.entities.VehiculoEntity;
import com.proyectosw.autofix.repositories.RegistroRepository;
import com.proyectosw.autofix.repositories.ReparacionRespository;
import com.proyectosw.autofix.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistroService {
    @Autowired
    RegistroRepository registroRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Autowired
    ReparacionRespository reparacionRespository;

    public RegistroEntity crearRegistro(RegistroEntity registro) {
        return registroRepository.save(registro);
    }

    public List<RegistroEntity> obtenerRegistros() {
        return registroRepository.findAll();
    }

    public Detalle calcularTotal(Long idRegistro, int descuentoPorBono) {
        RegistroEntity registro = registroRepository.findByIdRegistro(idRegistro);
        VehiculoEntity vehiculo = vehiculoRepository.findByPatente(registro.getPatente());

        int sumaReparaciones = calcularTotalReparaciones(registro.getIdRegistro());

        double recargoPorKilometraje = sumaReparaciones * recargoPorKilometraje(vehiculo.getKilometraje(), vehiculo.getTipoAuto());
        double recargoPorAntiguedad = sumaReparaciones * recargoPorAntiguedad(vehiculo.getAnoFabricacion(), vehiculo.getTipoAuto());
        double recargoPorRestrasoRecogida = sumaReparaciones * recargoPorRetrasoRecogida(registro.getFechaSalida(), registro.getFechaRetiro());

        double descuentoPorNumeroReparacion = sumaReparaciones * descuentoPorNumeroReparaciones(registro.getPatente(), vehiculo.getTipoMotor(), registro.getFechaIngreso());
        double descuentoPorDiaAtencion = sumaReparaciones * descuentoPorDiaAtencion(registro.getFechaIngreso(), registro.getHoraIngreso());

        int recargos = (int) (recargoPorKilometraje + recargoPorAntiguedad + recargoPorRestrasoRecogida);
        int descuentos = (int) (descuentoPorNumeroReparacion + descuentoPorDiaAtencion + descuentoPorBono);
        int iva = (int) ((sumaReparaciones + recargos - descuentos) * .19);

        int montoTotal = (sumaReparaciones + recargos - descuentos) + iva;

        registro.setMontoTotal(montoTotal);

        registroRepository.save(registro);

        return new Detalle(sumaReparaciones, recargos, descuentos, iva, montoTotal);
    }

    public int calcularTotalReparaciones(Long idRegistro) {
        List<ReparacionEntity> reparaciones = reparacionRespository.findByIdRegistro(idRegistro);
        int sumaReparaciones = 0;

        for(ReparacionEntity reparacion : reparaciones) {
            sumaReparaciones += reparacion.getPrecio();
        }

        return sumaReparaciones;
    }

    public double descuentoPorNumeroReparaciones(String patente, String tipoMotor, LocalDate fechaIngreso) {
        int numeroReparaciones = contarReparaciones(patente, fechaIngreso);
        double descuento = .0;

        if(numeroReparaciones >= 1 && numeroReparaciones <= 2) {
            descuento = switch (tipoMotor) {
                case "Gasolina" -> .05;
                case "Diesel" -> .07;
                case "Híbrido" -> .1;
                case "Eléctrico" -> .08;
                default -> descuento;
            };
        }
        else if(numeroReparaciones >= 3 && numeroReparaciones <= 5) {
            descuento = switch (tipoMotor) {
                case "Gasolina" -> .1;
                case "Diesel" -> .12;
                case "Híbrido" -> .15;
                case "Eléctrico" -> .13;
                default -> descuento;
            };
        }
        else if(numeroReparaciones >= 6 && numeroReparaciones <= 9) {
            descuento = switch (tipoMotor) {
                case "Gasolina" -> .15;
                case "Diesel" -> .17;
                case "Híbrido" -> .2;
                case "Eléctrico" -> .18;
                default -> descuento;
            };
        }
        else if(numeroReparaciones >= 10) {
            descuento = switch (tipoMotor) {
                case "Gasolina" -> .2;
                case "Diesel" -> .22;
                case "Híbrido" -> .25;
                case "Eléctrico" -> .23;
                default -> descuento;
            };
        }

        System.out.println("D reparaciones = " + descuento);
        return descuento;
    }

    public double descuentoPorDiaAtencion(LocalDate fechaIngreso, LocalTime horaIngreso) {
        DayOfWeek diaSemanaIngreso = fechaIngreso.getDayOfWeek();
        double descuento = .0;

        if (diaSemanaIngreso == DayOfWeek.MONDAY || diaSemanaIngreso == DayOfWeek.THURSDAY) {
            if (!horaIngreso.isBefore(LocalTime.of(9, 0)) && !horaIngreso.isAfter(LocalTime.of(12, 0))) {
                descuento = .1;
            }
        }

        System.out.println("D atencion = " + descuento);
        return descuento;
    }

    public double recargoPorKilometraje(Integer kilometraje, String tipoAuto) {
        double recargo = .0;

        if(kilometraje >= 5001 && kilometraje <= 12000) {
            recargo = switch (tipoAuto) {
                case "Sedan", "Hatchback" -> .03;
                case "SUV", "Pickup", "Furgoneta" -> .05;
                default -> recargo;
            };
        }
        else if(kilometraje >= 12001 && kilometraje <= 25000) {
            recargo = switch (tipoAuto) {
                case "Sedan", "Hatchback" -> .07;
                case "SUV", "Pickup", "Furgoneta" -> .09;
                default -> recargo;
            };
        }
        else if(kilometraje >= 25001 && kilometraje <= 40000) {
            recargo = switch (tipoAuto) {
                case "Sedan", "Hatchback", "SUV", "Pickup", "Furgoneta" -> .12;
                default -> recargo;
            };
        }
        else if(kilometraje > 40000) {
            recargo = switch (tipoAuto) {
                case "Sedan", "Hatchback", "SUV", "Pickup", "Furgoneta" -> .2;
                default -> recargo;
            };
        }

        System.out.println("R kilometraje = " + recargo);
        return recargo;
    }

    public double recargoPorAntiguedad(int anoFabricacion, String tipoAuto) {
        int anoAntiguedad = 2024 - anoFabricacion;
        double recargo = .0;

        if(anoAntiguedad >= 6 && anoAntiguedad <= 10) {
            recargo = switch (tipoAuto) {
                case "Sedan", "Hatchback" -> .05;
                case "SUV", "Pickup", "Furgoneta" -> .07;
                default -> recargo;
            };
        }
        else if(anoAntiguedad >= 11 && anoAntiguedad <= 15) {
            recargo = switch (tipoAuto) {
                case "Sedan", "Hatchback" -> .09;
                case "SUV", "Pickup", "Furgoneta" -> .11;
                default -> recargo;
            };
        }
        else if(anoAntiguedad >= 16) {
            recargo = switch (tipoAuto) {
                case "Sedan", "Hatchback" -> .15;
                case "SUV", "Pickup", "Furgoneta" -> .20;
                default -> recargo;
            };
        }

        System.out.println("R antiguedad = " + recargo);
        return recargo;
    }

    public double recargoPorRetrasoRecogida(LocalDate fechaSalida, LocalDate fechaRetiro) {
        int retraso = Period.between(fechaSalida, fechaRetiro).getDays();
        System.out.println("R retraso = " + retraso);
        return retraso * .05;
    }

    public int contarReparaciones(String patente, LocalDate fechaFinal) {
        LocalDate fechaInicio = fechaFinal.minusMonths(12).minusDays(1);
        List<Long> idRegistros = registroRepository.findByPatenteAndFechaReparacionAnterior(patente, fechaInicio, fechaFinal);
        int cantidadReparaciones = 0;
        System.out.println("IdRegistros = " + idRegistros);
        System.out.println("fechaFinal = " + fechaFinal);
        System.out.println("fechaInicio = " + fechaInicio);

        for(Long idRegistro : idRegistros) {
            cantidadReparaciones += reparacionRespository.findByIdRegistro(idRegistro).size();
        }
        System.out.println("Cantidad reparaciones = " + cantidadReparaciones);
        return cantidadReparaciones;
    }

    public int obtenerNumeroTiposAutos(List<Long> idRegistros) {
        List<String> tiposAutos = new ArrayList<>();

        for(Long idRegistro : idRegistros) {
            String patente = registroRepository.findPatenteByIdRegistro(idRegistro);
            String tipoAuto = vehiculoRepository.findByPatente(patente).getTipoAuto();

            if(!tiposAutos.contains(tipoAuto)) {
                tiposAutos.add(tipoAuto);
            }
        }

        return tiposAutos.size();
    }
    public int obtenerNumeroPorTiposMotores(List<Long> idRegistros, String tipoMotor) {
        List<String> patentes = new ArrayList<>();
        int numeroAutos = 0;

        for(Long idRegistro : idRegistros) {
            String patente = registroRepository.findPatenteByIdRegistro(idRegistro);

            if(!patentes.contains(patente)) {
                numeroAutos += vehiculoRepository.countTipoMotor(patente, tipoMotor);
                patentes.add(patente);
            }
        }

        return numeroAutos;
    }

    public int calcularPromedioTiempoReparacion(List<String> patentes) {
        long sumaDiasReparacion = 0L;
        int cantidadRegistros = 0;

        for(String patente : patentes) {
            List<RegistroEntity> registros = registroRepository.findRegistroEntitiesByPatente(patente);

            for(RegistroEntity registro : registros) {
                sumaDiasReparacion += ChronoUnit.DAYS.between(registro.getFechaIngreso(), registro.getFechaSalida());
            }

            cantidadRegistros += registros.size();
        }

        return (int) (sumaDiasReparacion / cantidadRegistros);
    }
}
