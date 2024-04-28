import{ useState } from 'react';
import { InputLabel,Select, MenuItem, TextField, Button, Grid,Typography, FormControl, Menu } from '@mui/material';
import gestionVehiculos from '../services/vehiculos.service.js'



export default function RegistroVehiculoForm(){
  const [numeroPatente, setPatente] = useState('');
  const [marca, setMarca] = useState('');
  const [modelo, setModelo] = useState('');
  const [tipo, setTipo] = useState('');
  const [yearFabricacion, setAnio] = useState('');
  const [tipoMotor, setTipoMotor] = useState('');
  const [numeroAsientos, setNumAsientos] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await gestionVehiculos.crearVehiculo({
        numeroPatente,
        marca,
        modelo,
        tipo,
        yearFabricacion,
        tipoMotor,
        numeroAsientos
      });
      console.log('Respuesta del servidor:', response.data);
      
        
      // Reset the input fields
        setPatente('');
        setMarca('');
        setModelo('');
        setTipo('');
        setAnio('');
        setTipoMotor('');
        setNumAsientos('');

        alert('Vehículo registrado con éxito.');


    } catch (error) {
      console.error('Error al enviar la solicitud:', error);
      alert('Error al registrar el vehículo. Por favor, inténtelo de nuevo.');

    }
  };

  

  return (
    <form onSubmit={handleSubmit}>
      <Grid container direction="column" spacing={2}>
        <Typography variant="h2" gutterBottom>
                REGISTRO DE VEHICULOS
        </Typography>
        <Grid item>
          <FormControl fullWidth>
            
            <TextField
                label="Número de patente"
                value={numeroPatente}
                onChange={(e) => setPatente(e.target.value)}
                required
                inputProps={{
                    pattern: "[A-Za-z]{4}[0-9]{2}",
                    maxLength: 6
                }}
                error={!/^([A-Za-z]{4}[0-9]{2})?$/.test(numeroPatente)}
                helperText={!/^([A-Za-z]{4}[0-9]{2})?$/.test(numeroPatente) ? "Formato de patente inválido. Recuerde que el formato es ABCD12" : ""}
            />
          </FormControl>
        </Grid>
        <Grid item>
          <FormControl fullWidth>

            <TextField
                label="Marca"
                value={marca}
                onChange={(e) => setMarca(e.target.value)}
                required
            />
          </FormControl>
        </Grid>
        
        <Grid item>
          <FormControl fullWidth>

            <TextField
                label="Modelo"
                value={modelo}
                onChange={(e) => setModelo(e.target.value)}
                required
            />
          </FormControl>

        </Grid>

        <Grid item>
          <FormControl fullWidth>
            <InputLabel id="demo-simple-select-label"> Tipo Vehiculo *</InputLabel>
            <Select
                select
                label="Tipo Vehiculo"                    
                value={tipo}
                onChange={(e) => setTipo(e.target.value)}
                required
            >
                <MenuItem value="Sedan">Sedan</MenuItem>
                <MenuItem value="Hatchback">Hatchback</MenuItem>
                <MenuItem value="SUV">SUV</MenuItem>
                <MenuItem value="Pickup">Pickup</MenuItem>
                <MenuItem value="Furgoneta">Furgoneta</MenuItem>
            </Select>
          </FormControl>
          
        </Grid>

        <Grid item>
          <FormControl fullWidth>

            <TextField
                label="Año de fabricación"
                value={yearFabricacion}
                onChange={(e) => setAnio(e.target.value)}
                type="number"
                required
            />
          </FormControl>

          
        </Grid>

        <Grid item>
            <FormControl fullWidth>

                <InputLabel id="demo-simple-select-label"> Tipo de Motor *</InputLabel>
                <Select
                    select
                    label="Tipo de motor"
                    value={tipoMotor}
                    onChange={(e) => setTipoMotor(e.target.value)}
                    required
                >
                    <MenuItem value="Gasolina">Gasolina</MenuItem>
                    <MenuItem value="Diesel">Diesel</MenuItem>
                    <MenuItem value="Hibrido">Híbrido</MenuItem>
                    <MenuItem value="Electrico">Eléctrico</MenuItem>
                </Select>
            </FormControl>

        </Grid>

        <Grid item>
          <FormControl fullWidth>

            <TextField
                label="Número de asientos"
                value={numeroAsientos}
                onChange={(e) => setNumAsientos(e.target.value)}
                type="number"
                required
            />
          </FormControl>

        </Grid>

        <Grid item>
          <Button type="submit" variant="contained" color="primary">Registrar vehículo</Button>
        </Grid>
      </Grid>
    </form>
  );
}

