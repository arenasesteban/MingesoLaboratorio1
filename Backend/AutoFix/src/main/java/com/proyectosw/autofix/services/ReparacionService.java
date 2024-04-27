package com.proyectosw.autofix.services;

import com.proyectosw.autofix.dtos.ReparacionPorTipoAuto;
import com.proyectosw.autofix.dtos.ReparacionPorTipoMotor;
import com.proyectosw.autofix.entities.ReparacionEntity;
import com.proyectosw.autofix.repositories.ReparacionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReparacionService {
    @Autowired
    ReparacionRespository reparacionRespository;

    @Autowired
    RegistroService registroService;

    public List<ReparacionEntity> crearReparacion(List<ReparacionEntity> reparaciones, Long idRegistro, String tipoMotor) {
        List<ReparacionEntity> reparacionesGuardados = new ArrayList<>();
        int monto = 0;

        for(ReparacionEntity reparacion : reparaciones) {
            if(reparacion.getNumeroReparacion() == 1) {
                monto = switch (tipoMotor) {
                    case "Gasolina", "Diesel" -> 120000;
                    case "Híbrido" -> 180000;
                    case "Eléctrico" -> 220000;
                    default -> 0;
                };
            }
            else if(reparacion.getNumeroReparacion() == 2) {
                monto = switch (tipoMotor) {
                    case "Gasolina", "Diesel" -> 130000;
                    case "Híbrido" -> 190000;
                    case "Eléctrico" -> 230000;
                    default -> 0;
                };
            }
            else if(reparacion.getNumeroReparacion() == 3) {
                monto = switch (tipoMotor) {
                    case "Gasolina" -> 350000;
                    case "Diesel" -> 450000;
                    case "Híbrido" -> 700000;
                    case "Eléctrico" -> 800000;
                    default -> 0;
                };
            }
            else if(reparacion.getNumeroReparacion() == 4) {
                monto = switch (tipoMotor) {
                    case "Gasolina", "Diesel" -> 210000;
                    case "Híbrido", "Eléctrico" -> 300000;
                    default -> 0;
                };
            }
            else if(reparacion.getNumeroReparacion() == 5) {
                monto = switch (tipoMotor) {
                    case "Gasolina", "Diesel" -> 150000;
                    case "Híbrido" -> 200000;
                    case "Eléctrico" -> 250000;
                    default -> 0;
                };
            }
            else if(reparacion.getNumeroReparacion() == 6) {
                monto = switch (tipoMotor) {
                    case "Gasolina" -> 100000;
                    case "Diesel" -> 120000;
                    case "Híbrido" -> 450000;
                    case "Eléctrico" -> 0;
                    default -> 0;
                };
            }
            else if(reparacion.getNumeroReparacion() == 7) {
                monto = switch (tipoMotor) {
                    case "Gasolina", "Diesel", "Híbrido", "Eléctrico" -> 100000;
                    default -> 0;
                };
            }
            else if(reparacion.getNumeroReparacion() == 8) {
                monto = switch (tipoMotor) {
                    case "Gasolina", "Diesel" -> 180000;
                    case "Híbrido" -> 210000;
                    case "Eléctrico" -> 250000;
                    default -> 0;
                };
            }
            else if(reparacion.getNumeroReparacion() == 9) {
                monto = switch (tipoMotor) {
                    case "Gasolina", "Diesel" -> 150000;
                    case "Híbrido", "Eléctrico" -> 180000;
                    default -> 0;
                };
            }
            else if(reparacion.getNumeroReparacion() == 10) {
                monto = switch (tipoMotor) {
                    case "Gasolina" -> 130000;
                    case "Diesel" -> 140000;
                    case "Híbrido" -> 220000;
                    case "Eléctrico" -> 0;
                    default -> 0;
                };
            }
            else if(reparacion.getNumeroReparacion() == 11) {
                monto = switch (tipoMotor) {
                    case "Gasolina", "Diesel", "Híbrido", "Eléctrico" -> 80000;
                    default -> 0;
                };
            }

            reparacion.setPrecio(monto);
            reparacion.setIdRegistro(idRegistro);
            ReparacionEntity reparacionGuardado = reparacionRespository.save(reparacion);
            reparacionesGuardados.add(reparacionGuardado);
        }

        return reparacionesGuardados;
    }

    public List<ReparacionPorTipoAuto> reporteReparacionPorTipoAuto() {
        List<ReparacionPorTipoAuto> reparacionesPorTipoAuto = new ArrayList<>();

        for(int numeroReparacion = 1; numeroReparacion <= 11; numeroReparacion++) {
            List<Long> idRegistros = reparacionRespository.findIdRegistroByNumeroReparacion(numeroReparacion);

            String tipoReparacion = reparacionRespository.findFirstByIdRegistro(idRegistros.get(0)).getTipoReparacion();
            int numeroTiposAutos = registroService.obtenerNumeroTiposAutos(idRegistros);
            int montoTotal = reparacionRespository.sumPrecioByNumeroReparacion(numeroReparacion);

            ReparacionPorTipoAuto reparacionPorTipoAuto = new ReparacionPorTipoAuto(tipoReparacion, numeroTiposAutos, montoTotal);
            reparacionesPorTipoAuto.add(reparacionPorTipoAuto);
        }

        return reparacionesPorTipoAuto;
    }

    public List<ReparacionPorTipoMotor> reporteReparacionPorTipoMotor() {
        List<ReparacionPorTipoMotor> reparacionesTipoMotor = new ArrayList<>();

        for(int numeroReparacion = 1; numeroReparacion <= 11; numeroReparacion++) {
            List<Long> idRegistros = reparacionRespository.findIdRegistroByNumeroReparacion(numeroReparacion);

            String tipoReparacion = reparacionRespository.findFirstByIdRegistro(idRegistros.get(0)).getTipoReparacion();
            int cantidadGasolina = registroService.obtenerNumeroPorTiposMotores(idRegistros, "Gasolina");
            int cantidadDiesel = registroService.obtenerNumeroPorTiposMotores(idRegistros, "Diesel");
            int cantidadHibrido = registroService.obtenerNumeroPorTiposMotores(idRegistros, "Hibrido");
            int cantidadElectrico = registroService.obtenerNumeroPorTiposMotores(idRegistros, "Electrico");
            int montoTotal = reparacionRespository.sumPrecioByNumeroReparacion(numeroReparacion);

            ReparacionPorTipoMotor reparacionPorTipoMotor = new ReparacionPorTipoMotor(tipoReparacion, cantidadGasolina, cantidadDiesel, cantidadHibrido, cantidadElectrico, montoTotal);
            reparacionesTipoMotor.add(reparacionPorTipoMotor);
        }

        return reparacionesTipoMotor;
    }
}
