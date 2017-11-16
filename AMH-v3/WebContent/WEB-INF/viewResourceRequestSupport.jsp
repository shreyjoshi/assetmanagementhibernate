<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>resource requests</title>
<style>
body{
background-color:#EEEEEE;
}


.top_bar{
	background:black;
	padding:5px 0;}
.social_icons{margin-right:5px;}
.contact_info{color:#fff; font-size:16px;}
.upper{
		margin-top:-250px;
		margin-left:300px;
}
.ram{
		background-color: #fff;
	border-top: 2px dashed #8c8b8b;
	}
</style>

</head>
<body>

		<!----Header---->
	<div class="container-fluid top_bar">
         <div class="container">
			<div class="row">
			<div class="col-sm-12">
			<font color=white><a href="supportstaff"><i class="" style="font-size:17px; color:#fff;">Asset Support Staff </i></a></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-sign-out" style="font-size:17px; color:#fff;">welcome ${username}</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="social_icons" align="right"><font color=white><a href="logout"><i class="fa fa-sign-out" style="font-size:17px; color:#fff;">Support Staff Logout</i></a></font></span>
			</div>
			
			
			</div>
			</div>
			</div>
			

<center>

<h3>${approvemsg}</h3>
<%@ page import="java.util.ArrayList,beans.Asset" %>
<% ArrayList<Asset> list=(ArrayList<Asset>)request.getAttribute("requests");
	if(!(list.isEmpty()))
	{
%>
<table border="3">
<tr><td>request id</td><td>asset name</td><td>request date</td><td>user name</td><td>request status</td><td>action Perform</td></tr>
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
				<td><input type="submit" name="op" value="approve" />	
				<input type="submit" name="op" value="cancel" /></td>
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