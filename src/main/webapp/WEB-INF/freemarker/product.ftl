<#import "/WEB-INF/freemarker/common/carousel.ftl" as common>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>	<#if currentProduct??>${currentProduct.productName}<#else>Product Page</#if> | Seu Cloud Lab</title>
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
			<#if currentProduct??>
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
			</#if>
			<#if productRecommends??>
			<h3>These are results from Hadoop:</h3>
			<@common.carousel productRecommends = productRecommends/>
			</#if>
		</div>
		<#include "/WEB-INF/freemarker/footer.ftl"/>
	</body>
</html>