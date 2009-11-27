<%@ page import="java.util.*" %>
<%@ page import="pt.uc.dei.eai.*" %>
<%@ page import="pt.uc.dei.eai.common.*" %>
<%
String error = null;
Integer orderId = null;
Order order = null;
try {
	String orderIdString = request.getParameter("order");
	orderId = Integer.parseInt(orderIdString);
	order = null;//lpco.getOrder(orderId);
} catch (Exception exc) {
	error = "Error obtaining the order detail.";
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
            	<table style="width: 100%">
            		<%
        			// Verify login
        			String myname = (String)session.getAttribute("username");
        			if (myname != null) {
	        			if (order != null) {
	            			%>
	 	            		<tr>
		            			<td>Order id</td>
		            			<td><%=order.getId()%></td>
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
						            	<table style="width: 100%">
			    		            		<tr>
			    		            			<th>Model</th>
			    		            			<th>Price</th>
			    		            		</tr>
			    							<%
				                			for (int i = 0; i < cameras.size(); i++) {
				    							%>
				    		            		<tr>
				    		            			<td><%=cameras.get(i).getModel()%></td>
				    		            			<td align="center"><%=cameras.get(i).getPrice()%> &euro;</td>
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
