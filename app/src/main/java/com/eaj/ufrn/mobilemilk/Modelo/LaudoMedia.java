package com.eaj.ufrn.mobilemilk.Modelo;

import java.util.List;

public class LaudoMedia {

    private Integer idMedia;
    private String batchIdMedia;
    private String fatMedia;
    private String truproMedia;
    private String totproMedia;
    private String caseinMedia;
    private String solidsMedia;
    private String snfMedia;
    private String fpdMedia;
    private String ureaMedia;
    private String ccsMedia;
    private String celMedia;
    private String phMedia;
    private String denMedia;
    private String rantMedia;
    private String cbtMedia;
    private String cmtMedia;
    private String gordMedia;
    private String protMedia;
    private String lactMedia;
    private String esdMedia;
    private String pcMedia;
    private List<Laudo> listaLaudos;
    private Solicitacao solicitacao;

    public LaudoMedia() {
        super();
    }

    public Integer getIdMedia() {
        return idMedia;
    }

    public void setIdMedia(Integer idMedia) {
        this.idMedia = idMedia;
    }

    public String getBatchIdMedia() {
        return batchIdMedia;
    }

    public void setBatchIdMedia(String batchIdMedia) {
        this.batchIdMedia = batchIdMedia;
    }

    public String getFatMedia() {
        return fatMedia;
    }

    public void setFatMedia(String fatMedia) {
        this.fatMedia = fatMedia;
    }

    public String getTruproMedia() {
        return truproMedia;
    }

    public void setTruproMedia(String truproMedia) {
        this.truproMedia = truproMedia;
    }

    public String getTotproMedia() {
        return totproMedia;
    }

    public void setTotproMedia(String totproMedia) {
        this.totproMedia = totproMedia;
    }

    public String getCaseinMedia() {
        return caseinMedia;
    }

    public void setCaseinMedia(String caseinMedia) {
        this.caseinMedia = caseinMedia;
    }

    public String getSolidsMedia() {
        return solidsMedia;
    }

    public void setSolidsMedia(String solidsMedia) {
        this.solidsMedia = solidsMedia;
    }

    public String getSnfMedia() {
        return snfMedia;
    }

    public void setSnfMedia(String snfMedia) {
        this.snfMedia = snfMedia;
    }

    public String getFpdMedia() {
        return fpdMedia;
    }

    public void setFpdMedia(String fpdMedia) {
        this.fpdMedia = fpdMedia;
    }

    public String getUreaMedia() {
        return ureaMedia;
    }

    public void setUreaMedia(String ureaMedia) {
        this.ureaMedia = ureaMedia;
    }

    public String getCcsMedia() {
        return ccsMedia;
    }

    public void setCcsMedia(String ccsMedia) {
        this.ccsMedia = ccsMedia;
    }

    public String getCelMedia() {
        return celMedia;
    }

    public void setCelMedia(String celMedia) {
        this.celMedia = celMedia;
    }

    public String getPhMedia() {
        return phMedia;
    }

    public void setPhMedia(String phMedia) {
        this.phMedia = phMedia;
    }

    public String getDenMedia() {
        return denMedia;
    }

    public void setDenMedia(String denMedia) {
        this.denMedia = denMedia;
    }

    public String getRantMedia() {
        return rantMedia;
    }

    public void setRantMedia(String rantMedia) {
        this.rantMedia = rantMedia;
    }

    public String getCbtMedia() {
        return cbtMedia;
    }

    public void setCbtMedia(String cbtMedia) {
        this.cbtMedia = cbtMedia;
    }

    public String getCmtMedia() {
        return cmtMedia;
    }

    public void setCmtMedia(String cmtMedia) {
        this.cmtMedia = cmtMedia;
    }

    public String getGordMedia() {
        return gordMedia;
    }

    public void setGordMedia(String gordMedia) {
        this.gordMedia = gordMedia;
    }

    public String getProtMedia() {
        return protMedia;
    }

    public void setProtMedia(String protMedia) {
        this.protMedia = protMedia;
    }

    public String getLactMedia() {
        return lactMedia;
    }

    public void setLactMedia(String lactMedia) {
        this.lactMedia = lactMedia;
    }

    public String getEsdMedia() {
        return esdMedia;
    }

    public void setEsdMedia(String esdMedia) {
        this.esdMedia = esdMedia;
    }

    public String getPcMedia() {
        return pcMedia;
    }

    public void setPcMedia(String pcMedia) {
        this.pcMedia = pcMedia;
    }

    public List<Laudo> getListaLaudos() {
        return listaLaudos;
    }

    public void setListaLaudos(List<Laudo> listaLaudos) {
        this.listaLaudos = listaLaudos;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    @Override
    public String toString() {
        return "LaudoMedia [idMedia=" + idMedia + ", fatMedia=" + fatMedia + ", truproMedia=" + truproMedia
                + ", totproMedia=" + totproMedia + ", caseinMedia=" + caseinMedia + ", solidsMedia=" + solidsMedia
                + ", snfMedia=" + snfMedia + ", fpdMedia=" + fpdMedia + ", ureaMedia=" + ureaMedia + ", ccsMedia="
                + ccsMedia + ", celMedia=" + celMedia + ", phMedia=" + phMedia + ", denMedia=" + denMedia
                + ", rantMedia=" + rantMedia + ", cbtMedia=" + cbtMedia + ", cmtMedia=" + cmtMedia + ", gordMedia="
                + gordMedia + ", protMedia=" + protMedia + ", lactMedia=" + lactMedia + ", esdMedia=" + esdMedia
                + ", pcMedia=" + pcMedia + ", listaLaudos=" + listaLaudos + ", solicitacao=" + solicitacao + "]";
    }
}
