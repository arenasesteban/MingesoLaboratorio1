import React from 'react';

export default function OpcionSideBar({ nombreOpcion, iconoOpcion, iconoDropdown, onClick}) {

    return (
        <div className="mx-3 mt-auto">
            <button onClick={onClick} className="flex gap-3 items-center w-full px-4 py-3 text-white font-semibold rounded-lg hover:bg-cyan-700 hover:shadow-md active:text-cyan-600 active:bg-white">
                {iconoOpcion && <span>{iconoOpcion}</span>}
                {nombreOpcion}
                {iconoDropdown && <span>{iconoDropdown}</span>}
            </button>
        </div>
    );
}