package com.example.covidstatics;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {
    private ServicioWeb servicio;
    private Retrofit retrofit;
    private RespuestaWS_Region respuesta;
    private List<Region> regiones;
    private RespuestaWS respuestaByRegion;
    private Spinner spiner;
    private ArrayAdapter<String>adapter;

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
        View root = inflater.inflate(R.layout.fragment_busqueda, container, false);

        spiner= (Spinner) root.findViewById(R.id.spiner_regiones);


        acum_total = root.findViewById(R.id.acumulado_total_region);
        casos_nuev_total = root.findViewById(R.id.casos_nuevos_total_region);
        casos_nuev_csin = root.findViewById(R.id.casos_nuevos_csintomas_region);
        casos_nuev_ssin = root.findViewById(R.id.casos_nuevos_ssintomas_region);
        casos_nuev_snotificar = root.findViewById(R.id.casos_nuevos_snotificar_region);
        fallecidos = root.findViewById(R.id.fallecidos_region);
        casos_activos_confirmados = root.findViewById(R.id.casos_activos_confirmados_region);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://covid.unnamed-chile.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        servicio = retrofit.create(ServicioWeb.class);

        Call<RespuestaWS_Region> respuestaWS_regionCall = servicio.listRegiones();

        respuestaWS_regionCall.enqueue(new Callback<RespuestaWS_Region>() {

            @Override
            public void onResponse(Call<RespuestaWS_Region> call, Response<RespuestaWS_Region> response) {
                respuesta = response.body();
                if(response.isSuccessful()){
                    Log.d("estado", respuesta.getEstado().toString());
                    Log.d("info", respuesta.getInfo());
                    regiones = respuesta.getRegiones();
                    List<String> nombre = new ArrayList<String>();
                    for (int i = 0; i <regiones.size() ; i++) {
                       nombre.add(regiones.get(i).getNombre());
                    }

                    adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_dropdown_item,nombre);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spiner.setAdapter(adapter);

                    spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            Call<RespuestaWS> respuestaByIdRegion = servicio.infoByRegion("data/"+ (spiner.getSelectedItemPosition()+1));
                            respuestaByIdRegion.enqueue(new Callback<RespuestaWS>() {
                                @Override
                                public void onResponse(Call<RespuestaWS> call, Response<RespuestaWS> response) {

                                    respuestaByRegion = response.body();
                                    if(response.isSuccessful()){
                                        Log.d("info", respuestaByRegion.getEstado().toString());
                                        Log.d("info", respuestaByRegion.getInfo());

                                        acum_total.setText(String.valueOf(respuestaByRegion.getReporte().getAcumulado_total()));
                                        casos_nuev_total.setText(String.valueOf(respuestaByRegion.getReporte().getCasos_nuevos()));
                                        casos_nuev_csin.setText(String.valueOf(respuestaByRegion.getReporte().getCasos_nuevos_csin()));
                                        casos_nuev_ssin.setText(String.valueOf(respuestaByRegion.getReporte().getCasos_nuevos_sin_sin()));
                                        casos_nuev_snotificar.setText(String.valueOf(respuestaByRegion.getReporte().getCasos_nuevos_snotificar()));
                                        fallecidos.setText(String.valueOf(respuestaByRegion.getReporte().getFallecidos()));
                                        casos_activos_confirmados.setText(String.valueOf(respuestaByRegion.getReporte().getCasos_activos_confirmados()));
                                    }
                                }

                                @Override
                                public void onFailure(Call<RespuestaWS> call, Throwable t) {

                                }
                            });

                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                }
            }
            @Override
            public void onFailure(Call<RespuestaWS_Region> call, Throwable t) {
                Log.d("Servicio_error", t.getMessage());
            }
        });

        return root;
    }

}
