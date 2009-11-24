/**
 * LPCOService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package pt.uc.dei.eai.lpco;

public interface LPCOService extends javax.xml.rpc.Service {
    public java.lang.String getLPCOPortAddress();

    public pt.uc.dei.eai.lpco.LPCO getLPCOPort() throws javax.xml.rpc.ServiceException;

    public pt.uc.dei.eai.lpco.LPCO getLPCOPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
