<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view reports</title>
</head>
<body>
<h2>
<p align="right"><a href="supportstaff">home</a>&nbsp;<a href="logout">logout</a></p></h2>
 <center>
<h2> <font color="orange">previously approved requests</font></h2>
<%@ page import="java.util.ArrayList,beans.AllocatedAsset" %>
<% ArrayList<AllocatedAsset> list=(ArrayList)request.getAttribute("report");
	if(!(list.isEmpty()))
	{
%>
<table border="5">
<tr><td>asset id</td><td>user id</td><td>asset name</td><td>date of allocation</td></tr>
<%
		for(AllocatedAsset a:list)
		{
%>
			<tr>
			 <td><%=a.getAllocatedid() %></td>
			 <td><%=a.getEmailid() %></td>
			 <td><%=a.getAssetname() %></td>
			 <td><%=a.getDateofallocation() %></td>
			</tr>
<%
		}
%>
</table>
<%
	}else{
		%>
			<h1><font color="red">no reports to view!!!</font></h1>
		<%
	}
%> 
</center>
</body>
</html>