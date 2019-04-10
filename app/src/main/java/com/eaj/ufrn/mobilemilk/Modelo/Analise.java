package com.eaj.ufrn.mobilemilk.Modelo;

import com.eaj.ufrn.mobilemilk.Enum.Leite;

import java.util.Date;

public class Analise {

    private Date coleta;
    private Integer numAmostra;
    private String qrCode;
    private String observacao;

    public Analise(Date coleta, Integer numAmostra, String qrCode, String observacao) {
        this.coleta = coleta;
        this.numAmostra = numAmostra;
        this.qrCode = qrCode;
        this.observacao = observacao;
    }

    public Analise(){

    }

    public Date getColeta() {
        return coleta;
    }

    public void setColeta(Date coleta) {
        this.coleta = coleta;
    }

    public Integer getNumAmostra() {
        return numAmostra;
    }

    public void setNumAmostra(Integer numAmostra) {
        this.numAmostra = numAmostra;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
