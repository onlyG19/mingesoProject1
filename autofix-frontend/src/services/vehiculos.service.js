import axios from "axios";

const VEHICULO_API_URL = "http://localhost:8080/vehiculos";

function crearVehiculo(vehiculo){
    return axios.post(VEHICULO_API_URL, vehiculo)
}

function getVehiculos() {
    return axios.get(VEHICULO_API_URL);
}

export default {crearVehiculo, getVehiculos}
