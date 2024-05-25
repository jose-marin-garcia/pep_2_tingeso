import httpClient from "../http-common";

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
    return httpClient.get('/register/vehicles-not-finished/');
}

const finalizarReparacion = patent => {
    return httpClient.put(`/register/finish/${patent}`);
}

const getVehiclesNotRemoved = () => {
    return httpClient.get('/register/vehicles-not-removed/');
}

const retirarAuto = patent => {
    return httpClient.put(`/register/remove/${patent}`);
}

const getBond = idMark => {
    return httpClient.get(`/repairs/bonds/${idMark}`);
}

export default { getMarks, getRepairs, create, update, getBond, finalizarReparacion, getVehiclesNotFinished, getVehiclesNotRemoved, retirarAuto };