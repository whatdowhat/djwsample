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

    div.left {
    	float: left;
        box-sizing: border-box;
        border: 1px solid #000;
        background: #ff0;
    }
    div.right {
        float: right;
        box-sizing: border-box;
    	border: 1px solid #000;        
        background: #0ff;
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
                                                        <!-- <iframe id="iframe" width="1024" height="500" src="/admin/member/page.do"></iframe> -->
                                                        
                                                            <a href="#">
                                                                <div class="my-3">
                                                                    <i class="dripicons-question h2 text-primary"></i>
                                                                </div>
                                                                <h5 class="font-size-15 mb-3">당원 현황</h5>
                                                                 <br>
                                                                 <fmt:formatNumber value="${total }" pattern="#,###" />
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
                                                                 <fmt:formatNumber value="${groupCount }" pattern="#,###" />
                                                                
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
                                                                 <fmt:formatNumber value="${dangwonCount01 }" pattern="#,###" />
                                                              
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
                                                                <fmt:formatNumber value="${dangwonCount00 }" pattern="#,###" />
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
																<c:if test="${cityCode == item.cityCode}">
																	<button type="button" class="btn btn-danger waves-effect waves-light" style="border-width:20px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickCity(${item.cityCode})">${item.cityN}<br>${item.cityCount}</button>
																</c:if>
																<c:if test="${cityCode != item.cityCode}">
																	<button type="button" class="btn btn-danger waves-effect waves-light" style="margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickCity(${item.cityCode})">${item.cityN}<br>${item.cityCount}</button>
																</c:if>
																
																<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},'','')">확인</button>
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
															<c:if test="${gunCode == item.gunCode}">
																<button type="button" class="btn btn-info waves-effect waves-light" style="border-width:20px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickGun(${item.cityCode},${item.gunCode})">${item.gunN}<br>${item.gunCount}</button>
															</c:if>
															<c:if test="${gunCode != item.gunCode}">
																<button type="button" class="btn btn-info waves-effect waves-light" style="margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickGun(${item.cityCode},${item.gunCode})">${item.gunN}<br>${item.gunCount}</button>
															</c:if> 
																
																<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},${item.gunCode},'')">확인</button>
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
															<c:if test="${dongCode == item.dongCode}">
																<button type="button" class="btn btn-light waves-effect dong" style="border-width:20px; border-color:yellow; "  onclick="goMember(${item.cityCode},${item.gunCode},${item.dongCode})">${item.dongN}<br>${item.dongCount}</button>
															</c:if>
															<c:if test="${dongCode != item.dongCode}">
																<button type="button" class="btn btn-light waves-effect dong" onclick="clickDong(${item.cityCode},${item.gunCode},${item.dongCode})">${item.dongN}<br>${item.dongCount}</button>
															</c:if> 
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

	
	//alert(cityCode + '-' + gunCode + '-'+ dongCode);
	/* 
	var myform = $('#SPECIFICATION');
	fromJSP = $('#fromJSP2').val(JSON.stringify(fromJSP));
	myform.append(fromJSP);
	var checkedList = $('#checkedList').val(global_checked_list);
	myform.append(checkedList);
	myform.submit(); */
	
	location.href="/admin/member/list.do?cityCode="+cityCode+"&gunCode="+gunCode+"&dongCode="+dongCode+"&page=0";
	
}


function goMember(cityCode,gunCode,dongCode){

	//alert(cityCode + '-' + gunCode + '-'+ dongCode);
	/* 
	var myform = $('#SPECIFICATION');
	fromJSP = $('#fromJSP2').val(JSON.stringify(fromJSP));
	myform.append(fromJSP);
	var checkedList = $('#checkedList').val(global_checked_list);
	myform.append(checkedList);
	myform.submit(); */
	location.href="/admin/main/member.do?cityCode="+cityCode+"&gunCode="+gunCode+"&dongCode="+dongCode+"&page=0";

}


</script>
    