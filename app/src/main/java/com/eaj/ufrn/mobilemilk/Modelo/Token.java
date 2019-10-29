package com.eaj.ufrn.mobilemilk.Modelo;

import java.util.HashMap;
import java.util.List;

public class Token {

    private String token;
    private String perfil;

    public Token(String token, String perfil) {
        this.token = token;
        this.perfil = perfil;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", perfil='" + perfil + '\'' +
                '}';
    }
}
