<%@ page import="pt.uc.dei.eai.*" %>
<%@ page import="javax.xml.ws.WebServiceRef" %>
<%
String error = null;
String submit_action = request.getParameter("Submit");
String myname = (String)session.getAttribute("username");

if (submit_action != null && submit_action.equals("Logout")) {
    if (true) {
		// se deslogou ok no servidor
		session.removeAttribute("username");
		myname = null;
	} else {
		error = "Login error";
	}
} else if (submit_action != null && submit_action.equals("Login")) {
	String user = request.getParameter("User");
	String password = request.getParameter("Password");
	// Logar no servidor
    usercompositeorchestrator.CasaService1 service = new usercompositeorchestrator.CasaService1();
    usercompositeorchestrator.WSLoginWrapperPortType port = service.getLogin();
    org.netbeans.xml.schema.userschema.UserInfo loginRequest = new org.netbeans.xml.schema.userschema.UserInfo();
    loginRequest.setUsername(user);
    loginRequest.setPassword(password);
    org.netbeans.xml.schema.userschema.User userLogged = port.wsLoginWrapperOperation(loginRequest);

	if (userLogged != null) {
		// se logou ok no servidor
		session.setAttribute("username", user);
		myname = userLogged.getUsername();
	} else {
		error = "Login error";
	}
}
if (myname != null) {
	%>
	
<form method="post">
	<table height="5">
		<tr valign="top">
			<td><%=myname%></td>
		</tr>
		<tr valign="top">
			<td colspan="2"><input id="Submit" name="Submit" type="submit" value="Logout" /></td>
		</tr>
	</table>
	<br />
	</form>
	<%
} else  {
	%>
	<form method="post">
	<table height="5">
		<tr>
			<td colspan="2">Login</td>
		</tr>
		<tr>
			<td>User:</td>
			<td><input id="User" name="User" type="text" /></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input id="Password" name="Password" type="password" /></td>
		</tr>
		<tr>
			<td colspan="2"><input id="Submit" name="Submit" type="submit" value="Login" /></td>
		</tr>
		<%
		if (error != null) {
			%>
			<tr>
				<td colspan="2"><font color="red"><%=error%></font></td>
			</tr>
			<%
		}
		%>
		<tr>
			<td colspan="2"><a href="create_user.jsp">Register</a></td>
		</tr>
	</table>
	<br />
	</form>
	<% 
}
%>

