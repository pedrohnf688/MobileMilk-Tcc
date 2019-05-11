package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.ModeloDTO.FazendaDto;

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
    Call<List<FazendaDto>> listaFazendasPorId(@Header("Authorization") String authorization, @Path("id") String id);

    @POST("fazenda/{clienteId}")
    Call<Fazenda> cadastrarFazenda(@Body Fazenda fazenda, @Header("Authorization") String authorization, @Path("clienteId") String id);

    @DELETE("fazenda/{id}")
    Call<FazendaDto> deletarFazenda(@Path("id") String id, @Header("Authorization") String authorization);

    @PUT("fazenda/{id}")
    Call<FazendaDto> editarFazenda(@Path("id") String id, @Header("Authorization") String authorization, @Body Fazenda fazenda);

}
