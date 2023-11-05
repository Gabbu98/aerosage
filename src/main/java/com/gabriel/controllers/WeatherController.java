package com.gabriel.controllers;

import com.gabriel.exceptions.InvalidRequestException;
import com.gabriel.exceptions.NotFoundException;
import com.gabriel.tafMetarAdapter.TafMetarClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

@Controller
public class WeatherController {

    private final TafMetarClient tafMetarClient;

    public WeatherController(final TafMetarClient tafMetarClient) {
        this.tafMetarClient = tafMetarClient;
    }

    @Get(value = "/metars/{icao}")
    public HttpResponse<String> getMetar(final String icao) {
        try {
            final String metar = tafMetarClient.getMetar(icao);
            return HttpResponse.ok(metar);
        } catch (InvalidRequestException e) {
            return HttpResponse.badRequest(e.getMessage());
        } catch (NotFoundException e) {
            return HttpResponse.notFound(e.getMessage());
        }
    }

    @Get(value = "/tafs/{icao}")
    public HttpResponse<String> getTaf(final String icao) {
        try {
            final String metar = tafMetarClient.getTaf(icao);
            return HttpResponse.ok(metar);
        } catch (InvalidRequestException e) {
            return HttpResponse.badRequest(e.getMessage());
        } catch (NotFoundException e) {
            return HttpResponse.notFound(e.getMessage());
        }
    }

}
