package com.yaganaste.transferencia.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class TransferenciaRequestDTO {

    private BigDecimal importe;
    private Integer cliente1;
    private Integer cliente2;
}
