import { AppBar, Toolbar, Button, Typography, Menu, MenuItem } from '@mui/material';
import { useState } from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
    const [anchorEl, setAnchorEl] = useState(null);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    return (
        <AppBar position="fixed">
            <Toolbar>
                <Typography variant="h5">
                    Autofix
                </Typography>
                <Toolbar sx={{ justifyContent: 'flex-end' }}>
                    <Button color="inherit" component={Link} to="/home">Inicio</Button>
                    <Button color="inherit" component={Link} to="/vehiculos/crear">Registrar Vehiculos</Button>
                    <Button color="inherit" component={Link} to="/reparaciones/crear">Registrar Reparacion</Button>
                    <Button color="inherit" component={Link} to="/reparaciones">Historial de Reparaciones</Button>

                    <Button color="inherit" aria-controls="simple-menu" aria-haspopup="true" onClick={handleClick}>
                        Reportería
                    </Button>
                    <Menu
                        id="simple-menu"
                        anchorEl={anchorEl}
                        keepMounted
                        open={Boolean(anchorEl)}
                        onClose={handleClose}
                    >
                        <MenuItem onClick={handleClose} component={Link} to="/reportes/2"> Reporte #2</MenuItem>
                        <MenuItem onClick={handleClose} component={Link} to="/reportes/3">Reporte #3</MenuItem>
                        <MenuItem onClick={handleClose} component={Link} to="/reportes/4">Reporte #4</MenuItem>
                    </Menu>
                </Toolbar>
            </Toolbar>
        </AppBar>
    );
};

export default Navbar;