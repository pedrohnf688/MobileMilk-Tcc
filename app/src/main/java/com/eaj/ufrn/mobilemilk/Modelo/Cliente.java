package com.eaj.ufrn.mobilemilk.Modelo;

public class Cliente {

    private String email;
    private String nome;
    private String cpf;
    private String telefone;
    //private List<Solicitacao> listaSolicitacoes;
    //private List<Fazenda> listafazendas;
    //private Credencial credencial;

    public Cliente(String nome, String email, String username, String senha){
        this.nome = nome;
        this.email = email;
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
}
