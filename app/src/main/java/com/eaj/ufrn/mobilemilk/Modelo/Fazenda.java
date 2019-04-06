package com.eaj.ufrn.mobilemilk.Modelo;

public class Fazenda {

    private String empresa;
    private String localizacao;
    private String cnpj;
    private String cep;
    private String endereco;
    private String estado;
    private Integer numero;
    private String imagem;

    // General Constructor
    public Fazenda(String empresa, String localizacao, String cnpj, String cep, String endereco, String imagem, String estado, Integer numero) {
        this.empresa = empresa;
        this.localizacao = localizacao;
        this.cnpj = cnpj;
        this.cep = cep;
        this.endereco = endereco;
        this.imagem = imagem;
        this.estado = estado;
        this.numero = numero;
    }

    // Emprty Constructor
    public Fazenda(){

    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getStado() {
        return estado;
    }

    public void setStado(String stado) {
        this.estado = stado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
