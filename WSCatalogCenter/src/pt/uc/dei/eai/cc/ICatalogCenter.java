package pt.uc.dei.eai.cc;
import java.util.List;

import pt.uc.dei.eai.common.Camera;

public interface ICatalogCenter {
	public List<Camera> searchCameras(String searchTerms);
	public Camera getCamera(Integer cameraId);
	public List<Camera> addCamerasToDB(List<Camera> cameras);
}
