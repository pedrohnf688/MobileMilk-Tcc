package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.Status;

public class Solicitacao {

    private Fazenda fazenda;
    //private OrdemServico ordemServico;
    private Analise analise;
    private Enum<Status> status;
    private String observacao;

    public void registrarStatus(Status status){
        this.status = status;
    }

    //Getters and Setters

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Enum<Status> getStatus() {
        return status;
    }
}
