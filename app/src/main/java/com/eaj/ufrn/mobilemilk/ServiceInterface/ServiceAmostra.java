package com.eaj.ufrn.mobilemilk.ServiceInterface;

import com.eaj.ufrn.mobilemilk.ModeloDTO.AmostraDto;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ServiceAmostra {

    @GET("amostra/buscarAmostra/{identifAmostra}")
    Call<AmostraDto> buscarAmostra(@Path("identifAmostra") String identifAmostra, @Header("Authorization") String authorization);

}
