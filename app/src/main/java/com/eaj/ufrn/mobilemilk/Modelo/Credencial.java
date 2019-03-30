package com.eaj.ufrn.mobilemilk.Modelo;

public class Credencial {

    private String senha;
    private String username;

    public Credencial(String senha, String username){
        this.senha = senha;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha(){
        return senha;
    }

    public boolean autenticacao(String senha, String username){
        if(getUsername().equals(username) && getSenha().equals(senha))
            return  true;
        else
            return false;
    }
}
