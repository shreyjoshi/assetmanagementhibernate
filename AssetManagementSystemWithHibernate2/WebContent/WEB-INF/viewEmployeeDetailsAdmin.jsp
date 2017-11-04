<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view employee details</title>
</head>
<%@ page import="java.util.ArrayList,beans.Employee" %>
<center>
<body>
${updatemsg}
<p align="right"><a href="adminHome">home</a> &nbsp; <a href="logout">logout</a></p>
<% ArrayList<Employee> list=(ArrayList)request.getAttribute("viewdetails");
if(!(list.isEmpty())){
	%>
	<table border="3">
	<tr><th>eid</th><th>Name</th><th>manager id</th><th>Mobile</th><th>email</th><th>password</th><th>designation</th><th>date_of_joining</th><th>status</th></tr>
<%	for(Employee e:list)
	{
%>
<form action="./UpdateData" method="post">
<tr>
	<td><input type="text" name="eid" value="<%=e.getEid()%>" readonly /></td>
	<td><input type="text" pattern="[A-Za-z- ]+" title="only Allow Alphabet" name="name" value="<%=e.getName() %>" required/></td>
	<td><input type="text" name="managerid" value="<%=e.getManagerid() %>" required/></td>
	<td><input type="text" maxlength="10"   pattern="[789][0-9]{9}" title="Only Allow Numbers" name="mobile" value="<%=e.getMobile() %>" required/></td>
	<td><input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"  name="email" value="<%=e.getEmail() %>" readonly required/></td>
	<td><input type="text" name="password" value="<%=e.getPassword() %>" required/></td>
	<td><input type="text" name="designation" value="<%=e.getDesignation() %>" readonly required/>
				<select name="designation" id="designation" required>
					<option value=null>--designation--</option>
					<option>developer</option>
					<option>manager</option>
					<option>supportstaff</option>
				</select></td>
	<td><input type="date" name="doj" value="<%=e.getDoj()%>" required readonly/></td>
	<td><input type="text" name="status" value="<%=e.getStatus() %>" /></td>
	<td><input type="submit"  name="op" value="update" /></td>
</tr>
</form>
<%}
	}else{
		%>
			<h1><font color="red">no employees details to view!!!</font></h1>
		<%
	}%>
	</table>
</body>
</center>
</html>