package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cliente {

    private String email;
    private String nome;
    private String cpf;
    private List<String> telefone;
    private String tipoPerfilUsuario;
    private List<Solicitacao> listaSolicitacoes;
    private List<Fazenda> listafazendas;
    private Credencial credencial;

    public Cliente(){

    }

    public Cliente(String nome, String email, String cpf, String telefone, String tipoPerfilUsuario, Credencial credencial) {
        this.email = email;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = new ArrayList<>();
        this.telefone.add(telefone);
        this.tipoPerfilUsuario = tipoPerfilUsuario;
        this.credencial = credencial;
    }

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

    public List<String> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<String> telefone) {
        this.telefone = telefone;
    }

    public String getTipoPerfilUsuario() {
        return tipoPerfilUsuario;
    }

    public void setTipoPerfilUsuario(String tipoPerfilUsuario) {
        this.tipoPerfilUsuario = tipoPerfilUsuario;
    }

    public List<Solicitacao> getListaSolicitacoes() {
        return listaSolicitacoes;
    }

    public void setListaSolicitacoes(List<Solicitacao> listaSolicitacoes) {
        this.listaSolicitacoes = listaSolicitacoes;
    }

    public List<Fazenda> getListafazendas() {
        return listafazendas;
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

    // Cadastrar novo cliente
    public static Call<Cliente> cadastrarCliente(Cliente cliente){
        Call<Cliente> call = new RetrofitConfig().getClienteService().cadastrarCliente(cliente);
        return call;
    }

    // Retorna as solicitações Referentes ao Id do Cliente logado
    public static Call<List<Solicitacao>> listarSolicitacoes(int id){
        Call<List<Solicitacao>> call = new RetrofitConfig().getSolicitacaoService().listaSolicitacoesById(id);
        return call;
    }
}
