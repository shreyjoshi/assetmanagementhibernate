<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>viewTransferredHistorySupport</title>
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
<%@page import="java.util.ArrayList,beans.TransferredAsset" %>
<%ArrayList<TransferredAsset> list=(ArrayList<TransferredAsset> )request.getAttribute("list");
if(!list.isEmpty())
{
	%>
	<table border="3">
	<tr><th>transfer id</th><th>asset name</th><th>request from</th><th>manager id</th><th>request to</th><th>transfer date</th><th>transfer request id</th></tr>
	
	<%
	for(TransferredAsset ta:list){
		%>
		<tr>
		<td><%=ta.getTransferId() %></td>
		<td><%=ta.getAssetname() %></td>
		<td><%=ta.getFromEmp() %></td>
		<td><%=ta.getManagerid() %></td>
		<td><%=ta.getToEmp() %></td>
		<td><%=ta.getTransferredDate() %></td>
		<td><%=ta.getTransferReqId() %></td><br/>	
		</tr>	
		<%
	}
	%>
	</table>
	<%
}
%>
</body>
</html>