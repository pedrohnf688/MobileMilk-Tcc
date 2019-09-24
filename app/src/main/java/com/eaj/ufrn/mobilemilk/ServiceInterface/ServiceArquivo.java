package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.Modelo.Arquivo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ServiceArquivo {

    @Multipart
    @PUT("uploadFileUsuario/{id}")
    Call<Arquivo> uploadFileCliente(@Part MultipartBody.Part file, @Path("id") String id, @Header("Authorization") String authorization);


}
