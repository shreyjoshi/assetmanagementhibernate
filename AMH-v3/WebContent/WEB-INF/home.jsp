<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		$("#admin").hide();
		$("#logintype").change(function(){
			var data=$("#logintype").val();
			
			if(data.match('admin'))
				$("#admin").show();
				$("#employee").hide();
			
		});
		$("#logintype1").change(function(){
			var data=$("#logintype1").val();
			
			if(data.match('employee'))
			{	$("#admin").hide();
				$("#employee").show();
			}
		});
	});
</script>
${logout}
<body>
<center>
<br/><br/>
<div id="selecttype">
		<input type="radio" name="logintype" value="admin" id=logintype />admin
		<input type="radio" name="logintype" value="employee" id=logintype1 />employee
</div>
<div id="admin"><h1><a href="AdminLogin"> admin login</a></h1></div>
<br/><br/>
<div id="employee"><h1><a href="EmployeeLogin">employee login</a></h1></div>
<br/><br/>
</center>
</body>
</html>