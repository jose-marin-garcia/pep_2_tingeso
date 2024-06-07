import { useState } from "react";
import { FormControl, TextField, Box, Button } from "@mui/material";
import registerService from "../services/register.service";
import SaveIcon from "@mui/icons-material/Save";

const AgregarNuevaReparación = () => {
    const [reparacion, setReparacion] = useState("");

    const guardarReparación = async (e) => {
        e.preventDefault();
        if (reparacion === "") {
            alert("Debe ingresar una marca");
            return;
        }else{
            registerService
            .saveRepair(reparacion)
            .then((response) => {
                console.log(response.data);
                alert("Marca grabada correctamente");
                setReparacion("");
            AgregarPrecios(reparacion);
            }).catch((e) => {
                console.log(e);
                alert("Error al grabar la marca");
            });
        }
    };

    return (
        <Box
        sx={{
            display: 'static',
            justifyContent: 'center',
            alignItems: 'center',
            flexDirection: 'column',
            p: 3,  // Padding around the form
            boxShadow: '0px 4px 8px rgba(0, 0, 0, 0.1)',  // Shadow effect
            border: '1px solid #ccc',  // Solid line border
            borderRadius: '8px',  // Rounded corners
            bgcolor: 'background.paper',  // Background color
            width: '80%',  // Adjust width as needed
            maxWidth: '500px',  // Maximum width
            margin: 'auto',  // Center the box
            marginTop: 2,  // Top margin
        }}
        >
            <h3 style={{ fontSize: '24px', marginTop: '0px', marginBottom: '0px' }}> Nueva Marca </h3>
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
                <br />
                <Button
                    variant="contained"
                    color="info"
                    onClick={(e) => saveRepair(e)}
                    style={{ marginLeft: "0.5rem" }}
                    startIcon={<SaveIcon />}
                >
                    Grabar reparaciónn
                </Button>
            </FormControl>
        </Box>
    );
};

export default AgregarNuevaReparación;