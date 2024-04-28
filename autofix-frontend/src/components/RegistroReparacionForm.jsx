import{ useState } from 'react';

import { Button, Grid, Typography, TextField } from "@mui/material";
import gestionReparacion from '../services/reparacion.service'


export default function RegistroReparacionForm(){
    // campos
    const [fechaIngreso, setFechaIngreso] = useState('');
    const [horaIngreso, setHoraIngreso] = useState('');
    const [tipoReparacion, setTipoReparacion] = useState('');
    const [montoTotal, setMontoTotal] = useState('');
    const [fechaSalida, setFechaSalida] = useState('');
    const [horaSalida, setHoraSalida] = useState('');
    const [fechaEntregaCliente, setFechaEntregaCliente] = useState('');
    const [horaEntregaCliente, setHoraEntregaCliente] = useState('');



    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await gestionReparacion.crearReparacion({
                fechaIngreso,
                horaIngreso,
                tipoReparacion,
                montoTotal,
                fechaSalida,
                horaSalida,
                fechaEntregaCliente,
                horaEntregaCliente
                
            });
            console.log('Respuesta del servidor:', response.data);

            setFechaIngreso('');
            setHoraIngreso('');
            setTipoReparacion('');
            setMontoTotal('');
            setFechaSalida('');
            setHoraSalida('');
            setFechaEntregaCliente('');
            setHoraEntregaCliente('');


            alert('Vehículo registrado con éxito.');

        } catch (error) {
            console.error('Error al enviar la solicitud:', error);
            alert('Error al registrar la reparacion. Por favor, inténtelo de nuevo.');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <Grid container direction="column" spacing={2}>
                <Typography variant="h2" gutterBottom>
                    Registro de Reparaciones
                </Typography>
                <Grid item>
                    <TextField
                        id="fechaIngreso"
                        label="Fecha Ingreso"
                        type="date"
                        value={fechaIngreso}
                        onChange={(e) => setFechaIngreso(e.target.value)}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Grid>

                <Grid item>
                    <TextField
                        id="horaIngreso"
                        label="Hora Ingreso"
                        type="time"
                        value={horaIngreso}
                        onChange={(e) => setHoraIngreso(e.target.value)}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Grid>

                <Grid item>
                    <TextField
                        id="tipoReparacion"
                        label="Tipo de Reparación"
                        value={tipoReparacion}
                        onChange={(e) => setTipoReparacion(e.target.value)}
                    />
                </Grid>

                <Grid item>
                    <TextField
                        id="montoTotal"
                        label="Monto Total"
                        type="number"
                        value={montoTotal}
                        onChange={(e) => setMontoTotal(e.target.value)}
                    />
                </Grid>

                <Grid item>
                    <TextField
                        id="fechaSalida"
                        label="Fecha Salida"
                        type="date"
                        value={fechaSalida}
                        onChange={(e) => setFechaSalida(e.target.value)}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Grid>

                <Grid item>
                    <TextField
                        id="horaSalida"
                        label="Hora Salida"
                        type="time"
                        value={horaSalida}
                        onChange={(e) => setHoraSalida(e.target.value)}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Grid>

                <Grid item>
                    <TextField
                        id="fechaEntregaCliente"
                        label="Fecha Entrega Cliente"
                        type="date"
                        value={fechaEntregaCliente}
                        onChange={(e) => setFechaEntregaCliente(e.target.value)}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Grid>

                <Grid item>
                    <TextField
                        id="horaEntregaCliente"
                        label="Hora Entrega Cliente"
                        type="time"
                        value={horaEntregaCliente}
                        onChange={(e) => setHoraEntregaCliente(e.target.value)}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Grid>

                <Grid item>
                    <Button type="submit" variant="contained" color="primary">Registrar reparacion</Button>
                </Grid>
            </Grid>
        </form>
    );

}


