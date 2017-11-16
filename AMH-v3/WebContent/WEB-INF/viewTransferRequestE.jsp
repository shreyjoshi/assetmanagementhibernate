<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Transfer Request by Employee</title>
</head>
<body>
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
<form action="./" method="post">
<tr>

<td><input type="text" name="transferReqId" value="<%=tr.getTransferReqId()%>" readonly/></td>
<td><input type="text" name="assetname" value="<%=tr.getAssetname()%>"/></td>
<td><input type="text" name="fromEmp" value="<%=tr.getFromEmp()%>"/></td>
<td><input type="text" name="status" value="<%=tr.getStatus()%>"/></td>
<td><input type="text" name="toEmp" value="<%=tr.getToEmp()%>"/></td>
<td><input type="text" name="transferRequestDate" value="<%=tr.getTransferRequestDate()%>"/>
<input type="hidden" name="managerid" value="<%=tr.getManagerid()%>"/></td>
<input type="hidden" name="allocatedid" value="<%=tr.getAllocatedid()%>"/></td>

<td><input type="submit" value="Ask Transfer" name="op" /></td>
	
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
</body>
</html>