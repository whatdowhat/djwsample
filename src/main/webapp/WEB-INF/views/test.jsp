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
#link {color: #E45635;display:block;font: 12px "Helvetica Neue", Helvetica, Arial, sans-serif;text-align:center; text-decoration: none;}
#link:hover {color: #CCCCCC}

#link, #link:hover {-webkit-transition: color 0.5s ease-out;-moz-transition: color 0.5s ease-out;-ms-transition: color 0.5s ease-out;-o-transition: color 0.5s ease-out;transition: color 0.5s ease-out;}

/** BEGIN CSS **/
        body {background: #333333;}
        @keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-moz-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-webkit-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-o-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-moz-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-webkit-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @-o-keyframes rotate-loading {
            0%  {transform: rotate(0deg);-ms-transform: rotate(0deg); -webkit-transform: rotate(0deg); -o-transform: rotate(0deg); -moz-transform: rotate(0deg);}
            100% {transform: rotate(360deg);-ms-transform: rotate(360deg); -webkit-transform: rotate(360deg); -o-transform: rotate(360deg); -moz-transform: rotate(360deg);}
        }

        @keyframes loading-text-opacity {
            0%  {opacity: 0}
            20% {opacity: 0}
            50% {opacity: 1}
            100%{opacity: 0}
        }

        @-moz-keyframes loading-text-opacity {
            0%  {opacity: 0}
            20% {opacity: 0}
            50% {opacity: 1}
            100%{opacity: 0}
        }

        @-webkit-keyframes loading-text-opacity {
            0%  {opacity: 0}
            20% {opacity: 0}
            50% {opacity: 1}
            100%{opacity: 0}
        }

        @-o-keyframes loading-text-opacity {
            0%  {opacity: 0}
            20% {opacity: 0}
            50% {opacity: 1}
            100%{opacity: 0}
        }
        .loading-container,
        .loading {
            height: 100px;
            position: relative;
            width: 100px;
            border-radius: 100%;
        }


        .loading-container { margin: 40px auto }

        .loading {
            border: 2px solid transparent;
            border-color: transparent #fff transparent #FFF;
            -moz-animation: rotate-loading 1.5s linear 0s infinite normal;
            -moz-transform-origin: 50% 50%;
            -o-animation: rotate-loading 1.5s linear 0s infinite normal;
            -o-transform-origin: 50% 50%;
            -webkit-animation: rotate-loading 1.5s linear 0s infinite normal;
            -webkit-transform-origin: 50% 50%;
            animation: rotate-loading 1.5s linear 0s infinite normal;
            transform-origin: 50% 50%;
        }

        .loading-container:hover .loading {
            border-color: transparent #E45635 transparent #E45635;
        }
        .loading-container:hover .loading,
        .loading-container .loading {
            -webkit-transition: all 0.5s ease-in-out;
            -moz-transition: all 0.5s ease-in-out;
            -ms-transition: all 0.5s ease-in-out;
            -o-transition: all 0.5s ease-in-out;
            transition: all 0.5s ease-in-out;
        }

        #loading-text {
            -moz-animation: loading-text-opacity 2s linear 0s infinite normal;
            -o-animation: loading-text-opacity 2s linear 0s infinite normal;
            -webkit-animation: loading-text-opacity 2s linear 0s infinite normal;
            animation: loading-text-opacity 2s linear 0s infinite normal;
            color: #ffffff;
            font-family: "Helvetica Neue, "Helvetica", ""arial";
            font-size: 10px;
            font-weight: bold;
            margin-top: 45px;
            opacity: 0;
            position: absolute;
            text-align: center;
            text-transform: uppercase;
            top: 0;
            width: 100px;
        }
</style>
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
					childParam1 :"childParam1 value" ,
					childParam2 :"childParam2 value" ,
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
		
		
		$("#btn3").click(function() {
			var list = [];
			list.push(1);
			list.push(2);
			var map = {};
			
			map.list = list;
			
			var formData = {
					//name:"test",
					//age:"20",
					input1 :"input1 value" ,
					input2 :"input2 value" ,
					list : list,
					map : map
			}
			console.dir(formData);
			$.ajax({
				url: "<%= request.getContextPath() %>/test/form4",
				data: JSON.stringify(formData),
				dataType: 'json',
				//encode :true,
				type: 'POST',
				//traditional: true,
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
	
function beforesubmit(){
	$("#ld").css("display","block");
}
	
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

<form action="<%= request.getContextPath() %>/test/form2" method="post" name="inputform2" id ="inputform2" onsubmit="beforesubmit()">
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
<br>
실패 - <button type="button" id="btn3">발송3</button>

<div class="loading-container" id="ld" style="display: none;">
    <div class="loading"></div>
    <div id="loading-text">loading</div>
</div>

<!--// Link Attribution -->
<a href="http://www.domsammut.com/about#utm_source=web&utm_medium=demo&utm_campaign=CodePen" title="domsammut.com" id="link">domsammut.com</a>

</body>
</html>