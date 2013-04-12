<#import "/WEB-INF/freemarker/common/carousel.ftl" as common>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Search Result | Seu Cloud Lab</title>
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
		<#if searchResult??>
		<table class="table table-striped table-bordered table-hover">
			<tr>
				<td>Product Id</td>
				<td>Product Name</td>
			</tr>
			<#list searchResult as item>
			<tr>
				<td><a href="/product?productId=${item.id}">${item.id}</a></td>
				<td><a href="/product?productId=${item.id}">${item.productName}</a></td>
			</tr>
			</#list>
		</table>
		</#if>
		<#if productRecommendsFromSearch??>
		<h3>These are results from search results:</h3>
		<@common.carousel productRecommends=productRecommendsFromSearch/>
		</#if>
		</div>
		<#include "/WEB-INF/freemarker/footer.ftl"/>
		<script src="/assets/js/jquery-1.9.1.js"></script>
		<script src="/assets/js/bootstrap.js"></script>
		<script src="/assets/js/holder.js"></script>
	</body>
</html>