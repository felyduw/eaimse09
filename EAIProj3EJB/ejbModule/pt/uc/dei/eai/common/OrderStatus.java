package pt.uc.dei.eai.common;

public enum OrderStatus {
	WAITING_FOR_SHIPPING,	//Ordered, not yet shipped
	SHIPPED,				//Ordered, shipped
	NOT_PAID				//Not enough funds, not shipped
}
