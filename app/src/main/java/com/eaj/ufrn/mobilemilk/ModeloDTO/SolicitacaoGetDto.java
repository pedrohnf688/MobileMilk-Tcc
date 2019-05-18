package com.eaj.ufrn.mobilemilk.ModeloDTO;

import com.eaj.ufrn.mobilemilk.Modelo.Analise;
import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;

import java.util.List;

public class SolicitacaoGetDto {

    private String id;
    private Fazenda fazenda;
    private List<Analise> listaAnalise;
    private String status;
    private String observacao;

    public SolicitacaoGetDto(String id, Fazenda fazenda, List<Analise> listaAnalise, String status, String observacao) {
        this.id = id;
        this.fazenda = fazenda;
        this.listaAnalise = listaAnalise;
        this.status = status;
        this.observacao = observacao;
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

    @Override
    public String toString() {
        return "SolicitacaoGetDto{" +
                "id='" + id + '\'' +
                ", fazenda=" + fazenda +
                ", listaAnalise=" + listaAnalise +
                ", status='" + status + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
