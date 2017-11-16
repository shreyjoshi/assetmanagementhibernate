<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<title>Asset transfer panel</title>
<style>
body{
background-color:#EEEEEE;
}


.top_bar{
	background:black;
	padding:5px 0;}
.social_icons{margin-right:5px;}
.contact_info{color:#fff; font-size:16px;}

</style>

</head>
<body>

	<div class="top_bar">
			<font color=white><a href="employeehome"><i class="" style="font-size:17px; color:#fff;">Employee Home</i></a></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="social_icons" align="right">><font color=white><a href="logout"><i class="fa fa-sign-out" style="font-size:17px; color:#fff;">User Logout</i></a></font></span>
			</div>
<script>
$(document).ready(function(){
	//alert("hello");
	$("#noassetmsg").hide();
	$("#checktoemp").hide();
	var data1="fromEmp="+$("#fromEmp").val();
	//alert(data1);
	/* $.ajax({
		url:'./fetchManager',
		data:data1,
		type:'post',
		success:function(result)
		{
			//alert(result);
			if(!result.match("0"))
			{
				$("#managerid").val(result);	
			}
		else
		{
			$("#managerid").val('');				
		}
		}
	}); */
		var data="fromEmp="+$("#fromEmp").val();
		//alert(data);
		$.ajax({
			url:'./fetchAssetName',
			data:data,
			type:'post',
			success:function(result){
				//alert(result);
				if(!result.match("0"))
					{
						$("#assetname").append(result);
						$("#assetname").show();
						$("#noassetmsg").hide();
					}
				else{
					$("#assetname").hide();
					$("#noassetmsg").show();
					
					//alert("you don't posses any assets");
				}
			}
		});
		$("#toEmp").keydown(function(){
			var toEmp="toEmp="+$("#toEmp").val();
			//alert(toEmp);
			$.ajax({
				url:'./checkToEmp',
				data:toEmp,
				type:'post',
				success:function(result){
					//alert(result);
					if(result.match("0"))
					{
						$("#checktoemp").show();
					}
					else{
						$("#checktoemp").hide();
					}
				}
			});
		});

$("#assetname").change(function(){
		var data="assetname="+$("#assetname").val()+"&emailid="+$("#fromEmp").val();
			//alert(data);
			$.ajax({
				url:'Fetchassetid',
				data:data,
				type:'post',									
					success:function(result){
						//alert(result);
						if(!result.match("0"))
						{
							$("#allocatedid").val(result);
						}
					else{
					alert("not-success");
					}
					}

					});
});

});
</script>
<br/><br/><br/><br/>
<center>
<center><span style="width:500px;color:blue;font-size:30px;font-weight:bold;border-bottom:1px solid blue;">Asset Transfer Panel</span></center>

<font color="red">${transfermsg}</font>
<!-- <a href="./employeehome">home</a><br/> -->
<form action="TransferToEmp" method="post">
From Employee:<input type="text" name="fromEmp" id="fromEmp" value="<%=session.getAttribute("username") %>" />
To Employee:<input type="text" name="toEmp" id="toEmp" required/><div id="checktoemp"><font color="red">enter valid user id!!</font></div>
manager id:<input type="text" name="managerid" id="managerid"/>

<select name="assetname" id="assetname">
<option value=null>---select---</option>
</select>&nbsp;
asset id:<input type="text" name="allocatedid" id="allocatedid" required/>

<div id="noassetmsg"><font color="red" size="5">You don't have any asset to transfer</font></div>
<input type="submit" value="Transfer" />
</form>
<br/><br/>
<font color="red" size="5">Your requests for transfer!</font><br/><br/>
<table border="3">
<tr><th>Allocated_id</th><th>Asset_Name</th><th>From_Emp</th><th>Manager_Id</th><th>To_Emp</th><th>Transfer_Request_Date</th><th>Transfer_Request_Id</th><th>Status</th></tr>
         
<%@page import="java.util.ArrayList,beans.Tansfer" %>
<%ArrayList<Tansfer> list2=(ArrayList<Tansfer> )request.getAttribute("List2"); 
	if(!list2.isEmpty())
	{
		for(Tansfer t:list2)
		{
%>
<tr>
<!-- <form action="./AskManager" method="post"> -->
			<td><input type="text" name="allocatedid" value="<%=t.getAllocatedid() %>"/></td>
			<td><%=t.getAssetname() %></td>
			<td><%=t.getFromEmp() %></td>
			<td><%=t.getManagerid() %></td>
			<%-- <td><%=t.getStatus() %></td> --%>
			<td><%=t.getToEmp() %></td>
			<td><%=t.getTransferRequestDate() %></td>
			<td><input type="text" name="transferReqId" value="<%=t.getTransferReqId() %>"/></td>
			<td><%if(t.getStatus()==3){
				%>pending with manager
			<%}else if(t.getStatus()==4){
				%>
				<font color="green">Your manager has approved<br/> transfer request</font>
			<%}
			else if(t.getStatus()==0){
				%>
				<font color="green">Your transfer request<br/>is being with other employee</font>
				<% 
			}else{
				%>
				<font color="green">Your transfer request<br/>is aprroved</font>
				<%
			}
			%>
			
			
			</td>
			<!-- <td><input type="submit" value="Ask your Manager"></td> -->
			</form>
			</tr>
<%
		}
	}
	else{
		%>
		</table>
		<font color="red" size="4">Nothing in list2!!!</font>
		<%
	}
%>
</table>
<br/><br/>

<font color="red" size="5">Requests from other employees for transfer!</font><br/><br/>
<table border="3">
<tr><th>Allocated_id</th><th>Asset_Name</th><th>From_Emp</th><th>Manager_Id</th><th>To_Emp</th><th>Transfer_Request_Date</th><th>Transfer_Request_Id</th><th>Status</th><th>Action_Task</th></tr>
         
<%@page import="java.util.ArrayList,beans.Tansfer" %>
<%ArrayList<Tansfer> list=(ArrayList<Tansfer> )request.getAttribute("List"); 
	if(!list.isEmpty())
	{
		for(Tansfer t:list)
		{
%>
<tr>
<form action="./AskManager" method="post">
			<td><input type="text" name="allocatedid" value="<%=t.getAllocatedid() %>"/></td>
			<td><%=t.getAssetname() %></td>
			<td><%=t.getFromEmp() %></td>
			<td><%=t.getManagerid() %></td>
			<%-- <td><%=t.getStatus() %></td> --%>
			<td><%=t.getToEmp() %></td>
			<td><%=t.getTransferRequestDate() %></td>
			<td><input type="text" name="transferReqId" value="<%=t.getTransferReqId() %>"/></td>
			<td><%if(t.getStatus()==3){
				%>pending with manager
			<%}else if(t.getStatus()==4){
				%>
				<font color="green">Employee's manager has approved<br/> transfer request</font>
			<%} %>
			
			</td>
			<td><input type="submit" value="Ask your Manager"></td>
			</form>
			</tr>
<%
		}
	}else{
		%>
		</table>
		<font color="red" size="4">Nothing in list!!!</font>
		<%
	}
%>
</table>
<br/><br/>
<font color="red" size="5">Requests you asked to manager</font><br/><br/>
<table border="3">
<tr><th>Allocated_id</th><th>Asset_Name</th><th>From_Emp</th><th>Manager_Id</th><th>To_Emp</th><th>Transfer_Request_Date</th><th>Transfer_Request_Id</th><th>Status</th></tr>
         
<%ArrayList<Tansfer> list1=(ArrayList<Tansfer> )request.getAttribute("List1"); 
	if(!list1.isEmpty())
	{
		for(Tansfer t:list1)
		{
%>
<tr>
<!-- <form action="./AskManager" method="post"> -->
			<td><input type="text" name="allocatedid" value="<%=t.getAllocatedid() %>"/></td>
			<td><%=t.getAssetname() %></td>
			<td><%=t.getFromEmp() %></td>
			<td><%=t.getManagerid() %></td>
			<%-- <td><%=t.getStatus() %></td> --%>
			<td><%=t.getToEmp() %></td>
			<td><%=t.getTransferRequestDate() %></td>
			<td><input type="text" name="transferReqId" value="<%=t.getTransferReqId() %>"/></td>
			<td><%if(t.getStatus()==0){
				%>pending with your manager
			<%}else{
				%>
				<font color="green">You can transfer asset</font>
			<%} %>
			
			</td>
			<!-- </form> -->
			</tr>
<%
		}
	}else{
		%>
		</table>
		<font color="red" size="4">Nothing in list1!!!</font>
		<%
	}
%>
</table>
</center>
</body>
</html>