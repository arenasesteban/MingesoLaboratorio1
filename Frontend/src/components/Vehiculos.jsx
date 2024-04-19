import React from "react";
import BotonNuevo from './BotonNuevo';
import BotonEliminar from "./BotonEliminar";

export default function Vehiculos() {
    return (
        <div className="flex h-4/5 m-9 p-12 bg-gray-100 shadow-md border border-gray-300 rounded-md">   
            <div className="flex flex-col w-full">
                <div className="flex justify-between items-center border-b border-gray-300 pb-4">
                    <div className="text-3xl font-bold text-gray-700 uppercase">
                        <h1>Vehiculos registrados</h1>
                    </div>
                    <BotonNuevo enlace="/vehiculos-formulario" tipoElemento={"Registrar vehiculo"}/>
                </div>
                <div className="mt-6 overflow-auto shadow">
                    <div>
                        <table className="bg-white text-left w-full">
                            <thead className="text-gray-700 uppercase border-b">
                                <tr>
                                    <th scope="col" className="px-6 py-4">
                                        Patente
                                    </th>
                                    <th scope="col" className="px-6 py-4">
                                        Marca
                                    </th>
                                    <th scope="col" className="px-6 py-4">
                                        Modelo
                                    </th>
                                    <th scope="col" class="px-6 py-4">
                                        Tipo vehiculo
                                    </th>
                                    <th scope="col" className="px-6 py-4">
                                        Tipo motor
                                    </th>
                                    <th scope="col" className="px-6 py-4">
                                        Numero asientos
                                    </th>
                                    <th scope="col" className="px-6 py-4">
                                        Kilometraje
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                {/* {
                                    bonos.map((bono, index) => (
                                        <tr key={index} class="bg-white border-b hover:bg-gray-50">
                                            <td scope="row" class="px-6 py-4 font-medium text-gray-900">
                                                {bono.marca}
                                            </td>
                                            <td class="px-6 py-3">
                                                {bono.cantidad}
                                            </td>
                                            <td class="px-6 py-3">
                                                $ {bono.valor}
                                            </td>
                                            <td class="px-6 py-3">
                                                <BotonEliminar />
                                            </td>
                                        </tr>
                                    ))
                                } */}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    );
}
