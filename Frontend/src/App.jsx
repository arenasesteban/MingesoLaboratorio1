import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import SideBar from './components/SideBar'
import NavBar from './components/NavBar';
import Inicio from './components/Inicio';
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
                        <Route path="/bonos" element={<Bonos />}></Route>
                        <Route></Route>
                        <Route></Route>
                        <Route></Route>
                        <Route></Route>
                        <Route></Route>
                    </Routes>
                </div>
            </div>
        </BrowserRouter>
    )
}