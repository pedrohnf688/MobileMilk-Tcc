package com.eaj.ufrn.mobilemilk.Modelo;

import java.util.Arrays;

public class Arquivo {

    private String id;
    private String fileName;
    private String fileType;
    //private byte[] data;
    private String fileDownloadUri;
    private long size;
    private Cliente fotoPerfil;
    private Solicitacao fotoSolicitacao;
    private Solicitacao comprovanteSolicitacao;

    public Arquivo() {
    }

    public Arquivo(String fileName, String fileType, String fileDownloadUri, long size, Cliente fotoPerfil, Solicitacao fotoSolicitacao, Solicitacao comprovanteSolicitacao) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileDownloadUri = fileDownloadUri;
        this.size = size;
        this.fotoPerfil = fotoPerfil;
        this.fotoSolicitacao = fotoSolicitacao;
        this.comprovanteSolicitacao = comprovanteSolicitacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Cliente getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Cliente fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Solicitacao getFotoSolicitacao() {
        return fotoSolicitacao;
    }

    public void setFotoSolicitacao(Solicitacao fotoSolicitacao) {
        this.fotoSolicitacao = fotoSolicitacao;
    }

    public Solicitacao getComprovanteSolicitacao() {
        return comprovanteSolicitacao;
    }

    public void setComprovanteSolicitacao(Solicitacao comprovanteSolicitacao) {
        this.comprovanteSolicitacao = comprovanteSolicitacao;
    }

    @Override
    public String toString() {
        return "Arquivo{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileDownloadUri='" + fileDownloadUri + '\'' +
                ", size=" + size +
                ", fotoPerfil=" + fotoPerfil +
                ", fotoSolicitacao=" + fotoSolicitacao +
                ", comprovanteSolicitacao=" + comprovanteSolicitacao +
                '}';
    }
}
