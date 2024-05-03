import axios from "axios";

const VEHICULO_API_URL = "http://autofix-app.eastus.cloudapp.azure.com/vehiculos";

function crearVehiculo(vehiculo){
    return axios.post(VEHICULO_API_URL, vehiculo)
}

function getVehiculos() {
    return axios.get(VEHICULO_API_URL);
}

export default {crearVehiculo, getVehiculos}
