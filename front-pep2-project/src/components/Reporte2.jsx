import React, { useState } from 'react';
import reportesService from '../services/reportes.service';
import { Button, TextField, MenuItem, Box, FormControl, InputLabel, Select, Typography } from '@mui/material';
import OutlinedInput from '@mui/material/OutlinedInput';

const Reporte2 = () => {
    const [Reporte2, setReporte2] = useState([]);
    const [mes, setMes] = useState('');
    const [anio, setAnio] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    const handleSubmit = () => {
        if (mes && anio) {
            setLoading(true);
            setError('');
            reportesService.getReport2(mes, anio)
                .then(response => {
                    console.log("Mostrando resumen de reparaciones.", response.data);
                    setReporte2(response.data);
                    setLoading(false);
                })
                .catch(error => {
                    console.error("Error al intentar mostrar el reporte.", error);
                    setError('Error al cargar los datos del reporte.');
                    setLoading(false);
                });
        } else {
            alert("Por favor, complete todos los campos requeridos.");
        }
    };

    return (
        <Box sx={{ p: 4, display: 'flex', flexDirection: 'column', gap: 2 }}>
            <h1>Reporte 2</h1>
            <div className="form-container">
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
            {Reporte2.length > 0 ? (
                <table>
                    <thead>
                        <tr>
                            <th>Nombre de Reparaciones</th>
                            <th>{Reporte2[0].mes1}</th>
                            <th>Variación%</th>
                            <th>{Reporte2[0].mes2}</th>
                            <th>Variación%</th>
                            <th>{Reporte2[0].mes3}</th>
                        </tr>
                    </thead>
                    <tbody>
                        {Reporte2.map((repair, index) => (
                            <>
                                <tr key={'count' + index}>
                                    <td rowSpan="2">{repair.nombreReparacion}</td>
                                    <td>{repair.mes1count.toLocaleString()}</td>
                                    <td>{repair.variacion12Count}</td>
                                    <td>{repair.mes2count.toLocaleString()}</td>
                                    <td>{repair.variacion23Count}</td>
                                    <td>{repair.mes3count.toLocaleString()}</td>
                                </tr>
                                <tr key={'mount' + index}>
                                    
                                    <td>{repair.mes1mount.toLocaleString()}</td>
                                    <td>{repair.variacion12Mount}</td>
                                    <td>{repair.mes2mount.toLocaleString()}</td>
                                    <td>{repair.variacion23Mount}</td>
                                    <td>{repair.mes3mount.toLocaleString()}</td>
                                </tr>
                            </>
                        ))}
                    </tbody>
                </table>
            ) : (
                <Typography>No hay datos para mostrar. Por favor, seleccione un mes y un año y haga clic en 'Generar Reporte'.</Typography>
            )}
        </Box>
    );
};

export default Reporte2;
