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
			<div id="myCarousel2" class="carousel slide">
			<div class="carousel-inner">
    			<#list productRecommendsFromSearch as item>
    			<#if item_index = 0>
    			<div class="active item">
    				<div class="row-fluid">
						<ul class="thumbnails">
				</#if>
							<li class="span2">
		                		<div class="thumbnail">
		                 			<img data-src="holder.js/160x120" alt="">
		                 			<div class="caption">
		                   		 		<h5>
		                   		 			<a href="/product?productId=${item.id}">${item.productName}</a>
		                   		 		</h5>
		                   		 		<p>This is the product recommended for you with product id: ${item.id}</p>
		                   		 		<p>
		                   		 			<a class="btn btn-mini" href="/product?productId=${item.id}">View details</a>
		                   		 		</p>
		                 		 	</div>
		                		</div>
	              			</li>
    			<#if (item_index + 1) %6 = 0 &&  item_index != productRecommendsFromSearch?size - 1>
    					</ul>
    				</div>
    			</div>
    			<div class="item">
    				<div class="row-fluid">
						<ul class="thumbnails">
    			</#if>
    			<#if item_index = productRecommendsFromSearch?size - 1>
    					</ul>
    				</div>
    			</div>
    			</#if>
    			</#list>
 			</div>
 			<a class="carousel-control left" href="#myCarousel2" data-slide="prev">&lsaquo;</a>
 			<a class="carousel-control right" href="#myCarousel2" data-slide="next">&rsaquo;</a>
		</div>
		</#if>
		</div>
		<#include "/WEB-INF/freemarker/footer.ftl"/>
		<script src="/assets/js/jquery-1.9.1.js"></script>
		<script src="/assets/js/bootstrap.js"></script>
		<script src="/assets/js/holder.js"></script>
	</body>
</html>