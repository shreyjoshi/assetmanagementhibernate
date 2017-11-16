<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>create Employee</title>
 <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
         rel = "stylesheet">
      <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
      <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>-->
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <style>
body{
background-color:#EEEEEE;
}


.top_bar1{
	background:black;
	padding:5px 0;}
.social_icons1{margin-right:5px;}
.contact_info{color:#fff; font-size:16px;}

.upper{
		margin-top:-240px;
		margin-left:250px;
}
.ram{
		background-color: #fff;
	border-top: 2px dashed #8c8b8b;
	}
</style>
</head>
<body>
<div class="top_bar1">
			<font color=white><a href="adminHome"><i class="" style="font-size:17px; color:#fff;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Admin Home</i></a></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="social_icons1" align="right">><font color=white><a href="logout"><i class="fa fa-sign-out" style="font-size:17px; color:#fff;">Admin Logout</i></a></font></span>
			</div>
<%@ page import="java.util.ArrayList,beans.Employee" %>



  <!-- <script>
         $(function() {
            
			  $( "#doj" ).datepicker(
			  {
   
	//minDate: 01-01-1980,
    //selectOtherMonths: true,
   // changeMonth: true,
//changeYear: true,
  //  showButtonPanel: true,
   // dateFormat: 'dd-mm-yy'
}
			  
			  );
       
			
         });
      </script> -->


<script type="text/javascript">
$(document).ready(function(){
	$("#mid").hide();

	

	$("#designation").change(function(){
		var data=$("#designation").val();
		if(data.match('developer'))
			{
			$("#mid").show();
			$.ajax({
				url:'FetchManager',						
				type:'post',									
					success:function(result){
						alert(result);
						$("#managerid").empty().append(result);
					}

					});
			}
		else{
			$("#mid").hide();
			$("#managerid").empty();
		}
			});
	
	$("#email").change(function(){
		var data="email="+$("#email").val();
		var data1=$("#email").val();
		//alert(data);
		$.ajax({
			url:'./checkEmailExist',
			data:data,
			type:'post',
			success:function(result){
				if(result.match("1")){
					$("#email").val('');
					$("#emailexist").html(data1+" already exist");
				}
				else{
					$("#emailexist").html("");
				}
			}
		});
	});
	
	 $("#doj").change(function(){
		var d=new Date($("#doj").val());
		//alert(d)
		if(d.getFullYear()<1980){
			alert("your company didn't existed at that time");
			$("#doj").val('');
		}
	}); 
});
</script>
<br/>
<center><span style="width:500px;color:blue;font-size:30px;font-weight:bold;border-bottom:1px solid blue;">Create Employee</span></center>
<font color=red><h2>

&nbsp;&nbsp;&nbsp;&nbsp;${addmsg}
</h2></font>

<center>
<pre>
<form action="./AddEmployee" method="post">
name:	<input type="text" pattern="[A-Za-z]+" title="Only Allow Alphabets"  name="name" placeholder="Enter Name" required/>
<br/>
mobile:	<input type="text" maxlength="10" pattern="[789][0-9]{9}" title="Enter mobile number starting from 9,8,7"  name="mobile" placeholder="Enter Mobile No." required/>
<br/>
email:	<input type="email" pattern="[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" name="email" placeholder="Enter Email Id" id="email" required/><br/>
<font color="red" size="4"><div id="emailexist"></div></font>
password:<input type="password" name="password" placeholder="Enter Password" required/>
<br/>
date:	<input type="date" name="doj" id="doj" required/>
<br/>
Designation: <select name="designation" id="designation"required>
					<option value=null>--designation--</option>
					<option>developer</option>
					<option>manager</option>
					<option>supportstaff</option>
				</select>
				
	<div id="mid">
			<label>manager id's:</label><select name="managerid" id="managerid" >
				<option value=0>-select-</option>
			</select>
	</div>			
<input type="submit" value="add user" />

<h3><a href="adminHome">Back</a></h3>
</form>

</pre>
</center>

</body>

</html>