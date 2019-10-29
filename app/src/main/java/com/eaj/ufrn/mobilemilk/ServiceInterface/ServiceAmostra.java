package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Amostra;
import com.eaj.ufrn.mobilemilk.ModeloDTO.AmostraDto;
import com.eaj.ufrn.mobilemilk.ModeloDTO.AmostrasDetalhes;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceAmostra {

    @GET("amostra/buscarAmostra/{analiseId}/{identifAmostra}")
    Call<AmostraDto> buscarAmostra(@Path("analiseId") String analiseId, @Path("identifAmostra") String identifAmostra, @Header("Authorization") String authorization);

    @PUT("amostra/{identifAmostra}")
    Call<Amostra> atualizarAmostra(@Path("identifAmostra") String identifAmostra , @Body Amostra amostra, @Header("Authorization") String authorization);

    @GET("amostra/listaQrCode/{analiseId}")
    Call<List<Amostra>> listarAmostrasPorAnalise(@Path("identifAmostra") Integer analiseId, @Header("Authorization") String authorization);

    @GET("amostra/dadosAmostras/{analiseId}")
    Call<AmostrasDetalhes> buscarDadosAmostrasPorAnaliseId(@Path("analiseId") String analiseId, @Header("Authorization") String authorization);

    @GET("amostra/buscarAmostraInfo/{identifAmostra}")
    Call<AmostraDto> buscarAmostraInfo(@Path("identifAmostra") String identifAmostra, @Header("Authorization") String authorization);



}
