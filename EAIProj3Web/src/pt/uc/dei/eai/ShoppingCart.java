package pt.uc.dei.eai;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.xml.schema.cameraresponse.*;

/**
 * This object holds the current shopping cart of the customer.
 */
public class ShoppingCart {
	
	private List<CameraInfo> cameras = new ArrayList<CameraInfo>();

    /**
     * Adds a new camera to the shopping cart.
     * @param camera    The new camera to add.
     */
	public void addCamera(CameraInfo camera) {
		this.cameras.add(camera);
	}
    
    /**
     * Gets the number of cameras in the shopping cart.
     * @return The number of cameras in the shopping cart.
     */
	public int getNumberCameras() {
		return cameras.size();
	}

	/**
	 * Gets the total of all the camera prices in the cart.
	 * @return Total price.
	 */
	public float getTotalAmount() {
		float totalAmount = 0;
		for (int i = 0; i < cameras.size(); i++) {
			CameraInfo currentCamera = cameras.get(i);
			if (currentCamera != null) {
				totalAmount += currentCamera.getPrice();
			}
		}
		return totalAmount;
	}

    public Integer getId(int index) {
        if (index < getNumberCameras()) {
            CameraInfo cameraInfo = cameras.get(index);
            if (cameraInfo != null) {
                return cameraInfo.getId();
            }
        }
        return null;
    }

    public String getModel(int index) {
        if (index < getNumberCameras()) {
            CameraInfo cameraInfo = cameras.get(index);
            if (cameraInfo != null) {
                return cameraInfo.getModel();
            }
        }
        return null;
    }

    public Float getPrice(int index) {
        if (index < getNumberCameras()) {
            CameraInfo cameraInfo = cameras.get(index);
            if (cameraInfo != null) {
                return cameraInfo.getPrice();
            }
        }
        return null;
    }

}
