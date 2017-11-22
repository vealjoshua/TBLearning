<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 
  <style>
  .modal-header {
  color: #fff;
    background-image: linear-gradient(255deg, #f9b25e, #f17b30);
}</style>
</head>
<body>

<c:if test="${not empty sessionScope.msg1}">
 <div class="alert alert-success alert-dismissable">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    <strong>${sessionScope.msg1}</strong> 
    </div>
    <div class="alert alert-success alert-dismissable" >
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    <strong>${request.getSession().getAttribute("msg2")}</strong> 
  </div>

</c:if>
<div class="container">
  <h2>Modal Example</h2>
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
	<form data-toggle="validator" role="form" method="post" action="LoginServlet">
				
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Course Code</label>
								<input type="text"  name="Course_code" placeholder="Enter Course Code Here.." class="form-control" min="2" required>
							</div>
							<div class="col-sm-6 form-group">
								<label>Course Title</label>
								<input type="text"  name="Course_Desc" pattern="^[a-zA-Z0-9]+$" placeholder="Enter Course Title Here.." class="form-control" required>
							</div>
						</div>	
                        <div class="clearfix visible-xs-block"> </div>
						
						<div class="col-sm-6  form-group">
							<label>Year</label>
							<input type="number" name="Course_year" placeholder="Enter Year  Here.." value="2017"class="form-control" maxlength=10 required>
						</div>	
						<div class="col-sm-6 form-group">
								<label>Semester</label>
								<input type="text" name="Course_Semster" placeholder="Enter Semester Here.." class="form-control" required>
							</div>
							<div class="col-sm-6 form-group">		
			<label >Course Time</label>
			<div class="radio">
				<label>
				<input  type="radio" name="Course_Time" value="D" required>Day</label></br>
				<label>
				<input  type="radio" name="Course_Time" value="E" required>Evening</label></br>
				<label>
				<input  type="radio" name="Course_Time" value="DE" required>Both</label></br>
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
		<button type="submit" id="createcourse" class="btn btn-lg btn-info col-sm-3.5 col-md-offset-0.5"><div class="col-spaced">Submit</div></button> 
		<button type="cancel" class="btn btn-lg btn-warning col-sm-3.5 col-md-offset-1" data-dismiss="modal"><div class="col-spaced">Cancel</div></button> 
          
      <script>
                                    document.getElementById("createcourse").onclick = function() {
                                    document.getElementById("task").value = "courseCreation";                        
                                    };
                                   
 </script>
	</div>				
</center>				
</div>
     </div>
      
    </div>
  </div>
  
</div>

</body>
</html>
