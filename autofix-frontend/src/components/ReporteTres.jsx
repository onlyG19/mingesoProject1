import { useEffect, useState } from 'react';
import { Typography,Grid,Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper } from '@mui/material';
import gestionReporteria from '../services/reporteria.service';


const ReporteTres = () => {

    const[dataReporte3, setDataReporte3] = useState([]);

    useEffect(() => {
        fetchDataReporte3();
    }, []);

    


    const fetchDataReporte3 = async () => {
        try {
            // Fetch data from the server
            const response = await gestionReporteria.getDataReporte3();
            console.log(response);
            setDataReporte3(response.data);

        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <Grid container direction="column" spacing={2}>
            <Grid item > 
                <Typography variant="h2" component="h2" sx={{ marginBottom: '76px',marginTop: '16px' }}>
                Reporte 3: Tiempo promedio de reparación por marca de vehículo
                </Typography>
            </Grid>

        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }} size="medium" aria-label="a dense table">
                <TableHead>
                    <TableRow>
                        <TableCell align='center'> <b> Tiempo  / Marcas de Vehiculos </b> </TableCell>
                        {
                            dataReporte3.map((columna) => (
                                <TableCell key={columna.marca} align='center'>{ columna.marca }</TableCell>
                                
                            ))
                        }
                    </TableRow>
                </TableHead>
                
                <TableBody>
                    <TableRow>
                        <TableCell align='center' >Tiempo Promedio de Reparacion (en dias) </TableCell>
                        {dataReporte3.map((columna) => (
                            <TableCell key={columna.tipo_reparacion} align='center'>{columna.tiempo_promedio_reparacion}</TableCell>
                            ))
                        }
                    </TableRow>
                   
                </TableBody>
            </Table>
        </TableContainer>
    </Grid>

    );
};

export default ReporteTres;