/**
 * LPCOServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pt.uc.dei.eai.lpco;

public class LPCOServiceLocator extends org.apache.axis.client.Service implements pt.uc.dei.eai.lpco.LPCOService {

    public LPCOServiceLocator() {
    }


    public LPCOServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LPCOServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LPCOPort
    private java.lang.String LPCOPort_address = "http://127.0.0.1:8080/WSLPCO";

    public java.lang.String getLPCOPortAddress() {
        return LPCOPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LPCOPortWSDDServiceName = "LPCOPort";

    public java.lang.String getLPCOPortWSDDServiceName() {
        return LPCOPortWSDDServiceName;
    }

    public void setLPCOPortWSDDServiceName(java.lang.String name) {
        LPCOPortWSDDServiceName = name;
    }

    public pt.uc.dei.eai.lpco.LPCO getLPCOPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LPCOPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLPCOPort(endpoint);
    }

    public pt.uc.dei.eai.lpco.LPCO getLPCOPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            pt.uc.dei.eai.lpco.LPCOBindingStub _stub = new pt.uc.dei.eai.lpco.LPCOBindingStub(portAddress, this);
            _stub.setPortName(getLPCOPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLPCOPortEndpointAddress(java.lang.String address) {
        LPCOPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (pt.uc.dei.eai.lpco.LPCO.class.isAssignableFrom(serviceEndpointInterface)) {
                pt.uc.dei.eai.lpco.LPCOBindingStub _stub = new pt.uc.dei.eai.lpco.LPCOBindingStub(new java.net.URL(LPCOPort_address), this);
                _stub.setPortName(getLPCOPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("LPCOPort".equals(inputPortName)) {
            return getLPCOPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://lpco.eai.dei.uc.pt/", "LPCOService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://lpco.eai.dei.uc.pt/", "LPCOPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LPCOPort".equals(portName)) {
            setLPCOPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
