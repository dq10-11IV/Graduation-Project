<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title> ${currentProduct.productName} | Seu Cloud Lab</title>
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
				<h1>${currentProduct.productName}</h1>
				<p>
				This is the product page and the product info is as follows:
				</p>
				<table class="table table-striped table-bordered table-hover">
					<tr>
						<td>Product Id:</td><td>${currentProduct.id}</td>
					</tr>
					<tr>
						<td>Product Name:</td><td>${currentProduct.productName}</td>
					</tr>
					<tr>
						<td>Product Index1:</td><td>${currentProduct.productIndex1}</td>
					</tr>
					<tr>
						<td>Product Index2:</td><td>${currentProduct.productIndex2}</td>
					</tr>
				</table>
			</div>
			<#if productRecommends??>
			<h3>These are results from Hadoop:</h3>
				<div id="myCarousel1" class="carousel slide">
					<div class="carousel-inner">
		    			<#list productRecommends as item>
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
				                   		 			<a href="/product?productId=${item.product.id}">${item.product.productName}</a>
				                   		 			<span class="badge badge-info pull-right">${item.recommendValue}</span>
				                   		 		</h5>
				                   		 		<p>This is the product recommended for you with product id: ${item.product.id}</p>
				                   		 		<p>
				                   		 			<a class="btn btn-mini" href="/product?productId=${item.product.id}">View details</a>
				                   		 		</p>
				                 		 	</div>
				                		</div>
			              			</li>
		    			<#if (item_index + 1) %6 = 0 &&  item_index != productRecommends?size - 1>
		    					</ul>
		    				</div>
		    			</div>
		    			<div class="item">
		    				<div class="row-fluid">
								<ul class="thumbnails">
		    			</#if>
		    			<#if item_index = productRecommends?size - 1>
		    					</ul>
		    				</div>
		    			</div>
		    			</#if>
		    			</#list>
		 			</div>
		 			<a class="carousel-control left" href="#myCarousel1" data-slide="prev">&lsaquo;</a>
		 			<a class="carousel-control right" href="#myCarousel1" data-slide="next">&rsaquo;</a>
				</div>
			</#if>
		</div>
		<#include "/WEB-INF/freemarker/footer.ftl"/>
		<script src="/assets/js/jquery-1.9.1.js"></script>
		<script src="/assets/js/bootstrap.js"></script>
		<script src="/assets/js/holder.js"></script>
	</body>
</html>