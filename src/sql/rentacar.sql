-- ============================================================
-- RentACar — Script SQL completo
-- ============================================================

DROP DATABASE IF EXISTS rentacar;
CREATE DATABASE rentacar CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE rentacar;

-- ------------------------------------------------------------
-- TABLA RAÍZ de usuarios (Joined Table Inheritance)
-- ------------------------------------------------------------
CREATE TABLE usuarios (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(50)  NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    nombre      VARCHAR(100) NOT NULL,
    apellidos   VARCHAR(100) NOT NULL,
    dni         VARCHAR(20)  NOT NULL UNIQUE,
    rol         ENUM('cliente','empleado') NOT NULL DEFAULT 'cliente',
    activo      TINYINT(1)   NOT NULL DEFAULT 1,
    fecha_alta  DATE         NOT NULL DEFAULT (CURRENT_DATE)
);

-- ------------------------------------------------------------
-- TABLA HIJA — clientes
-- ------------------------------------------------------------
CREATE TABLE clientes (
    usuario_id        INT PRIMARY KEY,
    telefono          VARCHAR(20),
    direccion         VARCHAR(200),
    carnet_conducir   VARCHAR(20),
    fecha_nacimiento  DATE,
    CONSTRAINT fk_cliente_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
        ON DELETE CASCADE
);

-- ------------------------------------------------------------
-- TABLA HIJA — empleados
-- ------------------------------------------------------------
CREATE TABLE empleados (
    usuario_id      INT PRIMARY KEY,
    cargo           ENUM('GERENTE','AGENTE','MECANICO','ADMINISTRATIVO') NOT NULL DEFAULT 'AGENTE',
    salario         DECIMAL(8,2),
    fecha_contrato  DATE,
    CONSTRAINT fk_empleado_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
        ON DELETE CASCADE
);

-- ------------------------------------------------------------
-- TABLA ENTIDAD PRINCIPAL — vehículos
-- ------------------------------------------------------------
CREATE TABLE vehiculos (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    matricula    VARCHAR(10)  NOT NULL UNIQUE,
    marca        VARCHAR(50)  NOT NULL,
    modelo       VARCHAR(50)  NOT NULL,
    anio         INT          NOT NULL,
    categoria    ENUM('ECONOMICO','COMPACTO','SUV','FAMILIAR','LUJO','FURGONETA') NOT NULL,
    precio_dia   DECIMAL(7,2) NOT NULL,
    km_actuales  INT          NOT NULL DEFAULT 0,
    disponible   TINYINT(1)   NOT NULL DEFAULT 1,
    color        VARCHAR(30),
    imagen_url   VARCHAR(255)
);

-- ------------------------------------------------------------
-- TABLA N:M — alquileres (cliente ↔ vehículo)
-- ------------------------------------------------------------
CREATE TABLE alquileres (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id    INT          NOT NULL,
    vehiculo_id   INT          NOT NULL,
    empleado_id   INT,
    fecha_inicio  DATE         NOT NULL,
    fecha_fin     DATE         NOT NULL,
    km_salida     INT          NOT NULL DEFAULT 0,
    km_entrada    INT,
    precio_total  DECIMAL(9,2) NOT NULL,
    estado        ENUM('ACTIVO','FINALIZADO','CANCELADO') NOT NULL DEFAULT 'ACTIVO',
    observaciones TEXT,
    CONSTRAINT fk_alquiler_cliente
        FOREIGN KEY (cliente_id)  REFERENCES clientes(usuario_id) ON DELETE CASCADE,
    CONSTRAINT fk_alquiler_vehiculo
        FOREIGN KEY (vehiculo_id) REFERENCES vehiculos(id)        ON DELETE CASCADE,
    CONSTRAINT fk_alquiler_empleado
        FOREIGN KEY (empleado_id) REFERENCES empleados(usuario_id) ON DELETE SET NULL
);

-- ============================================================
-- DATOS DE PRUEBA
-- ============================================================

-- Contraseña de todos los usuarios de prueba: "1234"
-- Hash BCrypt de "1234"
SET @hash = '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lHuu';

-- Usuarios base
INSERT INTO usuarios (username, password, email, nombre, apellidos, dni, rol) VALUES
('lucia',    @hash, 'lucia@rentacar.es',    'Lucía',   'Melero Fernández', '12345678A', 'cliente'),
('carlos',   @hash, 'carlos@rentacar.es',   'Carlos',  'García López',     '23456789B', 'cliente'),
('maria',    @hash, 'maria@rentacar.es',    'María',   'Rodríguez Sanz',   '34567890C', 'empleado'),
('admin',    @hash, 'admin@rentacar.es',    'Admin',   'Sistema',          '45678901D', 'empleado');

-- Clientes (tabla hija)
INSERT INTO clientes (usuario_id, telefono, direccion, carnet_conducir, fecha_nacimiento) VALUES
(1, '600111222', 'Calle Mayor 1, Granada',   'GR-123456', '2000-03-15'),
(2, '611222333', 'Av. Constitución 5, Sevilla', 'SE-654321', '1995-07-22');

-- Empleados (tabla hija)
INSERT INTO empleados (usuario_id, cargo, salario, fecha_contrato) VALUES
(3, 'AGENTE',  1800.00, '2023-01-10'),
(4, 'GERENTE', 2500.00, '2020-06-01');

-- Vehículos
INSERT INTO vehiculos (matricula, marca, modelo, anio, categoria, precio_dia, km_actuales, disponible, color) VALUES
('1234ABC', 'Seat',       'Ibiza',     2021, 'ECONOMICO', 35.00,  45000, 1, 'Rojo'),
('5678DEF', 'Volkswagen', 'Golf',      2022, 'COMPACTO',  55.00,  30000, 1, 'Negro'),
('9012GHI', 'Toyota',     'RAV4',      2023, 'SUV',       80.00,  15000, 1, 'Blanco'),
('3456JKL', 'BMW',        'Serie 5',   2022, 'LUJO',     120.00,  20000, 1, 'Gris'),
('7890MNO', 'Ford',       'Transit',   2020, 'FURGONETA', 90.00,  60000, 0, 'Blanco'),
('2345PQR', 'Renault',    'Megane',    2021, 'FAMILIAR',  60.00,  38000, 1, 'Azul');

-- Alquileres de ejemplo
INSERT INTO alquileres (cliente_id, vehiculo_id, empleado_id, fecha_inicio, fecha_fin, km_salida, km_entrada, precio_total, estado, observaciones) VALUES
(1, 1, 3, '2026-05-01', '2026-05-05', 45000, 45320, 140.00, 'FINALIZADO', 'Sin incidencias'),
(2, 3, 3, '2026-05-10', '2026-05-15', 15000, NULL,  400.00, 'ACTIVO',     'Cliente habitual'),
(1, 2, 4, '2026-05-18', '2026-05-20', 30000, NULL,  110.00, 'ACTIVO',     NULL);