<html>
<head>
<title>admin</title>
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

<style>body {
    background-color: lightblue;
}
.navbar {
    margin-bottom: 0;
    border-radius: 0;
}
#navColor{
    background-color: #337ab7 ;
   border-color: #337ab7 ;
}

#navLogo{
    color: #fff;
   background-color: transparent;
}</style>
</head>
<body>
<div class="container-fluid text-center">    
     <div class="col-sm-2">
      <p></p>
    </div>
<nav class="navbar navbar-inverse" id="navColor">
 <div class="container-fluid">
   <div class="navbar-header">
     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>                        
     </button>
     <a class="navbar-brand" id="navLogo" href="#">Logo</a>
   </div>
   <div class="collapse navbar-collapse" id="myNavbar">
     <ul class="nav navbar-nav navbar-right">
       <li><a href="LogoutServlet" id="navLogo"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
     </ul>
   </div>
 </div>
</nav>
    <br>
      <div class="container col-sm-10">   <div class="row">
    
    <div class="col-sm-4">
		<div class="panel panel-primary">
		<div class="panel-heading"> User Creation <br></div>
		<div class="panel-footer"><a href=registrationform.html >Create user</a></div>
		</div>
	</div>

	  
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
     <div class="panel-heading"> Edit User <br></div>
     <div class="panel-footer"><a href=edit.html>Edit User </a></div>
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
     <div class="panel-heading"> Sort User <br></div>
	 <div class="panel">
	 <div class="radio">
                        <label><input type="radio" name="optradio" value="fname">First name</label>
                        </div>
                        <div class="radio">
                        <label><input type="radio" name="optradio" value="lname">Last name</label>
                        </div>
	 </div>
     <div class="panel-footer"><a href=#>Sort User</a></div>
     </div></div>

	  
</div> </div> 
	  
</div> 

</body>
</html>   