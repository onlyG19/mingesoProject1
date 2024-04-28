
import './App.css'
import Home from './components/Home'
import {Route, Routes, BrowserRouter} from 'react-router-dom'
import Navbar from "./components/Navbar"
import RegistroVehiculoForm from './components/RegistroVehiculoForm'
import RegistroReparacionForm from './components/RegistroReparacionForm'
import ReparacionList from './components/ReparacionList'

function App(){
  return (
    <BrowserRouter>
      <Navbar/>
      
      {/* listado de rutas */}
      <Routes>
          <Route path="/home" element={<Home/>} />
          <Route path="/vehiculos/crear" element={<RegistroVehiculoForm/>} />
          <Route path="/vehiculos" element={<ReparacionList/>} /> 
          <Route path="/reparacion/crear" element={<RegistroReparacionForm/>} />
          <Route path="/reparaciones" element={<ReparacionList/>} />

      </Routes>
      
    </BrowserRouter>
  )
}


export default App
