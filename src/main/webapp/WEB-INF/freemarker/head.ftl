<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
  	<div class="container">
      	<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        	<span class="icon-bar"></span>
        	<span class="icon-bar"></span>
        	<span class="icon-bar"></span>
      	</a>
      	<a class="brand" href="#">Seu Cloud Lab</a>
      	<div class="nav-collapse collapse">
      		<ul class="nav">
      			<li>
      				<a href="/index">Home</a>
      			</li>
      			<li>
      				<a href="#myModal" data-toggle="modal">About</a>
      			</li>
      		</ul>
      		<form class="navbar-search pull left">
      			<input type="text" class="search-query span2" placeholder="Search">
      		</form>
			<ul class="nav pull-right">
				<li class="divider-vertical"></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Login as: ${user.username}
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="/logout">Log Out</a>
						</li>
					</ul>
				</li>
			</ul>
      	</div>
  	</div>
  </div>
</div>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">	
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		<h3>This is About Page</h3>
	</div>
	<div class="modal-body">
		<p>This is a project showing the recommend system.</p>
		<p>By Seu Cloud Lab</p>
		<p>Please Contact: XXX</p>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	</div>
</div>