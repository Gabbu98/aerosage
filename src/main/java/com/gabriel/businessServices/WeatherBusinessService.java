package com.gabriel.businessServices;

import com.gabriel.controllers.models.Metar;
import com.gabriel.controllers.models.Taf;
import com.gabriel.controllers.models.data.MetaData;
import com.gabriel.exceptions.ValidationException;
import com.gabriel.tafMetarAdapter.TafMetarClient;
import io.micronaut.core.util.StringUtils;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class WeatherBusinessService {

    private TafMetarClient tafMetarClient;

    public WeatherBusinessService(TafMetarClient tafMetarClient) {
        this.tafMetarClient = tafMetarClient;
    }

    public Metar fetchMetar(final String icao) {
        checkIcaoCode(icao);
        final String metar = tafMetarClient.getMetar(icao);
        return mapToMetar(metar);
    }

    public Taf fetchTaf(final String icao) {
        checkIcaoCode(icao);
        final String taf = tafMetarClient.getTaf(icao);
        return mapToTaf(taf);
    }

    private void checkIcaoCode(final String icao) {
        if (!StringUtils.hasText(icao)) {
            throw new IllegalArgumentException(String.format("Invalid ICAO code [%s]", icao));
        }
    }

    private Metar mapToMetar(final String metar) {
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(metar);

        if (matcher.find()) {
            String icao = matcher.group();
            return new Metar().setIcao(icao).setDetails(metar).setMetaData(new MetaData("v0"));
        } else {
            throw new ValidationException("Invalid Mathcer.", List.of(String.format("Does not match pattern %s", matcher.find())));
        }
    }

    private Taf mapToTaf(final String metar) {
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(metar);

        if (matcher.find()) {
            String icao = matcher.group();
            return new Taf().setIcao(icao).setDetails(metar).setMetaData(new MetaData("v0"));
        } else {
            throw new ValidationException("Invalid Mathcer.", List.of(String.format("Does not match pattern %s", matcher.find())));
        }
    }

}
