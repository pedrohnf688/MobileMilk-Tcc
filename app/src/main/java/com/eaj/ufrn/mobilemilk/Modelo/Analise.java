package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.AnalisesSolicitadas;
import com.eaj.ufrn.mobilemilk.Enum.Leite;
import com.eaj.ufrn.mobilemilk.Enum.OrigemLeite;
import com.eaj.ufrn.mobilemilk.Enum.Produtos;

import java.util.Date;
import java.util.List;

public class Analise {

    private Leite leite;
    private OrigemLeite origemLeite;
    private Produtos produtos;
    private String especie;
    private AnalisesSolicitadas analisesSolicitadas;
    private Solicitacao solicitacao;
    private List<Amostra> listaAmostras;

    public Analise(Leite leite, OrigemLeite origemLeite, Produtos produtos, String especie, AnalisesSolicitadas analisesSolicitadas,
                   Solicitacao solicitacao, List<Amostra> listaAmostras) {
        this.leite = leite;
        this.origemLeite = origemLeite;
        this.produtos = produtos;
        this.especie = especie;
        this.analisesSolicitadas = analisesSolicitadas;
        this.solicitacao = solicitacao;
        this.listaAmostras = listaAmostras;
    }

    public Analise(){

    }

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

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public AnalisesSolicitadas getAnalisesSolicitadas() {
        return analisesSolicitadas;
    }

    public void setAnalisesSolicitadas(AnalisesSolicitadas analisesSolicitadas) {
        this.analisesSolicitadas = analisesSolicitadas;
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
