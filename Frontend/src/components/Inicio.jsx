import React from "react";

export default function Inicio() {
    return (
        <div className="flex justify-start m-12 p-16 bg-gray-100 shadow-lg border border-gray-300 rounded-md">   
            <div className="w-full h-full flex flex-col gap-4">
                <div className="flex justify-between items-center">
                    <div className="text-gray-700">
                        <h1 className="text-3xl font-bold uppercase">¿Qué hacemos hoy?</h1>
                    </div>
                </div>
            </div>
        </div>
    );
}