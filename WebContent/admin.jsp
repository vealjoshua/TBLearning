<html>
<head>
<title>admin</title>
<script type="text/javascript">
function checkSession() {
	var timeout = document.getElementById("timeout").value * 1000;
	var endDate = new Date().getTime() + timeout;
	
	function checkTimeOut(){
	    if(new Date().getTime() > endDate){
	        // redirect to timeout page
	        window.location = "login.jsp";
	    }
	}
	/*
	function resetGlobal() {
	    endDate = new Date().getTime() + timeout;
 	    ${pageContext.session.setMaxInactiveInterval(60)};
		console.log(endDate);
	}
	
	$(document).ready(function(){
	    $('html').mousemove(function(event){
	        resetGlobal();
	    });
	    $('html').click(function(event){
	        resetGlobal();
	    });
	    $('html').keyup(function(event){
	        resetGlobal();
	    });
	});
	*/
	window.setInterval(checkTimeOut, 1000); // check once per second
}
</script>
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
}</style>


</head>
<body>
<% int timeout=request.getSession(false).getMaxInactiveInterval();%>
<input type="hidden" id="timeout" value=<%=timeout%> />
<script type="text/javascript">
            checkSession();
</script>
<div class="container-fluid text-center">    
     <div class="col-sm-2">
      <p></p>
    </div>
    <br>
      <div class="container col-sm-10">   <div class="row">
    
    <div class="col-sm-4">
		<div class="panel panel-primary">
		<div class="panel-heading"> User Creation <br></div>
		<div class="panel-footer"><a href=registrationform.jsp >Create user</a></div>
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