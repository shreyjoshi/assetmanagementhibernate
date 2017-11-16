<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ManagerHomePages</title>
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
${msg}

	<!----Header---->
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
			${transfermsg}
			<div class="container">
			<div class="row">
			<div class="col-sm-12">
		
			
			<h3><a href="ManagerProfile">My Profile</a></h3>
			<h3><a href="CreateManagerRequest">Create Request</a></h3>
			<h3><a href="ViewManagerRequest">View my request</a></h3>
			<h3><a href="MyAssetManager">My Assets</a></h3>
			<h3><a href="AssetsTransfor">Assets Transfer</a></h3>
			<h3><a href="Employee_Assets">View Employee And Assets</a></h3>
			<h3><a href="PendingRequestApproval">Pending Request And Approval</a></h3>
			<h3><a href="PendingTransferRequests">Pending Transfer Requests</a></h3>
			<h3><a href="#">View all Request by Employee</a></h3>
			<hr width="3" class="upper" size="280">
			</div>
			</div>
			</div>
			
	
<hr class="ram">
<center><footer><font color=green><h3>&copy; Copyright ® 2017 AssetManagement.in All rights reserved.
Developed and Powered by R.K. & Team</h3></font></footer></center>



</body>
</html>