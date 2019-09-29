package com.eaj.ufrn.mobilemilk.ModeloDTO;

public class ArquivoDto {

    private String email;
    private String linkUrlFoto;

    public ArquivoDto() {
    }

    public ArquivoDto(String email, String linkUrlFoto) {
        this.email = email;
        this.linkUrlFoto = linkUrlFoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkUrlFoto() {
        return linkUrlFoto;
    }

    public void setLinkUrlFoto(String linkUrlFoto) {
        this.linkUrlFoto = linkUrlFoto;
    }

    @Override
    public String toString() {
        return "ArquivoDto{" +
                "email='" + email + '\'' +
                ", linkUrlFoto='" + linkUrlFoto + '\'' +
                '}';
    }
}
