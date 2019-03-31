package com.eaj.ufrn.mobilemilk.Retrofit;

import com.eaj.ufrn.mobilemilk.ServiceInterface.ServiceCliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    Retrofit rt; // Objeto da classe Retrofit

    /*
    *   para referenciar um serviço deve se o usar o IP ou URL
    *
    *   IP > quando serviço estiver rodando local a máquina.
    *   URL > quando o serviço estiver hospedado na nuvem.
    * */


    //Constructor
    public  RetrofitConfig(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        Gson gsonConvertFactory = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
        this.rt = new Retrofit.Builder()
                .baseUrl("URL ou IP")
                .addConverterFactory(GsonConverterFactory.create(gsonConvertFactory))
                .client(client)
                .build();
    }

    public ServiceCliente getClienteService(){
        return this.rt.create(ServiceCliente.class);
    }
}
