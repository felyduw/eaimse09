package pt.uc.dei.eai.lpco;

import java.rmi.RemoteException;

public class LPCOProxy implements pt.uc.dei.eai.lpco.LPCO {
  private String _endpoint = null;
  private pt.uc.dei.eai.lpco.LPCO lPCO = null;
  
  public LPCOProxy() {
    _initLPCOProxy();
  }
  
  public LPCOProxy(String endpoint) {
    _endpoint = endpoint;
    _initLPCOProxy();
  }
  
  private void _initLPCOProxy() {
    try {
      lPCO = (new pt.uc.dei.eai.lpco.LPCOServiceLocator()).getLPCOPort();
      if (lPCO != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)lPCO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)lPCO)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (lPCO != null)
      ((javax.xml.rpc.Stub)lPCO)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public pt.uc.dei.eai.lpco.LPCO getLPCO() {
    if (lPCO == null)
      _initLPCOProxy();
    return lPCO;
  }
  
  public boolean shipped(java.lang.Integer orderId, java.lang.String shippedDates) throws RemoteException{
    if (lPCO == null)
      _initLPCOProxy();
    return lPCO.shipped(orderId, shippedDates);
  }
  
  
}