package com.eaj.ufrn.mobilemilk.Modelo;

public class Fazenda {

    private String empresa;
    private String localizacao;
    private String cnpj;
    private String cep;
    private String endereco;
    private String imagem;

    public Fazenda(String empresa, String localizacao, String cnpj, String cep, String endereco, String imagem) {
        this.empresa = empresa;
        this.localizacao = localizacao;
        this.cnpj = cnpj;
        this.cep = cep;
        this.endereco = endereco;
        this.imagem = imagem;
    }

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
}
