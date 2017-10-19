package com.badbanana.poll.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @Column(name = "noticeId")
    @Setter
    @Getter
    private long noticeId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "license_id")
    private License lic;

    public License getLic() {
        return lic;
    }

    public void setLic(License lic) {
        if (same(lic)) {
            return;
        }
        License oldlic = this.lic;
        this.lic = lic;
        if (oldlic != null) {
            oldlic.setNotice(null);
        }
        if (lic != null) {
            lic.setNotice(this);
        }
    }

    private boolean same(License newLic) {
        if (lic == null)
            return newLic == null;
        return lic.equals(newLic);
    }


    @Setter
    @Getter
    @ManyToOne
    @JsonIgnore
    private User user;


    @Column(name = "address", length = 2048)
    @Setter
    @Getter
    private String address;

    @Column(name = "licenseNumber")
    @Setter
    @Getter
    private String licenseNumber;

    @Column(name = "token")
    @Setter
    @Getter
    private String token;

    @Column(name = "location")
    @Setter
    @Getter
    private String location;


    @Column(name = "payDate")
    @Setter
    @Getter
    private Date payDate;

    @Column(name = "rejectionReason", length = 2048)
    @Setter
    @Getter
    private String rejectionReason;

    @Column(name = "amount")
    @Setter
    @Getter
    private String amount;

    @Column(name = "email")
    @Setter
    @Getter
    private String email;


    @Column(name = "status")
    @Setter
    @Getter
    private String status;


    @Override
    public String toString() {
        return "Notice{" +
                "noticeId=" + noticeId +
                ", lic=" + lic +
                ", user=" + user +
                ", address='" + address + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", token='" + token + '\'' +
                ", location='" + location + '\'' +
                ", payDate=" + payDate +
                ", rejectReason='" + rejectionReason + '\'' +
                ", amount='" + amount + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
