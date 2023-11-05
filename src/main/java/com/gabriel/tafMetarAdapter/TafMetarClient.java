package com.gabriel.tafMetarAdapter;

public interface TafMetarClient {

    public String getMetar(final String icaoCode);

    public String getTaf(final String icaoCode);
}
