import axios from "axios";

const REPARACION_API_URL = "http://localhost:8080/reparaciones";

function crearReparacion(reparacion){
    return axios.post(REPARACION_API_URL, reparacion)
}

function getReparaciones() {
    return axios.get(REPARACION_API_URL);
}

export default {crearReparacion, getReparaciones}
