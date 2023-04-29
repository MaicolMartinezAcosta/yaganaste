package com.yaganaste.transferencia.service.impl;


import com.yaganaste.transferencia.exception.SaldoInsuficienteException;
import com.yaganaste.transferencia.dto.model.Cliente;
import com.yaganaste.transferencia.repository.ClienteRepository;
import com.yaganaste.transferencia.service.TransferenciaService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;

@Service
@Transactional(rollbackFor = Exception.class)
public class TrasnferenciaServiceImpl implements TransferenciaService {

    @Autowired
    private ClienteRepository clienteRepository;

    @SneakyThrows
    @Override
    public void transferencia(BigDecimal importe, Integer idcliente1, Integer idcliente2) {
        Cliente cliente1 = clienteRepository.findById(Long.valueOf(idcliente1))
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        Cliente cliente2 = clienteRepository.findById(Long.valueOf(idcliente2))
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        // Verificar si el saldo del cliente 1 es suficiente
        if (cliente1.getSaldo().compareTo(importe) < 0) {
            throw new SaldoInsuficienteException("Saldo insuficiente en el cliente " + cliente1.getNombre());
        }

        // Realizar la transferencia
        cliente1.setSaldo(cliente1.getSaldo().subtract(importe));
        cliente2.setSaldo(cliente2.getSaldo().add(importe));
        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
    }
}
