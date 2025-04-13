--liquibase formatted sql
--changeset Francisco Trujillo:8

ALTER TABLE Movimiento
RENAME COLUMN numero_cuenta TO numeroCuenta;

--changeset Francisco Trujillo:9
ALTER TABLE Cuenta
RENAME COLUMN cliente_id TO clienteId;