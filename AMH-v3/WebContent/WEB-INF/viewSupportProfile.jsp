<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>your profile</title>
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
		margin-top:-160px;
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

<%@ page import="java.util.ArrayList,beans.Employee" %>
<center>
${updatemsg}

<% ArrayList<Employee> list=(ArrayList)request.getAttribute("LIST");
if(list!=null){
	%>
	<table border="3">
	<!-- <tr><th>Name</th><th>Mobile</th><th>email</th><th>password</th><th>designation</th></tr> -->
<%	for(Employee e:list)
	{
%>
<pre>
<h1>
<form action="./UpdateSupportProfile" method="post">
<tr>
<td>eid:</td><td><input type="text" name="eid" value="<%=e.getEid() %>" readonly/></td>
</tr>
<tr>
<td>name:</td><td><input type="text" pattern="[A-Za-z- ]+" title="only Allow alphabet" name="name" value="<%=e.getName() %>" required/></td>
</tr>
<tr>	
<td>mobile:</td><td><input type="text" maxlength="10" pattern="[789][0-9]{9}" title="Only Allow Numbers" name="mobile" value="<%=e.getMobile() %>" required/></td>
</tr>
<tr>	
<td>email:</td><td><input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" name="email" value="<%=e.getEmail() %>" required/></td>
</tr>
<tr>
<td>doj :</td><td><input type="text" name="doj" value="<%=e.getDoj() %>" readonly/></td>
</tr>
<tr>	
<td>password:</td><td><input type="text" name="password" value="<%=e.getPassword() %>" required/></td>
</tr>
<tr>
<td>designation:</td><td><input type="text" name="designation" value="<%=e.getDesignation() %>" readonly/></td>
</tr>
<tr>
<td>status:</td><td><input type="text" name="status" value="<%=e.getStatus() %>" readonly/></td>
</tr>
<tr>
<td>manager id:</td><td><input type="text" name="managerid" value="<%=e.getManagerid() %>" readonly/></td>
</tr>
<tr>	
	<td><input type="submit"  name="op" value="update" /></td>
	
</tr>

<%}
	}%>
	</form>
	</h1></pre>
	
	</table>

</center>
</body>
</html>