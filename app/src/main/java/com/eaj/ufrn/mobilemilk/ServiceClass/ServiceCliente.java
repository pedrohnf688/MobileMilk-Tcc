package com.eaj.ufrn.mobilemilk.ServiceClass;

import com.eaj.ufrn.mobilemilk.Modelo.Cliente;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceCliente {

    @GET("URL DO RECURSO")
    Call<Cliente> buscarClienteCpf(@Path("cpf") String cliente);

    @GET("URL DO RECURSO")
    Call<Cliente> listaClientes();

    @POST("URL DO RECURSO")
    Call<Cliente> cadastrarCliente(@Body Cliente cliente);

    @DELETE("URL DO RECURSO")
    Call<ResponseBody> deletarCliente(@Path("cpf") int cpf);

    @PUT("URL DO RECURSO")
    Call<ResponseBody> editarCliente(@Path("cpf") int cpf);

}
