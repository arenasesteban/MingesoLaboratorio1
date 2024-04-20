import axios from "axios";

const REPARACION_API_URL = "http://localhost:8090/reparacion/";

function obtenerRepracionTipoVehiculo() {
    return axios.get(REPARACION_API_URL + "reparaciones-por-tipo-auto")
}

function obtenerReparacionTipoMotor() {
    return axios.get(REPARACION_API_URL + "reparaciones-por-tipo-motor")
}

export default { obtenerRepracionTipoVehiculo, obtenerReparacionTipoMotor };