
import './App.css'
import Home from './components/Home'
import {Route, Routes, BrowserRouter} from 'react-router-dom'
import Navbar from "./components/Navbar"
import RegistroVehiculoForm from './components/RegistroVehiculoForm'
import RegistroReparacionForm from './components/RegistroReparacionForm'
import ReparacionList from './components/ReparacionList'
import { Box } from '@mui/material';
import ReporteDos from './components/ReporteDos'
import ReporteCuatro from './components/ReporteCuatro'
import ReporteTres from './components/ReporteTres'


function App(){
  return (
    <BrowserRouter>
      <Navbar/>
      <Box mt={8}> 
      {/* listado de rutas */}
        <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/home" element={<Home/>} />
            <Route path="/vehiculos/crear" element={<RegistroVehiculoForm/>} />
            <Route path="/vehiculos" element={<ReparacionList/>} /> 
            <Route path="/reparaciones/crear" element={<RegistroReparacionForm/>} />
            <Route path="/reparaciones" element={<ReparacionList/>} />
            <Route path="/reportes/2" element={<ReporteDos/>} />
            <Route path="/reportes/3" element={<ReporteTres/>} />
            <Route path="/reportes/4" element={<ReporteCuatro/>} />

        </Routes>
      </Box>
    </BrowserRouter>
  )
}


export default App
