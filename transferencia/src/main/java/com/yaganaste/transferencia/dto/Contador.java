package com.yaganaste.transferencia.dto;

public class Contador {
    private int valor;

    public Contador() {
        this.valor = 0;
    }

    public synchronized void incrementar() {
        this.valor++;
    }

    public int getValor() {
        return valor;
    }
}