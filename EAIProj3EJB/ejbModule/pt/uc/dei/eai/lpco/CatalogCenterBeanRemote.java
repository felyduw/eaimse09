package pt.uc.dei.eai.lpco;
import java.util.List;

import javax.ejb.Remote;

import pt.uc.dei.eai.common.Camera;

@Remote
public interface CatalogCenterBeanRemote {
	public List<Camera> searchCameras(String searchTerms);
	public Camera getCamera(Integer cameraId);
	public List<Camera> addCamerasToDB(String searchTerms);
}
