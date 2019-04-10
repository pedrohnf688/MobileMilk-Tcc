package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.AnalisesSolicitadas;
import com.eaj.ufrn.mobilemilk.Enum.Leite;
import com.eaj.ufrn.mobilemilk.Enum.OrigemLeite;
import com.eaj.ufrn.mobilemilk.Enum.Produtos;

public class Amostra {

    private Enum<Leite> leite;
    private Enum<OrigemLeite> origemLeite;
    private Enum<Produtos> produtos;
    private String especie;
    private Enum<AnalisesSolicitadas> analisesSolicitadas;

    public Amostra(Enum<Leite> leite, Enum<OrigemLeite> origemLeite, Enum<Produtos> produtos, String especie, Enum<AnalisesSolicitadas> analisesSolicitadas) {
        this.leite = leite;
        this.origemLeite = origemLeite;
        this.produtos = produtos;
        this.especie = especie;
        this.analisesSolicitadas = analisesSolicitadas;
    }

    public Amostra(){

    }

    public Enum<Leite> getLeite() {
        return leite;
    }

    public void setLeite(Enum<Leite> leite) {
        this.leite = leite;
    }

    public Enum<OrigemLeite> getOrigemLeite() {
        return origemLeite;
    }

    public void setOrigemLeite(Enum<OrigemLeite> origemLeite) {
        this.origemLeite = origemLeite;
    }

    public Enum<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(Enum<Produtos> produtos) {
        this.produtos = produtos;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Enum<AnalisesSolicitadas> getAnalisesSolicitadas() {
        return analisesSolicitadas;
    }

    public void setAnalisesSolicitadas(Enum<AnalisesSolicitadas> analisesSolicitadas) {
        this.analisesSolicitadas = analisesSolicitadas;
    }
}