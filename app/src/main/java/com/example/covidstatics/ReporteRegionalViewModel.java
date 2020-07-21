package com.example.covidstatics;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReporteRegionalViewModel extends ViewModel {
    private MutableLiveData<List<Reporte>> reporte = new MutableLiveData<>();
    public ReporteRegionalViewModel(){
        reporte.postValue(new ArrayList<>());
    }
    public LiveData<List<Reporte>> obtenerReporte(){
        return reporte;
    }
    public void actualizarReporte(List<Reporte> r){
        reporte.setValue(r);
    }

}
