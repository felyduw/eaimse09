
<%@ page import="javax.naming.*" %> 
<%@ page import="eai.*" %>

<html><body>

<%
	InitialContext ctx = new javax.naming.InitialContext();
	Calculator calculator = (Calculator) ctx.lookup("CalculatorBean/remote");
	
	double a = Double.parseDouble(request.getParameter("value_a"));
	double b = Double.parseDouble(request.getParameter("value_b"));
	
	double result = calculator.add(a, b); 
%>

Value A: <%= a %> <br/>
Value B: <%= b %> <br/>
Result:  <%= result %> <br/>

</body></html>


