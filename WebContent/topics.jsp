<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.sql.*"%>
<%@ page import="java.sql.ResultSet" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script language="javascript" type="text/javascript">  
      var xmlHttp ;
      var xmlHttp;
      function showSemester(str){
      if (typeof XMLHttpRequest != "undefined"){
      xmlHttp= new XMLHttpRequest();
      }
      else if (window.ActiveXObject){
      xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");
      }
      if (xmlHttp==null){
      alert("Browser does not support XMLHTTP Request")
      return;
      } 
      var url="Semester.jsp";
      url +="?count=" +str;
      xmlHttp.onreadystatechange = stateChange;
      xmlHttp.open("GET", url, true);
      xmlHttp.send(null);
      
      
      }

      function stateChange(){   
      if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){   
      document.getElementById("Offered").innerHTML=xmlHttp.responseText; 
      
      }   
      }

      function showCourses(str){
      if (typeof XMLHttpRequest != "undefined"){
        xmlHttp= new XMLHttpRequest();
        }
      else if (window.ActiveXObject){
        xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");
        }
      if (xmlHttp==null){
      alert("Browser does not support XMLHTTP Request")
      return;
      } 
      var count = document.getElementById("Year").value;
      var url="Dropdown_Course.jsp";
      url +="?count="+count+"&count1="+str;
      xmlHttp.onreadystatechange = stateChange1;
      xmlHttp.open("GET", url, true);
      xmlHttp.send(null);
      }
      function stateChange1(){   
      if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete"){   
      document.getElementById("Courses").innerHTML=xmlHttp.responseText   
      }   
      }
      </script>  
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
<%@include file="includes/header.jsp" %>
<c:if test="${not empty sessionScope.msg1}">
 <div class="alert alert-success alert-dismissable">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    <strong>${sessionScope.msg1}</strong> 
    </div>
</c:if>
<div class="container" align="right" style="padding-right:50px">
 
  <!-- Trigger the modal with a button -->
  <button type="button"   class="btn btn-info btn" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-plus"></span>Add Topic</button>
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
     <form action="LoginServlet" method="post">
        <div class="modal-header" align="center">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Topics</h4>
        </div>
        <div class="modal-body" align="left">
          
          <table >
      <tr><th class=" form-control col-sm-3.5 col-md-offset-0.5">Year</th><td>
      <select id="Year" name='Year' onchange="showSemester(this.value)" class="form-control col-sm-3.5 col-md-offset-0.5" style="width:250px;">  
       <option value="none">Select</option>  
    <%
 Class.forName("com.mysql.jdbc.Driver").newInstance();  
 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tblearning_db","root","");  
 Statement stmt = con.createStatement();  
 ResultSet rs = stmt.executeQuery("Select distinct(Course_year) from course");
 while(rs.next()){
     %>
      <option value="<%=rs.getInt(1)%>"><%=rs.getInt(1)%></option>  
      <%
 }
     %>
      </select> 
      </td></tr>
      <tr><th class=" form-control col-sm-6 col-md-offset-0.5">Offered</th><td id='Offered'><select name='Offered' >  
      <option value='-1'></option>  
      </select>
      </td></tr>
      <tr><th class=" form-control col-sm-6 col-md-offset-0.5">Courses</th>
         
       <td id='Courses'> <select name='Courses' >  
      <option value='-1'></option>  
      </select>   
      </td>
      </tr>
      </table>
     <div class="col-sm-8 form-group">
								<label>Topic Title</label>
								<input type="text"  name="Tobics_Desc" pattern="^[a-zA-Z\s]+$" placeholder="Enter Topic Title Here.." class="form-control" required>
	  </div>
	  </div>
	  
        <div class="modal-footer">
        <div class="form-group">
         
		<input type="hidden" id="task" name="task"/>
		<button  type=button id="createCourse" class="btn btn-lg btn-info col-sm-3.5 col-md-offset-0.5"><div class="col-spaced">Submit</div></button> 
		  <script>
                                    document.getElementById("createCourse").onclick = function() {
                                    document.getElementById("task").value = "topicCreation";   
                                    
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
							
							<td  width="40%" >Topic Description</td>
							<td width="30%" >Course Code</td>
							<td width="40%">Course Title</td>
							
							
					</tr>
			</thead>
				<%ResultSet res=(ResultSet)request.getAttribute("resultSet");//String a1="null",a2="null";
				//ResultSet rs2=(ResultSet)request.getAttribute("resultSet_course");
				while (res.next()) {%>
				
				
<tr>	
	<td id="<%res.getInt(1); %>" width="40%"> <%=res.getString(2) %></td>
	<td id="<%res.getInt(1); %>" width="30%"> <%=res.getString(9) %></td>
	<td id="<%res.getInt(1); %>" width="40%"> <%=res.getString(10) %></td>
</tr>
<%}%>
</table>
</div>
</div>

</body>
</html>
