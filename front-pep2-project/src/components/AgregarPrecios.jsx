import { useState } from "react";
import { FormControl, TextField, Box, Button } from "@mui/material";
import registerService from "../services/register.service";
import SaveIcon from "@mui/icons-material/Save";

const AgregarPrecios = () => {
    const [formValues, setFormValues] = useState({
        reparacion: '',
        precioGasolina: '',
        precioDiesel: '',
        precioHibrido: '',
        precioElectrico: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormValues(prev => ({ ...prev, [name]: value }));
    };

    const validateForm = () => {
        return Object.values(formValues).every(x => x.trim() !== '');
    };

    const savePrice = async (e) => {
        e.preventDefault();
        if (!validateForm()) {
            alert("Debe llenar todos los campos");
            return;
        }

        try {
            const response = await registerService.savePrices(formValues);
            console.log(response.data);
            alert("Precios grabados correctamente");
            setFormValues({
                reparacion: '',
                precioGasolina: '',
                precioDiesel: '',
                precioHibrido: '',
                precioElectrico: '',
            });
        } catch (error) {
            console.error(error);
            alert("Error al grabar los precios");
        }
    };

    return (
        <Box className="form-box">
            <h3 style={{ fontSize: '24px', marginTop: '0px', marginBottom: '0px' }}> Agregar Nueva Reparación </h3>
            <FormControl fullWidth sx={{ mt: 0.7, mb: 0.7 }}>
                <TextField
                    id="reparacion"
                    name="reparacion"
                    label="Nombre Reparacion"
                    value={formValues.reparacion}
                    variant="standard"
                    onChange={handleChange}
                />
            </FormControl>
            <h3 style={{ fontSize: '18px', marginTop: '20px', marginBottom: '0px', textAlign: 'left' }}> Precios por Tipo de Motor: </h3>
            <FormControl fullWidth sx={{ mt: 0.7, mb: 0.7 }}>
                <TextField
                    id="precioGasolina"
                    name="precioGasolina"
                    label="Gasolina"
                    value={formValues.precioGasolina}
                    type="number"
                    variant="standard"
                    onChange={handleChange}
                />
            </FormControl>
            <FormControl fullWidth sx={{ mt: 0.7, mb: 0.7 }}>
                <TextField
                    id="precioDiesel"
                    name="precioDiesel"
                    label="Diesel"
                    value={formValues.precioDiesel}
                    type="number"
                    variant="standard"
                    onChange={handleChange}
                />
            </FormControl>
            <FormControl fullWidth sx={{ mt: 0.7, mb: 0.7 }}>
                <TextField
                    id="precioHibrido"
                    name="precioHibrido"
                    label="Híbrido"
                    value={formValues.precioHibrido}
                    type="number"
                    variant="standard"
                    onChange={handleChange}
                />
            </FormControl>
            <FormControl fullWidth sx={{ mt: 0.7, mb: 0.7 }}>
                <TextField
                    id="precioElectrico"
                    name="precioElectrico"
                    label="Eléctrico"
                    value={formValues.precioElectrico}
                    type="number"
                    variant="standard"
                    onChange={handleChange}
                />
            </FormControl>
            <FormControl fullWidth>
                <br />
                <Button
                    variant="contained"
                    color="info"
                    onClick={(e) => savePrice(e)}
                    style={{ marginLeft: "0.5rem" }}
                    startIcon={<SaveIcon />}
                >
                    Grabar
                </Button>
            </FormControl>
        </Box>
    );
};

export default AgregarPrecios;