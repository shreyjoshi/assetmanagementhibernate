<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ManagerHomePages</title>
</head>
<body>
${msg}
<br>
<a href="logout"><h4>Logout</h4></a><br><br>
 <center><h3>Welcome ${eid}</h3></center>



<a href="ManagerProfile">My Profile</a><br><br>
<a href="ViewManagerRequest">View my request</a><br><br>
<a href="CreateManagerRequest">Create Request</a><br><br>
<a href="MyAssetManager">My Assets</a><br><br>
<a href="AssetsTransfor">Assets Transfer </a><br><br>
<a href="Employee_Assets">View Employee And Assets</a><br><br>
<h2><center><a href="PendingRequestApproval">Pending Request And Approval</a><br><br></center></h2>
</body>
</html>