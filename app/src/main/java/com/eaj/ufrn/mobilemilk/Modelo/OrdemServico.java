package com.eaj.ufrn.mobilemilk.Modelo;

import java.util.Date;

public class OrdemServico {

    private Integer id;
    private Date dataHora;
    private String emissaoLaudo;
    private String analiseLaboratorial;
    private Solicitacao solicitacao;
    private double valorPreco;
    private String ordem;
    private String entregaAmostras;
    private String dataRecebimento;
    private String dataAnalise;
    private String dataProcessamento;
    private int amostrasRecebidas;
    private int amostrasNaoAnalisadas;
    private int amostrasNaoColetadas;


    public OrdemServico(Date dataHora, String emissaoLaudo, String analiseLaboratorial, Solicitacao solicitacao, double valorPreco, String ordem, String entregaAmostras, String dataRecebimento, String dataAnalise, String dataProcessamento, int amostrasRecebidas, int amostrasNaoAnalisadas, int amostrasNaoColetadas) {
        this.dataHora = dataHora;
        this.emissaoLaudo = emissaoLaudo;
        this.analiseLaboratorial = analiseLaboratorial;
        this.solicitacao = solicitacao;
        this.valorPreco = valorPreco;
        this.ordem = ordem;
        this.entregaAmostras = entregaAmostras;
        this.dataRecebimento = dataRecebimento;
        this.dataAnalise = dataAnalise;
        this.dataProcessamento = dataProcessamento;
        this.amostrasRecebidas = amostrasRecebidas;
        this.amostrasNaoAnalisadas = amostrasNaoAnalisadas;
        this.amostrasNaoColetadas = amostrasNaoColetadas;
    }

    public OrdemServico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getEmissaoLaudo() {
        return emissaoLaudo;
    }

    public void setEmissaoLaudo(String emissaoLaudo) {
        this.emissaoLaudo = emissaoLaudo;
    }

    public String getAnaliseLaboratorial() {
        return analiseLaboratorial;
    }

    public void setAnaliseLaboratorial(String analiseLaboratorial) {
        this.analiseLaboratorial = analiseLaboratorial;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public double getValorPreco() {
        return valorPreco;
    }

    public void setValorPreco(double valorPreco) {
        this.valorPreco = valorPreco;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getEntregaAmostras() {
        return entregaAmostras;
    }

    public void setEntregaAmostras(String entregaAmostras) {
        this.entregaAmostras = entregaAmostras;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(String dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public String getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(String dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    public int getAmostrasRecebidas() {
        return amostrasRecebidas;
    }

    public void setAmostrasRecebidas(int amostrasRecebidas) {
        this.amostrasRecebidas = amostrasRecebidas;
    }

    public int getAmostrasNaoAnalisadas() {
        return amostrasNaoAnalisadas;
    }

    public void setAmostrasNaoAnalisadas(int amostrasNaoAnalisadas) {
        this.amostrasNaoAnalisadas = amostrasNaoAnalisadas;
    }

    public int getAmostrasNaoColetadas() {
        return amostrasNaoColetadas;
    }

    public void setAmostrasNaoColetadas(int amostrasNaoColetadas) {
        this.amostrasNaoColetadas = amostrasNaoColetadas;
    }

    @Override
    public String toString() {
        return "OrdemServico{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", emissaoLaudo='" + emissaoLaudo + '\'' +
                ", analiseLaboratorial='" + analiseLaboratorial + '\'' +
                ", solicitacao=" + solicitacao +
                ", valorPreco=" + valorPreco +
                ", ordem='" + ordem + '\'' +
                ", entregaAmostras='" + entregaAmostras + '\'' +
                ", dataRecebimento='" + dataRecebimento + '\'' +
                ", dataAnalise='" + dataAnalise + '\'' +
                ", dataProcessamento='" + dataProcessamento + '\'' +
                ", amostrasRecebidas=" + amostrasRecebidas +
                ", amostrasNaoAnalisadas=" + amostrasNaoAnalisadas +
                ", amostrasNaoColetadas=" + amostrasNaoColetadas +
                '}';
    }
}
