-- ========================================
-- Script de Inicialización de Base de Datos
-- HelpDesk BoW System
-- ========================================

-- Crear base de datos si no existe
CREATE DATABASE IF NOT EXISTS mydatabase;

-- Usar la base de datos
USE mydatabase;

-- ========================================
-- Tabla: usuarios
-- ========================================
CREATE TABLE IF NOT EXISTS usuarios (
    id VARCHAR(255) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(50),
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    INDEX idx_correo (correo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ========================================
-- Tabla: departamentos
-- ========================================
CREATE TABLE IF NOT EXISTS departamentos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    contacto VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ========================================
-- Tabla: tickets
-- ========================================
CREATE TABLE IF NOT EXISTS tickets (
    id INT PRIMARY KEY AUTO_INCREMENT,
    asunto VARCHAR(255) NOT NULL,
    descripcion TEXT NOT NULL,
    estado VARCHAR(50) NOT NULL,
    prioridad VARCHAR(50) NOT NULL,
    usuarioId VARCHAR(255) NOT NULL,
    departamentoId INT NOT NULL,
    fechaCreacion DATETIME NOT NULL,
    fechaActualizacion DATETIME NOT NULL,
    FOREIGN KEY (usuarioId) REFERENCES usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (departamentoId) REFERENCES departamentos(id) ON DELETE CASCADE,
    INDEX idx_usuario (usuarioId),
    INDEX idx_departamento (departamentoId),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ========================================
-- Tabla: diccionario_emocional
-- ========================================
CREATE TABLE IF NOT EXISTS diccionario_emocional (
    id INT PRIMARY KEY AUTO_INCREMENT,
    palabra VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    UNIQUE KEY idx_palabra_categoria (palabra, categoria),
    INDEX idx_categoria (categoria)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ========================================
-- Tabla: diccionario_tecnico
-- ========================================
CREATE TABLE IF NOT EXISTS diccionario_tecnico (
    id INT PRIMARY KEY AUTO_INCREMENT,
    palabra VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    UNIQUE KEY idx_palabra_categoria (palabra, categoria),
    INDEX idx_categoria (categoria)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ========================================
-- Datos de Prueba - Usuarios
-- ========================================
INSERT INTO usuarios (id, nombre, correo, telefono, password, rol) VALUES
    ('admin-001', 'Administrador', 'admin@helpdesk.com', '8888-8888', 'admin123', 'admin'),
    ('user-001', 'Juan Perez', 'juan@example.com', '8765-4321', '1234', 'usuario'),
    ('sop-001', 'Maria Lopez', 'maria@example.com', '8765-1234', '1234', 'soporte')
ON DUPLICATE KEY UPDATE nombre=nombre;

-- ========================================
-- Datos de Prueba - Departamentos
-- ========================================
INSERT INTO departamentos (id, nombre, descripcion, contacto) VALUES
    (1, 'Soporte Técnico', 'Departamento encargado de resolver problemas técnicos', 'soporte@helpdesk.com'),
    (2, 'Desarrollo', 'Departamento de desarrollo de software', 'dev@helpdesk.com'),
    (3, 'Infraestructura', 'Departamento de infraestructura y redes', 'infra@helpdesk.com'),
    (4, 'Recursos Humanos', 'Departamento de gestión de personal', 'rrhh@helpdesk.com')
ON DUPLICATE KEY UPDATE nombre=nombre;

-- ========================================
-- Datos de Prueba - Tickets
-- ========================================
INSERT INTO tickets (id, asunto, descripcion, estado, prioridad, usuarioId, departamentoId, fechaCreacion, fechaActualizacion) VALUES
    (1, 'No puedo acceder al sistema', 'He intentado varias veces pero me dice que mis credenciales son incorrectas', 'abierto', 'alta', 'user-001', 1, NOW(), NOW()),
    (2, 'Error en la aplicación', 'La aplicación se cierra inesperadamente al intentar guardar', 'en progreso', 'media', 'user-001', 2, NOW(), NOW())
ON DUPLICATE KEY UPDATE asunto=asunto;

-- ========================================
-- Datos - Diccionario Emocional
-- ========================================
-- Frustración
INSERT INTO diccionario_emocional (palabra, tipo, categoria) VALUES
    ('enojado', 'Emocional', 'Frustración'),
    ('molesto', 'Emocional', 'Frustración'),
    ('estresado', 'Emocional', 'Frustración'),
    ('preocupado', 'Emocional', 'Frustración'),
    ('irritado', 'Emocional', 'Frustración'),
    ('frustrado', 'Emocional', 'Frustración'),
    ('agobiado', 'Emocional', 'Frustración'),
    ('desanimado', 'Emocional', 'Frustración'),
    ('insatisfecho', 'Emocional', 'Frustración'),
    ('molestia', 'Emocional', 'Frustración')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- Urgencia
INSERT INTO diccionario_emocional (palabra, tipo, categoria) VALUES
    ('urgente', 'Emocional', 'Urgencia'),
    ('ahora', 'Emocional', 'Urgencia'),
    ('rápido', 'Emocional', 'Urgencia'),
    ('inmediato', 'Emocional', 'Urgencia'),
    ('prioridad', 'Emocional', 'Urgencia'),
    ('apresurado', 'Emocional', 'Urgencia'),
    ('pronto', 'Emocional', 'Urgencia'),
    ('imperativo', 'Emocional', 'Urgencia'),
    ('crítico', 'Emocional', 'Urgencia'),
    ('inminente', 'Emocional', 'Urgencia')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- Neutralidad
INSERT INTO diccionario_emocional (palabra, tipo, categoria) VALUES
    ('neutral', 'Emocional', 'Neutralidad'),
    ('tranquilo', 'Emocional', 'Neutralidad'),
    ('moderado', 'Emocional', 'Neutralidad'),
    ('equilibrado', 'Emocional', 'Neutralidad'),
    ('estable', 'Emocional', 'Neutralidad'),
    ('normal', 'Emocional', 'Neutralidad'),
    ('indiferente', 'Emocional', 'Neutralidad'),
    ('regular', 'Emocional', 'Neutralidad'),
    ('medio', 'Emocional', 'Neutralidad'),
    ('calmado', 'Emocional', 'Neutralidad')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- Positivo
INSERT INTO diccionario_emocional (palabra, tipo, categoria) VALUES
    ('feliz', 'Emocional', 'Positivo'),
    ('contento', 'Emocional', 'Positivo'),
    ('motivado', 'Emocional', 'Positivo'),
    ('entusiasmado', 'Emocional', 'Positivo'),
    ('satisfecho', 'Emocional', 'Positivo'),
    ('alegre', 'Emocional', 'Positivo'),
    ('animado', 'Emocional', 'Positivo'),
    ('optimista', 'Emocional', 'Positivo'),
    ('agradable', 'Emocional', 'Positivo'),
    ('positivo', 'Emocional', 'Positivo')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- ========================================
-- Datos - Diccionario Técnico
-- ========================================
-- Redes
INSERT INTO diccionario_tecnico (palabra, tipo, categoria) VALUES
    ('red', 'Técnico', 'Redes'),
    ('wifi', 'Técnico', 'Redes'),
    ('router', 'Técnico', 'Redes'),
    ('cable', 'Técnico', 'Redes'),
    ('lan', 'Técnico', 'Redes'),
    ('ethernet', 'Técnico', 'Redes'),
    ('switch', 'Técnico', 'Redes'),
    ('conexión', 'Técnico', 'Redes'),
    ('servidor', 'Técnico', 'Redes'),
    ('ping', 'Técnico', 'Redes')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- Impresoras
INSERT INTO diccionario_tecnico (palabra, tipo, categoria) VALUES
    ('impresora', 'Técnico', 'Impresoras'),
    ('imprimir', 'Técnico', 'Impresoras'),
    ('papel', 'Técnico', 'Impresoras'),
    ('toner', 'Técnico', 'Impresoras'),
    ('cartucho', 'Técnico', 'Impresoras'),
    ('cola', 'Técnico', 'Impresoras'),
    ('error', 'Técnico', 'Impresoras'),
    ('driver', 'Técnico', 'Impresoras'),
    ('escáner', 'Técnico', 'Impresoras'),
    ('configuración', 'Técnico', 'Impresoras')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- Cuentas
INSERT INTO diccionario_tecnico (palabra, tipo, categoria) VALUES
    ('login', 'Técnico', 'Cuentas'),
    ('usuario', 'Técnico', 'Cuentas'),
    ('contraseña', 'Técnico', 'Cuentas'),
    ('perfil', 'Técnico', 'Cuentas'),
    ('acceso', 'Técnico', 'Cuentas'),
    ('permiso', 'Técnico', 'Cuentas'),
    ('seguridad', 'Técnico', 'Cuentas'),
    ('auth', 'Técnico', 'Cuentas'),
    ('email', 'Técnico', 'Cuentas'),
    ('sesión', 'Técnico', 'Cuentas')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- Hardware
INSERT INTO diccionario_tecnico (palabra, tipo, categoria) VALUES
    ('cpu', 'Técnico', 'Hardware'),
    ('ram', 'Técnico', 'Hardware'),
    ('disco', 'Técnico', 'Hardware'),
    ('placa', 'Técnico', 'Hardware'),
    ('tarjeta', 'Técnico', 'Hardware'),
    ('monitor', 'Técnico', 'Hardware'),
    ('mouse', 'Técnico', 'Hardware'),
    ('teclado', 'Técnico', 'Hardware'),
    ('fuente', 'Técnico', 'Hardware'),
    ('ventilador', 'Técnico', 'Hardware')
ON DUPLICATE KEY UPDATE palabra=palabra;

-- ========================================
-- Verificación de Tablas
-- ========================================
SHOW TABLES;

-- Ver estructura de tablas
DESCRIBE usuarios;
DESCRIBE departamentos;
DESCRIBE tickets;
DESCRIBE diccionario_emocional;
DESCRIBE diccionario_tecnico;

-- Ver datos insertados
SELECT COUNT(*) AS total_usuarios FROM usuarios;
SELECT COUNT(*) AS total_departamentos FROM departamentos;
SELECT COUNT(*) AS total_tickets FROM tickets;
SELECT COUNT(*) AS total_diccionario_emocional FROM diccionario_emocional;
SELECT COUNT(*) AS total_diccionario_tecnico FROM diccionario_tecnico;

-- ========================================
-- Consultas Útiles para Debugging
-- ========================================

-- Ver todos los usuarios
-- SELECT * FROM usuarios;

-- Ver todos los departamentos
-- SELECT * FROM departamentos;

-- Ver todos los tickets con información completa
-- SELECT 
--     t.id,
--     t.asunto,
--     t.estado,
--     t.prioridad,
--     u.nombre AS usuario,
--     d.nombre AS departamento,
--     t.fechaCreacion
-- FROM tickets t
-- INNER JOIN usuarios u ON t.usuarioId = u.id
-- INNER JOIN departamentos d ON t.departamentoId = d.id;

-- ========================================
-- Limpiar Datos (CUIDADO - Borra todo)
-- ========================================
-- DELETE FROM tickets;
-- DELETE FROM usuarios WHERE id NOT IN ('admin-001', 'user-001', 'sop-001');
-- DELETE FROM departamentos WHERE id > 4;
