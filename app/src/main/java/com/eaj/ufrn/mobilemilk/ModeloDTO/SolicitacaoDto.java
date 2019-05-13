package com.eaj.ufrn.mobilemilk.ModeloDTO;

import com.eaj.ufrn.mobilemilk.Activity.ListarFazendaActivity;
import com.eaj.ufrn.mobilemilk.Modelo.Analise;

import java.util.List;

public class SolicitacaoDto {

    private String cnpj;
    private List<Analise> listaAnalise;

    public SolicitacaoDto(String cnpj, List<Analise> listaAnalise) {
        this.cnpj = cnpj;
        this.listaAnalise = listaAnalise;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Analise> getListaAnalise() {
        return listaAnalise;
    }

    public void setListaAnalise(List<Analise> listaAnalise) {
        this.listaAnalise = listaAnalise;
    }

}
