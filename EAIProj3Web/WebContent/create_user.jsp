<%@ page import="java.util.*" %>
<%@ page import="pt.uc.dei.eai.*" %>
<%@ page import="pt.uc.dei.eai.common.*" %>
<%
String error = null;
Boolean userCreated = false;
String submit_action = request.getParameter("Submit");
String username = request.getParameter("Username");
String password = request.getParameter("Password");
String address = request.getParameter("Address");
String email = request.getParameter("Email");
if (submit_action != null && submit_action.equals("Register")) {
	
	// validate data
	if (username == null || username.isEmpty()) {
		error = "Username not valid.";
	} else if (password == null || password.isEmpty()) {
		error = "Password not valid.";
	} else if (address == null || address.isEmpty()) {
		error = "Address not valid.";
	} else if (email == null || email.isEmpty()) {
		error = "Email not valid.";
	} else {
		// Register user in server
		//List<String> camerasList = lpco.registerUser(username, password, address, email);
		userCreated = null;//lpco.registerUser(username, password, address, email);;
	}
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
            	<form method="post">
            	<table style="width: 100%">
					<%
					if (userCreated) {
						%>
						<tr>
							<td colspan="2"><font color="green">User successefully created.</font></td>
						</tr>
						<%
					} else {
						%>
	            		<tr>
	            			<td colspan="2">Enter the user details</td>
	            		</tr>
						<tr>
							<td>Username:</td>
							<td><input id="Username" name="Username" type="text" value="<%=(username==null)?"":username%>" /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input id="Password" name="Password" type="password" value="<%=(password==null)?"":password%>" /></td>
						</tr>
						<tr>
							<td>Address:</td>
							<td><input id="Address" name="Address" type="text" value="<%=(address==null)?"":address%>" /></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><input id="Email" name="Email" type="text" value="<%=(email==null)?"":email%>" /></td>
						</tr>
						<tr>
							<td colspan="2"><input id="Submit" name="Submit" type="submit" value="Register" /></td>
						</tr>
						<%
						if (error != null) {
							%>
							<tr>
								<td colspan="2"><font color="red"><%=error%></font></td>
							</tr>
							<%
						}
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
