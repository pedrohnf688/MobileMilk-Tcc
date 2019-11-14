package com.eaj.ufrn.mobilemilk.Modelo;

import java.util.Date;

public class OrdemServico {

    private Bolsista bolsista;
    private Date dataHora;
    private Solicitacao solicitacao;
    private Integer valorPreco;

    public OrdemServico(Bolsista bolsista, Date dataHora, Solicitacao solicitacao, Integer valorPreco) {
        this.bolsista = bolsista;
        this.dataHora = dataHora;
        this.solicitacao = solicitacao;
        this.valorPreco = valorPreco;
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

    public Integer getValorPreco() {
        return valorPreco;
    }

    public void setValorPreco(Integer valorPreco) {
        this.valorPreco = valorPreco;
    }
}
