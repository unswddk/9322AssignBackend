package com.badbanana.poll.api.service;

import com.badbanana.poll.api.DTO.LicenseDTO;
import com.badbanana.poll.api.model.License;

import com.badbanana.poll.api.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository licenseRepository;

    @Autowired
    public LicenseServiceImpl(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    public License findLicenseByNumber(String number) {
        return licenseRepository.findLicenseByNumber(number);
    }

    @Override
    public License createLicense(License license) {
        return licenseRepository.save(license);
    }

    @Override
    public License addLicense(License license) {
        return licenseRepository.save(license);
    }

    @Override
    public List<License> findLicenseByName(String name) {
        return this.licenseRepository.findByNameContaining(name);
    }

    @Override
    public List<License> findAllLicense() {
        return this.licenseRepository.findAll();
    }

    @Override
    public License findLicenseById(Long id) {
        return licenseRepository.findLicenseById(id);
    }

    @Override
    public License save(License license) {
        return licenseRepository.save(license);
    }

}
