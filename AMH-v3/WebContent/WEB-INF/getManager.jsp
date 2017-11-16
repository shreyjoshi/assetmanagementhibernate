

<%@page import="java.util.ArrayList,beans.Employee" %>
					<%
					ArrayList<Integer> list=(ArrayList)request.getAttribute("mid");
					if(list!=null){
						for(Integer e:list)
						{
						%>
						<option><%=e%></option>
				
						<%
						}}
						%>
	