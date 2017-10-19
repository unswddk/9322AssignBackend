package com.badbanana.poll.api.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public final class AddressDTO {
    private final String preStreet;
    private final String streetName;
    private final String streetType;
    private final String suburb;
    private final String state;



    public AddressDTO(@JsonProperty("preStreet") String preStreet,
                      @JsonProperty("streetName") String streetName,
                      @JsonProperty("streetType") String streetType,
                      @JsonProperty("suburb") String suburb,
                      @JsonProperty("state") String state

    ) {
        this.preStreet=preStreet;
        this.state=state;
        this.streetName=streetName;
        this.suburb=suburb;
        this.streetType=streetType;
    }

    public String getPreStreet() {
        return preStreet;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetType() {
        return streetType;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getState() {
        return state;
    }
}

