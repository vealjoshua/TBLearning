<html>
<head>
<title>registration</title>
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
	    "${request.getSession(false).setMaxInactiveInterval(60)}";
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="Stylings.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
</head>
<body>
<% int timeout=request.getSession(false).getMaxInactiveInterval();%>
<input type="hidden" id="timeout" value=<%=timeout%> />
<script type="text/javascript">
            checkSession();
</script>
<div class="container" >
<h1 class="well">Registration Form</h1>
<div class="row">
<div class="col-lg-12 well">
<form data-toggle="validator" role="form" method="post" action="RegistrationServlet">
  <div class="form-group">
	<div class="row">
		<div class="col-sm-6 form-group">
			<label for="firstname" class="control-label" >First Name</label>
			<input id="firstname" type="text" placeholder="Enter First Name Here.." class="form-control" name="firstName" minlength="2" required />
		</div>
		<div class="col-sm-6 form-group">
			<label for="lastname" class="control-label">Last Name</label>
			<input id="lastname" type="text" placeholder="Enter Last Name Here.." class="form-control" name="lastName" minlength="2" required />
		</div>	
  
	</div>
	<div class="row">
		<div class="col-sm-6  form-group">
			<label for ="ssoid" class="control-label" >SSO ID</label>
			<input id="ssoid" name="ssoId" type="text" pattern="^[a-zA-Z0-9]+$" placeholder="Enter SSO ID  Here which is number only.." class="form-control help-block with-errors" minlength="6" maxlength="10" data-error="Enter only numbers" required/>
		</div>	
		<div class="col-sm-6 form-group">
			<label for="department" class="control-label" >Department</label>
			<input id="department" name="department" type="text" placeholder="Enter Department Name Here.." class="form-control" minlength="2" required />
		</div>	
	</div>
	
	<div class="form-group">
		<div class="col-sm-7 ">						
			<label for="userrole">User Role</label>
			<div class="radio">
				<label>
				<input id="userrole" type="radio" name="optradio" value="ins" required>Instructor</label></br>
				<label>
				<input id="userrole" type="radio" name="optradio" value="admin" required>Admin</label></br>
				<label>
				<input id="userrole" type="radio" name="optradio" value="both" required>Both</label></br>
			</div>
			
		</div>
	</div>
	
	<div class="form-group">
		
		<button type="submit" class="btn btn-lg btn-info col-sm-6">Submit</button> 
		
		<button type="cancel" class="btn btn-lg btn-warning col-sm-6" onclick="javascript:window.location='admin.jsp';">Cancel</button> 

	</div>
  </div>
</form>
</div>
</div>
</div>
</body>
</html>