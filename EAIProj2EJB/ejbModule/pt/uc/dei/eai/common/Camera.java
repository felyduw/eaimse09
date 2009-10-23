package pt.uc.dei.eai.common;

import java.io.Serializable;

public class Camera implements Serializable {
	
	private static final long serialVersionUID = -6228004627121649521L;

	Integer cameraId;
	
	String model;
	
	Float price;
	
	public Integer getCameraId() {
		return cameraId;
	}
	public void setCameraId(Integer cameraId) {
		this.cameraId = cameraId;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	//Characteristics
	
}
