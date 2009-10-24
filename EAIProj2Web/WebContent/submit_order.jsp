<%@ page import="javax.naming.*" %>
<%@ page import="pt.uc.dei.eai.lpco.LPCOBean"%>
<%@page import="pt.uc.dei.eai.lpco.LPCOBeanRemote"%>
<%@ page import="pt.uc.dei.eai.common.*" %>
<%@page import="pt.uc.dei.eai.shopwebsite.ShoppingCart"%>
<%
String error = null;
ShoppingCart existingCart = (ShoppingCart)session.getAttribute("cart");
InitialContext ctx = new InitialContext();
LPCOBeanRemote lpco = (LPCOBeanRemote)ctx.lookup("EAIProj2/LPCOBean/remote");
if (existingCart != null) {
	if (lpco.submitOrder(existingCart.getCameras())) {
		// purchase done, clean shopping cart
		session.removeAttribute("cart");
	} else {
		error = "Error submiting purchase.";
	}
} else {
	error = "Shopping cart was empty.";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Low-Price Cameras Online</title>
</head>
<body>
    <table border="1">
        <tr>
            <td colspan="2"><a href="index.jsp">Low-Price Cameras Online</a></td>
        </tr>
        <tr>
            <td colspan="2"><jsp:include page="cart.jsp"></jsp:include></td>
        </tr>
        <tr>
        	<td>
 	          	<jsp:include page="login.jsp"></jsp:include>
           	</td>
            <td rowspan="2">
            <%
            if (error == null) {
	            %>
	            Your purchase was completed with success.<br />
	            <a href="check_orders.jsp">Check pending orders</a>
	            <%	
            } else {
	            %>
	            <font color="Red"><%=error%></font>
	            <%	
            }
            %>
            </td>
        </tr>
        <tr>
        	<td>
           		<jsp:include page="main_menu.html"></jsp:include>
           	</td>
        </tr>
    </table>
</body>
</html>
