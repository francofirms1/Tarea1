package com.example.covidstatics;

import java.util.List;
import java.util.Objects;

public class RespuestaWS_Region {

    private String info;
    private Boolean estado;

    private List<Region> regiones;

    public RespuestaWS_Region() {

    }

    public RespuestaWS_Region(String info, Boolean estado, List<Region> regiones) {
        this.info = info;
        this.estado = estado;
        this.regiones = regiones;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Region> getRegiones() {
        return regiones;
    }

    public void setRegiones(List<Region> regiones) {
        this.regiones = regiones;
    }

    @Override
    public String toString() {
        return "RespuestaWS_Region{" +
                "info='" + info + '\'' +
                ", estado=" + estado +
                ", region=" + regiones +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RespuestaWS_Region that = (RespuestaWS_Region) o;
        return Objects.equals(info, that.info) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(regiones, that.regiones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(info, estado, regiones);
    }
}
