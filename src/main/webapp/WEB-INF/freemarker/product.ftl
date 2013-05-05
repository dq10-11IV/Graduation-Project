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
				<p>
					<form class="form-inline">
						<input type="text" class="span1" placeholder="1" id="pNum">
						<a class="btn btn-primary" id="addToSC">Add To Shopping Cart</a>
					</form>
				</p>
			</div>
			</#if>
			<#if productRecommends??>
			<h3>These are results from Hadoop:</h3>
			<p>
				Consult the anaylsis Info Page: 
				<a href="#analysisInfo" role="button" class="btn" data-toggle="modal">Analysis Info</a>
			</p>
			<@common.carousel productRecommends = productRecommends/>
			</#if>
		</div>
		<div id="analysisInfo" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  			<div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h3 id="myModalLabel">Analysis Info</h3>
		  </div>
		  <div class="modal-body">
		  	<canvas id="chart" width="500" height="400"></canvas>
		  </div>
		  <div class="modal-footer">
		    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		  </div>
		</div>	
		<#include "/WEB-INF/freemarker/footer.ftl"/>
	    <script src="/assets/js/Chart.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
			$("#shoppingCart").popover({
							placement: 'bottom',
							title: 'ShoppingCart',
							content: 'Product ${productId} has been added into the shopping cart!'
						});
		});
		$("#addToSC").click(function(){
			var productId = ${productId};
			var productNum = parseInt($("#pNum").val());
			if(isNaN(productNum) || productNum <= 1) {
				productNum = 1
			}
			$.post("ajax/shoppingcart",
			{
				action: "add",
				productId: productId,
				productNum: productNum
			},
			function(data, status) {
				if(status == "success" && data.code == "success") {
					$("#shoppingCart").popover('show');	
					setTimeout(function() {
						$("#shoppingCart").popover('hide');	
					},
					2000);
				}
			},
			"json");
		});
		$("#analysisInfo").on('shown',function(){
			var data = {
				labels : [
				<#list productRecommends as item>
				"${item.product.id}"<#if item_index != productRecommends?size - 1>,</#if>
				</#list>
				],
				datasets : [
					{
						fillColor : "rgba(220,220,220,0.5)",
						strokeColor : "rgba(220,220,220,1)",
						pointColor : "rgba(220,220,220,1)",
						pointStrokeColor : "#fff",
						data : 
						[
						<#list productRecommends as item>
						${item.recommendValue}<#if item_index != productRecommends?size - 1>,</#if>
						</#list>
						]
					}
				]
			}
			var ctx = $("#chart").get(0).getContext("2d");
			var analysisInfoChart = new Chart(ctx).Bar(data);
		});
		$('#analysisInfo').on('hidden',function(){
			var ctx = $("#chart").get(0).getContext("2d");
			ctx.clearRect(0, 0, 500, 400);
		});
		</script>
	</body>
</html>