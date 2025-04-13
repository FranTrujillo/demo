--liquibase formatted sql
--changeset Francisco Trujillo:1

CREATE TABLE Persona (
    identificacion VARCHAR(255) PRIMARY KEY,
    nombre VARCHAR(255),
    genero VARCHAR(255),
    edad INT,
    direccion VARCHAR(255),
    telefono VARCHAR(255)
);

--changeset Francisco Trujillo:2
INSERT INTO Persona (identificacion, nombre, genero, edad, direccion, telefono) VALUES
    ('123456789', 'Jose Lema ', 'Masculino', 30, 'Otavalo sn y principal', '098254785'),
    ('987654321', 'Marianela Montalvo', 'Femenino', 25, 'Amazonas y NNUU', '097548965'),
    ('234534663', 'Juan Osorio', 'Masculino', 25, '13 junio y Equinoccial', '098874587');

--changeset Francisco Trujillo:3
CREATE TABLE Cliente (
    clienteId VARCHAR(255) PRIMARY KEY,
    identificacion VARCHAR(255),
    contrasena VARCHAR(255),
    estado BOOLEAN,
    FOREIGN KEY (identificacion) REFERENCES Persona(identificacion)
);

--changeset Francisco Trujillo:4
INSERT INTO Cliente (clienteId, identificacion, contrasena, estado) VALUES
    ('C001', '123456789', '1234', TRUE),
    ('C002', '987654321', '5678', TRUE),
    ('C003', '234534663', '1245', TRUE);

--changeset Francisco Trujillo:8
CREATE TABLE Cuenta (
    numeroCuenta VARCHAR(255) PRIMARY KEY,
    tipoCuenta VARCHAR(255),
    saldoInicial DECIMAL(10, 2),
    estado BOOLEAN,
    cliente_id VARCHAR(255),
    FOREIGN KEY (cliente_id) REFERENCES Cliente(clienteId)
);

--changeset Francisco Trujillo:9
INSERT INTO Cuenta (numeroCuenta, tipoCuenta, saldoInicial, estado, cliente_id) VALUES
    ('001', 'Ahorros', 2000.00, TRUE, 'C001'),
    ('002', 'Corriente', 100.00, TRUE, 'C002'),
    ('003', 'Ahorros', 0.00, TRUE, 'C003'),
    ('004', 'Ahorros', 540.00, TRUE, 'C002');

--changeset Francisco Trujillo:10
CREATE TABLE Movimiento (
    movimientoId SERIAL PRIMARY KEY,
    fecha DATE,
    tipoMovimiento VARCHAR(255),
    valor DECIMAL(10, 2),
    saldo DECIMAL(10, 2),
    numeroCuenta VARCHAR(255),
    FOREIGN KEY (numeroCuenta) REFERENCES Cuenta(numeroCuenta)
);

-- INSERT INTO Movimiento (fecha, tipoMovimiento, valor, saldo, numero_cuenta) VALUES
--     ('2025-05-29', 'Deposito', 500.00, 1500.00, '001'),
--     ('2025-05-29', 'Retiro', 200.00, 1300.00, '001'),
--     ('2025-05-29', 'Deposito', 1000.00, 3000.00, '002'),
--     ('2025-05-29', 'Retiro', 500.00, 2500.00, '002');