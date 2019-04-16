package com.eaj.ufrn.mobilemilk.Modelo;

import java.util.Date;

public class OrdemServico {

    private Bolsista bolsista;
    private Date dataHora;
    private Solicitacao solicitacao;
    private Integer valor;

    public OrdemServico(Bolsista bolsista, Date dataHora, Solicitacao solicitacao, Integer valor) {
        this.bolsista = bolsista;
        this.dataHora = dataHora;
        this.solicitacao = solicitacao;
        this.valor = valor;
    }

    public OrdemServico(){
    }

    public Bolsista getBolsista() {
        return bolsista;
    }

    public void setBolsista(Bolsista bolsista) {
        this.bolsista = bolsista;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
