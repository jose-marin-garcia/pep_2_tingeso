import * as React from "react";
import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import PaidIcon from "@mui/icons-material/Paid";
import CalculateIcon from "@mui/icons-material/Calculate";
import AnalyticsIcon from "@mui/icons-material/Analytics";
import DoneIcon from '@mui/icons-material/Done';
import DoneAllIcon from '@mui/icons-material/DoneAll';
import CarRepairIcon from '@mui/icons-material/CarRepair';
import DiscountIcon from "@mui/icons-material/Discount";
import HailIcon from "@mui/icons-material/Hail";
import MedicationLiquidIcon from "@mui/icons-material/MedicationLiquid";
import MoreTimeIcon from "@mui/icons-material/MoreTime";
import HomeIcon from "@mui/icons-material/Home";
import { useNavigate } from "react-router-dom";

export default function Sidemenu({ open, toggleDrawer }) {
  const navigate = useNavigate();

  const listOptions = () => (
    <Box
      role="presentation"
      onClick={toggleDrawer(false)}
    >
      <List>
        <ListItemButton onClick={() => navigate("/home")}>
          <ListItemIcon>
            <HomeIcon />
          </ListItemIcon>
          <ListItemText primary="Home" />
        </ListItemButton>

        <Divider />

        <ListItemButton onClick={() => navigate("/registro")}>
          <ListItemIcon>
            <CarRepairIcon />
          </ListItemIcon>
          <ListItemText primary="Nuevo registro" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/finalizar")}>
          <ListItemIcon>
            <DoneIcon />
          </ListItemIcon>
          <ListItemText primary="Finalizar reparación" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/retirar")}>
          <ListItemIcon>
            <DoneAllIcon />
          </ListItemIcon>
          <ListItemText primary="Retirar auto" />
        </ListItemButton>

        <Divider />

        <ListItemButton onClick={() => navigate("/nueva-marca")}>
          <ListItemIcon>
            <MoreTimeIcon />
          </ListItemIcon>
          <ListItemText primary="Agregar Nueva Marca" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/nueva-reparacion")}>
          <ListItemIcon>
            <MoreTimeIcon />
          </ListItemIcon>
          <ListItemText primary="Agregar Nueva Reparación" />
        </ListItemButton>

        <Divider />

        <ListItemButton onClick={() => navigate("/listado")}>
          <ListItemIcon>
            <MoreTimeIcon />
          </ListItemIcon>
          <ListItemText primary="Listado Historiales" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/reporte1")}>
          <ListItemIcon>
            <PaidIcon />
          </ListItemIcon>
          <ListItemText primary="Reporte 1" />
        </ListItemButton>

        <ListItemButton onClick={() => navigate("/reporte2")}>
          <ListItemIcon>
            <CalculateIcon />
          </ListItemIcon>
          <ListItemText primary="Reporte 2" />
        </ListItemButton>
        
      </List>
    </Box>
  );

  return (
    <div>
      <Drawer anchor={"left"} open={open} onClose={toggleDrawer(false)}>
        {listOptions()}
      </Drawer>
    </div>
  );
}
