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

//Add camera to shopping cart
if (cameraId != null) {
    WsConnector ws = new WsConnector();
    org.netbeans.xml.schema.cameraresponse.CameraInfo camera = ws.InvokeGetCameraInfo(cameraId);
    if (existingCart == null) {
        // Shopping Cart is still empty
        ShoppingCart newCart = new ShoppingCart();
        newCart.addCamera(camera);
        existingCart = newCart;
    } else  {
        // Shopping Cart is not empty
        existingCart.addCamera(camera);
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
	Shopping cart has <%=existingCart.getNumberCameras()%> cameras, with a total value of <%=existingCart.getTotalAmount()%> &euro;&nbsp;&nbsp;&nbsp;
	<a href="checkout.jsp">Checkout</a>
	</form>
<% 
}
%>

	