<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Shopping Cart | Seu Cloud Lab</title>
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
		<#if cartItemDtoList??>
		<table class="table table-striped">
		<thead>
			<td><b>Product ID</b></td>
			<td><b>Product Name</b></td>
			<td><b>Product Number</b></td>
			<td><b>Operation</b></td>
		</thead>
		<#list cartItemDtoList as item>
		<tr>
			<td>${item.id}</td>
			<#if item.productName?length lt 80>
			<td>${item.productName}</td>
			<#else>
			<td>${item.productName[0..78]}..</td>
			</#if>
			<td>${item.productNum}</td>
			<td><button id="delete" class="btn btn-mini btn-danger">Delete</button></td>
		</tr>
		</#list>
		</table>
		</#if>
		</div>
		<#include "/WEB-INF/freemarker/footer.ftl"/>
		<script type="text/javascript">
		$(".btn-danger").click(function(){
			var productId = $(this).parents("tr").children("td:nth-child(1)").text();
			var parent = $(this).parents("tr");
			$.post("ajax/shoppingcart",
			{
				action:"delete",
				productId:productId
			},
			function(data, status) {
				if(status == "success" && data.code == "success") {
					parent.hide();
					}
				}
			);
		});
		</script>
	</body>
</html>