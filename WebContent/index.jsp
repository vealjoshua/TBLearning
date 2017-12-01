<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.ResultSet" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%ResultSet rs = (ResultSet) request.getAttribute("resultSet"); 
%>
<div class="marginTable"  data-count="5">
<%        while (rs.next()) {%>
                
<tr>    
    <td id="<%rs.getInt(1); %>" width="10%"><%=rs.getString(2) %></td>
    <td id="<%rs.getInt(1); %>" width="50%"><%=rs.getString(3) %></td>
    <td id="<%rs.getInt(1); %>" width="20%"><%=rs.getInt(4)%></td>
    <td id="<%rs.getInt(1); %>" width="20%" ><%=rs.getString(5) %></td>
    </tr>
<%}%>
</div>
</body>
</html>