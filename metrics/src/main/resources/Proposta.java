package com.redhat.metrics;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Proposta {

    private int id;
    private String cliente;
    private String corretor;
    private Double valor;
    private Boolean aprovada;

    public String getCliente() {
        return cliente;
    }

    public String getCorretor() {
        return corretor;
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Proposta [id=" + id + ", corretor=" + corretor + ", cliente=" + cliente + ", valor=" + valor + ", aprovada="+ aprovada +"]";
    }

}
