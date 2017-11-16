<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login Page</title>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <style>
        /* Basics */
        html, body
        {
            padding: 0;
            margin: 0;
            width: 100%;
            height: 100%;
            font-family: "Helvetica Neue" , Helvetica, sans-serif;
            background: #FFFFFF;
        }
        
         .fullscreen_bg {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-size: cover;
    background-position: 50% 50%;
    background-image: url('https://imgur.com/EdPZARd.jpg');
    background-repeat:repeat;
  }
        .logincontent
        {
            position: fixed;
            width: 350px;
            height: 300px;
            top: 50%;
            left: 50%;
            margin-top: -150px;
            margin-left: -180px;
            background: #07A8C3;
            padding-top: 10px;
        }
        .loginheading
        {
            border-bottom: solid 1px #ECF2F5;
            padding-left: 18px;
            padding-bottom: 10px;
            color: #ffffff;
            font-size: 20px;
            font-weight: bold;
            font-family: sans-serif;
        }
        label
        {
            color: #ffffff;
            display: inline-block;
            margin-left: 18px;
            padding-top: 10px;
            font-size: 15px;
        }
        input[type=email], input[type=password]
        {
            font-size: 14px;
            padding-left: 10px;
            margin: 10px;
            margin-top: 12px;
            margin-left: 18px;
            width: 300px;
            height: 35px;
            border: 1px solid #ccc;
            border-radius: 2px;
            box-shadow: inset 0 1.5px 3px rgba(190, 190, 190, .4), 0 0 0 5px #f5f5f5;
            font-size: 14px;
        }
        input[type=checkbox]
        {
            margin-left: 18px;
            margin-top: 30px;
        }
        .loginremember
        {
            background: #ECF2F5;
            height: 70px;
            margin-top: 20px;
        }
        .check
        {
            font-family: sans-serif;
            position: relative;
            top: -2px;
            margin-left: 2px;
            padding: 0px;
            font-size: 12px;
            color: #321;
        }
        .loginbtn
        {
            float: right;
            margin-right: 20px;
            margin-top: 20px;
            padding: 6px 20px;
            font-size: 14px;
            font-weight: bold;
            color: #fff;
            background-color: #07A8C3;
            background-image: -webkit-gradient(linear, left top, left bottom, from(#07A8C3), to(#6EE4E8));
            background-image: -moz-linear-gradient(top left 90deg, #07A8C3 0%, #6EE4E8 100%);
            background-image: linear-gradient(top left 90deg, #07A8C3 0%, #6EE4E8 100%);
            border-radius: 30px;
            border: 1px solid #07A8C3;
            cursor: pointer;
        }
        .loginbtn:hover
        {
            background-image: -webkit-gradient(linear, left top, left bottom, from(#b6e2ff), to(#6ec2e8));
            background-image: -moz-linear-gradient(top left 90deg, #b6e2ff 0%, #6ec2e8 100%);
            background-image: linear-gradient(top left 90deg, #b6e2ff 0%, #6ec2e8 100%);
        }
        .upper{margin-top:50px;}
    </style>
</head>
<body>
<br/>

 <div id="fullscreen_bg" class="fullscreen_bg"/>

 <center class="upper"><span  style="width:500px;color:white;font-size:50px;font-weight:bold;border-bottom:1px solid blue;">Asset Management System</span></center>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red"><h3>${msg} &nbsp; ${welcome} &nbsp; ${forgetpassmsg} <br/><br/>${activationmsg}</h3></font>

    <div class="logincontent">
      <form action="./CheckLogin" method="post">
        <div class="loginheading">
            Login
        </div>
        <label for="txtUserName">
            Email Id:</label>
        <input type="email" id="txtUserName" pattern="[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder="Enter Your Emailid" name="username" required/>
        <label for="txtPassword">
            Password:</label>
        <input type="password" id="txtPassword" placeholder="Enter Your password	" name="password" required/>

           
        <div class="loginremember">
            <input type="submit" class="loginbtn" value="Login" id="btnSubmit" />
            <label class="check" for="chbRemember"><a href="forgetpassword">Forget Password</a></label>
            
        </div>
        </form>
    </div>
</body>
</html>