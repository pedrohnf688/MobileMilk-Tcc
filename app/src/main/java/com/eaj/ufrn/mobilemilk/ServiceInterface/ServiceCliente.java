package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Cliente;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceCliente {


    @POST("/cliente")
    Call<Cliente> cadastrarCliente(@Body Cliente cliente);

    @DELETE("usuarios/clientes/{id}")
    Call<ResponseBody> deletarCliente(@Path("id") String cpf);

    @PUT("usuarios/clientes/{id}")
    Call<ResponseBody> editarCliente(@Path("id") String cpf);

}
