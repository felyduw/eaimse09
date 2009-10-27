package pt.uc.dei.eai.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer orderId;
	
	private String username;
	
	private String shippingAddress;
	
	private List<Camera> orderedCameras;
	
	private Date purchaseDate;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public List<Camera> getOrderedCameras() {
		return orderedCameras;
	}
	public void setOrderedCameras(List<Camera> orderedCameras) {
		this.orderedCameras = orderedCameras;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
