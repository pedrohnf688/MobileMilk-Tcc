package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoPostDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceSolicitacao {

    @POST("solicitacao")
    Call<SolicitacaoPostDto> cadastrarSolicitacao(@Body SolicitacaoPostDto solicitacaoPostDto, @Header("Authorization") String authorzation);

}
