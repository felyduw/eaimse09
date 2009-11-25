<%@ page import="javax.naming.*" %>
<%@ page import="pt.uc.dei.eai.*" %>
<%
String error = null;
ShoppingCart existingCart = (ShoppingCart)session.getAttribute("cart");
if (existingCart.getCameras().size() != 0) {
	if (false/*lpco.submitOrder()*/) {
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
