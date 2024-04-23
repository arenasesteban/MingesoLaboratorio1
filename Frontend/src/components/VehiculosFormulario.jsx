import React from "react";
import BotonRegistrar from "./BotonRegistrar";

export default function VehiculosFormulario() {
    return (
        <div className="flex h-4/5 m-9 p-12 bg-gray-100 shadow-md border border-gray-300 rounded-md">   
            <div className="flex flex-col w-full">
                <div className="flex justify-between items-center border-b border-gray-300 pb-4">
                    <div className="flex items-center text-gray-700">
                        <h1 className="text-3xl font-bold uppercase pr-4 mr-2 border-r border-gray-700">Vehiculos</h1>
                        <h2 className="text-2xl font-light ml-2">Registrar vehiculo</h2>
                    </div>
                </div>
                <form className="mt-6">
                    <div className="grid gap-4 grid-cols-2">
                        <div>
                            <label for="patente" class="block mb-2 font-medium text-gray-700">Patente</label>
                            <input type="text" id="patente" class="bg-gray-50 border border-gray-300 text-gray-700 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 " placeholder="" required />
                        </div>
                        <div>
                            <label for="marca" class="block mb-2 font-medium text-gray-700">Marca</label>
                            <input type="text" id="marca" class="bg-gray-50 border border-gray-300 text-gray-700 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 " placeholder="" required />
                        </div>
                        <div>
                            <label for="modelo" class="block mb-2 font-medium text-gray-700">Modelo</label>
                            <input type="text" id="modelo" class="bg-gray-50 border border-gray-300 text-gray-700 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 " placeholder="" required />
                        </div>
                        <div>
                            <label for="anofabricacion" class="block mb-2 font-medium text-gray-700">Año fabricación</label>
                            <input type="text" id="anofabricacion" class="bg-gray-50 border border-gray-300 text-gray-700 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 " placeholder="" required />
                        </div>
                        <div>
                            <label for="motor" class="block mb-2 font-medium text-gray-700">Motor</label>
                            <select id="motor" class="bg-gray-50 border border-gray-300 text-gray-700 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                                <option>Gasolina</option>
                                <option>Diesel</option>
                                <option>Hibrido</option>
                                <option>Electrico</option>
                            </select>
                        </div>
                        <div>
                            <label for="tipo" class="block mb-2 font-medium text-gray-700">Tipo</label>
                            <select id="tipo" class="bg-gray-50 border border-gray-300 text-gray-700 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                                <option>Sedan</option>
                                <option>Hatchback</option>
                                <option>SUV</option>
                                <option>Pickup</option>
                                <option>Furgoneta</option>
                            </select>
                        </div>
                        <div>
                            <label for="numeroasientos" class="block mb-2 font-medium text-gray-700">Número asientos</label>
                            <input type="text" id="numeroasientos" class="bg-gray-50 border border-gray-300 text-gray-700 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 " placeholder="" required />
                        </div>
                        <div>
                            <label for="kilometraje" class="block mb-2 font-medium text-gray-700">Kilometraje</label>
                            <input type="text" id="kilometraje" class="bg-gray-50 border border-gray-300 text-gray-700 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 " placeholder="" required />
                        </div>
                        <div className="col-span-2 flex justify-end">
                            <BotonRegistrar />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    )
}