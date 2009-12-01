<%@ page import="java.util.*" %>
<%@ page import="pt.uc.dei.eai.*" %>
<%@ page import="pt.uc.dei.eai.common.*" %>
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
            	<table style="width: 100%">
            		<%
        			// Verify login
        			String myname = (String)session.getAttribute("username");
        			if (myname != null) {
                        try {
                            WsConnector ws = new WsConnector();
                            List<Order> orders = ws.InvokeGetPurchases(myname);
                            if (orders != null && orders.size() > 0) {
                                %>
                                <tr>
                                    <td colspan="3">You have <%=orders.size()%> purchases.</td>
                                </tr>
                                <tr>
                                    <th>Order id</th>
                                    <th>Purchase date</th>
                                    <th># Cameras</th>
                                </tr>
                                <%
                                for (int i = 0; i < orders.size(); i++) {
                                    Order order = orders.get(i);
                                    %>
                                    <tr>
                                        <td><a href="order_detail.jsp?order=<%=order.getId()%>"><%=order.getId()%></a></td>
                                        <td align="center"><%=order.getPurchaseDate()%></td>
                                        <td align="center"><%=order.getOrderedCameras().size()%></td>
                                    </tr>
                                    <%
                                }
                            } else {
                                %>
                                <tr>
                                    <td colspan="3">You don't have any purchase.</td>
                                </tr>
                                <%
                            }
                        } catch (Exception exc) {
                            %>
                            <tr>
                                <td colspan="2"> <font color="Red">Error obtaining purchases.</font></td>
                            </tr>
                            <%
                        }
        			} else {
            			%>
	            		<tr>
	            			<td colspan="2">You must login before you can see your purchases.</td>
	            		</tr>
            			<%
        			}
        			%>
            	</table>
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
