<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>resource requests</title>
</head>
<p align="right"><a href="supportstaff">home</a>&nbsp;<a href="logout">logout</a></p>
<center>
<body>
<h3>${approvemsg}</h3>
<%@ page import="java.util.ArrayList,beans.Asset" %>
<% ArrayList<Asset> list=(ArrayList<Asset>)request.getAttribute("requests");
	if(!(list.isEmpty()))
	{
%>
<table border="3">
<tr><td>asset id</td><td>asset name</td><td>request date</td><td>user name</td><td>request status</td></tr>
<%		
		for(Asset a:list)
		{
%>
			<tr>
				<form action="ApproveFromSupport" method="post">
				<td><input type="text" value="<%=a.getAssetid() %>" name="assetid" readonly /></td>
				<td><input type="text" value="<%=a.getAssetname() %>" name="assetname" readonly /></td>
				<td><input type="text" value="<%=a.getRequestdate() %>" name="requestdate" readonly /></td>
				<td><input type="text" value="<%=a.getemailid()%>" name="emailid" readonly /></td>
				<td><% if(a.getRequeststatus()==1)
						{
					%>
					<input type="text" placeholder="approvred from manager" value="approvred from manager" readonly />
					<%
						}
					%></td>
				<td><input type="submit" name="op" value="approve" /></td>	
				<td><input type="submit" name="op" value="cancel" /></td>
				</form>
			</tr>
<%
		}
%>
</table>
<%
	}else{
		%>
		<h1><font color="red">no requests to view!!!</font></h1>
		<%
	}
%>
</body>
</center>
</html>