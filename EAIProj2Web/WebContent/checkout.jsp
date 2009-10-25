<%@ page import="pt.uc.dei.eai.common.*" %>
<%@page import="pt.uc.dei.eai.shopwebsite.ShoppingCart"%>
<%
String error = null;
ShoppingCart existingCart = (ShoppingCart)session.getAttribute("cart");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Low-Price Cameras Online</title>
	<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <table border="1">
        <tr>
            <th colspan="2"><a href="index.jsp"><font color="#FFFFFF">Low-Price Cameras Online</font></a></th>
        </tr>
        <tr>
            <td colspan="2"><jsp:include page="cart.jsp"></jsp:include></td>
        </tr>
        <tr>
        	<td style="width: 200px"><jsp:include page="login.jsp"></jsp:include></td>
            <td style="width: 600px" rowspan="2" valign="top">
            	<form method="post">
            	<table style="width: 100%">
            		<%
            		if (existingCart != null && existingCart.getCameras().size() > 0) {
            			%>
	            		<tr>
	            			<td colspan="2">There are <%=existingCart.getCameras().size()%> cameras in your shopping cart.</td>
	            		</tr>
            			<%
            			for (int i = 0; i < existingCart.getCameras().size(); i++) {
            				Camera camera = existingCart.getCameras().get(i);
							%>
		            		<tr>
		            			<td><%=camera.getModel()%></td>
		            			<td><%=camera.getPrice()%> &euro;</td>
		            		</tr>
							<%
            			}
            			// Verify login
            			String myname = (String)session.getAttribute("username");
            			if (myname != null) {
                			%>
    	            		<tr>
    	            			<td colspan="2"><a href="submit_order.jsp">Submit your purchase order</a></td>
    	            		</tr>
                			<%
            			} else {
                			%>
    	            		<tr>
    	            			<td colspan="2">You must login before you can submit your purchase order.</td>
    	            		</tr>
                			<%
            			}
            		} else {
            			%>
	            		<tr>
	            			<td colspan="2">Your shopping cart is empty.</td>
	            		</tr>
            			<%
            		}
            		%>
            	</table>
            	</form>
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
