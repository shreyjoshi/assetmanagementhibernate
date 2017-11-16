<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList,beans.Asset"%>
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
			<font color=white><a href="employeehome"><i class="" style="font-size:17px; color:#fff;">Employee Home</i></a></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span class="social_icons" align="right">><font color=white><a href="logout"><i class="fa fa-sign-out" style="font-size:17px; color:#fff;">User Logout</i></a></font></span>
			</div>
	<br/>		
<center><span style="width:500px;color:blue;font-size:30px;font-weight:bold;border-bottom:1px solid blue;">Asset Request Progress</span></center>
			
<font color=red>
<h2>${deletemsg}</h2>
</font>
<center><table border="3">

            
            <tr><th>request id</th><th>user_id</th><th>Type_of_Assets</th><th>Request_Date</th><th>status</th><th>Action_Task</th></tr>
         <%
                ArrayList <Asset> list=(ArrayList)request.getAttribute("LIST");
         	if(list!=null)
         	{
                for(Asset ar:list)
                {
        %>
            <tr> 
            <form action="./updateRequest" method="post" >
            	<td><center><input type="text" name="assetid" value="<%=ar.getAssetid() %>" readonly /></center></td>
                <td><center><%=ar.getemailid()%></center></td>
                <td><center><%=ar.getAssetname()%></center></td>
                <td><center><%=ar.getRequestdate()%></center></td>
                <td><center><% int x=ar.getRequeststatus();
                				if(x==0){
                					out.println("pending with manager");
                				}else if(x==1)
                				{
                					out.println("pending with support staff");
                				}else if(x==2)
                				{
                					%>
                					<font color="red">rejected by manager</font>
                				<%}else if(x==3)
                				{
                					%>	
                				<font color="green">approved from support</font>
                					<%
                				}else
                				{
                					%>
                				<font color="red">rejected support staff</font>
                				<%}
                				%></center></td>               			                
               <!--   <td><center><input type="submit" name="op" value="Cancel" /></center></td> -->
        	<%if(x==3){
                		%>
                		<td id="approvedstatus"><font color="red">you request for<%=ar.getAssetname() %> has been approved collect it from support staff</font></td>
                	<%} %>	
        	</form>
                </tr>
                <%
              	 } 
         	}                
                %>
               
        </table>
        </center>

<h3><a href="employeehome">Back</a></h3>

</body>
</html>