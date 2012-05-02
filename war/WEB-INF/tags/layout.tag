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

	<script type="text/javascript">

	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-3159223-3']);
	  _gaq.push(['_trackPageview']);

	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();

	</script>

</body></html>
