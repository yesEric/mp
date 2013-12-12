<!DOCTYPE html><%@ include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="<c:url value="/images/favicon.ico"/>" />
<title><decorator:title /> | <fmt:message key="webapp.name" /></title>
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/lib/bootstrap.min.css'/>" />
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/lib/bootstrap-responsive.css'/>" />
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/styles/style.css'/>" />
<!--[if lt IE 9]> <script src="/scripts/lib/html5shiv.js"></script> <![endif]-->
<decorator:head />
<script type="text/javascript"
	src="<c:url value='/scripts/lib/jquery-1.10.2.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/scripts/lib/bootstrap.min.js'/>"></script>
</head>
<body
	<decorator:getProperty property="body.id" writeEntireProperty="true"/>
	<decorator:getProperty property="body.class" writeEntireProperty="true"/>>
	<!-- 这里是程序菜单部分 -->
	<div class="container">
		<h1 class="page-header">
			Barner<small>Resize Response</small>
		</h1>
		<div class="navbar">
			<div class="navbar-inner">
				<a href="#" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a href="#" class="brand"> Bootstrap</a>
				<div class="nav-collapse">
					<ul class="nav">
						<li class="active"><a href="#">HomePage</a></li>
						<li><a href="#">Item1</a></li>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">Item2 </a>
							<ul class="dropdown-menu">
								<li><a href="#">DropdownList1</a></li>
								<li><a href="#">DropdownList2</a></li>
								<li><a href="#">DropdownList3</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<decorator:body />
				<!-- 这里是将被继承的Body部分 -->
			</div>
		</div>
		<div id="footer">
			<span class="right"> </span>
		</div>
	</div>
</body>
</html>