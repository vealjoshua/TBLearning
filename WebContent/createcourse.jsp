<html>
<head>
<title>create course</title>
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
	    request.getSession(false).setMaxInactiveInterval(60);
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

<style>
body {
    background-color: lightblue;
}
@import "font-awesome.min.css";
@import "font-awesome-ie7.min.css";
/* Space out content a bit */
body {
  padding-top: 20px;
  padding-bottom: 20px;
}

/* Everything but the jumbotron gets side spacing for mobile first views */
.header,
.marketing,
.footer {
  padding-right: 15px;
  padding-left: 15px;
}

/* Custom page header */
.header {
  border-bottom: 1px solid #e5e5e5;
}
/* Make the masthead heading the same height as the navigation */
.header h3 {
  padding-bottom: 19px;
  margin-top: 0;
  margin-bottom: 0;
  line-height: 40px;
}

/* Custom page footer */
.footer {
  padding-top: 19px;
  color: #777;
  border-top: 1px solid #e5e5e5;
}

/* Customize container */
@media (min-width: 768px) {
  .container {
    max-width: 730px;
  }
}
.container-narrow > hr {
  margin: 30px 0;
}

/* Main marketing message and sign up button */
.jumbotron {
  text-align: center;
  border-bottom: 1px solid #e5e5e5;
}
.jumbotron .btn {
  padding: 14px 24px;
  font-size: 21px;
}

/* Supporting marketing content */
.marketing {
  margin: 40px 0;
}
.marketing p + h4 {
  margin-top: 28px;
}

/* Responsive: Portrait tablets and up */
@media screen and (min-width: 768px) {
  /* Remove the padding we set earlier */
  .header,
  .marketing,
  .footer {
    padding-right: 0;
    padding-left: 0;
  }
  /* Space out the masthead */
  .header {
    margin-bottom: 30px;
  }
  /* Remove the bottom border on the jumbotron for visual effect */
  .jumbotron {
    border-bottom: 0;
  }
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
</head>
<body>
<% int timeout=request.getSession(false).getMaxInactiveInterval();%>
<input type="hidden" id="timeout" value=<%=timeout%> />
<script type="text/javascript">
            checkSession();
</script>
<div class="container">
    <h1 class="well">Course Creation Form</h1>
	<div class="col-lg-12 well">
	<div class="row">
	<form data-toggle="validator" role="form" method="post" action="CreateCourseServlet">
				
					<div class="col-sm-12">
						<div class="row">
							<div class="col-sm-6 form-group">
								<label>Course Code</label>
								<input type="text" id="course_code" name="courseCode" placeholder="Enter Course Code Here.." class="form-control" min="2" required>
							</div>
							<div class="col-sm-6 form-group">
								<label>Course Title</label>
								<input type="text" id="course_title" name="courseTitle" pattern="^[a-zA-Z0-9]+$" placeholder="Enter Course Title Here.." class="form-control" required>
							</div>
						</div>	
                        <div class="clearfix visible-xs-block"></div>
						
						<div class="col-sm-6  form-group">
							<label>Year</label>
							<input type="number" name="year" placeholder="Enter Year  Here.." value="2017"class="form-control" maxlength=10 required>
						</div>	
						<div class="col-sm-6 form-group">
								<label>Semester</label>
								<input type="text" name="semester" placeholder="Enter Semester Here.." class="form-control" required>
							</div>	
						</div>	
						
                        													
					<br/>
					<center>
					<div class="form-group">
		
		<button type="submit" class="btn btn-lg btn-info col-sm-6">Submit</button> 
		
		<button type="cancel" class="btn btn-lg btn-warning col-sm-6" onclick="javascript:window.location='instructor.jsp';">Cancel</button> 

	</div>				
					</center>				
					</div>
				</form> 
				</div>
	</div>
	</div>
	</body>
	</html>