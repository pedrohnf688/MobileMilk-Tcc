package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.Status;

import java.util.List;

public class Solicitacao {

   private Cliente cliente;
   private Fazenda fazenda;
   private Status status;
   private String observacao;
   private List<Analise> listaAnalises;
   private OrdemServico ordemServico;
   private List<LaudoMedia> listaLaudoMedia;
   private double temperatura;


    public Solicitacao(Cliente cliente, Fazenda fazenda, Status status, String observacao,
                       List<Analise> listaAnalises, OrdemServico ordemServico) {
        this.cliente = cliente;
        this.fazenda = fazenda;
        this.status = status;
        this.observacao = observacao;
        this.listaAnalises = listaAnalises;
        this.ordemServico = ordemServico;
    }

    public Solicitacao(Cliente cliente, Fazenda fazenda, Status status, String observacao, List<Analise> listaAnalises, OrdemServico ordemServico, List<LaudoMedia> listaLaudoMedia, double temperatura) {
        this.cliente = cliente;
        this.fazenda = fazenda;
        this.status = status;
        this.observacao = observacao;
        this.listaAnalises = listaAnalises;
        this.ordemServico = ordemServico;
        this.listaLaudoMedia = listaLaudoMedia;
        this.temperatura = temperatura;

    }

    public Solicitacao(){

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Analise> getListaAnalises() {
        return listaAnalises;
    }

    public void setListaAnalises(List<Analise> listaAnalises) {
        this.listaAnalises = listaAnalises;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public List<LaudoMedia> getListaLaudoMedia() {
        return listaLaudoMedia;
    }

    public void setListaLaudoMedia(List<LaudoMedia> listaLaudoMedia) {
        this.listaLaudoMedia = listaLaudoMedia;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }


    @Override
    public String toString() {
        return "Solicitacao{" +
                "cliente=" + cliente +
                ", fazenda=" + fazenda +
                ", status=" + status +
                ", observacao='" + observacao + '\'' +
                ", listaAnalises=" + listaAnalises +
                ", ordemServico=" + ordemServico +
                ", listaLaudoMedia=" + listaLaudoMedia +
                ", temperatura=" + temperatura +
                '}';
    }
}


