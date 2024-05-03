import axios from "axios";

const REPORTES_API_URL = "http://autofix-app.eastus.cloudapp.azure.com/reportes";

function getDataReporte1(){
    return axios.get(REPORTES_API_URL+"/1");
}

function getDataReporte2(){
    return axios.get(REPORTES_API_URL+"/2");
}

function getDataReporte3(){
    return axios.get(REPORTES_API_URL+"/3");
}
function getDataReporte4(){
    return axios.get(REPORTES_API_URL+"/4");
}



export default {getDataReporte2, getDataReporte4, getDataReporte3, getDataReporte1}
