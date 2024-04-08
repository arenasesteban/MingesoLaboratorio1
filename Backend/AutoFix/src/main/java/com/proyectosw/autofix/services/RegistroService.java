package com.proyectosw.autofix.services;

import com.proyectosw.autofix.dtos.ReparacionPorTipoMotor;
import com.proyectosw.autofix.entities.RegistroEntity;
import com.proyectosw.autofix.entities.VehiculoEntity;
import com.proyectosw.autofix.repositories.RegistroRepository;
import com.proyectosw.autofix.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistroService {
    @Autowired
    RegistroRepository registroRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Autowired
    ReparacionService reparacionService;

    @Autowired
    DetalleService detalleService;

    @Autowired
    BonoService bonoService;

    public RegistroEntity crearRegistro(RegistroEntity registro) {
        return registroRepository.save(registro);
    }

    public RegistroEntity calcularTotal(Long idRegistro, Boolean bono) {
        RegistroEntity registro = registroRepository.findByIdRegistro(idRegistro);
        VehiculoEntity vehiculo = vehiculoRepository.findByPatente(registro.getPatente());

        int sumaReparaciones = reparacionService.calcularTotalReparaciones(registro.getIdRegistro());

        System.out.println(sumaReparaciones);

        double recargoPorKilometraje = sumaReparaciones * recargoPorKilometraje(vehiculo.getKilometraje(), vehiculo.getTipoAuto());
        double recargoPorAntiguedad = sumaReparaciones * recargoPorAntiguedad(vehiculo.getAnoFabricacion(), vehiculo.getTipoAuto());
        double recargoPorRestrasoRecogida = sumaReparaciones * recargoPorRetrasoRecogida(registro.getFechaSalida(), registro.getFechaRetiro());

        System.out.println(recargoPorKilometraje);
        System.out.println(recargoPorAntiguedad);
        System.out.println(recargoPorRestrasoRecogida);

        double descuentoPorNumeroReparacion = sumaReparaciones * descuentoPorNumeroReparaciones(registro.getPatente(), vehiculo.getTipoMotor());
        double descuentoPorDiaAtencion = sumaReparaciones * descuentoPorDiaAtencion(registro.getFechaIngreso(), registro.getHoraIngreso());
        int descuentoPorBonos = descuentoPorBonos(vehiculo.getMarca(), bono);

        System.out.println(descuentoPorNumeroReparacion);
        System.out.println(descuentoPorDiaAtencion);
        System.out.println(descuentoPorBonos);

        int recargos = (int) (recargoPorKilometraje + recargoPorAntiguedad + recargoPorRestrasoRecogida);
        int descuentos = (int) (descuentoPorNumeroReparacion + descuentoPorDiaAtencion + descuentoPorBonos);
        int iva = (int) ((sumaReparaciones + recargos - descuentos) * .19);

        int montoTotal = (sumaReparaciones + recargos - descuentos) + iva;

        registro.setMontoTotal(montoTotal);

        detalleService.crearDetalle(sumaReparaciones, recargos, descuentos, iva, idRegistro);

        return registroRepository.save(registro);
    }

    public double descuentoPorNumeroReparaciones(String patente, String tipoAuto) {
        Integer numeroReparaciones = contarReparaciones(patente);
        double descuento = .0;

        if(numeroReparaciones >= 1 && numeroReparaciones <= 2) {
            descuento = switch (tipoAuto) {
                case "Gasolina" -> .05;
                case "Diesel" -> .07;
                case "Hibrido" -> .1;
                case "Electrico" -> .08;
                default -> descuento;
            };
        }
        else if(numeroReparaciones >= 3 && numeroReparaciones <= 5) {
            descuento = switch (tipoAuto) {
                case "Gasolina" -> .1;
                case "Diesel" -> .12;
                case "Hibrido" -> .15;
                case "Electrico" -> .13;
                default -> descuento;
            };
        }
        else if(numeroReparaciones >= 6 && numeroReparaciones <= 9) {
            descuento = switch (tipoAuto) {
                case "Gasolina" -> .15;
                case "Diesel" -> .17;
                case "Hibrido" -> .2;
                case "Electrico" -> .18;
                default -> descuento;
            };
        }
        else if(numeroReparaciones >= 10) {
            descuento = switch (tipoAuto) {
                case "Gasolina" -> .2;
                case "Diesel" -> .22;
                case "Hibrido" -> .25;
                case "Electrico" -> .23;
                default -> descuento;
            };
        }

        return descuento;
    }

    public double descuentoPorDiaAtencion(LocalDate fechaIngreso, LocalTime horaIngreso) {
        DayOfWeek diaSemanaIngreso = fechaIngreso.getDayOfWeek();
        double descuento = .0;

        if (diaSemanaIngreso.compareTo(DayOfWeek.THURSDAY) <= 0) {
            if(!horaIngreso.isBefore(LocalTime.of(9, 0)) && !horaIngreso.isAfter(LocalTime.of(12, 0))) {
                descuento = .1;
            }
        }

        return descuento;
    }

    public int descuentoPorBonos(String marca, Boolean bono) {
        int descuento = 0;

        if(bono) {
            return bonoService.aplicarBono(marca);
        }

        return descuento;
    }

    public double recargoPorKilometraje(Integer kilometraje, String tipoAuto) {
        double descuento = .0;

        if(kilometraje >= 5001 && kilometraje <= 12000) {
            descuento = switch (tipoAuto) {
                case "Sedan" -> .03;
                case "Hatchback" -> .03;
                case "SUV" -> .05;
                case "Pickup" -> .05;
                case "Furgoneta" -> .05;
                default -> descuento;
            };
        }
        else if(kilometraje >= 12001 && kilometraje <= 25000) {
            descuento = switch (tipoAuto) {
                case "Sedan" -> .07;
                case "Hatchback" -> .07;
                case "SUV" -> .09;
                case "Pickup" -> .09;
                case "Furgoneta" -> .09;
                default -> descuento;
            };
        }
        else if(kilometraje >= 25001 && kilometraje <= 40000) {
            descuento = switch (tipoAuto) {
                case "Sedan" -> .12;
                case "Hatchback" -> .12;
                case "SUV" -> .12;
                case "Pickup" -> .12;
                case "Furgoneta" -> .12;
                default -> descuento;
            };
        }
        else if(kilometraje > 40000) {
            descuento = switch (tipoAuto) {
                case "Sedan" -> .20;
                case "Hatchback" -> .20;
                case "SUV" -> .20;
                case "Pickup" -> .20;
                case "Furgoneta" -> .20;
                default -> descuento;
            };
        }

        return descuento;
    }

    public double recargoPorAntiguedad(int anoFabricacion, String tipoAuto) {
        int anoAntiguedad = 2024 - anoFabricacion;
        double descuento = .0;

        if(anoAntiguedad >= 6 && anoAntiguedad <= 10) {
            descuento = switch (tipoAuto) {
                case "Sedan" -> .05;
                case "Hatchback" -> .05;
                case "SUV" -> .07;
                case "Pickup" -> .07;
                case "Furgoneta" -> .07;
                default -> descuento;
            };
        }
        else if(anoAntiguedad >= 11 && anoAntiguedad <= 15) {
            descuento = switch (tipoAuto) {
                case "Sedan" -> .09;
                case "Hatchback" -> .09;
                case "SUV" -> .11;
                case "Pickup" -> .11;
                case "Furgoneta" -> .11;
                default -> descuento;
            };
        }
        else if(anoAntiguedad >= 16) {
            descuento = switch (tipoAuto) {
                case "Sedan" -> .15;
                case "Hatchback" -> .15;
                case "SUV" -> .20;
                case "Pickup" -> .20;
                case "Furgoneta" -> .20;
                default -> descuento;
            };
        }

        return descuento;
    }

    public double recargoPorRetrasoRecogida(LocalDate fechaSalida, LocalDate fechaRetiro) {
        int retraso = Period.between(fechaSalida, fechaRetiro).getDays();
        return retraso * .05;
    }

    public int contarReparaciones(String patente) {
        LocalDate fechaInicio = LocalDate.now().minusMonths(12);
        List<RegistroEntity> registros = registroRepository.findByPatenteAndFechaSalidaAfter(patente, fechaInicio);
        int cantidadReparaciones = 0;

        for(RegistroEntity registro : registros) {
            cantidadReparaciones += reparacionService.contarReparaciones(registro.getIdRegistro());
        }

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
        int numeroAutos = 0;

        for(Long idRegistro : idRegistros) {
            String patente = registroRepository.findPatenteByIdRegistro(idRegistro);
            numeroAutos += vehiculoRepository.countTipoMotor(patente, tipoMotor);
        }

        return numeroAutos;
    }
}
