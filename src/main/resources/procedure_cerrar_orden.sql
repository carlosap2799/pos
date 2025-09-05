CREATE OR REPLACE PROCEDURE cerrar_orden(
    p_orden_id INT,
    p_metodo_pago CHARACTER VARYING,
    p_monto_efectivo DOUBLE PRECISION,
    p_monto_tarjeta DOUBLE PRECISION,
    p_monto_transferencia DOUBLE PRECISION,
    p_cambio DOUBLE precision,
    p_usuario_id INT
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_total NUMERIC;
    v_numero_ticket INT;
    v_numero_orden_dia INT;
    v_mesa_id INT;
BEGIN
    -- 1. Obtener total de la orden y mesa
    SELECT total, mesa_id
    INTO v_total, v_mesa_id
    FROM ordenes
    WHERE id = p_orden_id
      AND estado = 'abierta';

    IF NOT FOUND THEN
        RAISE EXCEPTION 'Orden no encontrada o ya cerrada: %', p_orden_id;
    END IF;

    -- 2. Calcular numero_ticket (MAX+1)
    SELECT COALESCE(MAX(numero_ticket), 0) + 1
    INTO v_numero_ticket
    FROM ventas
    WHERE DATE(fecha_venta) = CURRENT_DATE;

    -- 3. Calcular numero_orden_dia
    SELECT COALESCE(MAX(numero_orden_dia), 0) + 1
    INTO v_numero_orden_dia
    FROM ordenes
    WHERE DATE(fecha_creacion) = CURRENT_DATE;

    -- 4. Insertar en ventas
    INSERT INTO ventas (
        cambio, fecha_venta, metodo_pago,
        monto_efectivo, monto_tarjeta, monto_transferencia,
        numero_ticket, total, orden_id, usuario_id
    ) VALUES (
        p_cambio, NOW(), p_metodo_pago,
        p_monto_efectivo, p_monto_tarjeta, p_monto_transferencia,
        v_numero_ticket, v_total, p_orden_id, p_usuario_id
    );

    -- 5. Actualizar la orden
    UPDATE ordenes
    SET estado = 'cerrada',
        fecha_actualizacion = NOW(),
        numero_orden_dia = v_numero_orden_dia
    WHERE id = p_orden_id;

    -- 6. Liberar la mesa
    UPDATE mesas
    SET estado = 'disponible',
        fecha_actualizacion = NOW()
    WHERE id = v_mesa_id;

END;
$$;