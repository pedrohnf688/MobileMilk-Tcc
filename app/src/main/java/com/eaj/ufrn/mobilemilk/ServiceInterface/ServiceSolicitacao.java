package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Solicitacao;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceSolicitacao {

    @GET("solicitacoes/{id}")
    Call<List<Solicitacao>> listaSolicitacoesById(@Path("id") int id);

    @GET("solicitacoes")
    Call<List<Solicitacao>> listaSolicitacoes();

    @POST("solicitacoes")
    Call<Solicitacao> cadastraSolicitacao(@Body Solicitacao solicitacao);

    @DELETE("solicitacoes/{id}")
    Call<ResponseBody> deletarSolicitacao(@Path("id") int id);

    @PUT("solicitacoes/{id}")
    Call<ResponseBody> atualizarSolicitacao(@Path("id") int id);

}
