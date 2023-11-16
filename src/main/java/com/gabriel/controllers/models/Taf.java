package com.gabriel.controllers.models;

import com.gabriel.controllers.models.data.MetaData;
import io.micronaut.serde.annotation.Serdeable;

import java.util.Objects;

@Serdeable.Serializable
public class Taf {

    private String icao;
    private String details;
    private MetaData metaData;

    public String getIcao() {
        return icao;
    }

    public Taf setIcao(String icao) {
        this.icao = icao;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public Taf setDetails(String details) {
        this.details = details;
        return this;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public Taf setMetaData(MetaData metaData) {
        this.metaData = metaData;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taf taf = (Taf) o;
        return Objects.equals(icao, taf.icao) && Objects.equals(details, taf.details) && Objects.equals(metaData, taf.metaData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(icao, details, metaData);
    }

    @Override
    public String toString() {
        return "Taf{" +
                "icao='" + icao + '\'' +
                ", details='" + details + '\'' +
                ", metaData=" + metaData +
                '}';
    }
}
