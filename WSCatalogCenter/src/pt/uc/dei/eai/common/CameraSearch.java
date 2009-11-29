package pt.uc.dei.eai.common;

import java.io.Serializable;

public class CameraSearch implements Serializable {
	private static final long serialVersionUID = -6228004627121649521L;
	
	public CameraSearch() {}

	String date;
	
	String model;
	
	Float price;
	
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CameraSearch other = (CameraSearch) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	
	
}
