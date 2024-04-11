import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import SideBar from './components/SideBar'
import NavBar from './components/NavBar';

export default function App() {
    return (
        <div className="grid grid-cols-6 h-screen">
            <div className="col-span-1">
                <SideBar />
            </div>
                <div className="col-span-5">
                    <div className="">
                        <NavBar />
                    </div>
                    <div className="">
                        {/* Contenido */}
                </div>
            </div>
        </div>
    )
}