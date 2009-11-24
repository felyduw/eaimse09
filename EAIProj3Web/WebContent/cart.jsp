<%@ page import="javax.naming.*" %>
<%@ page import="pt.uc.dei.eai.common.*" %>
<%@ page import="pt.uc.dei.eai.lpco.LPCOBean"%>
<%@page import="pt.uc.dei.eai.lpco.LPCOBeanRemote"%>
<%
String error = null;
Integer cameraId = null;
try {
	String cameraIdString = request.getParameter("add");
	cameraId = Integer.parseInt(cameraIdString);
} catch (Exception exc) { }

HttpSession s = request.getSession();
LPCOBeanRemote lpco = (LPCOBeanRemote) s.getAttribute("MyBean");
if (lpco == null) {
	InitialContext ctx = new InitialContext();
	lpco = (LPCOBeanRemote)ctx.lookup("EAIProj2/LPCOBean/remote");
	s.setAttribute("MyBean", lpco);
}

// Add camera to shopping cart
if (cameraId != null) {
	Camera camera = lpco.getCamera(cameraId);
	lpco.addCamera(camera);
}
// Show shopping cart
if (lpco.getShoppingCart().size() == 0) {
%>
	Shopping cart empty.
<%
} else  {
%>
	<form method="post">
	Shopping cart has <%=lpco.getShoppingCart().size()%> cameras, with a total value of <%=lpco.getTotalAmount()%> &euro;&nbsp;&nbsp;&nbsp;
	<a href="checkout.jsp">Checkout</a>
	</form>
<% 
}
%>


	