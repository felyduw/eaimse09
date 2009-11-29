<%@ page import="java.util.*" %>
<%@ page import="pt.uc.dei.eai.*" %>
<%@ page import="pt.uc.dei.eai.common.*" %>
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
	WebServiceAux webServiceAux = new WebServiceAux();
	Camera camera = webServiceAux.InvokeGetCameraInfo(cameraId);
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
	
<%@page import="org.netbeans.xml.schema.cameraresponse.SearchCamerasResponse"%><form method="post">
	Shopping cart has <%=existingCart.getCameras().size()%> cameras, with a total value of <%=existingCart.getTotalAmount()%> &euro;&nbsp;&nbsp;&nbsp;
	<a href="checkout.jsp">Checkout</a>
	</form>
<% 
}
%>

	