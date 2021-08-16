<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<style>

.city{

	margin: 10px;
	margin-top: 10px;
}

.gu{

	margin: 10px;
	margin-top: 10px;
}

.dong{
	
	margin: 10px;
	margin-top: 10px;
}

</style>
	

    <body data-topbar="colored">

        <!-- Begin page -->
        <div id="layout-wrapper">
        	<jsp:include page="../template/menu.jsp"></jsp:include>
        	<jsp:include page="../template/header.jsp"></jsp:include>
        	
        	
        	<!--  page content -->
        	
            <div class="main-content">

                <div class="page-content">
                    <div class="container-fluid">

	            <!-- ============================================================== -->
	            <!-- Start right Content here -->
	            <!-- ============================================================== -->
<!-- 						<div class="row">
                            <div class="col-12">
                                <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                                    <h4 class="mb-sm-0">조직 현황</h4>
                                </div>
                            </div>
                        </div> -->
                        <!-- end page title -->

                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">

                                        <div class="row justify-content-center mt-3">
                                            <div class="col-lg-8">
                                                <div class="row">
                                                    <div class="col-lg-3">
                                                        <div class="border p-3 text-center rounded mb-4">
                                                            <a href="#">
                                                                <div class="my-3">
                                                                    <i class="dripicons-question h2 text-primary"></i>
                                                                </div>
                                                                <h5 class="font-size-15 mb-3">당원 현황</h5>
                                                                 <br>
                                                                123,123
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <div class="border p-3 text-center rounded mb-4">
                                                            <a href="#">
                                                                <div class="my-3">
                                                                    <i class="dripicons-tags h2 text-primary"></i>
                                                                </div>
                                                                <h5 class="font-size-15 mb-3">단체현황</h5>
                                                                 <br>
                                                                123,123
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <div class="border p-3 text-center rounded mb-4">
                                                            <a href="#">
                                                                <div class="my-3">
                                                                    <i class="dripicons-help h2 text-primary"></i>
                                                                </div>
                                                                <h5 class="font-size-15 mb-3">책임당원</h5>
                                                                 <br>
                                                               1
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <div class="border p-3 text-center rounded mb-4">
                                                            <a href="#">
                                                                <div class="my-3">
                                                                    <i class="dripicons-article h2 text-primary"></i>
                                                                </div>
                                                                <h5 class="font-size-15 mb-3">일반당원</h5>
                                                                <br>
                                                                123,123
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- end row -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div id="target">
  						<div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">
                                            
                                                <div class="row justify-content-center">
													<ul class="nav nav-pills nav-justified" role="tablist">
			                                           
														<c:forEach var="item" items="${cities}" varStatus="status"> 
														
																	<button type="button" class="btn btn-danger waves-effect waves-light city" onclick="clickCity(${item.cityCode})">${item.cityN}(${item.cityCount})</button>
				 												
														</c:forEach>
			                                        </ul>
                                                </div>
                                                <!-- end row -->
                                    </div>
                                </div>
                            </div>
                        </div>
  						<div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">
                                                <div class="row justify-content-center">
													<ul class="nav nav-pills nav-justified" role="tablist">
			                                           
														<c:forEach var="item" items="${gus}" varStatus="status"> 
														
																	<button type="button" class="btn btn-info waves-effect waves-light gu" onclick="clickGun(${item.cityCode},${item.gunCode})">${item.gunN}(${item.gunCount})</button>
				 												
														</c:forEach>
			                                        </ul>
                                                </div>
                                                <!-- end row -->
                                    </div>
                                </div>
                            </div>
                        </div>
                                    
  						<div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">
                                                <div class="row justify-content-center">
													<ul class="nav nav-pills nav-justified" role="tablist">
			                                           
														<c:forEach var="item" items="${dongs}" varStatus="status"> 
														
																	<button type="button" class="btn btn-light waves-effect dong" onclick="clickDong(${item.cityCode},${item.gunCode},${item.dongCode})">${item.dongN}(${item.dongCount})</button>
				 												
														</c:forEach>
			                                        </ul>
                                                </div>
                                                <!-- end row -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        </div>
                        <!-- end row -->
                        
		            <!-- ============================================================== -->
		            <!-- End right Content here -->
		            <!-- ============================================================== -->

					<form action="">
						<input type="hidden" name ="cityCode" >
						<input type="hidden" name ="gunCode" >
						<input type="hidden" name ="dongCode" >
						
					</form>

                    </div> <!-- container-fluid -->
                </div>
                <!-- End Page-content -->
        	</div>
        	
        	<jsp:include page="../template/footer.jsp"></jsp:include>

        </div>

    </body>
    
<script type="text/javascript">


function clickCity(cityCode){
	
		var inputform = {};
		inputform.cityCode = cityCode;
		console.log(cityCode);
		var url = '/admin/getcity'
		
		ajaxPage(url,inputform,function(result){
			console.log("ajaxpage");
			console.dir(result);
			$("#target").html(result);
		})

}

function clickGun(cityCode,gunCode){
	
	var inputform = {};
	inputform.cityCode = cityCode;
	inputform.gunCode = gunCode;
	console.log(cityCode);
	var url = '/admin/getgun'
	
	ajaxPage(url,inputform,function(result){
		console.log("ajaxpage");
		console.dir(result);
		$("#target").html(result);
	})

}


function clickDong(cityCode,gunCode,dongCode){

	
	alert(cityCode + '-' + gunCode + '-'+ dongCode);
	/* 
	var myform = $('#SPECIFICATION');
	fromJSP = $('#fromJSP2').val(JSON.stringify(fromJSP));
	myform.append(fromJSP);
	var checkedList = $('#checkedList').val(global_checked_list);
	myform.append(checkedList);
	myform.submit(); */
	
	location.href="admin/member/list.do?cityCode="+cityCode+"&gunCode="+gunCode+"&dongCode="+dongCode+"&page=0";
	

}

</script>
    