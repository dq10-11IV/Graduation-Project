<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
		<title>Please Sign In | Seu Cloud Lab</title>
		<link href="/assets/css/bootstrap.css" rel="stylesheet">
		<style type="text/css">
	      body {
	        padding-top: 40px;
	        padding-bottom: 40px;
	        background-color: #f5f5f5;
	      }
	
	      .form-signin {
	        max-width: 300px;
	        padding: 19px 29px 29px;
	        margin: 0 auto 20px;
	        background-color: #fff;
	        border: 1px solid #e5e5e5;
	        -webkit-border-radius: 5px;
	           -moz-border-radius: 5px;
	                border-radius: 5px;
	        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
	           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
	                box-shadow: 0 1px 2px rgba(0,0,0,.05);
	      }
	      .form-signin .form-signin-heading,
	      .form-signin .checkbox {
	        margin-bottom: 10px;
	      }
	      .form-signin input[type="text"],
	      .form-signin input[type="password"] {
	        font-size: 16px;
	        height: auto;
	        margin-bottom: 15px;
	        padding: 7px 9px;
	      }
	    </style>
	    <link href="/assets/css/bootstrap-responsive.css" rel="stylesheet">  
	</head>
	<body>
		<div class="container">
			<form class="form-signin" action="login?action=login" method="post">
				<h2 class="form-signin-heading">Please Sign In</h2>
				<input name="username" type="text" class="input-block-level" placeholder="Email address">
	        	<input name="password" type="password" class="input-block-level" placeholder="Password">
	       		<label class="checkbox">
	          		<input name="rememberMe" type="checkbox" value="true"> Remember me
	        	</label>
	        	<button class="btn btn-large btn-primary" type="submit">Sign in</button>
			</form>
		<#if message??>
		<div class="alert alert-error">
			${message}
		</div>	
		</#if>
		</div>
		<script src="/assets/js/jquery-1.9.1.js"></script>
		<script src="/assets/js/bootstrap.js"></script>
	</body>
</html>