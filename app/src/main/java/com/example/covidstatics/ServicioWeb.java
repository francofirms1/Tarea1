package com.example.covidstatics;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ServicioWeb {


    @POST("data/nacional")
    Call<RespuestaWS> infonacional();

    @POST("data/all")
    Call<RespuestaWS_Regional> infoRegional();

    @POST
    Call<RespuestaWS> infoByRegion(@Url String url);

    /*
    @POST("data/")
    Call<RespuestaWS> infoByRegion(@Body Region region);*/


    @POST("regiones")
    Call<RespuestaWS_Region> listRegiones();


}
