<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<script type="text/javascript"
	src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
</script>
<script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/md5.js"></script>


<style>
body {
    background-color: lightblue;
}
.panel-heading {
    padding: 15px 15px;

}

</style>
</head>
<body> 

<div class="container" >
<h1><center> Team-Based Learning Application</center> </h1>
		<div class="row">
			<div class="col-md-offset-3 col-md-6 ">
<c:if test="${not empty requestScope.error}">
 <div class="alert alert-success alert-dismissable">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    <strong>${requestScope.error}</strong> 
    <c:if test="${not empty requestScope.attempt}">
     <strong>${requestScope.attempt}</strong> 
 </c:if>
  </div>
</c:if>
			
			
				<div class="panel panel-default" style="margin-top:50px">
					<div class="panel-heading"> 
						<strong> <center>Sign In for Team-Based Learning Application</center></strong>
					</div>
					<div class="panel-body">
						<form action="LoginServlet" method="post">
							<fieldset>
							<h4> ${request.msg}</h4>
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
												<!--  <input class="form-control" placeholder="Password" name="password" type="password" min length="6" maxlength="8" value="" required>-->
											</div>
										</div>
										<div class="form-group">
										<div class="col-sm-6  form-group">
										<input type="submit" id="loginform" class="btn btn-lg btn-primary btn-block">
										</div>
											<input type="hidden" id="task" name="task"/>
											<div class="col-sm-6  form-group">
													<button type="button" id="forgotpassword" class="btn btn-lg btn-warning col-sm-3.5 col-md-offset-1" data-toggle="modal" data-target="#myModal">
													<div class="col-spaced">Forgot Password</div></button> 	
										</div>
									<script>
                                    document.getElementById("loginform").onclick = function() {
                                    document.getElementById("task").value = "login";                        
                                    };
                                   
                                   </script>
                                  <script>
                                    document.getElementById("forgotpassword").onclick = function() {
                                    document.getElementById("task").value = "forgotPassword";                        
                                    };
                                   
                                   </script>
                                  
                                      
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
	</div>
<!--onclick="encrypt()"-->
<script>
    function encrypt()
    {
    var hash = "123";
    hash=CryptoJS.MD5("password");
    alert(hash);
    }
 </script>	
 </body>
</html>
	