import{ useEffect, useState } from 'react';
import { Paper, Grid, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import gestionReparacion from '../services/reparacion.service';
import { getReparacionNameById} from '../services/listaReparaciones'; // Asegúrate de reemplazar 'ruta/a/listaReparaciones' con la ruta correcta a tu archivo listaReparaciones.js.


export default function ReparacionList(){
    const [reparaciones, setReparaciones] = useState([]);


    async function fetchReparaciones(){
        try {
            const response = await gestionReparacion.getReparaciones();
            // Process the data as needed
            console.log(response);
            setReparaciones(response.data)
        } catch (error) {
            console.error('Error fetching reparaciones:', error);
        }
    }

    useEffect(() => {
        fetchReparaciones();
    }, []);

    return (
        <Grid container direction="column" spacing={2}>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell><b>Fecha Ingreso</b></TableCell>
                            <TableCell><b>Hora Ingreso</b></TableCell>
                            <TableCell><b>Tipo de reparación</b></TableCell>
                            <TableCell><b>Monto total de la reparación</b></TableCell>
                            <TableCell><b>Fecha de salida de la reparación</b></TableCell>
                            <TableCell><b>Hora de salida de la reparación</b></TableCell>
                            <TableCell><b>Fecha en que el cliente se llevó el vehículo</b></TableCell>
                            <TableCell><b>Hora en que el cliente se llevó el vehículo</b></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {reparaciones.map((reparacion) => (
                            <TableRow key={reparacion.id}>
                                <TableCell>{reparacion.fechaIngreso}</TableCell>
                                <TableCell>{reparacion.horaIngreso}</TableCell>
                                <TableCell>{getReparacionNameById(parseInt(reparacion.tipoReparacion))}</TableCell>
                                <TableCell>{reparacion.montoTotal}</TableCell>
                                <TableCell>{reparacion.fechaSalida}</TableCell>
                                <TableCell>{reparacion.horaSalida}</TableCell>
                                <TableCell>{reparacion.fechaEntregaCliente}</TableCell>
                                <TableCell>{reparacion.horaEntregaCliente}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Grid>
    );
}

