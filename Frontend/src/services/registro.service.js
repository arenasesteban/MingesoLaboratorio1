import axios from "axios";

const REGISTRO_API_URL = "http://localhost:8090/registro/";

function crearRegistro(registro) {
    return axios.post(REGISTRO_API_URL, registro);
}

function obtenerRegistros() {
    return axios.get(REGISTRO_API_URL);
}

function calcularTotal() {
    return axios.get(REGISTRO_API_URL + "calcular-total");
}

export default { crearRegistro, obtenerRegistros, calcularTotal };