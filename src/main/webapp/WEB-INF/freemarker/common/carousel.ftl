<#macro carousel productRecommends id="myCarousel">
<div id="${id}" class="carousel slide">
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
                   		 			<a href="/product?productId=${item.product.id}">${item.product.id}</a>
                   		 			<#if item.hasRecommendValue>
                   		 			<span class="badge badge-info pull-right">${item.recommendValue?string("0.#")}</span>
                   		 			</#if>
                   		 		</h5>
                   		 		<p>
                   		 			<#if item.product.productName?length lt 50>
                   		 			${item.product.productName}
                   		 			<#else>
                   		 			${item.product.productName[0..48]}..
                   		 			</#if>
                   		 		</p>
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
	<a class="carousel-control left" href="#${id}" data-slide="prev">&lsaquo;</a>
	<a class="carousel-control right" href="#${id}" data-slide="next">&rsaquo;</a>
</div>
</#macro>