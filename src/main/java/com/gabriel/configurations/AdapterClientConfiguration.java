package com.gabriel.configurations;

import com.gabriel.tafMetarAdapter.TafMetarClient;
import com.gabriel.tafMetarAdapter.TafMetarClientImpl;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

@Factory
public class AdapterClientConfiguration {

    @Bean
    public TafMetarClient tafMetarClient() {
        return new TafMetarClientImpl();
    }
}
