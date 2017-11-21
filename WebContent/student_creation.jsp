<html>
<head>
<script type="text/javascript">
	window.MathJax = {
		tex2jax : {
			inlineMath : [ [ '$', '$' ], [ "\\(", "\\)" ] ],
			processEscapes : true
		}
	};
</script>
<title>Student Creation</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="Stylings.css">

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
</head>
<body>
<%@include file="includes/header.jsp" %>
<div class="container" >
<h1 class="well">Registration Form</h1>
<div class="row">
<div class="col-lg-12 well">
<c:if test="${not empty sessionScope.msg1}">
 <div class="alert alert-success alert-dismissable">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    <strong>${sessionScope.msg1}</strong> 
    </div>
</c:if>
<form data-toggle="validator" role="form" method="post" action="createstudentservlet">
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
		<div class="col-sm-6  form-group">
							<label>Student number</label>
							<input name="stdno" placeholder="Enter Student Number Here.." class="form-control" maxlength=10 required>
	</div></div>
	<div class="row">
	<div class="col-sm-6  form-group">
							<label for="courseId">Courses</label>
							<select id="courseId" name="course" value="courseId">
						<option selected value="">Select</option>
						<%
							try {
								String Query = "select * from course";
								Class.forName("com.mysql.jdbc.Driver");
								Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tblearning_db", "root", "");
								Statement stm = conn.createStatement();
								ResultSet res = stm.executeQuery(Query);

								while (res.next()) {
						%>

						<option value=<%=res.getString("course_code")%>>
							<%=res.getString("course_title")%>
							</option>
						<%
							}
								res.close();

							} catch (Exception e) {
								e.printStackTrace();
							}
						%>

				</select>

	</div>
	</div>
	
	
	
	<div class="form-group">
		<button type="submit" class="btn btn-lg btn-info col-sm-3.5 col-md-offset-0.5"><div class="col-spaced">Submit</div></button> 
		<button type="cancel" class="btn btn-lg btn-warning col-sm-3.5 col-md-offset-1" onclick="javascript:window.location='instructor.jsp';"><div class="col-spaced">Cancel</div></button> 
		<button type="cancel" class="btn btn-lg btn-info col-sm-3.5 col-md-offset-1" onclick="javascript:window.location='coursemap.jsp';"> <div class="col-spaced">Display</div></button>
	</div>
  </div>
</form>
</div>
</div>
</div>
</body>
</html>