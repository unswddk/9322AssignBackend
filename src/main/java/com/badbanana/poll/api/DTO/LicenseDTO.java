package com.badbanana.poll.api.DTO;


import com.badbanana.poll.api.model.Notice;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public final class LicenseDTO {
    private final String email;
    private final String licenceClass;
    private final String name;
    private final String number;
    private final String address;
    private final Date expiryDate;
    private final Boolean noticed;
    private final Notice notice;


    public LicenseDTO(@JsonProperty("email") String email,
                      @JsonProperty("licenceClass") String licenceClass,
                      @JsonProperty("name") String name,
                      @JsonProperty("number") String number,
                      @JsonProperty("address") String address,
                      @JsonProperty("expiryDate") Date expiryDate,
                      @JsonProperty("noticed") Boolean noticed,
                      @JsonProperty("notice") Notice notice
    ) {
        this.email = email;
        this.licenceClass = licenceClass;
        this.name = name;
        this.number = number;
        this.address = address;
        this.expiryDate = expiryDate;
        this.noticed = noticed;
        this.notice = notice;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getLicenceClass() {
        return licenceClass;
    }

    public Boolean getNoticed() {
        return noticed;
    }

    public Notice getNotice() {
        return notice;
    }
}