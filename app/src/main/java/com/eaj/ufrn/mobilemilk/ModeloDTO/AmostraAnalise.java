package com.eaj.ufrn.mobilemilk.ModeloDTO;

import com.eaj.ufrn.mobilemilk.Enum.AnalisesSolicitadas;
import com.eaj.ufrn.mobilemilk.Enum.EnumEspecie;
import com.eaj.ufrn.mobilemilk.Enum.OrigemLeite;
import com.eaj.ufrn.mobilemilk.Enum.Produtos;

import java.util.Date;
import java.util.List;

public class AmostraAnalise {

    private Date dataColeta;
    private int numeroAmostra;
    private String qrCode;
    private String observacao;
    private OrigemLeite origemLeite;
    private List<Produtos> produtos;
    private EnumEspecie especie;

    public AmostraAnalise() {
    }

    public Date getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(Date dataColeta) {
        this.dataColeta = dataColeta;
    }

    public int getNumeroAmostra() {
        return numeroAmostra;
    }

    public void setNumeroAmostra(int numeroAmostra) {
        this.numeroAmostra = numeroAmostra;
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

    public OrigemLeite getOrigemLeite() {
        return origemLeite;
    }

    public void setOrigemLeite(OrigemLeite origemLeite) {
        this.origemLeite = origemLeite;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    public EnumEspecie getEspecie() {
        return especie;
    }

    public void setEspecie(EnumEspecie especie) {
        this.especie = especie;
    }
}
