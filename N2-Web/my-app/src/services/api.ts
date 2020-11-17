import axios from 'axios';
const proxyurl = "https://cors-anywhere.herokuapp.com/";
const api = axios.create({
    // baseURL: 'http://25.98.165.147:8080/projnotas.api/'
    baseURL: 'http://localhost:8080/projnotas.api/'
});  //192.168.0.32


export default api;