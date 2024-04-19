import React from "react";

export default function ReparacionTipoVehiculo() {
    return (
        <div className="flex justify-start m-12 p-16 bg-gray-100 shadow-lg border border-gray-300 rounded-md">   
            <div className="w-full h-full flex flex-col gap-4">
                <div className="flex justify-between items-center border-b border-gray-300 pb-4">
                    <div className="flex items-center text-gray-700">
                        <h1 className="text-3xl font-bold uppercase pr-4 mr-2 border-r border-gray-400">Reporte</h1>
                        <h6 className="text-2xl font-light">Número de reparaciones por cada tipo de vehiculo</h6>
                    </div>
                </div>
                <table className="bg-white text-left shadow-md w-full mt-6">
                    <thead className="text-gray-700 uppercase border-b">
                        <tr>
                            <th scope="col" className="px-6 py-4">
                                Tipo reparación
                            </th>
                            <th scope="col" className="px-6 py-4">
                                Número de tipos de autos
                            </th>
                            <th scope="col" className="px-6 py-4">
                                Monto total
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="bg-white border-b hover:bg-gray-50">
                            <th scope="row" class="px-3 py-4 font-medium text-gray-900">
                            </th>
                            <td class="px-6 py-3">
                            </td>
                            <td class="px-6 py-3">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
}
