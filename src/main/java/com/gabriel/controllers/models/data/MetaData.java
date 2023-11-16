package com.gabriel.controllers.models.data;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable.Serializable
public class MetaData {
    private String version;

    public MetaData(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public MetaData setVersion(String version) {
        this.version = version;
        return this;
    }
}
