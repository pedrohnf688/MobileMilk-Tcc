package com.eaj.ufrn.mobilemilk.ModeloDTO;

import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;

public class FazendaDto {

    Fazenda data;
    String[] erros;

    public FazendaDto(Fazenda data, String[] erros) {
        this.data = data;
        this.erros = erros;
    }

    public Fazenda getData() {
        return data;
    }

    public void setData(Fazenda data) {
        this.data = data;
    }

    public String[] getErros() {
        return erros;
    }

    public void setErros(String[] erros) {
        this.erros = erros;
    }
}
