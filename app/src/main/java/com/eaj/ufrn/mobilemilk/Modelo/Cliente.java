package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.TipoPerfilUsuario;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cliente extends Usuario{

    private List<String> telefones;
    private List<Solicitacao> listaSolicitacoes;
    private List<Fazenda> listaFazendas;

    public Cliente(String nome, String email, String cpf, TipoPerfilUsuario codigoTipoPerfilUsuario, Fazenda f, Solicitacao s, String telefone) {
        super(nome, email, cpf, codigoTipoPerfilUsuario);
        this.listaFazendas = new ArrayList<>();
        this.listaSolicitacoes = new ArrayList<>();
        this.telefones = new ArrayList<>();
    }

    public Cliente() {

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

    public void setTelefone(String telefone){
        this.telefones.add(telefone);
    }

    // Cadastrar novo cliente
    public static Call<Usuario> cadastrarCliente(Credencial credencial){
        Call<Usuario> call = new RetrofitConfig().getClienteService().cadastrarCliente(credencial);
        return call;
    }

    // Cadastrar novo cliente
    public static Call<List<Usuario>> listaCliente(){
        Call<List<Usuario>> call = new RetrofitConfig().getClienteService().listarUsuarios();
        return call;
    }

    // Retorna as solicitações Referentes ao Id do Cliente logado
    public static Call<List<Solicitacao>> listarSolicitacoes(int id){
        Call<List<Solicitacao>> call = new RetrofitConfig().getSolicitacaoService().listaSolicitacoesById(id);
        return call;
    }
}
