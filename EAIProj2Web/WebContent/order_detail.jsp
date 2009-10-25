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
            	<table>
            		<%
        			// Verify login
        			String myname = (String)session.getAttribute("username");
        			if (myname != null) {
	        			if (order != null) {
	            			%>
	 	            		<tr>
		            			<td>Order id</td>
		            			<td><%=order.getOrderId()%></td>
		            		</tr>
	 	            		<tr>
		            			<td>Purchase Date</td>
		            			<td><%=order.getPurchaseDate()%></td>
		            		</tr>
	 	            		<tr>
		            			<td>Shipping Address</td>
		            			<td><%=order.getShippingAddress()%></td>
		            		</tr>
	            			<%
	            			List<Camera> cameras = order.getOrderedCameras();
	               			if (cameras != null && cameras.size() > 0) {
    							%>
		 	            		<tr>
			            			<td colspan="2">
						            	<table border="1">
			    		            		<tr>
			    		            			<td>Model</td>
			    		            			<td>Price</td>
			    		            		</tr>
			    							<%
				                			for (int i = 0; i < cameras.size(); i++) {
				    							%>
				    		            		<tr>
				    		            			<td><%=cameras.get(i).getModel()%></td>
				    		            			<td><%=cameras.get(i).getPrice()%> &euro;</td>
				    		            		</tr>
				    							<%
				                			}
			    							%>
						            	</table>
					            	</td>
					            </tr>
    							<%
	                		} else {
	                			%>
	    	            		<tr>
	    	            			<td colspan="3">There are no cameras in this order.</td>
	    	            		</tr>
	                			<%
	                		}
	        			} else {
	            			%>
		            		<tr>
		            			<td colspan="2">There is no order with this id.</td>
		            		</tr>
	            			<%
	        			}
	        		} else {
            			%>
	            		<tr>
	            			<td colspan="2">You must login before you can see your order.</td>
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
