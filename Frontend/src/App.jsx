import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import NavBar from './components/NavBar';

function App() {
    return (
        <BrowserRouter>
            <NavBar/>
            <Routes>
                <Route></Route>
            </Routes>
        </BrowserRouter>
    )
}

export default App
