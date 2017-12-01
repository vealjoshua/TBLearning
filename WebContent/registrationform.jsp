<html>
<head>
<title>registration</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

 <script>
                                    document.getElementById("register").onclick = function() {
                                    document.getElementById("task").value = "userCreation";                        
                                    };
                                   
 </script>
 
</head>
<body>
<%@include file="includes/admin_header.jsp" %>

<div class="container">
  
  <!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-info btn" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-plus"></span></button>
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add Course</h4>
        </div>
        <div class="modal-body">
          	<div class="row">
          	<div class="container" style="width: 504px;" >
<h1 class="well">Registration Form</h1>
<div class="row">
<div class="col-lg-12 well">
<form data-toggle="validator" role="form" method="post" action="LoginServlet">
  <div class="form-group">
	<div class="row">
		<div class="col-sm-6 form-group">
			<label for="firstName" class="control-label" >First Name</label>
			<input id="firstName" type="text" placeholder="Enter First Name Here.." class="form-control" name="firstName" minlength="2" required />
		</div>
		<div class="col-sm-6 form-group">
			<label for="lastName" class="control-label">Last Name</label>
			<input id="lastName" type="text" placeholder="Enter Last Name Here.." class="form-control" name="lastName" minlength="2" required />
		</div>	
  
	</div>
	<div class="row">
		<div class="col-sm-6  form-group">
			<label for ="ssoId" class="control-label" >SSO ID</label>
			<input id="ssoId" name="ssoId" type="text" pattern="^[a-zA-Z0-9]+$" placeholder="Enter SSO ID  Here which is number only.." class="form-control help-block with-errors" minlength="6" maxlength="10" data-error="Enter only numbers" required/>
		</div>	
		<div class="col-sm-6 form-group">
			<label for="department" class="control-label" >Department</label>
			<input id="department" name="department" type="text" placeholder="Enter Department Name Here.." class="form-control" minlength="2" required />
		</div>	
	</div>
	
	<div class="col-sm-6 form-group">
								<label>Email ID</label>
								<input type="text" id="email" name="email" placeholder="Enter registered email id Here.." class="form-control" min="7" required>
							</div>
<div class="col-sm-6 form-group">
								<label>User ID</label>
								<input type="number" id="userId" name="userId" placeholder="Enter User ID Here.." class="form-control" min="7" required>
							</div>
	<div class="form-group">
		<div class="col-sm-6 ">						
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
		
						</div>	
						
                        													
					<br/>
					<center>
					
					
	
		


        </div>
        <div class="modal-footer">
      
	<div class="form-group">
		<input type="hidden" id="task" name="task"/>
		
		<button type="submit" id="register"  class="btn btn-lg btn-info col-sm-4.5 col-md-offset-3">Submit</button> 
		<button type="cancel" class="btn btn-lg btn-warning col-sm-4.5 col-md-offset-1" onclick="javascript:window.location='admin.jsp';">Cancel</button> 

	</div>
  </div>
  </div>				
</center>				
</div>
</form>
</div>
</div>
</div>
</body>
</html>