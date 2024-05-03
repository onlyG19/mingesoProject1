import axios from "axios";

const REPARACION_API_URL = "http://autofix-app.eastus.cloudapp.azure.com/reparaciones";

function crearReparacion(reparacion){
    return axios.post(REPARACION_API_URL, reparacion)
}

function getReparaciones() {
    return axios.get(REPARACION_API_URL);
}

export default {crearReparacion, getReparaciones}
