import React, { useState, useEffect } from 'react';
import reportesService from '../services/reportes.service';
import { Button, TextField, MenuItem, Box, FormControl, InputLabel, Select, Typography } from '@mui/material';
import OutlinedInput from '@mui/material/OutlinedInput';

const ReparacionesResumen = () => {
    const [ReparacionesResumen, setReparacionesResumen] = useState([]);
    const [mes, setMes] = useState('');
    const [anio, setAnio] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const handleSubmit = () => {
        if (mes && anio) {
            setLoading(true);
            setError('');
            reportesService.getReport1(mes, anio)
                .then(response => {
                    console.log("Mostrando resumen de reparaciones.", response.data);
                    setReparacionesResumen(response.data);
                    setLoading(false);
                })
                .catch(error => {
                    console.log("Error al intentar mostrar el reporte.", error);
                    setError('Error al cargar los datos del reporte.');
                    setLoading(false);
                });
        } else {
            alert("Por favor, complete todos los campos requeridos.");
        }
    };

    return (
        <Box sx={{ p: 4, display: 'flex', flexDirection: 'column', gap: 2 }}>
            <h1>Reporte 1</h1>
            <div class="form-container">
            <FormControl fullWidth>
                <InputLabel id="mes-label">Mes</InputLabel>
                <Select
                    labelId="mes-label"
                    id="mes-select"
                    value={mes}
                    onChange={(e) => setMes(e.target.value)}
                    required
                    displayEmpty
                    input={<OutlinedInput label="Mes" />}
                >
                    <MenuItem value=""><em>Seleccione un mes</em></MenuItem>
                    <MenuItem value="01">Enero</MenuItem>
                    <MenuItem value="02">Febrero</MenuItem>
                    <MenuItem value="03">Marzo</MenuItem>
                    <MenuItem value="04">Abril</MenuItem>
                    <MenuItem value="05">Mayo</MenuItem>
                    <MenuItem value="06">Junio</MenuItem>
                    <MenuItem value="07">Julio</MenuItem>
                    <MenuItem value="08">Agosto</MenuItem>
                    <MenuItem value="09">Septiembre</MenuItem>
                    <MenuItem value="10">Octubre</MenuItem>
                    <MenuItem value="11">Noviembre</MenuItem>
                    <MenuItem value="12">Diciembre</MenuItem>
                </Select>
            </FormControl>
            <TextField
                id="anio-input"
                label="Año"
                type="number"
                value={anio}
                onChange={(e) => setAnio(e.target.value)}
                required
                fullWidth
            />
            <Button variant="contained" color="primary" onClick={handleSubmit}>
                Generar Reporte
            </Button>
            </div>
            {loading && <Typography>Cargando...</Typography>}
            {error && <Typography color="error">{error}</Typography>}
            {ReparacionesResumen.length> 0 ? (
            <table>
                <thead>
                    <tr>
                        <th rowSpan="2">Lista de reparaciones</th>
                        <th colSpan="2">Sedan</th>
                        <th colSpan="2">Hatchback</th>
                        <th colSpan="2">SUV</th>
                        <th colSpan="2">Pickup</th>
                        <th colSpan="2">Furgoneta</th>
                        <th colSpan="2">Total</th>
                    </tr>
                    <tr>
                        <th>Contador</th>
                        <th>Monto</th>
                        <th>Contador</th>
                        <th>Monto</th>
                        <th>Contador</th>
                        <th>Monto</th>
                        <th>Contador</th>
                        <th>Monto</th>
                        <th>Contador</th>
                        <th>Monto</th>
                        <th>Contador</th>
                        <th>Monto</th>
                    </tr>
                </thead>
                <tbody>
                    {ReparacionesResumen.map((repair, index) => (
                        <tr key={index}>
                            <td>{repair.repairName}</td>

                            <td>{repair.countSedans}</td>
                            <td>{repair.mountSedans.toLocaleString()}</td>

                            <td>{repair.countHatchbacks}</td>
                            <td>{repair.mountHatchbacks.toLocaleString()}</td>

                            <td>{repair.countSuvs}</td>
                            <td>{repair.mountSuvs.toLocaleString()}</td>

                            <td>{repair.countPickups}</td>
                            <td>{repair.mountPickups.toLocaleString()}</td>

                            <td>{repair.countFurgons}</td>
                            <td>{repair.mountFurgons.toLocaleString()}</td>

                            <td>{repair.countTotal}</td>
                            <td>{repair.mountTotal.toLocaleString()}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            ):(
                <Typography>No hay datos para mostrar. Por favor, seleccione un mes y un año y haga clic en 'Generar Reporte'.</Typography>
            )}
            </Box> 
    );
};

export default ReparacionesResumen;
