package com.yaganaste.transferencia.service.impl;

import com.yaganaste.transferencia.dto.Contador;
import com.yaganaste.transferencia.service.ContadorService;
import org.springframework.stereotype.Service;

@Service
public class ContadorServiceImpl implements ContadorService {

    private Contador contador;
    public ContadorServiceImpl() {
        this.contador = new Contador();
    }
    @Override
    public int incrementarContador() {
        this.contador.incrementar();
        return this.contador.getValor();
    }
}
