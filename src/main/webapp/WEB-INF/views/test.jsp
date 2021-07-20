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
</head>
<body>
<h1>hello world test 1</h1>
<script type="text/javascript">

	

	$(document).ready(function() {
	
		
		$("#btn1").click(function() {
			
			var aa = $("#inputform");
			aa.submit();
		})
		
		$("#btn2").click(function() {
			var aa = $("#inputform2");
			
			
			var list = [];
			list.push(1);
			list.push(2);
			
			
			var formData = {
					//name:"test",
					//age:"20",
					input1 :"input1 value" ,
					input2 :"input2 value" ,
					list : list
			}
			console.dir(formData);
			$.ajax({
				url: "<%= request.getContextPath() %>/test/form3",
				data: formData,
				dataType: 'json',
				encode :true,
				type: 'POST',
				traditional: true,
				success: function(data){
					
					console.dir(data);
					console.log(data.name);
					console.log(data.age);
					
				},
				 error: function(XMLHttpRequest, textStatus, errorThrown) { 
					 
					 alert('관리자에게 문의하세요.');
					 
	             }       
			});// ajax
			
			
		})
		
	});
	
</script>
${jpaObject.seq}<br>
${jpaObject.name}<br>
${jpaObject.phone}<br>
<%-- <c:set target="${daoObject}" property="COMPANYNAME" value="${daoObject.COMPANYNAME}" var="company"/> --%>
<c:set  var="company" value="${daoObject.COMPANYNAME}"/>

<c:out value="${daoObject.COMPANYNAME}"/> test<br>
<c:out value="${company}"/><br>

<form action="<%= request.getContextPath() %>/test/form1" method="get" name="inputform" >
	<input type="text" name="input1">
	<input type="text" name="input2">
	<button type="submit">발송</button>
</form>

<form action="<%= request.getContextPath() %>/test/form2" method="post" name="inputform2" id ="inputform2">
	<input type="text" name="input1">
	<input type="text" name="input2">
	<input type="text" name="input3">
	<input type="text" name="input4">
	<input type="hidden" name="input10" value="empty">
	
	<button type="submit">발송</button>
</form>

<input type="text" id="test" name ="test">
<button type="button" id="btn1">발송</button>
<button type="button" id="btn2">발송</button>

</body>
</html>