import axios from "axios";

const REGISTRO_API_URL = "http://localhost:8090/registro/";

function obtenerRegistros() {
    return axios.get(REGISTRO_API_URL);
}

export default { obtenerRegistros };