import React from 'react'

const Navbar = () => {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-primary">
            <div className="container py-1">
                <a className="navbar-brand text-light fs-3 fw-bold" to="#">AutoFix</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse align-middle" id="navbarNav">
                    <ul className="navbar-nav ms-auto">
                        <li className="nav-item mx-2">
                            <a className="nav-link text-light" to="/about">HOME</a>
                        </li>
                        <li className="nav-item dropdown mx-2 fw-normal">
                            <a className="nav-link dropdown-toggle text-light" href="/" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                REGISTRO
                            </a>
                            <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a className="dropdown-item" href="/"><span className="fw-bolder text-secondary">NUEVO</span> Vehiculo</a></li>
                                <li><a className="dropdown-item" href="/"><span className="fw-bolder text-secondary">NUEVA</span> Reparación</a></li>
                            </ul>
                        </li>
                        <li className="nav-item dropdown mx-2">
                            <a className="nav-link dropdown-toggle text-light" href="/" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                REPORTE
                            </a>
                            <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a className="dropdown-item" href="/">Tipos de autos</a></li>
                                <li><a className="dropdown-item" href="/">Tipos de motores action</a></li>
                                <li><a className="dropdown-item" href="/">Tiempo promedio reparación</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}

export default Navbar