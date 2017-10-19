package com.badbanana.poll.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Table(name = "license", uniqueConstraints = @UniqueConstraint(columnNames = {"number"}))
public class License {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @NotNull
    @Setter
    @Getter
    private String email;

    @NotNull
    @Column(name = "address", length = 1700)
    @Setter
    @Getter

    private String address;

    @NotNull
    @Setter
    @Getter
    private String licenseClass;

    @NotNull
    @Column(name = "expiryDate", length = 170)
    @Setter
    @Getter
    private Date expiryDate;

    @NotNull
    @Setter
    @Getter
    private String name;


    @NotNull
    @Setter
    @Getter
    private String number;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "lic")
    @JsonIgnore
    private Notice notice;


    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        if(same(notice))
            return;
        Notice oldNotice = this.notice;
        this.notice = notice;
        if(oldNotice != null)
            oldNotice.setLic(null);
        if(notice != null)
            notice.setLic(this);
    }
    private boolean same(Notice newNotice){
        return notice == null ? newNotice == null: notice.equals(newNotice);
    }


    @Setter
    @Getter
    private Boolean noticed;

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", licenseClass='" + licenseClass + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", noticed='" + noticed + '\'' +
                '}';
    }
}