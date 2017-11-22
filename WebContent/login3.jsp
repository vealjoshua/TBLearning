<html>
<head>
<title>login</title>
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
.panel-heading {
    padding: 15px 15px;

}

</style>
</head>
<body>   <div class="container" >
<h1><center> Team-Based Learning Application</center> </h1>
		<div class="row">
			<div class="col-md-offset-3 col-md-6 ">
			
				<div class="panel panel-default" style="margin-top:50px">
					<div class="panel-heading"> 
						<strong> <center>Sign In for Team-Based Learning Application</center></strong>
					</div>
					<div class="panel-body">
						<form role="form" action="LoginServlet" method="POST">
							<fieldset>
								<div class="row">
									<div class="col-sm-12 col-md-10  col-md-offset-1 ">
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-user"></i>
												</span> 
												<input class="form-control" placeholder="Username" name="ssoId" type="text"  min length="5" maxlength="10" required autofocus>
											</div>
										</div>
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-lock"></i>
												</span>
												<input class="form-control" placeholder="Password" name="password" type="password" min length="6" maxlength="8" value="" required>
											</div>
										</div>
										<div class="form-group">
											<input type="submit" class="btn btn-lg btn-primary btn-block" >
										</div>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					
                </div>
			</div>
		</div>
	</div>
	</body>
	</html>
	