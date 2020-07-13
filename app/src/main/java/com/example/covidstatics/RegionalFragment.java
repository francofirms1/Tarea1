package com.example.covidstatics;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegionalFragment extends Fragment {

    private ServicioWeb servicio;
    private Retrofit retrofit;
    private List<Reporte> reportes;
    private RespuestaWS_Regional regional;
    private TextView fecha;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_regional, container, false);
        fecha= root.findViewById(R.id.fecha_regional);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://covid.unnamed-chile.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        servicio = retrofit.create(ServicioWeb.class);
        Call<RespuestaWS_Regional> respuestaWSRegionalCall = servicio.infoRegional();
        respuestaWSRegionalCall.enqueue(new Callback<RespuestaWS_Regional>() {
            @Override
            public void onResponse(Call<RespuestaWS_Regional> call, Response<RespuestaWS_Regional> response) {
                regional= response.body();
                if(response.isSuccessful()){
                    fecha.setText(regional.getFecha());
                    Log.d("estado" , regional.getEstado().toString());
                    Log.d("fecha" , regional.getFecha());
                    Log.d("info" , regional.getInfo());

                    //Asignacion de arreglo de reporte para iterar
                    reportes = regional.getReporte();
                    System.out.println(reportes);
                }
            }

            @Override
            public void onFailure(Call<RespuestaWS_Regional> call, Throwable t) {
                Log.d("servicio", t.getLocalizedMessage());
            }
        });

        return root;
    }
}
