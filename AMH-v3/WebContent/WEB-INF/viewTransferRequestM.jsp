<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Transfer Request By Manager</title>

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
		margin-top:-290px;
		margin-left:300px;
}
.ram{
		background-color: #fff;
	border-top: 2px dashed #8c8b8b;
	}
</style>


</head>
<body>
<center>
<br/><br/><br/>
${approvalmsg}
<div class="container-fluid top_bar">
         <div class="container">
			<div class="row">
			<div class="col-sm-12">
			<font color=white><a href="manager"><i class="" style="font-size:17px; color:#fff;">Asset Manager</i></a></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-sign-out" style="font-size:17px; color:#fff;">welcome ${username}</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="social_icons" align="right"><font color=white><a href="logout"><i class="fa fa-sign-out" style="font-size:17px; color:#fff;">Manager Logout</i></a></font></span>
			</div>
			 
			
			</div>
			</div>
			</div>
<%@page import="java.util.ArrayList,beans.Tansfer"%>
<% ArrayList<Tansfer> ar=(ArrayList<Tansfer>)request.getAttribute("list"); 
if(!ar.isEmpty())
{
	%>
	
	<center><span style="width:500px;color:blue;font-size:30px;font-weight:bold;border-bottom:1px solid blue;">Transfer Request</span></center>
	<br/><br/>
	<center><table border="3">
	<tr><th>TransferRequestId</th><th>AssetName</th><th>From Employee</th><th>Status</th><th>To Employee</th><th>Transfer Request Date</th><th>Action Perform</th></tr>
	<%
	for(Tansfer tr:ar)
	{
%>
<form action="./ManipulateTransferRequests" method="post">
<tr>

<td><input type="text" name="transferReqId" value="<%=tr.getTransferReqId()%>" readonly/></td>
<td><input type="text" name="assetname" value="<%=tr.getAssetname()%>"/></td>
<td><input type="text" name="fromEmp" value="<%=tr.getFromEmp()%>"/></td>
<td><input type="text" name="status" value="<%=tr.getStatus()%>"/></td>
<td><input type="text" name="toEmp" value="<%=tr.getToEmp()%>"/></td>
<td><input type="text" name="transferRequestDate" value="<%=tr.getTransferRequestDate()%>"/>
<input type="hidden" name="managerid" value="<%=tr.getManagerid()%>"/></td>
<input type="hidden" name="allocatedid" value="<%=tr.getAllocatedid()%>"/></td>

<td><input type="submit" value="approved" name="op" />
	<input type="submit" value="reject" name="op" /></td>

</tr>
</form>
<%	
}
	%>
	</table></center>
	<%
}else
{
%>
<font color=red>Nothing in List</font>
<% 	
}
%>
</center>
</body>
</html>