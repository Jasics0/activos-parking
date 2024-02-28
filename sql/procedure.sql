DELIMITER //
CREATE TRIGGER before_insert_auto
    BEFORE INSERT ON automoviles
    FOR EACH ROW
BEGIN
    DECLARE old_row INT;

    -- Verificar si ya existe un registro con la misma placa sin fecha de salida
    SELECT COUNT(*) INTO old_row
    FROM automoviles
    WHERE placa = NEW.placa AND fecha_salida IS NULL;

    -- Si existe un registro con la misma placa sin fecha de salida, impedir la inserción
    IF old_row > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe un registro con la misma placa sin fecha de salida';
END IF;
END //
DELIMITER ;
