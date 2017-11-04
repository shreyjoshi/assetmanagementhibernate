<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${msg }

         <center> <h3>Apply for Assets</h3></center>

          <center>
          <form action="./applyCreateManagerRequest" method="post">
          
          
         Asset name<select name = "assetname" required>
               <option value = "Laptop">Laptop</option>
               <option value = "Card">Card</option>
               <option value = "Computer">Computer</option>
               <option value = "Printer">Printer</option>
             </select>
        
          
       
          <br><br>
          <input type="submit" value="Apply"/>
          
        
          
          </form>
          
          
          </center>


</body>
</html>