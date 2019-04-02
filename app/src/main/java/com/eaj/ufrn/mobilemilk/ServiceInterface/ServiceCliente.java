package com.eaj.ufrn.mobilemilk.ServiceInterface;

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

    @GET("usuarios/clientes/{cpf}")
    Call<Cliente> buscarClienteCpf(@Path("cpf") String cliente);

    @GET("usuarios/clientes")
    Call<Cliente> listaClientes();

    @POST("usuarios/clientes")
    Call<Cliente> cadastrarCliente(@Body Cliente cliente);

    @DELETE("usuarios/clientes/{cpf}")
    Call<ResponseBody> deletarCliente(@Path("cpf") String cpf);

    @PUT("usuarios/clientes/{cpf}")
    Call<ResponseBody> editarCliente(@Path("cpf") String cpf);

}
