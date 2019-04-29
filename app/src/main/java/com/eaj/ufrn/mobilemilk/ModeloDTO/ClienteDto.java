package com.eaj.ufrn.mobilemilk.ModeloDTO;

import com.eaj.ufrn.mobilemilk.Modelo.Cliente;

import java.util.List;


public class ClienteDto {

    Cliente cliente;
    List<String> erros;

    public ClienteDto(Cliente cliente, List<String> erros) {
        this.cliente = cliente;
        this.erros = erros;
    }

    public ClienteDto(){}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }

}
