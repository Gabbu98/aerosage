package com.gabriel.controllers.models;

import com.gabriel.controllers.models.data.MetaData;
import io.micronaut.serde.annotation.Serdeable;

import java.util.Objects;


@Serdeable.Serializable
public class Metar {

    private String icao;
    private String details;
    private MetaData metaData;

    public String getIcao() {
        return icao;
    }

    public Metar setIcao(String icao) {
        this.icao = icao;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public Metar setDetails(String details) {
        this.details = details;
        return this;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public Metar setMetaData(MetaData metaData) {
        this.metaData = metaData;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metar metar = (Metar) o;
        return Objects.equals(icao, metar.icao) && Objects.equals(details, metar.details) && Objects.equals(metaData, metar.metaData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icao, details, metaData);
    }

    @Override
    public String toString() {
        return "Metar{" +
                "icao='" + icao + '\'' +
                ", details='" + details + '\'' +
                ", metaData=" + metaData +
                '}';
    }
}
