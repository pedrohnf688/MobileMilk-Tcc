package com.eaj.ufrn.mobilemilk.Modelo;

public class Credencial {

    private String senha;
    private String username;
    private String usuario;

    public Credencial(String senha, String username, String usuario){
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

    public String getUsuario() {
        return usuario;
    }

}
