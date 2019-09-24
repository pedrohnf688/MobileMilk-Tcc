package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.EnumTipoPerfilUsuario;
import com.eaj.ufrn.mobilemilk.ModeloDTO.ClienteDto;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Cliente {

    private String telefone1;
    private String telefone2;
    private List<Solicitacao> listaSolicitacoes;
    private List<Fazenda> listaFazendas;
    private String nome;
    private String email;
    private String cpf;
    private EnumTipoPerfilUsuario codigoTipoPerfilUsuario;
    private String senha;
    private String username;
    private String id;
    private Arquivo foto;

    public Cliente(String id, String telefone1, String telefone2, String nome, String email, String cpf, EnumTipoPerfilUsuario codigoTipoPerfilUsuario, String senha, String username) {
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.codigoTipoPerfilUsuario = codigoTipoPerfilUsuario;
        this.senha = senha;
        this.username = username;
        this.id = id;
    }

    public Cliente(String telefone1, String telefone2, List<Solicitacao> listaSolicitacoes, List<Fazenda> listaFazendas, String nome, String email, String cpf, EnumTipoPerfilUsuario codigoTipoPerfilUsuario, String senha, String username, String id, Arquivo foto) {
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.listaSolicitacoes = listaSolicitacoes;
        this.listaFazendas = listaFazendas;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.codigoTipoPerfilUsuario = codigoTipoPerfilUsuario;
        this.senha = senha;
        this.username = username;
        this.id = id;
        this.foto = foto;
    }

    // SERVE PRA O CADASTRO DE FAZENDAS
    public Cliente(String telefone1, String telefone2,  String nome, String email, String cpf, EnumTipoPerfilUsuario codigoTipoPerfilUsuario, String id) {
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.codigoTipoPerfilUsuario = codigoTipoPerfilUsuario;
        this.id = id;
    }

    public Cliente(){

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

    public EnumTipoPerfilUsuario getCodigoTipoPerfilUsuario() {
        return codigoTipoPerfilUsuario;
    }

    public void setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario codigoTipoPerfilUsuario) {
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

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public Arquivo getFoto() {
        return foto;
    }

    public void setFoto(Arquivo foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "telefone1='" + telefone1 + '\'' +
                ", telefone2='" + telefone2 + '\'' +
                ", listaSolicitacoes=" + listaSolicitacoes +
                ", listaFazendas=" + listaFazendas +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", codigoTipoPerfilUsuario=" + codigoTipoPerfilUsuario +
                ", senha='" + senha + '\'' +
                ", username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", foto=" + foto +
                '}';
    }
}
