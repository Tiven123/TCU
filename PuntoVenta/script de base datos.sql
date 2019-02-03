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
);
CREATE TABLE desarrollo.producto
(
    id serial NOT NULL,
    codigo VARCHAR(50) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    precio_compra DOUBLE PRECISION,
    precio_venta DOUBLE PRECISION NOT NULL,
    impuestos BOOLEAN NOT NULL,
    porcentaje_ganancia DOUBLE PRECISION,
    cantidad INT NOT NULL,
    CONSTRAINT pk_producto PRIMARY KEY
    (id),
    CONSTRAINT unique_producto_codigo UNIQUE
    (codigo)
);
CREATE TABLE desarrollo.factura
(
    id serial NOT NULL,
    fecha_hora TIMESTAMP NOT NULL,
    nombre_cliente VARCHAR(50) NOT NULL,
    nombre_cajero VARCHAR(50) NOT NULL,
    subtotal_exento DOUBLE PRECISION NOT NULL,
    subtotal_gravado DOUBLE PRECISION NOT NULL,
    total DOUBLE PRECISION NOT NULL,
    efectivo DOUBLE PRECISION NOT NULL,
    tarjeta DOUBLE PRECISION NOT NULL,
    monto_impuestos DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_factura PRIMARY KEY
    (id)
);
CREATE TABLE desarrollo.detalle_factura
(
    id serial NOT NULL,
    id_factura INT NOT NULL REFERENCES factura(id),
    codigo_producto VARCHAR(50) NOT NULL REFERENCES producto(codigo),
    cantidad INT NOT NULL,
    CONSTRAINT pk_detalle_factura PRIMARY KEY
    (id)
);
