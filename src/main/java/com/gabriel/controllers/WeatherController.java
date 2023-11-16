package com.gabriel.controllers;

import com.gabriel.businessServices.WeatherBusinessService;
import com.gabriel.controllers.models.Metar;
import com.gabriel.controllers.models.Taf;
import com.gabriel.exceptions.InvalidRequestException;
import com.gabriel.exceptions.NotFoundException;
import com.gabriel.tafMetarAdapter.TafMetarClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

@Controller
public class WeatherController {

    private final TafMetarClient tafMetarClient;
    private final WeatherBusinessService weatherBusinessService;

    public WeatherController(TafMetarClient tafMetarClient, WeatherBusinessService weatherBusinessService) {
        this.tafMetarClient = tafMetarClient;
        this.weatherBusinessService = weatherBusinessService;
    }

    @Get(value = "/metars/{icao}")
    public HttpResponse<Metar> getMetarsByIcao(final String icao) {
        final Metar metar = weatherBusinessService.fetchMetar(icao);
        return HttpResponse.ok(metar).contentType(MediaType.APPLICATION_JSON_TYPE);
    }

    @Get(value = "/tafs/{icao}")
    public HttpResponse<Taf> getTafsByIcao(final String icao) {
        final Taf taf = weatherBusinessService.fetchTaf(icao);
        return HttpResponse.ok(taf).contentType(MediaType.APPLICATION_JSON_TYPE);
    }

}
