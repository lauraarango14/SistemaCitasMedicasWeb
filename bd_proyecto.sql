USE proyecto;
SELECT * FROM paciente;
-- =========================================================
-- TABLA: especialidad
-- =========================================================
CREATE TABLE IF NOT EXISTS especialidad (
    id_especialidad INT AUTO_INCREMENT,
    nombre VARCHAR(80) NOT NULL,
    descripcion VARCHAR(255),

    CONSTRAINT pk_especialidad
        PRIMARY KEY (id_especialidad),

    CONSTRAINT uq_especialidad_nombre
        UNIQUE (nombre)
);


-- =========================================================
-- TABLA: paciente
-- =========================================================
CREATE TABLE IF NOT EXISTS paciente (
    id_paciente INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    documento VARCHAR(20) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correo VARCHAR(100),
    fecha_nacimiento DATE NOT NULL,

    CONSTRAINT pk_paciente
        PRIMARY KEY (id_paciente),

    CONSTRAINT uq_paciente_documento
        UNIQUE (documento),

    CONSTRAINT uq_paciente_correo
        UNIQUE (correo)
);


-- =========================================================
-- TABLA: medico
-- =========================================================
CREATE TABLE IF NOT EXISTS medico (
    id_medico INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    documento VARCHAR(20) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correo VARCHAR(100),
    id_especialidad INT NOT NULL,

    CONSTRAINT pk_medico
        PRIMARY KEY (id_medico),

    CONSTRAINT uq_medico_documento
        UNIQUE (documento),

    CONSTRAINT uq_medico_correo
        UNIQUE (correo),

    CONSTRAINT fk_medico_especialidad
        FOREIGN KEY (id_especialidad)
        REFERENCES especialidad(id_especialidad)
);


-- =========================================================
-- TABLA: consultorio
-- =========================================================
CREATE TABLE IF NOT EXISTS consultorio (
    id_consultorio INT AUTO_INCREMENT,
    numero VARCHAR(10) NOT NULL,
    ubicacion VARCHAR(100) NOT NULL,
    estado VARCHAR(20) NOT NULL,

    CONSTRAINT pk_consultorio
        PRIMARY KEY (id_consultorio),

    CONSTRAINT uq_consultorio_numero
        UNIQUE (numero)
);


-- =========================================================
-- TABLA: usuario
-- =========================================================
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT,
    nombre_usuario VARCHAR(50) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(30) NOT NULL,

    CONSTRAINT pk_usuario
        PRIMARY KEY (id_usuario),

    CONSTRAINT uq_usuario_nombre
        UNIQUE (nombre_usuario),

    CONSTRAINT uq_usuario_correo
        UNIQUE (correo)
);


-- =========================================================
-- TABLA: cita
-- =========================================================
CREATE TABLE IF NOT EXISTS cita (
    id_cita INT AUTO_INCREMENT,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado VARCHAR(20) NOT NULL,
    motivo VARCHAR(255),

    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    id_consultorio INT NOT NULL,

    CONSTRAINT pk_cita
        PRIMARY KEY (id_cita),

    CONSTRAINT fk_cita_paciente
        FOREIGN KEY (id_paciente)
        REFERENCES paciente(id_paciente),

    CONSTRAINT fk_cita_medico
        FOREIGN KEY (id_medico)
        REFERENCES medico(id_medico),

    CONSTRAINT fk_cita_consultorio
        FOREIGN KEY (id_consultorio)
        REFERENCES consultorio(id_consultorio)
);


-- =========================================================
-- VERIFICACIÓN
-- =========================================================
SHOW TABLES;
USE proyecto;

DESCRIBE paciente;
DESCRIBE medico;
DESCRIBE especialidad;
DESCRIBE consultorio;
DESCRIBE usuario;
DESCRIBE cita;