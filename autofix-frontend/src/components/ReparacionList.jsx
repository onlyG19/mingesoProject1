import{ useEffect, useState } from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import gestionReparacion from '../services/reparacion.service';




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
        <TableContainer>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Fecha Ingreso</TableCell>
                        <TableCell>Hora Ingreso</TableCell>
                        <TableCell>Tipo Reparacion</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {reparaciones.map((reparacion) => (
                        <TableRow key={reparacion.id}>
                            <TableCell>{reparacion.fechaIngreso}</TableCell>
                            <TableCell>{reparacion.horaIngreso}</TableCell>
                            <TableCell>{reparacion.tipoReparacion}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}

