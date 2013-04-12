<#import "/WEB-INF/freemarker/common/carousel.ftl" as common>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Hello, ${user.username} | Seu Cloud Lab</title>
		<link href="/assets/css/bootstrap.css" rel="stylesheet">
		<style type="text/css">
     	body {
        	padding-top: 60px;
        	padding-bottom: 40px;
      	}
    	</style>
		<link href="/assets/css/bootstrap-responsive.css" rel="stylesheet">	
	</head>
	<body>
		<#include "/WEB-INF/freemarker/head.ftl">
		<div class="container">
			<div class="hero-unit">
				<h1>Welcome, ${user.username}</h1>
				<p>
				This is your home page and you can explorer the website by clicking the product recommeded for you below !
				Wish you have a good time !
				</p>
				<p>
				By Seu Cloud Lab.
				</p>
				<p>
					<a href="/logout" class="btn btn-primary btn-large">Log Out</a>
				</p>
			</div>
			<#if productRecommends??>
			<h3>These are results from Hadoop:</h3>
			<@common.carousel productRecommends=productRecommends id="myCarousel1"/>
			</#if>
			<#if productRecommendsFromLogs??>
			<h3>These are results from web logs:</h3>
			<@common.carousel productRecommends=productRecommendsFromLogs id="myCarousel2"/>
			</#if>
		</div>
		<#include "/WEB-INF/freemarker/footer.ftl"/>
		<script src="/assets/js/jquery-1.9.1.js"></script>
		<script src="/assets/js/bootstrap.js"></script>
		<script src="/assets/js/holder.js"></script>
	</body>
</html>