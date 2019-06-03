package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.AnalisesSolicitadas;
import com.eaj.ufrn.mobilemilk.Enum.EnumEspecie;
import com.eaj.ufrn.mobilemilk.Enum.Leite;
import com.eaj.ufrn.mobilemilk.Enum.OrigemLeite;
import com.eaj.ufrn.mobilemilk.Enum.Produtos;

import java.util.Date;
import java.util.List;

public class Analise {

    private Leite leite;
    private OrigemLeite origemLeite;
    private List<Produtos> produtos;
    private List<AnalisesSolicitadas> analisesSolicitadas;
    private EnumEspecie especie;
    private Integer quantidadeAmostras;
    private Solicitacao solicitacao;
    private List<Amostra> listaAmostras;
    private String descricao;

    public Analise(Leite leite, OrigemLeite origemLeite, List<Produtos> produtos, List<AnalisesSolicitadas> analisesSolicitadas
            , EnumEspecie especie, Integer quantidadeAmostras, List<Amostra> listaAmostras, String descricao) {
        this.leite = leite;
        this.origemLeite = origemLeite;
        this.produtos = produtos;
        this.analisesSolicitadas = analisesSolicitadas;
        this.especie = especie;
        this.quantidadeAmostras = quantidadeAmostras;
        this.listaAmostras = listaAmostras;
        this.descricao = descricao;
    }

    public Analise(){}

    public Leite getLeite() {
        return leite;
    }

    public void setLeite(Leite leite) {
        this.leite = leite;
    }

    public OrigemLeite getOrigemLeite() {
        return origemLeite;
    }

    public void setOrigemLeite(OrigemLeite origemLeite) {
        this.origemLeite = origemLeite;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    public List<AnalisesSolicitadas> getAnalisesSolicitadas() {
        return analisesSolicitadas;
    }

    public void setAnalisesSolicitadas(List<AnalisesSolicitadas> analisesSolicitadas) {
        this.analisesSolicitadas = analisesSolicitadas;
    }

    public EnumEspecie getEspecie() {
        return especie;
    }

    public void setEspecie(EnumEspecie especie) {
        this.especie = especie;
    }

    public Integer getQuantidadeAmostras() {
        return quantidadeAmostras;
    }

    public void setQuantidadeAmostras(Integer quantidadeAmostras) {
        this.quantidadeAmostras = quantidadeAmostras;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public List<Amostra> getListaAmostras() {
        return listaAmostras;
    }

    public void setListaAmostras(List<Amostra> listaAmostras) {
        this.listaAmostras = listaAmostras;
    }
}
