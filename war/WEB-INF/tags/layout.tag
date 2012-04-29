<%@tag isELIgnored="false" %><%@ 
include file="/WEB-INF/taglibs.jspf" %><%@ 
attribute name="title" %><%@ 
attribute name="js" fragment="true" %>
<!doctype html><html><head>
	<title>${title} - SimpleDS kickstart</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head><body>

    <tags:header/>
    
    <div role="main" class="container">
		<jsp:doBody/>
	</div>
	
	<tags:footer/>
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<jsp:invoke fragment="js"/> 

</body></html>
