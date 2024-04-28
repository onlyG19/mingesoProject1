import axios from "axios";

const REPORTES_API_URL = "http://localhost:8080/reportes";

function getDataReporte2(){
    return axios.get(REPORTES_API_URL+"/2");
}


export default {getDataReporte2, }
