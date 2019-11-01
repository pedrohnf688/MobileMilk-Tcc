package com.eaj.ufrn.mobilemilk.ModeloDTO;

import com.eaj.ufrn.mobilemilk.Modelo.Amostra;

import java.util.Arrays;

public class AmostraDto {
    AmostraAnalise data;
    String[] erros;

    public AmostraDto(AmostraAnalise amostra, String[] erros) {
        this.data = amostra;
        this.erros = erros;
    }

    public AmostraDto(){}

    public AmostraAnalise getData() {
        return data;
    }

    public void setData(AmostraAnalise data) {
        this.data = data;
    }

    public String[] getErros() {
        return erros;
    }

    public void setErros(String[] erros) {
        this.erros = erros;
    }

    @Override
    public String toString() {
        return "AmostraDto{" +
                "data=" + data +
                ", erros=" + Arrays.toString(erros) +
                '}';
    }
}
