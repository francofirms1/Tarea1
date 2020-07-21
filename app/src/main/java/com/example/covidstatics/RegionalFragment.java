package com.example.covidstatics;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegionalFragment extends Fragment {

    private ServicioWeb servicio;
    private Retrofit retrofit;
    private RespuestaWS_Regional regional;
    private TextView fecha;
    private HorizontalBarChart grafico;
    private ReporteRegionalViewModel reporte;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root =inflater.inflate(R.layout.fragment_regional, container, false);
        fecha= root.findViewById(R.id.fecha_regional);
        reporte = ViewModelProviders.of(getActivity()).get(ReporteRegionalViewModel.class);
        grafico=root.findViewById(R.id.chart);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://covid.unnamed-chile.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reporte.obtenerReporte().observe(getActivity(), new Observer<List<Reporte>>() {
            @Override
            public void onChanged(List<Reporte> reportes) {
                Log.d("tag",reportes.toString());
                if(reportes.size()>0){
                    ArrayList<BarEntry> entries = new ArrayList<>();
                    ArrayList<String> regiones= new ArrayList<>();

                    for(int i=0; i<reportes.size();i++){
                        BarEntry bar=new BarEntry(i,reportes.get(i).getAcumulado_total());
                        entries.add(bar);
                        regiones.add(reportes.get(i).getRegion());
                    }

                    ArrayList<Integer> colors=new ArrayList<>();
                    for(int c: ColorTemplate.JOYFUL_COLORS)
                        colors.add(c);

                    BarDataSet dataset= new BarDataSet(entries,"Reporte Regiones");
                    dataset.setColors(colors);
                    BarData data = new BarData(dataset);
                    XAxis xasis = grafico.getXAxis();
                    xasis.setPosition(XAxis.XAxisPosition.BOTTOM);
                    IndexAxisValueFormatter formatter= new IndexAxisValueFormatter(regiones);
                    //xasis.setGranularity(1f);
                    xasis.setTextSize(12);
                    xasis.setValueFormatter(formatter);
                    xasis.setDrawLabels(true);
                    data.setValueTextSize(14);

                    grafico.setData(data);
                    //grafico.setFitBars(true);
                    grafico.animateXY(5000,5000);
                    grafico.invalidate();
                }
            }
        });
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
                    reporte.actualizarReporte(regional.getReporte());



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
