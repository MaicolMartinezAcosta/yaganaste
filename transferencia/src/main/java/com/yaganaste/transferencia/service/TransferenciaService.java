package com.yaganaste.transferencia.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public interface TransferenciaService {
    void transferencia(BigDecimal importe, Integer cliente1, Integer cliente2);
}
