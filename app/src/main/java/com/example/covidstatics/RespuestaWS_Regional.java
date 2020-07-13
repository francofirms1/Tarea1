package com.example.covidstatics;

import java.util.List;

public class RespuestaWS_Regional {

    private String info;
    private String fecha;
    private Boolean estado;
    private List<Reporte> reporte;

    public RespuestaWS_Regional() {
    }

    public RespuestaWS_Regional(String info, String fecha, Boolean estado, List<Reporte> reporte) {
        this.info = info;
        this.fecha = fecha;
        this.estado = estado;
        this.reporte = reporte;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Reporte> getReporte() {
        return reporte;
    }

    public void setReporte(List<Reporte> reporte) {
        this.reporte = reporte;
    }
}
