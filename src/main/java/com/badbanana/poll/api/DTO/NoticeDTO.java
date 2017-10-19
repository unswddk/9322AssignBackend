package com.badbanana.poll.api.DTO;

import com.badbanana.poll.api.model.License;
import com.badbanana.poll.api.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;


public final class NoticeDTO {
    private final License license;
    private final String contact_mail;
    private final String address;
    private final String status;
    private final User user;
    private final String amount;
    private final String rejectReason;
    private final Date payDate;

    public License getLicense() {
        return license;
    }

    public String getContact_mail() {
        return contact_mail;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public String getReason() {
        return rejectReason;
    }

    public Date getPayDate() {
        return payDate;
    }

    public NoticeDTO(@JsonProperty("license") License license,
                     @JsonProperty("contact_mail") String contact_mail,
                     @JsonProperty("address") String address,
                     @JsonProperty("status") String status,
                     @JsonProperty("user") User user,
                     @JsonProperty("rejectReason") String rejectReason,
                     @JsonProperty("amount") String amount,
                     @JsonProperty("payDate") Date payDate
    ) {

        this.license = license;
        this.contact_mail = contact_mail;
        this.address = address;
        this.status = status;
        this.user = user;
        this.rejectReason = rejectReason;
        this.amount = amount;
        this.payDate=payDate;
    }

    public String getAmount() {
        return amount;
    }
}