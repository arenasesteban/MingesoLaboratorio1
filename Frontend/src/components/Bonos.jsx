import React from 'react';
import BotonNuevoElemento from './BotonNuevoElemento';

export default function Bonos() {
    return (
        <div className="flex justify-start m-12 p-16 bg-gray-100 shadow-lg border border-gray-300 rounded-md">   
            <div className="w-full flex flex-col gap-4">
                <div className="flex justify-between items-center border-b border-gray-300 pb-4">
                    <div className="text-3xl font-bold text-gray-700 uppercase">
                        <h1>Administración de Bonos</h1>
                    </div>
                    <BotonNuevoElemento tipoElemento={"Nuevo bono"}/>
                </div>
                <div className="">
                    <table className="bg-white text-left rounded-md shadow-md w-full">
                        <thead className="text-gray-700 uppercase border-b">
                            <tr>
                                <th scope="col" className="px-3 py-3">
                                    Marca vehiculo
                                </th>
                                <th scope="col" className="px-3 py-3">
                                    Monto bono
                                </th>
                                <th scope="col" className="px-3 py-3">
                                    Cantidad de bonos
                                </th>
                                <th scope="col" class="px-3 py-3">
                                    Acción
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="bg-white border-b hover:bg-gray-50">
                                <th scope="row" class="px-3 py-4 font-medium text-gray-900">
                                </th>
                                <td class="px-3 py-4">
                                </td>
                                <td class="px-3 py-4">
                                </td>
                                <td class="px-3 py-3">
                                    <a href="#" class="font-medium text-red-600 hover:underline">Eliminar</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}