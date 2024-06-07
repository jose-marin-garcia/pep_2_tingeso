import httpClient from "../http-common";

const getVehicleCosts = () => {
    return httpClient.get('/reports/costos-vehiculos');
}

const getReport1 = (mes, anio) => {
    return httpClient.get(`/reports/report1?mes=${mes}&anio=${anio}`);
}

const getReport2 = (mes,anio) => {
    return httpClient.get(`/reports/report2?mes=${mes}&anio=${anio}`);
}

export default { getVehicleCosts, getReport1, getReport2 };