package com.eaj.ufrn.mobilemilk.Modelo;

public class Credencial {

    private String senha;
    private String username;
    private Usuario usuario;

    public Credencial(String senha, String username, Usuario usuario){
        this.senha = senha;
        this.username = username;
        this.usuario = usuario;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
