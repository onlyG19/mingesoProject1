
import './App.css'
import Home from './components/Home'
import {Route, Routes, BrowserRouter} from 'react-router-dom'
import Navbar from "./components/Navbar"
import RegistroVehiculoForm from './components/RegistroVehiculoForm'
import RegistroReparacionForm from './components/RegistroReparacionForm'
import ReparacionList from './components/ReparacionList'
import { Box } from '@mui/material';


function App(){
  return (
    <BrowserRouter>
      <Navbar/>
      <Box mt={8}> 
      {/* listado de rutas */}
        <Routes>
            <Route path="/home" element={<Home/>} />
            <Route path="/vehiculos/crear" element={<RegistroVehiculoForm/>} />
            <Route path="/vehiculos" element={<ReparacionList/>} /> 
            <Route path="/reparaciones/crear" element={<RegistroReparacionForm/>} />
            <Route path="/reparaciones" element={<ReparacionList/>} />

        </Routes>
      </Box>
    </BrowserRouter>
  )
}


export default App
