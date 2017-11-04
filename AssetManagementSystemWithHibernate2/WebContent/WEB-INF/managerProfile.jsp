<%@page import="beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3>${m}</h3>
<br><br>

<a href="GoTOHome">GOTO Home</a>
<br>
<br>
 <%@page import="java.util.ArrayList" %>
<%

ArrayList<Employee> list=(ArrayList<Employee>)request.getAttribute("msg");
if(!list.isEmpty()){
	for(Employee e:list)
	{
    %>
    <form action="./updateManagerProfile" method="post">
    eid:<input type="text" name="eid" value="<%=e.getEid()%> " required/>
<br/><br/>
name:<input type="text" pattern="[A-Za-z- ]+" title="Only Allow Alphabets" name="name" value="<%=e.getName()%>" required/>
<br/><br/>
mobile:<input type="text" maxlength="10"   pattern="[789][0-9]{9}" title="Only Allow Numbers" name="mobile"  value="<%=e.getMobile()%>" required/>
<br/><br/>
email:<input type="email"  name="email" value="<%=e.getEmail()%>"  readonly="readonly"/>
<br/><br/>
date:<input type="date" name="doj" value="<%=e.getDoj()%>"  readonly="readonly"/>
<br/><br/>
Designation: <input type="text" name="designation" value="<%=e.getDesignation()%>"  readonly="readonly"/>
<br/><br/>
<input type="hidden" name="managerid" value="<%=e.getManagerid()%>" required/>
<input type="hidden" name="status" value="<%=e.getStatus()%>" required/>	
<input type="hidden" name="password" value="<%=e.getPassword()%>" required/>
<input type="submit" value="Update" />

</form> 
<%}}else{ %>
<font color="red">no employee details!!!!</font>
<%} %>
</body>
</html>