import axios from "axios";

const DETALLE_API_URL = "http://localhost:8090/detalle/";

function obtenerDetalle() {
    return axios.get(DETALLE_API_URL);
}

export default { obtenerDetalle };