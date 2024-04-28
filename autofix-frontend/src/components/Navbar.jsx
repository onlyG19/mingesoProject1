import { AppBar, Toolbar, Button,Typography } from '@mui/material';
import { Link } from 'react-router-dom';


const Navbar = () => {
    return (
        <AppBar position="fixed">
            <Toolbar>
                <Typography variant="h6">
                    Autofix ////
                </Typography>
                <Toolbar sx={{ justifyContent: 'flex-end' }}>
                    <Button color="inherit" component={Link} to="/home">Inicio</Button>
                    <Button color="inherit" component={Link} to="/vehiculos/crear">Registrar Vehiculos</Button>
                    <Button color="inherit" component={Link} to="/registrar-reparacion">Registrar Reparacion</Button>
                    <Button color="inherit" component={Link} to="/historial-reparaciones">Historial de Reparaciones</Button>
                    <Button color="inherit" component={Link} to="/reportes">Reportes</Button>
                </Toolbar>
            </Toolbar>
        </AppBar>
    );
};

export default Navbar;