package pt.uc.dei.eai.common;

import java.io.Serializable;

public class Camera implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	String model;
	String date;
	Float price;
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
}
