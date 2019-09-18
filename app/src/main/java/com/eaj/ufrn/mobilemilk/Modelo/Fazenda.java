package com.eaj.ufrn.mobilemilk.Modelo;

public class Fazenda {

    private String id;
    private String nome;
    private String cpfcnpj;
    private String cep;
    private String endereco;
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String imagem;
    private Cliente cliente;

    public Fazenda(){

    }

    public Fazenda(String id, String nome, String cpfcnpj, String cep, String endereco, String estado,
                   String cidade, String bairro, String numero, Cliente cliente, String imagem) {
        this.nome = nome;
        this.cpfcnpj = cpfcnpj;
        this.cep = cep;
        this.endereco = endereco;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.cliente = cliente;
        this.imagem = imagem;
        this.id = id;
    }

    public Fazenda(String nome, String cpfcnpj, String cep, String endereco, String estado,
                   String cidade, String bairro, String numero, Cliente cliente, String imagem) {
        this.nome = nome;
        this.cpfcnpj = cpfcnpj;
        this.cep = cep;
        this.endereco = endereco;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.cliente = cliente;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Fazenda{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", cpfcnpj='" + cpfcnpj + '\'' +
                ", cep='" + cep + '\'' +
                ", endereco='" + endereco + '\'' +
                ", estado='" + estado + '\'' +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", numero='" + numero + '\'' +
                ", imagem='" + imagem + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}
