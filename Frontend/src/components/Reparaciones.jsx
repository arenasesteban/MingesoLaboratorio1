import React from "react";
import BotonNuevo from './BotonNuevo';
import BotonEliminar from "./BotonEliminar";

export default function Reparaciones() {
    return (
        <div className="flex justify-start m-12 p-16 bg-gray-100 shadow-lg border border-gray-300 rounded-md">   
            <div className="w-full h-full flex flex-col">
                <div className="flex justify-between items-center border-b border-gray-300 pb-4">
                    <div className="text-3xl font-bold text-gray-700 uppercase">
                        <h1>Historial de reparaciones</h1>
                    </div>
                    <BotonNuevo tipoElemento={"Nueva reparación"}/>
                </div>
                <table className="bg-white text-left shadow-md w-full mt-6">
                    <thead className="text-gray-700 uppercase border-b">
                        <tr>
                            <th scope="col" className="px-6 py-4">
                                Marca vehiculo
                            </th>
                            <th scope="col" className="px-6 py-4">
                                Monto bono
                            </th>
                            <th scope="col" className="px-6 py-4">
                                Cantidad de bono
                            </th>
                            <th scope="col" class="px-6 py-4">
                                Acción
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
                            <td class="px-6 py-3">
                                <BotonEliminar />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
}
