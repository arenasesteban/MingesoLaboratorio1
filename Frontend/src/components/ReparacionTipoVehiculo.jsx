import React from "react";

export default function ReparacionTipoVehiculo() {
    return (
        <div className="flex h-4/5 m-9 p-12 bg-gray-100 shadow-md border border-gray-300 rounded-md">   
            <div className="flex flex-col gap-4 w-full">
                <div className="flex justify-between items-center border-b border-gray-300 pb-4">
                    <div className="flex items-center text-gray-700">
                        <h1 className="text-3xl font-bold uppercase pr-4 mr-2 border-r border-gray-400">Reporte</h1>
                        <h2 className="text-2xl font-light ml-2">Número de reparaciones por cada tipo de vehiculo</h2>
                    </div>
                </div>
                <div className="mt-6 overflow-auto shadow">
                    <div>
                        <table className="bg-white text-left w-full">
                            <thead className="text-gray-700 uppercase border-b">
                                <tr>
                                    <th scope="col" className="px-6 py-4">
                                        Tipo reparación
                                    </th>
                                    <th scope="col" className="px-6 py-4">
                                        Número de tipos de vehiculos
                                    </th>
                                    <th scope="col" className="px-6 py-4">
                                        Monto total
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
