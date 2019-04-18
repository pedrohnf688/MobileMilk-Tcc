package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Credencial;
import com.eaj.ufrn.mobilemilk.Modelo.Token;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;

import retrofit2.http.POST;

public interface ServiceAutenticacao {

    @POST("autenticacao")
    Call<Class> autenticarCliente(@Body Credencial credencial);

}
