package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Solicitacao;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoGetDto;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoPostDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceSolicitacao {

    @POST("solicitacao")
    Call<SolicitacaoPostDto> cadastrarSolicitacao(@Body SolicitacaoPostDto solicitacaoPostDto, @Header("Authorization") String authorzation);

    @GET("solicitacao/{id}")
    Call<List<SolicitacaoGetDto>> buscarPorIdSolicitacao(@Path("id") String id, @Header("Authorization") String authorzation);

    @DELETE
    Call<Solicitacao> deletarSolicitacao(@Path("id") String id, @Header("Authorization") String authorzation);

}
