<%@ page import="java.util.*" %>
<%@ page import="pt.uc.dei.eai.*" %>
<%@ page import="pt.uc.dei.eai.common.*" %>
<%
String error = null;
String[] submit_actions = request.getParameterValues("Submit");
boolean searchAction = Utils.stringArrayContains("Search", submit_actions);
List<Camera> camerasList = null;
if (searchAction) {
    String free_text_search = request.getParameter("free_text_search");
    // Pesquisa das cameras
    WsConnector ws = new WsConnector();
    camerasList = ws.InvokeSearchCameras(free_text_search);
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
            <td colspan="2" height="5"><jsp:include page="cart.jsp"></jsp:include></td>
        </tr>
        <tr>
        	<td style="width: 200px" height="5"><jsp:include page="login.jsp"></jsp:include></td>
            <td style="width: 600px" rowspan="2" valign="top">
            	<form method="get">
            	<table style="width: 100%">
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
                            Camera currentCamera = camerasList.get(i);
                            if (currentCamera != null) {
							%>
		            		<tr>
		            			<td><%=currentCamera.getModel()%></td>
		            			<td><%=currentCamera.getPrice()%> &euro;</td>
		            			<td><a href="?add=<%=currentCamera.getId()%>">Add to shopping cart</a></td>
		            		</tr>
							<%
                            }
                            else {
							%>
		            		<tr>
		            			<td colspan="3">Error: camera info empty.</td>
		            		</tr>
							<%
                            }
            			}
            		} else if (searchAction) {
            			%>
	            		<tr>
	            			<td colspan="3">No cameras found.</td>
	            		</tr>
            			<%
            		} else {
            			%>
	            		<tr>
	            			<td colspan="3">Search cameras using the free search textbox...</td>
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
