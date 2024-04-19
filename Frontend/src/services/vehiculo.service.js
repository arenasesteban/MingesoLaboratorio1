import axios from "axios";

const VEHICULO_API_URL = "http://localhost:8090/vehiculo/";

function obtenerVehiculos() {
    return axios.get(VEHICULO_API_URL);
}

export default { obtenerVehiculos };