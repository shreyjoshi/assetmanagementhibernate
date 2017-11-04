<%@page import="beans.Asset"%>
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
<br><a href="GoTOHome">GOTO Home</a>


    <%//<td><%=aa.getDateofallocation()%></td> %>>
    	 
      <table border="1">
            
            <tr><th>Asset_id</th><th>user_id</th><th>Asset_name</th><th>Allocation_date</th></tr>
            <%@page import="java.util.ArrayList,beans.AllocatedAsset"%>
         <%
         ArrayList<AllocatedAsset> list=(ArrayList<AllocatedAsset>)request.getAttribute("msg");
         if(!list.isEmpty())
         {
          for(AllocatedAsset aa:list)  
                {
        %>
            <tr> 
          
                <td><%=aa.getAllocatedid()%></td>
               <td><%=aa.getEmailid()%></td>
                  <td><%=aa.getAssetname()%></td>
              	<td><%=aa.getDateofallocation() %></td>
                  
           </tr>
                <%
                } 
                }  else{              
                %>
                <font color="red">no asset is alloted yet</font>
                <%} %>
                </center>
        </table>

</body>
</html>