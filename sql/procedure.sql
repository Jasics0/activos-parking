DELIMITER //
CREATE TRIGGER before_insert_auto
    BEFORE INSERT ON automoviles
    FOR EACH ROW
BEGIN
    DECLARE old_row INT;

    SELECT COUNT(*) INTO old_row
    FROM automoviles
    WHERE placa = NEW.placa AND fecha_salida IS NULL;

    IF old_row > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Ya existe un registro con la misma placa sin fecha de salida';
END IF;
END //
DELIMITER ;

DELIMITER $$

CREATE FUNCTION calcular_valor_a_pagar(fecha_ingreso DATETIME, fecha_salida DATETIME) RETURNS DECIMAL(10,2)
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE horas_estacionado INT;
    DECLARE valor_a_pagar DECIMAL(10,2);

    SET horas_estacionado = TIMESTAMPDIFF(HOUR, fecha_ingreso, fecha_salida);

    SET valor_a_pagar = horas_estacionado * 2000;

    RETURN valor_a_pagar;
END $$

DELIMITER ;
