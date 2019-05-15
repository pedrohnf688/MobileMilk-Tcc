package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Solicitacao;
import com.eaj.ufrn.mobilemilk.ModeloDTO.SolicitacaoDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceSolicitacao {

    @PUT("solicitacao")
    Call<SolicitacaoDto> cadastrarSolicitacao(@Body SolicitacaoDto solicitacaoDto, @Header("Authorization") String authorzation);

    @GET("solicitacao/{id}")
    Call<Solicitacao> buscarSolicitacao( @Path("id") String id, @Header("Authorization") String authorization);

}
