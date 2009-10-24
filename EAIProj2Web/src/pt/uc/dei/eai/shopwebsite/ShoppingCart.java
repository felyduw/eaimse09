/**
 * 
 */
package pt.uc.dei.eai.shopwebsite;

import java.util.ArrayList;
import java.util.List;
import pt.uc.dei.eai.common.Camera;

/**
 * This object holds the current shopping cart of the customer.
 */
public class ShoppingCart {
	
	private List<Camera> cameras = new ArrayList<Camera>();
	
	public List<Camera> getCameras() {
		return cameras;
	}

	public void setCameras(List<Camera> cameras) {
		this.cameras = cameras;
	}

	/**
	 * Gets the total of all the camera prices in the cart.
	 * @return Total price.
	 */
	public float getTotalAmount() {
		float totalAmount = 0;
		for (int i = 0; i < cameras.size(); i++) {
			Camera currentCamera = cameras.get(i);
			if (currentCamera != null) {
				totalAmount += cameras.get(i).getPrice();
			}
		}
		return totalAmount;
	}
	
}
