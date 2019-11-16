package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.Status;

import java.util.List;

public class Solicitacao {

   private String id;
   private Cliente cliente;
   private Fazenda fazenda;
   private Status status;
   private String observacao;
   private List<Analise> listaAnalises;
   private List<LaudoMedia> listaLaudoMedia;
   private double temperatura;


    public Solicitacao(Cliente cliente, Fazenda fazenda, Status status, String observacao,
                       List<Analise> listaAnalises) {
        this.cliente = cliente;
        this.fazenda = fazenda;
        this.status = status;
        this.observacao = observacao;
        this.listaAnalises = listaAnalises;
    }

    public Solicitacao(String id, Cliente cliente, Fazenda fazenda, Status status, String observacao, List<Analise> listaAnalises, List<LaudoMedia> listaLaudoMedia, double temperatura) {
        this.id = id;
        this.cliente = cliente;
        this.fazenda = fazenda;
        this.status = status;
        this.observacao = observacao;
        this.listaAnalises = listaAnalises;
        this.listaLaudoMedia = listaLaudoMedia;
        this.temperatura = temperatura;
    }

    public Solicitacao(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "id='" + id + '\'' +
                ", cliente=" + cliente +
                ", fazenda=" + fazenda +
                ", status=" + status +
                ", observacao='" + observacao + '\'' +
                ", listaAnalises=" + listaAnalises +
                ", listaLaudoMedia=" + listaLaudoMedia +
                ", temperatura=" + temperatura +
                '}';
    }
}


