package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Arquivo;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ServiceArquivo {

    @Multipart
    @PUT("uploadFileUsuario/{id}")
    Call<Arquivo> uploadFileCliente(@Part MultipartBody.Part file, @Path("id") String id, @Header("Authorization") String authorization);

    @Multipart
    @PUT("uploadFileFazenda/{id}")
    Call<Arquivo> uploadFileFazenda(@Part MultipartBody.Part file, @Path("id") String id, @Header("Authorization") String authorization);

    @GET("fileUrl/{id}")
    Call<Arquivo> fileUrlUser(@Path("id") String id, @Header("Authorization") String authorization);

    @Multipart
    @PUT("uploadFileSolicitacao/{id}")
    Call<Arquivo> uploadFileSolicitacao(@Part MultipartBody.Part file, @Path("id") String id, @Header("Authorization") String authorization);

    @Multipart
    @PUT("uploadFileComprovanteSolicitacao/{id}")
    Call<Arquivo> uploadFileComprovante(@Part MultipartBody.Part file, @Path("id") String id, @Header("Authorization") String authorization);

    @GET("fileUrlSolicitacao/{id}")
    Call<Arquivo> fileUrlSolicitacao(@Path("id") String id, @Header("Authorization") String authorization);

    @GET("fileUrlComprovante/{id}")
    Call<Arquivo> fileUrlComprovante(@Path("id") String id, @Header("Authorization") String authorization);


}
