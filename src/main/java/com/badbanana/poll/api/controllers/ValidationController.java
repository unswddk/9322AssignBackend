package com.badbanana.poll.api.controllers;

import com.badbanana.poll.api.*;
import com.badbanana.poll.api.DTO.AddressDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/api/validate")
public class ValidationController {
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> checkEmail(@RequestParam(value = "email", required = true) String email,
                                        HttpServletRequest httpServletRequest) {
        EmployeeValidationServiceImplService service = new EmployeeValidationServiceImplService();
        EmployeeValidationService port = service.getEmployeeValidationServiceImplPort();
        ObjectFactory objectFactory = new ObjectFactory();
        CheckEmailAddressRequest checkEmailAddressRequest = objectFactory.createCheckEmailAddressRequest();
        checkEmailAddressRequest.setEmail(email);
        CheckEmailAddressResponse checkEmailAddressResponse=port.checkEmailAddress(checkEmailAddressRequest);
        return new ResponseEntity<>(checkEmailAddressResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> checkAddress(@RequestBody AddressDTO addressDTO){
        EmployeeValidationServiceImplService service = new EmployeeValidationServiceImplService();
        EmployeeValidationService port = service.getEmployeeValidationServiceImplPort();
        ObjectFactory objectFactory = new ObjectFactory();
        CheckAddressRequest checkAddressRequest = objectFactory.createCheckAddressRequest();
        checkAddressRequest.setState(addressDTO.getState());
        checkAddressRequest.setSuburb(addressDTO.getSuburb());
        checkAddressRequest.setStreetType(addressDTO.getStreetType());
        checkAddressRequest.setStreetName(addressDTO.getStreetName());
        checkAddressRequest.setPreStreet(addressDTO.getPreStreet());
        try {
            CheckAddressResponse checkAddress = port.checkAddress(checkAddressRequest);
            return new ResponseEntity<>(checkAddress, HttpStatus.OK);
        }catch (ValidationFaultMsg validationFaultMsg){
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
