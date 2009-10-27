package pt.uc.dei.eai.cs;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import pt.uc.dei.eai.common.Camera;

@WebService
public class CameraSupplier {
	@WebMethod
	public List<Camera> getCameras(String name) {
		return null;
	}
}
