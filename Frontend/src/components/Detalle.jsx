import { useState, useEffect } from "react"
import { useParams, useNavigate } from "react-router-dom";
import registroService from "../services/registro.service";
import detalleService from "../services/detalle.service";

export default function Detalle() {
    const { idRegistro, bono } = useParams();
    const [registro, setRegistro] = useState("");
    const [detalle, setDetalle] = useState("");

    async function buscarInformacion(){ 
        try {
            const registroResponse = await registroService.calcularTotal(idRegistro, bono);
            setRegistro(registroResponse.data);

            const detalleResponse = await detalleService.obtenerDetalle(idRegistro);
            setDetalle(detalleResponse.data);
        } catch (error) {
            console.log(error);
        }
    }

    useEffect(() => {
        buscarInformacion();
    }, [])

    return (
        <div className="flex h-4/5 m-9 p-12 bg-gray-100 shadow-md border border-gray-300 rounded-md">   
            <div className="flex flex-col w-full">
                <div className="flex justify-between items-center border-b border-gray-300 pb-4">
                    <div className="flex items-center text-gray-700">
                        <h1 className="text-3xl font-bold uppercase pr-4 mr-2">Detalle reparación</h1>
                    </div>
                </div>
                <div>
                    <div>
                        <h2>Reparaciones</h2>
                        <span>{detalle.reparaciones}</span>
                    </div>
                    <div>
                        <h2>Recargos</h2>
                        <span>{detalle.recargos}</span>
                    </div>
                    <div>
                        <h2>Descuentos</h2>
                        <span>{detalle.descuentos}</span>
                    </div>
                    <div>
                        <span>{detalle.reparaciones + detalle.recargos - detalle.descuentos}</span>
                    </div>
                    <div>
                        <h2>IVA</h2>
                        <span>{detalle.iva}</span>
                    </div>
                    <div>
                        <h2>Monto total</h2>
                        <span>{registro.montoTotal}</span>
                    </div>
                </div>
            </div>
        </div>
    )
}