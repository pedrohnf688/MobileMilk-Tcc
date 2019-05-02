package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.ModeloDTO.ClienteDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceCliente {

    @POST("cliente")
    Call<Cliente> cadastrarCliente(@Body Cliente usuario);

    @PUT("cliente/{id}")
    Call<Cliente> atualizarCliete(@Path("id") String id ,@Body Cliente cliente, @Header("Authorization") String authorization);

    @DELETE("cliente/{id}")
    Call<Cliente> deletarCliente(@Path("id") Integer id);

    @GET("cliente/{id}")
    Call<ClienteDto> buscarCliente(@Path("id") String id, @Header("Authorization") String authorization);

}
