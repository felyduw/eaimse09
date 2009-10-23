<%@ page import="javax.naming.*" %>
<%@ page import="java.util.*" %>
<%@ page import="pt.uc.dei.eai.lpco.*"%>
<%@ page import="pt.uc.dei.eai.common.*"%>
<%@ page import="pt.uc.dei.eai.shopwebsite.ShoppingCart"%>
<%
String error = null;
String free_text_search = request.getParameter("free_text_search");
InitialContext ctx = new InitialContext();
LPCOBeanRemote lpco = (LPCOBeanRemote)ctx.lookup("EAIProj2/LPCOBean/remote");
// Pesquisa das cameras
List<Camera> camerasList = lpco.searchCameras(free_text_search);
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
        	<td><jsp:include page="login.jsp"></jsp:include></td>
            <td rowspan="2" valign="top">
            	<form method="post">
            	<table>
            		<tr>
            			<td colspan="3">Free text search:<input id="free_text_search" name="free_text_search" type="text" /><input id="Submit" name="Submit" type="submit" value="Search" /></td>
            		</tr>
            		<%
            		if (camerasList != null && camerasList.size() > 0) {
            			%>
	            		<tr>
	            			<td colspan="3"><%=camerasList.size()%> cameras found.</td>
	            		</tr>
            			<%
            			for (int i = 0; i < camerasList.size(); i++) {
							%>
		            		<tr>
		            			<td><%=camerasList.get(i).getModel()%></td>
		            			<td><%=camerasList.get(i).getPrice()%> &euro;</td>
		            			<td><a href="?add=<%=camerasList.get(i).getCameraId()%>">Add to shopping cart</a></td>
		            		</tr>
							<%
            			}
            		} else {
            			%>
	            		<tr>
	            			<td colspan="3">No cameras found.</td>
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
