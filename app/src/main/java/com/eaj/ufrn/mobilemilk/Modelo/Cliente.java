package com.eaj.ufrn.mobilemilk.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String email;
    private String nome;
    private String cpf;
    private String telefone;
    private String tipoPerfilUsuario;
    private List<Solicitacao> listaSolicitacoes;
    private List<Fazenda> listafazendas;
    private Credencial credencial;

    public Cliente(String nome, String email, String username, String senha){
        this.nome = nome;
        this.email = email;
        this.credencial = null;
        this.cpf = null;
        this.telefone = null;
        this.tipoPerfilUsuario = "ROLE_CLIENTE";
        //this.listafazendas = new ArrayList<>();
        //this.listaSolicitacoes = new ArrayList<>();
    }

    public Cliente(){

    }

    //Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Solicitacao> getListaSolicitacoes() {
        return this.listaSolicitacoes;
    }

    public void setListaSolicitacoes(List<Solicitacao> listaSolicitacoes) {
        this.listaSolicitacoes = listaSolicitacoes;
    }

    public List<Fazenda> getListafazendas() {
        return this.listafazendas;
    }

    public void setListafazendas(List<Fazenda> listafazendas) {
        this.listafazendas = listafazendas;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial credencial) {
        this.credencial = credencial;
    }

    // Cadastrar Fazenda
    public void cadastrarFazenda(Fazenda fazenda){
        getListafazendas().add(fazenda);
    }

    // Cadastrar Solicitação
    public void cadastrarSolicitacao(Solicitacao solicitacao){
        getListaSolicitacoes().add(solicitacao);
    }
}
