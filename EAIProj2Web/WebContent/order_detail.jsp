<%@ page import="javax.naming.*" %>
<%@ page import="java.util.*" %>
<%@ page import="pt.uc.dei.eai.common.*" %>
<%@ page import="pt.uc.dei.eai.lpco.LPCOBean"%>
<%@ page import="pt.uc.dei.eai.lpco.LPCOBeanRemote"%>
<%@ page import="pt.uc.dei.eai.shopwebsite.ShoppingCart"%>
<%
String error = null;
InitialContext ctx = new InitialContext();
LPCOBeanRemote lpco = (LPCOBeanRemote)ctx.lookup("EAIProj2/LPCOBean/remote");
Integer orderId = null;
Order order = null;
try {
	String orderIdString = request.getParameter("order");
	orderId = Integer.parseInt(orderIdString);
	order = lpco.getOrder(orderId);
} catch (Exception exc) {
	error = "Error obtaining the order detail.";
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
                NOT IMPLEMENTED YET
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
