<%@ page import="pt.uc.dei.eai.*" %>
<%
String error = null;
String[] submit_actions = request.getParameterValues("Submit");
String myname = (String)session.getAttribute("username");
boolean logoutAction = Utils.stringArrayContains("Logout", submit_actions);
boolean loginAction = Utils.stringArrayContains("Login", submit_actions);
if (logoutAction) {
    // deslogar do servidor
    session.removeAttribute("username");
    session.removeAttribute("password");
    myname = null;
} else if (loginAction) {
	String user = request.getParameter("User");
	String password = request.getParameter("Password");
	// Logar no servidor
    WsConnector ws = new WsConnector();
    org.netbeans.xml.schema.userschema.User userLogged = ws.InvokeLogin(user, password);
	if (userLogged != null) {
		// se logou ok no servidor
		session.setAttribute("username", userLogged.getUsername());
		session.setAttribute("password", userLogged.getPassword());
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

