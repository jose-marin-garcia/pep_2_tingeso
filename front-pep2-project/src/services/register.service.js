import httpClient from "../http-common";

const saveMark = (mark) => {
    return httpClient.post(`/repairs/mark/${mark}`);
}

const savePrices = (formValues) => {
    return httpClient.post('/prices/reparacion-precios', formValues);
}

const getMarks = () => {
    return httpClient.get('/repairs/');
}

const getRepairs = () => {
    return httpClient.get('/prices/')
}

const create = (vehicle, reparations, idBond) => {
    const data = { vehicle, reparations, idBond };
    return httpClient.post('/repairs/', data);
}

const update = (vehicle, reparations, idBond) => {
    const data = { vehicle, reparations, idBond };
    return httpClient.put('/register/', data);
}

const getVehiclesNotFinished = () => {
    return httpClient.get('/repairs/vehicles-not-finished/');
}

const finalizarReparacion = patent => {
    return httpClient.put(`/repairs/finish/${patent}`);
}

const getVehiclesNotRemoved = () => {
    return httpClient.get('/repairs/vehicles-not-removed/');
}

const retirarAuto = patent => {
    return httpClient.put(`/repairs/remove/${patent}`);
}

const getBond = idMark => {
    return httpClient.get(`/repairs/bonds/${idMark}`);
}

export default { saveMark, savePrices, getMarks, getRepairs, create, update, getBond, finalizarReparacion, getVehiclesNotFinished, getVehiclesNotRemoved, retirarAuto };