<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"%>
<%@page import="java.sql.*,java.io.*,java.text.*,java.util.*"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="Stylings.css">

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script>
  
</script>
  <style>
  .modal-header {
  color: #fff;
    background-image: linear-gradient(255deg, #f9b25e, #f17b30);
}
.dropdownbtn
{

}
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
<%@include file="includes/header.jsp" %>
<div class="container">
  <h2>Quiz page</h2>
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-info btn" align="right" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-plus"></span>Add Quiz</button>


  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
      <form action="LoginServlet" method="post">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title" align="center" >Add Quiz</h4>
        </div>
        <div class="modal-body" align="left">
          	<div class="row">
	
				
					<div class="col-sm-12">
						<div class="row">
						<div class="col-sm-6 form-group">
						<label for="courseId">Courses</label>
						<select id="courseId" name="Quiz_Course_ID" >
						<div class="dropdownbtn">
						<option selected value="Quiz_Course_ID">Select</option>
						<%
							try {
								String Query = "select * from course";
								Class.forName("com.mysql.jdbc.Driver");
								Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tblearning_db", "root", "");
								Statement stm = conn.createStatement();
								ResultSet res = stm.executeQuery(Query);

								while (res.next()) {
						%>

						<option value=<%=res.getInt("Course_ID")%>>
							<%=res.getString("Course_Desc")%>
							</option>
						<%
							}
								res.close();

							} catch (Exception e) {
								e.printStackTrace();
							}
						%>

				</select></div>
								
                                </div>
                               
                                <div class="col-sm-12 form-group">
								<label>Quiz Title</label>
								<input type="text"  name="Quiz_Desc" pattern="^[a-zA-Z0-9\s]+$" placeholder="Enter Quiz Title Here.." class="form-control" required>
							</div>
                               <div class="col-sm-6  form-group"><label>Time Limit (In minutes)</label><input type="time" name="timeLimit" placeholder="Enter Time limit" value="30"class="form-control"   required ></div>
 <div class="col-sm-12  form-group">
							
 <label>Duration of Quiz</label></div>
 <div class="col-sm-6  form-group">
  <label for="start_time" class="col-2 col-form-label"> Start Date and time</label>
  <div class="col-10">
    <input class="form-control" type="datetime-local" value="2017-12-01 13:15:00" name="startTime">
</div>
</div>
 <div class="col-sm-6  form-group">
  <label for="end_time" class="col-2 col-form-label"> End Date and time</label>
  <div class="col-10">
    <input class="form-control" type="datetime-local" value="2017-12-01 13:45:00" name="endTime">
 
</div>
</div>
 
  						
     
  </div>
  </div>
 
							</div>
					
					
					
	
		


        
        <div class="modal-footer" align="right">
        <div class="form-group">
		<input type="hidden" id="task" name="task"/>
		<button type="submit" id="createQuiz" class="btn btn-lg btn-info col-sm-3.5 col-md-offset-0.5"><div class="col-spaced">Submit</div></button> 
		<button  class="btn btn-lg btn-warning col-sm-3.5 col-md-offset-1" data-dismiss="modal"><div class="col-spaced">Cancel</div></button> 
      		  <script>
                                    document.getElementById("createQuiz").onclick = function() {
                                    document.getElementById("task").value = "quizCreation";   
                                    
                                    }
                                  
 </script>    
     
	</div>				
			
</div>
 </form>
</div>
     </div>
     
    </div>
  </div>
<div class ="panel-body" align="center" id="panelbox">
<div id="panelwithtable"  align="center" class = "panel panel-default" style="width:70%">
   
 <table align="center" width="50%"  cellspacing="6" id="display" style="width: 90%;">
    
      <thead>
    

<tr>
							
							<td width="50%" >Quiz Title</td>
							<td width="20%">Course_Code</td>
							<td width="20%">Course_Title</td>
							<td width="20%" >Time limit</td>
							<td width="20%">Start Time</td>
							<td width="20%">End Time</td>
							
					</tr>
			</thead>
				<%ResultSet rs=(ResultSet)request.getAttribute("resultSet");String a="null";
				while (rs.next()) {%>
				
			
<tr>	
	<td id="<%rs.getInt(1); %>" width="10%"> <%=rs.getString(2) %></td>
	<td id="<%rs.getInt(1); %>" width="50%"> <%=rs.getString(11) %></td>
	<td id="<%rs.getInt(1); %>" width="20%"> <%=rs.getString(12) %></td>
	<td id="<%rs.getInt(1); %>" width="20%"> <%=rs.getString(6) %></td>
	<td id="<%rs.getInt(1); %>" width="20%"> <%=rs.getString(7) %></td>
	<td id="<%rs.getInt(1); %>" width="20%"> <%=rs.getString(8) %></td>
	
	</tr>
<%}%>
</table>
</div>
</div>
 
  


</body>
</html>
