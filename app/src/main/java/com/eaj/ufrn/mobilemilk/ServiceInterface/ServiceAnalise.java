package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Analise;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ServiceAnalise {

    @GET("analise/{solicitacaoId}")
    Call<List<Analise>> buscarAnalisePorSolicitacaoId(@Path("solicitacaoId") String solicitacaoId, @Header("Authorization") String authorization);
}
