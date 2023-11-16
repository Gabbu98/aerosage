package com.gabriel.tafMetarAdapter;

import com.gabriel.exceptions.InvalidRequestException;
import com.gabriel.exceptions.NotFoundException;
import io.micronaut.core.util.StringUtils;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Singleton
public class TafMetarClientImpl implements TafMetarClient {

    private final HttpClient client;

    public TafMetarClientImpl() {
        this.client = HttpClient.newHttpClient();
    }

    @Override
    public String getMetar(final String icaoCode) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.met.no/weatherapi/tafmetar/1.0/metar?icao=".concat(icaoCode)))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            List<String> result = splitStringByLine(response.body());
            if (result.isEmpty()) {
                throw new NotFoundException("Metar is currently not available.");
            }

            if (response.statusCode() == 400) {
                throw new InvalidRequestException(String.format("Invalid ICAO code: %s", icaoCode));
            }

            return result.get(result.size() - 1);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTaf(final String icaoCode) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.met.no/weatherapi/tafmetar/1.0/taf?icao=".concat(icaoCode)))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<String> result = splitStringByLine(response.body());
            if (result.isEmpty()) {
                throw new NotFoundException("Taf is currently not available.");
            }

            if (response.statusCode()==400){
                throw new InvalidRequestException(String.format("Invalid ICAO code: %s", icaoCode));
            }
            return result.get(result.size() - 1);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> splitStringByLine(String inputString) {
        Pattern p = Pattern.compile("[\\r\\n]+");
        List<String> result = Arrays.asList(p.split(inputString.replace("=","")));
        return result.stream().filter(StringUtils::hasText).collect(Collectors.toList());
    }
}
