import React, { useState, useEffect } from 'react';
import reportesService from '../services/reportes.service';

const VehicleCosts = () => {
    const [vehicleCosts, setVehicleCosts] = useState([]);
    const [selectedPatent, setSelectedPatent] = useState(null);

    useEffect(() => {
        reportesService
            .getVehicleCosts()
            .then((response) => {
                console.log("Mostrando costos de vehículos.", response.data);
                setVehicleCosts(response.data);
            })
            .catch((error) => {
                console.log("Se ha producido un error al intentar mostrar Reporte 1.", error);
            });
    }, []);

    const handleDetailToggle = (patent) => {
        setSelectedPatent(selectedPatent === patent ? null : patent);
    };

    return (
        <div>
            <h1>Listado de historiales</h1>
            <div className="container">
    <table>
        <thead>
            <tr>
                <th>Patente</th>
                <th>Marca</th>
                <th>Modelo</th>
                <th>Tipo Vehículo</th>
                <th>Tipo Motor</th>
                <th>Año Fca.</th>
                <th>Fecha Ingreso Taller</th>
                <th>Hora Ingreso taller</th>
                <th>Monto Total Reparaciones</th>
                <th>Recargos</th>
                <th>Desct.º</th>
                <th>IVA</th>
                <th>Costo Total</th>
                <th>Fecha Salida Taller</th>
                <th>Hora Salida Taller</th>
                <th>Fecha Retiro Cliente</th>
                <th>Hora Retiro Cliente</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            {vehicleCosts.map((vehicle, index) => (
                <tr key={index} className={selectedPatent === vehicle.patent ? 'active' : ''}>
                    <td>{vehicle.patent}</td>
                    <td>{vehicle.mark}</td>
                    <td>{vehicle.model}</td>
                    <td>{vehicle.type}</td>
                    <td>{vehicle.typemotor}</td>
                    <td>{vehicle.year}</td>
                    <td>{vehicle.admissiondate}</td>
                    <td>{vehicle.admissionhour}</td>
                    <td>{vehicle.sumaReparaciones}</td>
                    <td>{vehicle.recargos}</td>
                    <td>{vehicle.descuentos}</td>
                    <td>{vehicle.iva}</td>
                    <td>{vehicle.mount}</td>
                    <td>{vehicle.enddate}</td>
                    <td>{vehicle.endhour}</td>
                    <td>{vehicle.clientdate}</td>
                    <td>{vehicle.clienthour}</td>
                    <td>
                        <button className={`button-detail ${selectedPatent === vehicle.patent ? 'selected' : ''}`} onClick={() => handleDetailToggle(vehicle.patent)}>Ver Detalle</button>
                        {selectedPatent === vehicle.patent && (
                            <ul className="details">
                                Reparaciones:
                                {vehicle.reparaciones.map((repair, index) => (
                                    <li key={index}>{repair}</li>
                                ))}
                            </ul>
                        )}
                    </td>
                </tr>
            ))}
        </tbody>
    </table>
</div>

        </div>
    );
};

export default VehicleCosts;
