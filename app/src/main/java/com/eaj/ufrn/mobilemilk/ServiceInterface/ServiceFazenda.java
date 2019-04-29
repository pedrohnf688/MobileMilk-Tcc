package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceFazenda {

    @GET("fazenda/{id}")
    Call<List<Fazenda>> listaFazendasPorId();

    @POST("fazenda/{clienteId}")
    Call<Fazenda> cadastrarFazenda(@Body Fazenda fazenda, @Header("Authorization") String authorization);

    @DELETE("fazenda/{id}")
    Call<Fazenda> deletarFazenda(@Path("id") int id);

    @PUT("fazenda/{id}")
    Call<Fazenda> editarFazenda(@Path("id") int id);

}
