package com.eaj.ufrn.mobilemilk.ModeloDTO;

import com.eaj.ufrn.mobilemilk.Modelo.Cliente;

import java.util.List;


public class ClienteDto {

    Cliente data;
    List<String> erros;

    public ClienteDto(Cliente cliente, List<String> erros) {
        this.data = cliente;
        this.erros = erros;
    }

    public ClienteDto(){}

    public Cliente getData() {
        return data;
    }

    public void setData(Cliente data) {
        this.data = data;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }

}
