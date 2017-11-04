<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>employee login</title>
</head>
<body>
<center>
<font color="red"><h1>${msg} &nbsp; ${welcome} &nbsp; ${forgetpassmsg} <br/><br/>${activationmsg}</h1></font>
<pre>
<form action="./CheckLogin" method="post">
User name :<input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder="Enter Your Emailid" name="username" required />
<br/>
password: <input type="password" name="password" required />
<br/>
<!--  
designation :<select name="designation" required>
				<option>manager</option>	
				<option>developer</option>	
				<option>supportstaff</option>	
			</select>
			-->
<input type="submit" value=" login">
</form>
</pre>
<a href=forgetpassword>forgot password</a>
</center>
</body>
</html>