package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Cliente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceCliente {

    @POST("/cliente")
    Call<Cliente> cadastrarCliente(@Body Cliente usuario);

}
