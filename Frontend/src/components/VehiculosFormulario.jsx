import React from "react";

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
            </div>
        </div>
    )
}