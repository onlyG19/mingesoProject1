import{ useEffect, useState } from 'react';

import { Select,FormControl, Button, Grid, Typography, TextField, InputLabel, MenuItem } from "@mui/material";
import gestionReparacion from '../services/reparacion.service'
import gestionVehiculo from '../services/vehiculos.service'
import {listaReparaciones} from '../services/listaReparaciones';


export default function RegistroReparacionForm(){
    // campos
    const [idVehiculo, setIdVehiculo] = useState('');
    const [fechaIngreso, setFechaIngreso] = useState('');
    const [horaIngreso, setHoraIngreso] = useState('');
    const [tipoReparacion, setTipoReparacion] = useState('');
    const [fechaSalida, setFechaSalida] = useState('');
    const [horaSalida, setHoraSalida] = useState('');
    const [fechaEntregaCliente, setFechaEntregaCliente] = useState('');
    const [horaEntregaCliente, setHoraEntregaCliente] = useState('');

    const [vehiculos, setVehiculos] = useState([]);


    async function fetchVehiculosDisponibles(){
        try {
            const response = await gestionVehiculo.getVehiculos();
            // procesar
            console.log(response);
            setVehiculos(response.data)

        }catch(error){
            console.error('Error fetching vehiculos disponibles:',error);
        }
    }

    useEffect(() => {
        fetchVehiculosDisponibles();
    }, []);


    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await gestionReparacion.crearReparacion({
                fechaIngreso,
                horaIngreso,
                tipoReparacion,
                fechaSalida,
                horaSalida,
                fechaEntregaCliente,
                horaEntregaCliente,
                idVehiculo,
            });
            console.log('Respuesta del servidor:', response.data);

            setIdVehiculo('');
            setFechaIngreso('');
            setHoraIngreso('');
            setTipoReparacion('');
            setFechaSalida('');
            setHoraSalida('');
            setFechaEntregaCliente('');
            setHoraEntregaCliente('');

            let tipoReparacionObj = listaReparaciones.find(reparacion => reparacion.id === tipoReparacion);
            let nombreTipoReparacion = tipoReparacionObj ? tipoReparacionObj.name : 'No encontrado';

            alert(`Reparación registrada con éxito. Detalles:
            Tipo de Reparación: ${nombreTipoReparacion}
            Monto Total de la Reparación (incluye recargos y descuentos): $${response.data.montoTotal}
            Fecha de Salida: ${fechaSalida}
            Hora de Salida: ${horaSalida}
            Fecha de Entrega al Cliente: ${fechaEntregaCliente}
            Hora de Entrega al Cliente: ${horaEntregaCliente}
            ID del Vehículo en Sistema: ${idVehiculo}`);

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
                    <FormControl fullWidth>
                    <InputLabel id="demo-simple-select-label"> Selecciona un Vehiculo </InputLabel>
                    <Select
                        required
                        label="Selecciona un Vehiculo"
                        value={idVehiculo}
                        onChange={(e) => setIdVehiculo(e.target.value)}
                    >
                        {
                            vehiculos.map((vehiculo) => (
                                <MenuItem key={vehiculo.id} value={vehiculo.id}><b>Marca: &nbsp; </b> {vehiculo.marca}  &nbsp; <b> Modelo: &nbsp;</b>{vehiculo.modelo} &nbsp;<b>Patente: &nbsp;</b> {vehiculo.numeroPatente} </MenuItem>
                            ))
                        }
                    </Select>
                    


                    </FormControl>
                </Grid>


                <Grid item>
                    <FormControl fullWidth>

                        <TextField
                            required
                            id="fechaIngreso"
                            label="Fecha Ingreso"
                            type="date"
                            value={fechaIngreso}
                            onChange={(e) => setFechaIngreso(e.target.value)}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                    </FormControl>    
                </Grid>

                <Grid item>
                    <FormControl fullWidth>

                        <TextField
                            required
                            id="horaIngreso"
                            label="Hora Ingreso"
                            type="time"
                            value={horaIngreso}
                            onChange={(e) => setHoraIngreso(e.target.value)}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                    </FormControl>
                </Grid>

                <Grid item>
                    <FormControl fullWidth>
                        <InputLabel id="demo-simple-select-label"> Tipo Reparacion *</InputLabel>

                        <Select
                            required
                            id="tipoReparacion"
                            label="Tipo de Reparación"
                            value={tipoReparacion}
                            onChange={(e) => setTipoReparacion(e.target.value)}
                        >
                            {listaReparaciones.length > 0 ? (
                                listaReparaciones.map((reparacion) => (
                                    <MenuItem key={reparacion.id} value={reparacion.id}>#{reparacion.id} {reparacion.name}</MenuItem>
                                ))
                            ) : (
                                <MenuItem disabled>No hay opciones disponibles</MenuItem>
                            )}
                            
                        </Select>
                    </FormControl>
                </Grid>

                


                <Grid item>
                    <FormControl fullWidth>

                        <TextField
                            required
                            id="fechaSalida"
                            label="Fecha Salida"
                            type="date"
                            value={fechaSalida}
                            onChange={(e) => setFechaSalida(e.target.value)}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                    </FormControl>
                </Grid>

                <Grid item>
                    <FormControl fullWidth>

                        <TextField
                            required    
                            id="horaSalida"
                            label="Hora Salida"
                            type="time"
                            value={horaSalida}
                            onChange={(e) => setHoraSalida(e.target.value)}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                        </FormControl>
                </Grid>

                <Grid item>
                    <FormControl fullWidth>

                        <TextField
                            required
                            id="fechaEntregaCliente"
                            label="Fecha Entrega Cliente"
                            type="date"
                            value={fechaEntregaCliente}
                            onChange={(e) => setFechaEntregaCliente(e.target.value)}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />

                    </FormControl>
                </Grid>

                <Grid item>
                    <FormControl fullWidth>

                        <TextField
                            required
                            id="horaEntregaCliente"
                            label="Hora Entrega Cliente"
                            type="time"
                            value={horaEntregaCliente}
                            onChange={(e) => setHoraEntregaCliente(e.target.value)}
                            InputLabelProps={{
                                shrink: true,
                            }}
                        />
                    </FormControl>

                </Grid>

                <Grid item>
                    <Button type="submit" variant="contained" color="primary">Grabar reparacion</Button>
                </Grid>
            </Grid>
        </form>
    );

}


