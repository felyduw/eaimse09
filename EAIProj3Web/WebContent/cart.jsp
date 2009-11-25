<%@ page import="javax.naming.*" %>
<%@ page import="pt.uc.dei.eai.*" %>
<%
String error = null;
ShoppingCart existingCart = (ShoppingCart)session.getAttribute("cart");
Integer cameraId = null;
try {
	String cameraIdString = request.getParameter("add");
	cameraId = Integer.parseInt(cameraIdString);
} catch (Exception exc) { }

HttpSession s = request.getSession();

//Add camera to shopping cart
if (cameraId != null) {
	Camera camera = null;//lpco.getCamera(cameraId);
	if (existingCart == null) {
		// Shopping Cart is still empty
		ShoppingCart newCart = new ShoppingCart();
		newCart.getCameras().add(camera);
		existingCart = newCart;
	} else  {
		// Shopping Cart is not empty
		existingCart.getCameras().add(camera);
	}	
	session.setAttribute("cart", existingCart);
}
// Show shopping cart
if (existingCart == null) {
%>
	Shopping cart empty.
<%
} else  {
%>
	<form method="post">
	Shopping cart has <%=existingCart.getCameras().size()%> cameras, with a total value of <%=existingCart.getTotalAmount()%> &euro;&nbsp;&nbsp;&nbsp;
	<a href="checkout.jsp">Checkout</a>
	</form>
<% 
}
%>

	