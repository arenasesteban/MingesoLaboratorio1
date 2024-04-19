import React from 'react';

export default function OpcionSideBar({ enlace, nombreOpcion, iconoOpcion, iconoDropdown}) {

    return (
        <div className="mx-3 mt-auto">
            <a href={enlace} className="flex gap-3 items-center px-4 py-3 text-white font-semibold rounded-lg hover:bg-cyan-700 hover:shadow-md active:text-cyan-600 active:bg-white">
                {iconoOpcion && <span>{iconoOpcion}</span>}
                {nombreOpcion}
                {iconoDropdown && <span>{iconoDropdown}</span>}
            </a>
        </div>
    );
}