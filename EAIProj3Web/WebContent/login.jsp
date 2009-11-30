<%@ page import="pt.uc.dei.eai.*" %>
<%@ page import="pt.uc.dei.eai.common.*" %>
<%
String error = null;
String submit_action = request.getParameter("Submit");
String myname = (String)session.getAttribute("username");
/*
WebServiceAux webServiceAux = new WebServiceAux();
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
    User userLogged = webServiceAux.InvokeLogin(user, password);
	if (userLogged != null) {
		// se logou ok no servidor
		session.setAttribute("username", user);
error = "DEBUG 7 - " + userLogged.getAddress() + ", " + userLogged.getEmail() + ", "
         + userLogged.getName() + ", " + userLogged.getPassword() + ", "
          + userLogged.getUsername() + ", " + userLogged.getId();
		myname = userLogged.getName();
	} else {
		error = "Login error";
	}
}
*/
//Debug
//User u = lpco.getUser();
//String debug = "none";
//if (u != null) debug = u.getUsername();

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

