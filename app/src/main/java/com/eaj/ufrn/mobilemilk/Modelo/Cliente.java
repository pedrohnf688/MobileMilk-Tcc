package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.EnumTipoPerfilUsuario;
import com.eaj.ufrn.mobilemilk.ModeloDTO.ClienteDto;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Cliente {

    private List<String> telefones;
    private List<Solicitacao> listaSolicitacoes;
    private List<Fazenda> listaFazendas;
    private String nome;
    private String email;
    private String cpf;
    private Integer codigoTipoPerfilUsuario;
    private String senha;
    private String username;
    private String id;

    public Cliente(String id, List<String> telefones, String nome, String email, String cpf, Integer codigoTipoPerfilUsuario, String senha, String username) {
        this.telefones = telefones;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.codigoTipoPerfilUsuario = codigoTipoPerfilUsuario;
        this.senha = senha;
        this.username = username;
        this.id = id;
    }

    public Cliente(){

    }

    public List<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<String> telefones) {
        this.telefones = telefones;
    }

    public List<Solicitacao> getListaSolicitacoes() {
        return listaSolicitacoes;
    }

    public void setListaSolicitacoes(List<Solicitacao> listaSolicitacoes) {
        this.listaSolicitacoes = listaSolicitacoes;
    }

    public List<Fazenda> getListaFazendas() {
        return listaFazendas;
    }

    public void setListaFazendas(List<Fazenda> listaFazendas) {
        this.listaFazendas = listaFazendas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getCodigoTipoPerfilUsuario() {
        return codigoTipoPerfilUsuario;
    }

    public void setCodigoTipoPerfilUsuario(Integer codigoTipoPerfilUsuario) {
        this.codigoTipoPerfilUsuario = codigoTipoPerfilUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "telefones=" + telefones +
                ", listaSolicitacoes=" + listaSolicitacoes +
                ", listaFazendas=" + listaFazendas +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", codigoTipoPerfilUsuario=" + codigoTipoPerfilUsuario +
                ", senha='" + senha + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
