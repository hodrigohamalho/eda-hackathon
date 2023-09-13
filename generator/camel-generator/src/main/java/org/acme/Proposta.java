package org.acme;

import java.io.Serializable;

public class Proposta implements Serializable{
    
    private static final long serialVersionUID = -1405849549831347801L;
    private int id;
    private String cliente;
    private String corretor;
    private Double valor;
    private Boolean aprovada;
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCorretor() {
        return corretor;
    }
    public void setCorretor(String corretor) {
        this.corretor = corretor;
    }
    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Boolean getAprovada() {
        return aprovada;
    }
    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

    @Override
    public String toString() {
        return "Proposta [id=" + id + ", corretor=" + corretor + ", cliente=" + cliente + ", valor=" + valor + ", aprovada="+ aprovada +"]";
    }
    
}