package com.example.covidstatics;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Reporte {

    private String region;
    @SerializedName("acumulado_total")
    private int acumulado_total;
    @SerializedName("casos_nuevos_total")
    private int casos_nuevos;
    @SerializedName("casos_nuevos_csintomas")
    private int casos_nuevos_csin;
    @SerializedName("casos_nuevos_ssintomas")
    private int casos_nuevos_sin_sin;
    @SerializedName("casos_nuevos_snotificar")
    private int casos_nuevos_snotificar;
    private int fallecidos;
    private int casos_activos_confirmados;

    public Reporte() {
    }

    public Reporte(String region, int acumulado_total, int casos_nuevos, int casos_nuevos_csin, int casos_nuevos_sin_sin, int casos_nuevos_snotificar, int fallecidos, int casos_activos_confirmados) {
        this.region = region;
        this.acumulado_total = acumulado_total;
        this.casos_nuevos = casos_nuevos;
        this.casos_nuevos_csin = casos_nuevos_csin;
        this.casos_nuevos_sin_sin = casos_nuevos_sin_sin;
        this.casos_nuevos_snotificar = casos_nuevos_snotificar;
        this.fallecidos = fallecidos;
        this.casos_activos_confirmados = casos_activos_confirmados;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getAcumulado_total() {
        return acumulado_total;
    }

    public void setAcumulado_total(int acumulado_total) {
        this.acumulado_total = acumulado_total;
    }

    public int getCasos_nuevos() {
        return casos_nuevos;
    }

    public void setCasos_nuevos(int casos_nuevos) {
        this.casos_nuevos = casos_nuevos;
    }

    public int getCasos_nuevos_csin() {
        return casos_nuevos_csin;
    }

    public void setCasos_nuevos_csin(int casos_nuevos_csin) {
        this.casos_nuevos_csin = casos_nuevos_csin;
    }

    public int getCasos_nuevos_sin_sin() {
        return casos_nuevos_sin_sin;
    }

    public void setCasos_nuevos_sin_sin(int casos_nuevos_sin_sin) {
        this.casos_nuevos_sin_sin = casos_nuevos_sin_sin;
    }

    public int getCasos_nuevos_snotificar() {
        return casos_nuevos_snotificar;
    }

    public void setCasos_nuevos_snotificar(int casos_nuevos_snotificar) {
        this.casos_nuevos_snotificar = casos_nuevos_snotificar;
    }

    public int getFallecidos() {
        return fallecidos;
    }

    public void setFallecidos(int fallecidos) {
        this.fallecidos = fallecidos;
    }

    public int getCasos_activos_confirmados() {
        return casos_activos_confirmados;
    }

    public void setCasos_activos_confirmados(int casos_activos_confirmados) {
        this.casos_activos_confirmados = casos_activos_confirmados;
    }


    @Override
    public String toString() {
        return "Reporte{" +
                "region='" + region + '\'' +
                ", acumulado_total=" + acumulado_total +
                ", casos_nuevos=" + casos_nuevos +
                ", casos_nuevos_csin=" + casos_nuevos_csin +
                ", casos_nuevos_sin_sin=" + casos_nuevos_sin_sin +
                ", casos_nuevos_snotificar=" + casos_nuevos_snotificar +
                ", fallecidos=" + fallecidos +
                ", casos_activos_confirmados=" + casos_activos_confirmados +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reporte reporte = (Reporte) o;
        return acumulado_total == reporte.acumulado_total &&
                casos_nuevos == reporte.casos_nuevos &&
                casos_nuevos_csin == reporte.casos_nuevos_csin &&
                casos_nuevos_sin_sin == reporte.casos_nuevos_sin_sin &&
                casos_nuevos_snotificar == reporte.casos_nuevos_snotificar &&
                fallecidos == reporte.fallecidos &&
                casos_activos_confirmados == reporte.casos_activos_confirmados &&
                Objects.equals(region, reporte.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, acumulado_total, casos_nuevos, casos_nuevos_csin, casos_nuevos_sin_sin, casos_nuevos_snotificar, fallecidos, casos_activos_confirmados);
    }
}
