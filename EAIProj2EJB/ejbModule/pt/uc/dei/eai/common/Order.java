package pt.uc.dei.eai.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
	
	//For Hibernate
	public Order() {}
	
	private static final long serialVersionUID = -3074461799615892816L;

	private Integer id;
	
	private String username;
	
	private String shippingAddress;
	
	private List<Camera> orderedCameras;
	
	private Date purchaseDate;
	
	private OrderStatus orderStatus;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
