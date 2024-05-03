import { useEffect, useState } from 'react';
import { Typography,Grid,Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
import gestionReporteria from '../services/reporteria.service';


const ReporteUno = () => {

    const[dataReporte1, setDataReporte1] = useState([]);

    useEffect(() => {
        fetchDataReporte1();
    }, []);

    const fetchDataReporte1 = async () => {
        try {
            // Fetch data from the server
            const response = await gestionReporteria.getDataReporte1();
            console.log(response);
            setDataReporte1(response.data);

        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <Grid container direction="column" spacing={2}>
            <Grid item > 
                <Typography variant="h2" component="h2" sx={{ marginBottom: '76px',marginTop: '16px' }}>
                Reporte 1:  Listado de vehiculo / todos los valores involucrados en el cálculo de la fórmula monto total. 
                </Typography>
            </Grid>

        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} size="medium" aria-label="a dense table">
                <TableHead>
                    <TableRow>
                        <TableCell align='center'> <b> Marca </b> </TableCell>
                        <TableCell align='center'> <b> Modelo </b> </TableCell>
                        <TableCell align='center'> <b> Numero Patente </b> </TableCell>
                        <TableCell align='center'> <b> Monto Final Reparacion </b> </TableCell>
                        <TableCell align='center'> <b> Monto Servicio Reparacion </b> </TableCell>
                        <TableCell align='center'> <b> % Descuento Número Reparaciones </b> </TableCell>
                        <TableCell align='center'> <b> % Descuento por Día Atención </b> </TableCell>
                        <TableCell align='center'> <b> Recargo Kilometraje Vehiculo </b> </TableCell>
                        <TableCell align='center'> <b> Recargo por Antiguedad </b> </TableCell>
                        <TableCell align='center'> <b> Recargo por Retraso en Recogida </b> </TableCell>

                    </TableRow>
                </TableHead>
                
                <TableBody>
                    {dataReporte1.map((item) => (
                        <TableRow key={item.id_vehiculo}>
                            <TableCell align='center'>{item.marca}</TableCell>
                            <TableCell align='center'>{item.modelo}</TableCell>
                            <TableCell align='center'>{item.numero_patente}</TableCell>
                            <TableCell align='center'>{item.monto_total_final}</TableCell>
                            <TableCell align='center'>{item.monto_reparaciones}</TableCell>
                            <TableCell align='center'>{item.dcto_numero_reparaciones}</TableCell>
                            <TableCell align='center'>{item.dcto_por_dia_atencion}</TableCell>
                            <TableCell align='center'>{item.recargo_kilometraje_vehiculo}</TableCell>
                            <TableCell align='center'>{item.recargo_por_antiguedad}</TableCell>
                            <TableCell align='center'>{item.recargo_por_retraso_recogida}</TableCell>
                        </TableRow>
                        ))
                    }   
                </TableBody>
            </Table>
        </TableContainer>
    </Grid>

    );
};

export default ReporteUno;