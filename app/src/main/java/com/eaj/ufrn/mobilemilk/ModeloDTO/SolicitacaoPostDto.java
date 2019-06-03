package com.eaj.ufrn.mobilemilk.ModeloDTO;

import com.eaj.ufrn.mobilemilk.Activity.ListarFazendaActivity;
import com.eaj.ufrn.mobilemilk.Modelo.Analise;

import java.util.Date;
import java.util.List;

public class SolicitacaoPostDto {

    private String cpfcnpj;
    private String dataCriada;
    private List<Analise> listaAnalise;

    public SolicitacaoPostDto(String cpfcnpj, String dataCriada, List<Analise> listaAnalise) {
        this.cpfcnpj = cpfcnpj;
        this.dataCriada = dataCriada;
        this.listaAnalise = listaAnalise;
    }

    public String getCnpj() {
        return cpfcnpj;
    }

    public void setCnpj(String cnpj) {
        this.cpfcnpj = cnpj;
    }

    public List<Analise> getListaAnalise() {
        return listaAnalise;
    }

    public void setListaAnalise(List<Analise> listaAnalise) {
        this.listaAnalise = listaAnalise;
    }

}
