CREATE DATABASE tcu;
COMMENT ON DATABASE tcu IS 'Proyecto TCU';

CREATE SCHEMA "desarrollo";
CREATE SCHEMA "pruebas";
CREATE SCHEMA "produccion";

ALTER DATABASE tcu SET search_path TO desarrollo;
ALTER ROLE <role_name> SET search_path TO schema1,schema2;


CREATE TABLE desarrollo.usuario
(
    id serial NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY
    (id),
    CONSTRAINT unique_usuario_usuario UNIQUE
    (usuario)
)