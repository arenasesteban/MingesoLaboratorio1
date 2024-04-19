import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import SideBar from './components/SideBar'
import NavBar from './components/NavBar';
import Inicio from './components/Inicio';
import Vehiculos from './components/Vehiculos';
import Reparaciones from './components/Reparaciones';
import PromedioReparacion from './components/PromedioReparacion';
import ReparacionTipoMotor from './components/ReparacionTipoMotor';
import ReparacionTipoVehiculo from './components/ReparacionTipoVehiculo';
import Bonos from './components/Bonos';

export default function App() {
    return (
        <BrowserRouter>
            <div className="grid grid-cols-10 h-screen">
                <div className="col-span-2">
                    <SideBar />
                </div>
                <div className="col-span-8">
                    <NavBar />
                    <Routes>
                        <Route path="/" element={<Inicio />}></Route>
                        <Route path="/vehiculos" element={<Vehiculos />}></Route>
                        <Route path="/reparaciones" element={<Reparaciones />}></Route>
                        <Route path="/promedio-reparacion" element={<PromedioReparacion />}></Route>
                        <Route path="/reparacion-tipo-motor" element={<ReparacionTipoMotor />}></Route>
                        <Route path="/reparacion-tipo-vehiculo" element={<ReparacionTipoVehiculo />}></Route>
                        <Route path="/bonos" element={<Bonos />}></Route>
                    </Routes>
                </div>
            </div>
        </BrowserRouter>
    )
}