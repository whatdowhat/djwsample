<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%= request.getContextPath() %>/resources/js/app.min.js"></script>
<style type="text/css">

body { 
	 width: 100%;
	 height: 100%;
	 margin: 0px; padding: 0px; border: 0px;
}

#loadingBackground{
	background-color: #384748; 
	color: #fff; 
	text-align:center; 
	opacity: 10%; 
	width: 100vw; 
	height: 100vh;
	/* width: 100%;
	height: 100%; */
}


#loading {
  display: inline-block;
  width: 50px;
  height: 50px;
  border: 3px solid rgba(255,255,255,.3);
  border-radius: 50%;
  border-top-color: #fff;
  animation: spin 1s ease-in-out infinite;
  -webkit-animation: spin 1s ease-in-out infinite;
}

@keyframes spin {
  to { -webkit-transform: rotate(360deg); }
}
@-webkit-keyframes spin {
  to { -webkit-transform: rotate(360deg); }
}


</style>
</head>
<body>
<%-- <h1>hello world test 1</h1>
<script type="text/javascript">
</script>
<h1>${data}</h1> --%>
<div id=loadingBackground>
	<br>background
	<br>
</div>
<div id="loading"></div>

</body>
</html>