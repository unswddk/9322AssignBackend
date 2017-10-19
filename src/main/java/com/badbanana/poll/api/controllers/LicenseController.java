package com.badbanana.poll.api.controllers;

import com.badbanana.poll.api.model.License;
import com.badbanana.poll.api.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;


@RestController
@RequestMapping("/api/license")
public class LicenseController {
    private final LicenseService licenseService;

    @Autowired
    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllLicense(@RequestParam(value = "searchText", required = false) String searchText,
                                           HttpServletRequest httpServletRequest) {

        Enumeration<String> headers = httpServletRequest.getHeaderNames();
        while (headers.hasMoreElements()) {
            String key = headers.nextElement();
            System.out.println(key + " | " + httpServletRequest.getHeader(key));
        }

        List<License> licenses;
        if (searchText != null && !searchText.isEmpty()) {
            licenses = licenseService.findLicenseByName(searchText);
        } else {
            licenses = licenseService.findAllLicense();
        }
        return new ResponseEntity<>(licenses, HttpStatus.OK);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public ResponseEntity<?> getLicenseById(@PathVariable Long id) {
//        License license = licenseService.findLicenseById(id);
//        System.out.println(license);
//        return new ResponseEntity<>(license, HttpStatus.OK);
//    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateLicense(@RequestBody License license) {
        java.sql.Date sql = new java.sql.Date(license.getExpiryDate().getTime());
        license.setExpiryDate(sql);
        licenseService.save(license);
        return new ResponseEntity<>(license, HttpStatus.OK);
    }


    @RequestMapping(value = "/{licenseNumber}", method = RequestMethod.GET)
    public ResponseEntity<?> getLicenseByLicenseNumber(@PathVariable String licenseNumber) {

        License license = licenseService.findLicenseByNumber(licenseNumber);

        return new ResponseEntity<>(license, HttpStatus.OK);
    }
}
