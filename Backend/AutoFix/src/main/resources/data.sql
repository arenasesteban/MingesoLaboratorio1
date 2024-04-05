-- Script para poblar la tabla bono

-- Eliminar datos existentes en la tabla
DELETE FROM bono;

-- Insertar nuevos datos
INSERT INTO bono(marca, cantidad_bonos, monto_por_bonos) VALUES
('Toyota', 5, 70000),
('Ford', 2, 50000),
('Hyundai', 1, 30000),
('Honda', 7, 40000);