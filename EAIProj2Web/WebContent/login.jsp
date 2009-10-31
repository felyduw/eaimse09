<%@ page import="javax.naming.*" %>
<%@ page import="pt.uc.dei.eai.lpco.LPCOBean"%>
<%@ page import="pt.uc.dei.eai.lpco.LPCOBeanRemote"%>
<%@ page import="pt.uc.dei.eai.common.User"%>
<%
String error = null;
String submit_action = request.getParameter("Submit");
String myname = (String)session.getAttribute("username");
InitialContext ctx = new InitialContext();
LPCOBeanRemote lpco = (LPCOBeanRemote)ctx.lookup("EAIProj2/LPCOBean/remote");
if (submit_action != null && submit_action.equals("Logout")) {
	if (lpco.doLogout(myname)) {
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
	if (lpco.doLogin(user, password)) {
		// se logou ok no servidor
		session.setAttribute("username", user);
		myname = user;
	} else {
		error = "Login error";
	}
}

//Debug
User u = lpco.getUser();
String debug = "none";
if (u != null) debug = u.getUsername();

if (myname != null) {
	%>
	
<form method="post">
	<table>
		<tr>
			<td><%=myname%> !!! <%= debug %></td>
		</tr>
		<tr>
			<td colspan="2"><input id="Submit" name="Submit" type="submit" value="Logout" /></td>
		</tr>
	</table>
	<br />
	</form>
	<%
} else  {
	%>
	<form method="post">
	<table>
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

