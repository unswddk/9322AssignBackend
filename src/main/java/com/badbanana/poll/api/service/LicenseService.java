package com.badbanana.poll.api.service;

import com.badbanana.poll.api.DTO.LicenseDTO;
import com.badbanana.poll.api.model.License;

import java.util.List;

public interface LicenseService {

    License createLicense(License license);


    License findLicenseByNumber(String number);

    License addLicense(License license);

    License findLicenseById(Long id);

    List<License> findLicenseByName(String name);



    List<License> findAllLicense();
    License  save(License license);
}
