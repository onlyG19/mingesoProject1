import { useEffect, useState } from 'react';
import { Typography,Grid,Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
import gestionReporteria from '../services/reporteria.service';
import { getReparacionNameById} from '../services/listaReparaciones'; // Asegúrate de reemplazar 'ruta/a/listaReparaciones' con la ruta correcta a tu archivo listaReparaciones.js.


const ReporteCuatro = () => {

    const[dataReporte4, setDataReporte4] = useState([]);

    useEffect(() => {
        fetchDataReporte4();
    }, []);

    


    const fetchDataReporte4 = async () => {
        try {
            // Fetch data from the server
            const response = await gestionReporteria.getDataReporte4();
            console.log(response);
            setDataReporte4(response.data);

        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <Grid container direction="column" spacing={2}>
            <Grid item > 
                <Typography variant="h2" component="h2" sx={{ marginBottom: '76px',marginTop: '16px' }}>
                Reporte 4: Monto total de reparaciones por tipo de motor y tipo de reparación
                </Typography>
            </Grid>
        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
                <TableHead>
                    <TableRow>
                        <TableCell> <b> Tipos de Motor  / Tipos de Reparacion </b> </TableCell>
                        {
                            dataReporte4.map((columna) => (
                                <TableCell key={columna.tipo_reparacion} align='center'>{ getReparacionNameById(parseInt(columna.tipo_reparacion)) }</TableCell>
                                
                            ))
                        }
                    </TableRow>
                </TableHead>
                
                <TableBody>
                    <TableRow>
                        <TableCell align='center' >Gasolina</TableCell>
                        {dataReporte4.map((columna) => (
                            <TableCell key={columna.tipo_reparacion} align='center'>{columna.gasolina}</TableCell>
                            ))
                        }
                    </TableRow>
                    <TableRow>
                        <TableCell  align='center'>Diesel</TableCell>
                        {dataReporte4.map((columna) => (
                            <TableCell key={columna.tipo_reparacion} align='center'>{columna.diesel}</TableCell>
                            ))
                        }
                    </TableRow>
                    <TableRow>
                        <TableCell  align='center'>Hibrido</TableCell>
                        {dataReporte4.map((columna) => (
                            <TableCell key={columna.tipo_reparacion} align='center'>{columna.hibrido}</TableCell>
                            ))
                        }

                    </TableRow>
                    <TableRow>
                        <TableCell  align='center'>Electrico</TableCell>
                        {dataReporte4.map((columna) => (
                            <TableCell key={columna.tipo_reparacion} align='center'>{columna.electrico}</TableCell>
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
                    {dataReporte4.map((columna) => (
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

export default ReporteCuatro;