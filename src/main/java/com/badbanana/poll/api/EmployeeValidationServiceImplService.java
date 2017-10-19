
package com.badbanana.poll.api;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EmployeeValidationServiceImplService", targetNamespace = "http://assignment1.comp9322/", wsdlLocation = "http://192.168.99.100:8888/assignment1/EmployeeValidation?wsdl")
public class EmployeeValidationServiceImplService
    extends Service
{

    private final static URL EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_QNAME = new QName("http://assignment1.comp9322/", "EmployeeValidationServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.99.100:8888/assignment1/EmployeeValidation?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public EmployeeValidationServiceImplService() {
        super(__getWsdlLocation(), EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_QNAME);
    }

    public EmployeeValidationServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_QNAME, features);
    }

    public EmployeeValidationServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_QNAME);
    }

    public EmployeeValidationServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_QNAME, features);
    }

    public EmployeeValidationServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EmployeeValidationServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EmployeeValidationService
     */
    @WebEndpoint(name = "EmployeeValidationServiceImplPort")
    public EmployeeValidationService getEmployeeValidationServiceImplPort() {
        return super.getPort(new QName("http://assignment1.comp9322/", "EmployeeValidationServiceImplPort"), EmployeeValidationService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EmployeeValidationService
     */
    @WebEndpoint(name = "EmployeeValidationServiceImplPort")
    public EmployeeValidationService getEmployeeValidationServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://assignment1.comp9322/", "EmployeeValidationServiceImplPort"), EmployeeValidationService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_EXCEPTION;
        }
        return EMPLOYEEVALIDATIONSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}