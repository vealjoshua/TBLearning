<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.*,java.io.*,java.text.*,java.util.*" %> 
<%@page import="java.util.*" %> 
<html>
<head>
<title>Instructor</title>
  
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    /* When the user clicks on the button, 
    toggle between hiding and showing the dropdown content */
    function myFunction() {
        document.getElementById("myDropdown").classList.toggle("show");
        }

        // Close the dropdown if the user clicks outside of it
        window.onclick = function(event) {
        if (!event.target.matches('.dropbtn')) {

        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
          var openDropdown = dropdowns[i];
          if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
          }
        }
        }
    }
    </script>
    
<style>body {
    background-color: lightblue;
}

.dropbtn {
       position: relative;
   top: 13px;
   border: none;
   box-shadow: none;
   background-color: transparent;
   color: #9d9d9d ;
}
.logoutbtn{
       position: relative;
   top: 13px;
   border: none;
   box-shadow: none;
   background-color: transparent;
   color: #9d9d9d ;
}




.dropdown {
   position: relative;
   display: inline-block;
}

.dropdown-content {
   display: none;
   position: absolute;
   background-color: #f9f9f9 ;
   min-width: 160px;
   overflow: auto;
   box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
   z-index: 1;
    right: 35px;
}

.dropdown-content a {
   color: black;
   padding: 12px 16px;
   text-decoration: none;
   display: block;
}

.dropdown-content a:hover {color: #23527c !important;
   text-decoration: underline !important;}

.show {display:block;}
</style>
</head>
<body>
<%@include file="includes/header.jsp" %>
<div class="container-fluid text-center">    
     <div class="col-sm-2">
      <p></p>
    </div>
    <br>
      <div class="container col-sm-10">   <div class="row">
    
    <div class="col-sm-4">
     <div class="panel panel-primary">
     <div class="panel-heading"> Create Course<br></div>
     <div class="panel-footer"><a href=course.jsp>Create course</a></div>
     </div></div>
</div> </div> 
</div>
<div class="container-fluid text-center">    
     <div class="col-sm-2">
      <p></p>
    </div>
    <br>
      <div class="container col-sm-10">   <div class="row">
    
    <div class="col-sm-4">
     <div class="panel panel-primary">
     <div class="panel-heading"> Create Student<br></div>
     <div class="panel-footer"><a href=student_creation.jsp>Student Creation </a></div>
     </div></div></div> </div> 
</div>
<div class="container-fluid text-center">    
     <div class="col-sm-2">
      <p></p>
    </div>
    <br>
      <div class="container col-sm-10">   <div class="row">
    
    <div class="col-sm-4">
     <div class="panel panel-primary">
     <div class="panel-heading"> Import Course <br></div>
     <div class="panel-footer"><a href=#>Import Course </a></div>
     </div></div>  
</div> </div> 

</div><div class="container-fluid text-center">    
     <div class="col-sm-2">
      <p></p>
    </div>
    <br>
      <div class="container col-sm-10">   <div class="row">
    
     <div class="col-sm-4">
     <div class="panel panel-primary">
     <div class="panel-heading"> Sort Course <br></div>
	 <div class="panel">
	 <div class="radio" required>
                        <label><input type="radio" name="optradio" value="yr">Year</label>
                       
                        <label><input type="radio" name="optradio" value="sem">Semester</label>
                       
						<label><input type="radio" name="optradio" value="title" checked="checked">Course Title</label>
                        </div>
	 </div>
     <div class="panel-footer"><a href=#>Sort Course</a></div>
 
 </div> </div>

	  
</div> </div> 
	  
</div> 

</body>
</html>   