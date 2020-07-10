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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NacionalFragment extends Fragment {

    private ServicioWeb servicio;
    private TextView reporte;
    private TextView fecha;
    private TextView acum_total;
    private TextView casos_nuev_total;
    private TextView casos_nuev_csin;
    private TextView casos_nuev_ssin;
    private TextView casos_nuev_snotificar;
    private TextView fallecidos;
    private TextView casos_activos_confirmados;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //System.out.println("Hola despues");

        View root = inflater.inflate(R.layout.fragment_nacional, container, false);

        reporte = root.findViewById(R.id.reporte);
        fecha = root.findViewById(R.id.fecha);
        acum_total = root.findViewById(R.id.acumulado_total);
        casos_nuev_total = root.findViewById(R.id.casos_nuevos_total);
        casos_nuev_csin = root.findViewById(R.id.casos_nuevos_csintomas);
        casos_nuev_ssin = root.findViewById(R.id.casos_nuevos_ssintomas);
        casos_nuev_snotificar = root.findViewById(R.id.casos_nuevos_snotificar);
        fallecidos = root.findViewById(R.id.fallecidos);
        casos_activos_confirmados = root.findViewById(R.id.casos_activos_confirmados);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://covid.unnamed-chile.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicio = retrofit.create(ServicioWeb.class);
        Call<RespuestaWS> respuesta = servicio.infonacional();

        respuesta.enqueue(new Callback<RespuestaWS>() {
            @Override
            public void onResponse(Call<RespuestaWS> call, Response<RespuestaWS> response) {
                if (response.isSuccessful() && response != null && response.body() != null) {
                    RespuestaWS respuesta = response.body();
                    System.out.println("Response: " + respuesta);
                    reporte.setText(respuesta.getInfo());
                    fecha.setText(respuesta.getFecha());
                    System.out.println("Acumulado total: -------------" + respuesta.getReporte().getAcum_total());
                    acum_total.setText(String.valueOf(respuesta.getReporte().getAcum_total()));
                    casos_nuev_total.setText(String.valueOf(respuesta.getReporte().getCasos_nuevos()));
                    casos_nuev_csin.setText(String.valueOf(respuesta.getReporte().getCasos_nuevos_csin()));
                    casos_nuev_ssin.setText(String.valueOf(respuesta.getReporte().getCasos_nuevos_sin_sin()));
                    casos_nuev_snotificar.setText(String.valueOf(respuesta.getReporte().getCasos_nuevos_snotificar()));
                    fallecidos.setText(String.valueOf(respuesta.getReporte().getFallecidos()));
                    casos_activos_confirmados.setText(String.valueOf(respuesta.getReporte().getCasos_activos_confirmados()));

                    Log.d("Retrofit", respuesta.getInfo());
                }else{
                    Log.d("Retrofit", "ERROR DE CONVERSION");
                }
            }

            @Override
            public void onFailure(Call<RespuestaWS> call, Throwable t) {

            }
        });

        return root;
    }
}
