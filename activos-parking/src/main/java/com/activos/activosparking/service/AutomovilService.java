package com.activos.activosparking.service;

import com.activos.activosparking.model.Automovil;
import com.activos.activosparking.repository.AutomovilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AutomovilService {

    private final AutomovilRepository automovilRepository;

    private final JdbcTemplate jdbcTemplate;


    public void deleteAutomovil(long id) {
        automovilRepository.deleteById(id);
    }

    public List<Automovil> getAllAutomovil() {
        return automovilRepository.findAll();
    }

    public Automovil addAutomovil(Automovil automovil) {
        automovil.setFechaIngreso(LocalDateTime.now());
        return automovilRepository.save(automovil);
    }

    public Automovil updateAutomovil(Automovil automovil) {
        Automovil automovilToUpdate = automovilRepository.findById(automovil.getId()).orElse(null);
        if (automovilToUpdate == null) {
            throw new RuntimeException("Automovil not found");
        }
        automovilToUpdate.setPlaca(automovil.getPlaca());
        automovilToUpdate.setFechaIngreso(automovil.getFechaIngreso());
        automovilToUpdate.setFechaSalida(automovil.getFechaSalida());
        automovilToUpdate.setTypeAutomovil(automovil.getTypeAutomovil());
        return automovilRepository.save(automovilToUpdate);
    }

    public List<Automovil> findByTypeAutomovilId(long id) {
        return automovilRepository.findByTypeAutomovilId(id);
    }

    public List<Automovil> findByFechaIngreso(LocalDate fechaIngreso) {
        return automovilRepository.findByFechaIngreso(fechaIngreso);
    }

    public List<Automovil> findByFechaSalida(LocalDate fechaSalida) {
        return automovilRepository.findByFechaSalida(fechaSalida);
    }

    public List<Automovil> findByTypeAutomovilType(String type) {
        return automovilRepository.findByTypeAutomovilType(type);
    }

    public Automovil salidaAutomovil(Automovil automovil) {
        Automovil automovilToUpdate = automovilRepository.findByPlacaAndFechaSalidaIsNull(automovil.getPlaca()).orElse(null);
        if (automovilToUpdate == null) {
            throw new RuntimeException("Automovil not found");
        }
        automovilToUpdate.setFechaSalida(LocalDateTime.now());
        return automovilRepository.save(automovilToUpdate);
    }

    public double calculatePayment(long id) {

        String sql = "SELECT calcular_valor_a_pagar(fecha_ingreso, fecha_salida) AS valor_a_pagar FROM automoviles WHERE id = ?";

        Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);

        BigDecimal valueToPay = (BigDecimal) result.get("valor_a_pagar");

        if (valueToPay == null) {
            return 0.0;
        }

        return valueToPay.doubleValue();

    }

}
