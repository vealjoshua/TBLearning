<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.*"%>
<%@ page import="java.sql.ResultSet" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Department</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
  <style>
  .modal-header {
  color: #fff;
    background-image: linear-gradient(255deg, #f9b25e, #f17b30);
}
#display {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#display td, #display thead {
    border: 1px solid #ddd;
    padding: 8px;
}
#display thead{
font-weight: bold;
background-color:black;
color:white;
}
#panelbox{
padding:10px,10px;}
#panelwithtable{
padding:10px;
 border:1px solid black;
}
</style>
</head>
<body>
<%@include file="includes/admin_header.jsp" %>
<c:if test="${not empty sessionScope.msg1}">
 <div class="alert alert-success alert-dismissable">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    <strong>${sessionScope.msg1}</strong> 
    </div>
</c:if>
<div class="container" align="right" style="padding-right:50px">
 
  <!-- Trigger the modal with a button -->
  <button type="button"   class="btn btn-info btn" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-plus"></span>Add Department</button>
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
     <form action="LoginServlet" method="post">
        <div class="modal-header" align="center">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Department</h4>
        </div>
        <div class="modal-body" align="left">
          	<div class="row">
	 
				
					<div class="col-sm-12">
							<div class="col-sm-6 form-group">
								<label>Department Code </label>
								<input type="text"  name="departmentCode" placeholder="Enter Department Code Here.." class="form-control" min="2" required>
							</div>
							<div class="col-sm-6 form-group">
								<label>Department Title</label>
								<input type="text"  name="departmentDesc" pattern="^[a-zA-Z0-9\s]+$" placeholder="Enter Department Title Here.." class="form-control" required>
							</div>
					
                       </div>
                       </div>
                       </div>
        <div class="modal-footer">
        <div class="form-group">
         
		<input type="hidden" id="task" name="task"/>
		<button  id="departmentCreate" class="btn btn-lg btn-info col-sm-3.5 col-md-offset-0.5"><div class="col-spaced">Submit</div></button> 
		  <script>
                                    document.getElementById("departmentCreate").onclick = function() {
                                    document.getElementById("task").value = "departmentCreation";   
                                    
                                    }
                                  
 </script>
		<button  class="btn btn-lg btn-warning col-sm-3.5 col-md-offset-1" data-dismiss="modal"><div class="col-spaced">Cancel</div></button> 
        
    
 
	</div>				
				
</div>
     </form> </div>
      
    </div>
  </div>
  
</div>
<div class ="panel-body" align="center" id="panelbox">
<div id="panelwithtable"  align="center" class = "panel panel-default" style="width:70%">
   
 <table align="center" width="50%"  cellspacing="6" id="display" style="width: 90%;">
    
      <thead>
    

<tr>
							
							<td  width="30%">Department Code</td>
							<td width="70%">Department Title</td>
							
							
							
					</tr>
			</thead>
				<%ResultSet rs=(ResultSet)request.getAttribute("resultSet");String a="null";
				while (rs.next()) {%>
				
				
				<% ;
				
									
				%>
<tr>	
	<td id="<%rs.getString(1); %>" width="30%"> <%=rs.getString(2) %></td>
	<td id="<%rs.getString(1); %>" width="70%"> <%=rs.getString(3) %></td>
	</tr>
<%}%>
</table>
</div>
</div>

</body>
</html>
