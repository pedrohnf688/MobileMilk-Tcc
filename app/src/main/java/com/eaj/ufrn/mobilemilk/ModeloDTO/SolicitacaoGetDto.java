package com.eaj.ufrn.mobilemilk.ModeloDTO;

import com.eaj.ufrn.mobilemilk.Modelo.Amostra;
import com.eaj.ufrn.mobilemilk.Modelo.Analise;
import com.eaj.ufrn.mobilemilk.Modelo.Arquivo;
import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.Modelo.LaudoMedia;

import java.util.Date;
import java.util.List;

public class SolicitacaoGetDto {

    private String id;
    private Date dataCriada;
    private Fazenda fazenda;
    private List<Analise> listaAnalise;
    private String status;
    private String observacao;
    private List<Amostra> amostraList;
    private List<LaudoMedia> listaLaudoMedia;
    private double temperatura;

    public SolicitacaoGetDto(String id, Date dataCriada, Fazenda fazenda, List<Analise> listaAnalise, String status, String observacao, List<Amostra> amostraList) {
        this.id = id;
        this.dataCriada = dataCriada;
        this.fazenda = fazenda;
        this.listaAnalise = listaAnalise;
        this.status = status;
        this.observacao = observacao;
        this.amostraList = amostraList;
    }

    public SolicitacaoGetDto(Date dataCriada, Fazenda fazenda, List<Analise> listaAnalise, String status, String observacao, List<Amostra> amostraList, List<LaudoMedia> listaLaudoMedia, double temperatura) {
        this.dataCriada = dataCriada;
        this.fazenda = fazenda;
        this.listaAnalise = listaAnalise;
        this.status = status;
        this.observacao = observacao;
        this.amostraList = amostraList;
        this.listaLaudoMedia = listaLaudoMedia;
        this.temperatura = temperatura;
    }

    public SolicitacaoGetDto(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public List<Analise> getListaAnalise() {
        return listaAnalise;
    }

    public void setListaAnalise(List<Analise> listaAnalise) {
        this.listaAnalise = listaAnalise;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getDataCriada() {
        return dataCriada;
    }

    public void setDataCriada(Date dataCriada) {
        this.dataCriada = dataCriada;
    }

    public List<Amostra> getAmostraList() {
        return amostraList;
    }

    public void setAmostraList(List<Amostra> amostraList) {
        this.amostraList = amostraList;
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
        return "SolicitacaoGetDto{" +
                "id='" + id + '\'' +
                ", dataCriada=" + dataCriada +
                ", fazenda=" + fazenda +
                ", listaAnalise=" + listaAnalise +
                ", status='" + status + '\'' +
                ", observacao='" + observacao + '\'' +
                ", amostraList=" + amostraList +
                ", listaLaudoMedia=" + listaLaudoMedia +
                ", temperatura=" + temperatura +
                '}';
    }
}
