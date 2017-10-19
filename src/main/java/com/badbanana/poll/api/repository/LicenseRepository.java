package com.badbanana.poll.api.repository;

import com.badbanana.poll.api.model.License;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LicenseRepository extends JpaRepository<License, Long> {
    List<License> findByLicenseClassContaining(String LicenseClass);

    List<License> findByNameContaining(String name);

    List<License> findAll();
    License findLicenseByNumber(String number);
    License findLicenseById(Long id);

    License findAllByNumber(String number);
}
