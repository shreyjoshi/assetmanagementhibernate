<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rejected Requests</title>
</head>
<center><a href="./supportstaff">home</a>&nbsp;<a href="./logout">logout</a></center>
<br/><br/><br/>
<body>
<center>
<%@page import="java.util.ArrayList,beans.RejectedRequest" %>
<% ArrayList<RejectedRequest> list=(ArrayList)request.getAttribute("rejectedrequests"); 
	if(!list.isEmpty())
	{
		%>
		<table border="4">
		<tr><th>reject request no</th><th>asset name</th><th>asset request id</th><th>user name</th><th>date of rejection</th><th>rejected by</th></tr>
		
		<%
		for(RejectedRequest r:list)
		{
%>
<tr>
			<td><%=r.getRejectedrequest() %></td>
			<td><%=r.getAssetname() %></td>
			<td><%=r.getAssetrequestid() %></td>
			<td><%=r.getEmailid() %></td>
			<td><%=r.getDateofrejection() %></td>
			<td><%=r.getRejectedby() %></td>
			</tr>
<%
		}
		out.println("</table>");
	}else{
		%>
		<font color="red" size="5"> no requests were rejected</font>
		<%
	}
%>
</center>
</body>
</html>