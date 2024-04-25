import axios from "axios";

const DETALLE_API_URL = "http://localhost:8090/detalle/";

function obtenerDetalle(idRegistro) {
    return axios.get(DETALLE_API_URL, { params: { idRegistro: idRegistro }});
}

export default { obtenerDetalle };