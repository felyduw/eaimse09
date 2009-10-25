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
List<Order> orders = lpco.listPurchases();
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
            	<table>
            		<%
        			// Verify login
        			String myname = (String)session.getAttribute("username");
        			if (myname != null) {
                		if (orders != null && orders.size() > 0) {
                			%>
    	            		<tr>
    	            			<td colspan="3">You have <%=orders.size()%> purchases.</td>
    	            		</tr>
     	            		<tr>
    	            			<td>Order id</td>
    	            			<td>Purchase date</td>
    	            			<td># Cameras</td>
    	            		</tr>
                			<%
                			for (int i = 0; i < orders.size(); i++) {
                				Order order = orders.get(i);
    							%>
    		            		<tr>
    		            			<td><a href="order_detail.jsp?order=<%=order.getOrderId()%>"><%=order.getOrderId()%></a></td>
    		            			<td><%=order.getPurchaseDate()%></td>
    		            			<td><%=order.getOrderedCameras().size()%></td>
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
