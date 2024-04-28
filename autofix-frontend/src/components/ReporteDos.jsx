import { useEffect, useState } from 'react';
import { Grid,Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
import gestionReporteria from '../services/reporteria.service';
import { getReparacionNameById} from '../services/listaReparaciones'; // Asegúrate de reemplazar 'ruta/a/listaReparaciones' con la ruta correcta a tu archivo listaReparaciones.js.


const ReporteDos = () => {

    const[dataReporte2, setDataReporte2] = useState([]);

    useEffect(() => {
        fetchDataReporte2();
    }, []);

    


    const fetchDataReporte2 = async () => {
        try {
            // Fetch data from the server
            const response = await gestionReporteria.getDataReporte2();
            console.log(response);
            setDataReporte2(response.data);

        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <Grid container direction="column" spacing={2}>
        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
                <TableHead>
                    <TableRow>
                        <TableCell> <b> Tipos Vehiculos  / Tipos de Reparacion </b> </TableCell>
                        {
                            dataReporte2.map((columna) => (
                                <TableCell key={columna.tipo_reparacion} align='center'>{ getReparacionNameById(parseInt(columna.tipo_reparacion)) }</TableCell>
                                
                            ))
                        }
                    </TableRow>
                </TableHead>
                
                <TableBody>
                    <TableRow>
                        <TableCell align='center' >Sedan</TableCell>
                        {dataReporte2.map((columna) => (
                            <TableCell key={columna.tipo_reparacion} align='center'>{columna.sedan}</TableCell>
                            ))
                        }
                    </TableRow>
                    <TableRow>
                        <TableCell  align='center'>Hatchback</TableCell>
                        {dataReporte2.map((columna) => (
                            <TableCell key={columna.tipo_reparacion} align='center'>{columna.hatchback}</TableCell>
                            ))
                        }
                    </TableRow>
                    <TableRow>
                        <TableCell  align='center'>SUV</TableCell>
                        {dataReporte2.map((columna) => (
                            <TableCell key={columna.tipo_reparacion} align='center'>{columna.suv}</TableCell>
                            ))
                        }

                    </TableRow>
                    <TableRow>
                        <TableCell  align='center'>Pickup</TableCell>
                        {dataReporte2.map((columna) => (
                            <TableCell key={columna.tipo_reparacion} align='center'>{columna.pickup}</TableCell>
                            ))
                        }

                    </TableRow>
                    
                    <TableRow>
                        <TableCell  align='center'>Furgoneta</TableCell>
                        {dataReporte2.map((columna) => (
                            <TableCell key={columna.tipo_reparacion} align='center'>{columna.furgoneta}</TableCell>
                            ))
                        }

                    </TableRow>

                <TableRow
                    key="Total"
                    sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                    <TableCell component="th" scope="row"  align='center'>
                        <b>Monto Total</b>
                    </TableCell>
                    {dataReporte2.map((columna) => (
                            <TableCell key={columna.tipo_reparacion} align='center'><b>{columna.monto_total}</b></TableCell>
                            ))
                    }

                
                </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    </Grid>

    );
};

export default ReporteDos;