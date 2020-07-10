package com.example.covidstatics;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ServicioWeb {

    @POST("data/nacional")
    Call<RespuestaWS> infonacional();
}
