package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.Status;

public class Solicitacao {

    private Fazenda fazenda;
    //private OrdemServico ordemServico;
    private Amostra analise;
    private Enum<Status> status;
    private String observacao;

    public Solicitacao(String observacao, Enum<Status> status){
        this.observacao = observacao;
        this.status = Status.APROVADO;
    }

    //Getters and Setters
    public void registrarStatus(Status status){
        this.status = status;
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public Amostra getAnalise() {
        return analise;
    }

    public void setAnalise(Amostra analise) {
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
