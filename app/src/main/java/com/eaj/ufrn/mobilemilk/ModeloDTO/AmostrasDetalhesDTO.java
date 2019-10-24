package com.eaj.ufrn.mobilemilk.ModeloDTO;

import java.util.Arrays;

public class AmostrasDetalhesDTO {

    AmostrasDetalhes amostrasDetalhes;
    String[] erros;

    public AmostrasDetalhesDTO() {
    }

    public AmostrasDetalhesDTO(AmostrasDetalhes amostrasDetalhes, String[] erros) {
        this.amostrasDetalhes = amostrasDetalhes;
        this.erros = erros;
    }

    public AmostrasDetalhes getAmostrasDetalhes() {
        return amostrasDetalhes;
    }

    public void setAmostrasDetalhes(AmostrasDetalhes amostrasDetalhes) {
        this.amostrasDetalhes = amostrasDetalhes;
    }

    public String[] getErros() {
        return erros;
    }

    public void setErros(String[] erros) {
        this.erros = erros;
    }

    @Override
    public String toString() {
        return "AmostrasDetalhesDTO{" +
                "amostrasDetalhes=" + amostrasDetalhes +
                ", erros=" + Arrays.toString(erros) +
                '}';
    }
}
