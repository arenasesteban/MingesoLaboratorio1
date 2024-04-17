import React from 'react';

export default function Button({ text, icon, dropdown}) {
  return (
    <div className="mx-4 mt-auto">
        <a className="flex gap-3 items-center px-4 py-3 my-1 text-white font-semibold rounded-lg hover:bg-cyan-700">
            {icon && <span>{icon}</span>}
            {text}
            {dropdown && <span>{dropdown}</span>}
        </a>
    </div>
  );
}