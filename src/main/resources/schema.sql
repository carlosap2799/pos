-- ==============================================
-- POS Restaurante 
CREATE TABLE mesas (
    id BIGSERIAL PRIMARY KEY,
    numero_mesa INT NOT NULL,
    capacidad INT NOT NULL,
    forma VARCHAR(20) DEFAULT 'redonda' CHECK (forma IN ('redonda', 'cuadrada', 'rectangular')),
    posicion_x INT NOT NULL,
    posicion_y INT NOT NULL,
    estado VARCHAR(20) DEFAULT 'disponible' CHECK (estado IN ('disponible','ocupada','reservada','fuera_servicio')),
    activo BOOLEAN DEFAULT true,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre_completo VARCHAR(250),
    rol VARCHAR(20) NOT NULL CHECK (rol IN ('mesero','cajero','admin','chef')),
    nombre_usuario VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    activo BOOLEAN DEFAULT true,
    email VARCHAR(200),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ordenes (
    id BIGSERIAL PRIMARY KEY,
    numero_orden SERIAL UNIQUE,
    mesa_id BIGINT NOT NULL REFERENCES mesas(id),
    usuario_id BIGINT NOT NULL REFERENCES usuarios(id),
    estado VARCHAR(20) DEFAULT 'pendiente' CHECK (estado IN ('pendiente', 'preparando', 'lista', 'entregada', 'cancelada')),
    subtotal DECIMAL(10,2) DEFAULT 0,
    impuestos DECIMAL(10,2) DEFAULT 0,
    total DECIMAL(10,2) DEFAULT 0,
    notas TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categorias_menu (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    activo BOOLEAN DEFAULT TRUE,
    fecha_creacion DATE DEFAULT CURRENT_DATE
);

CREATE TABLE productos_menu (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL CHECK (precio > 0),
    categoria_id INT REFERENCES categorias_menu(id) ON DELETE CASCADE,
    tipo VARCHAR(20), -- simple / bebida / paquete
    stock INTEGER DEFAULT 0 CHECK (stock >= 0),
    imagen_url VARCHAR(255),
    activo BOOLEAN DEFAULT true,
    fecha_creacion DATE DEFAULT CURRENT_DATE,
    fecha_actualizacion DATE DEFAULT CURRENT_DATE
);

CREATE TABLE paquetes_menu (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);


CREATE TABLE paquete_detalle (
	id BIGSERIAL PRIMARY KEY,
    paquete_id BIGINT REFERENCES paquetes_menu(id) ON DELETE CASCADE,
    producto_id BIGINT REFERENCES productos_menu(id) ON DELETE CASCADE,
    cantidad INT DEFAULT 1
);

CREATE TABLE orden_detalle (
    id BIGSERIAL PRIMARY KEY,
    orden_id BIGINT REFERENCES ordenes(id) ON DELETE CASCADE,
    producto_id BIGINT REFERENCES productos_menu(id) ON DELETE CASCADE,
    cantidad INT NOT NULL DEFAULT 1,
    precio_unitario DECIMAL(10,2) NOT NULL,
    total DECIMAL(10,2) NOT null,
    notas_especiales TEXT,
    fecha_creacion DATE
);

CREATE TABLE ventas (
    id BIGSERIAL PRIMARY KEY,
    numero_ticket INT,
    orden_id BIGINT REFERENCES ordenes(id) ON DELETE CASCADE,
    usuario_cajero_id BIGINT REFERENCES usuarios(id) ON DELETE SET NULL,
    metodo_pago VARCHAR(20) CHECK (metodo_pago IN ('efectivo','tarjeta','transferencia')),
    monto_efectivo DECIMAL(10,2) DEFAULT 0,
    monto_tarjeta DECIMAL(10,2) DEFAULT 0,
    monto_transferencia DECIMAL(10,2) DEFAULT 0,
    total DECIMAL(10,2) NOT NULL CHECK (total > 0),
    cambio DECIMAL(10,2) DEFAULT 0,
    fecha_venta DATE
);

CREATE TABLE arqueo_caja (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT REFERENCES usuarios(id),
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    total_efectivo DECIMAL(10,2) DEFAULT 0,
    total_tarjeta DECIMAL(10,2) DEFAULT 0,
    total_transferencia DECIMAL(10,2) DEFAULT 0,
    total DECIMAL(10,2) DEFAULT 0,
    cerrado_por BIGINT REFERENCES usuarios(id)
    
);