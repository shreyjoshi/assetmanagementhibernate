<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList,beans.Asset"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
			<font color=white><a href="manager"><i class="" style="font-size:17px; color:#fff;">manager Home</i></a></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="social_icons" align="right">><font color=white><a href="logout"><i class="fa fa-sign-out" style="font-size:17px; color:#fff;">User Logout</i></a></font></span>
			</div>

<center>

 <font color=red><h2>${m} ${pendingrequests}</h2></font>           
	<%
	   ArrayList <Asset> list=(ArrayList)request.getAttribute("msg");
	  if(!list.isEmpty())
	  {
	  %>
   	   <table border="3">
   	   	 <tr><th>request id</th><th>user_id</th><th>Type_of_Assets</th><th>Request_Date</th><th>status</th><th>approve</th><th>reject</th></tr>
     <%
          for(Asset ee:list)
          {
  	  %>
        <form action="./updateStatus" method="post">
			<tr>
			
					<td><input type="text" name=assetid value="<%=ee.getAssetid()%>" readonly/></td>
					<td><input type="text" name=emailid value="<%=ee.getemailid()%>" readonly/></td>
					<td><input type="text" name=assetname value="<%=ee.getAssetname()%>" readonly/></td>
					<td><input type="text" name=requestdate value="<%=ee.getRequestdate()%>" readonly/></td>
					<td><% int x=ee.getRequeststatus();
						if(x==0){%>
						<p>pending with manager</p><% 
						}else if(x==1)
						{%><p>approved by manager pending with support staff</p><%
						}else if(x==2)
						{%><p>rejected by manager </p><%
						}else if(x==3)
						{%><p>approved by support staff</p><%
						}else if(x==4)
						{%><p>rejected by support staff</p>
						<% 
						}
						%>
					</td>
					<td>
					<input type="submit" value="approve" name="op" /></td>
					<td><input type="submit" value="reject" name="op" /></td>
			
			</tr>
</form>
                <%
                } 
            	%>
            	 </table>  
            	   <% 
            	   } 
               else
               {               
                %>
                <strong><font color="Orange" size="5">no requests!!</font>
               <%} %>
                
        </table>

</center>
<center><h3><a href="GoTOHome">Back</a></h3></center>
</body>
</html>