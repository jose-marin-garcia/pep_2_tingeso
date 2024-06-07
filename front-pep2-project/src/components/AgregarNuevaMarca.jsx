import { useState } from 'react';
import { FormControl, TextField, Box, Button, Snackbar } from '@mui/material';
import SaveIcon from '@mui/icons-material/Save';
import registerService from '../services/register.service';

const AgregarNuevaMarca = () => {
    const [marca, setMarca] = useState("");
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState("");

    const handleSnackbarClose = () => {
        setSnackbarOpen(false);
    };

    const saveMark = async (e) => {
        e.preventDefault();
        if (marca.trim() === "") {
            setSnackbarMessage("Debe ingresar una marca");
            setSnackbarOpen(true);
            return;
        }

        try {
            const response = await registerService.saveMark(marca);
            console.log(response.data);
            setSnackbarMessage("Marca grabada correctamente");
            setMarca("");
        } catch (error) {
            console.error(error);
            setSnackbarMessage("Error al grabar la marca");
        }
        setSnackbarOpen(true);
    };

    return (
        <Box className="form-box">
            <h3 style={{ fontSize: '24px', marginTop: '0px', marginBottom: '0px' }}>Nueva Marca</h3>
            <FormControl fullWidth sx={{ mt: 0.7, mb: 0.7 }}>
                <TextField
                    id="marca"
                    label="Marca"
                    value={marca}
                    variant="standard"
                    onChange={(e) => setMarca(e.target.value)}
                />
            </FormControl>
            <FormControl fullWidth>
                <Button
                    variant="contained"
                    color="info"
                    onClick={saveMark}
                    sx={{ mt: 2 }}
                    startIcon={<SaveIcon />}
                >
                    Grabar marca
                </Button>
            </FormControl>
            <Snackbar
                open={snackbarOpen}
                autoHideDuration={6000}
                onClose={handleSnackbarClose}
                message={snackbarMessage}
            />
        </Box>
    );
};

export default AgregarNuevaMarca;
