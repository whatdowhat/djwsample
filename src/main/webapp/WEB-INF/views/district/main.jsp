<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
	            
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">

                                        <div class="row justify-content-center mt-3">
                                            <div class="col-lg-6">
                                                <div class="row">
                                                    <div class="col-lg-6">
                                                        <div class="border p-3 text-center rounded mb-4">
                                                            <a href="#">
                                                                <div class="my-3">
                                                                    <i class="dripicons-question h2 text-primary"></i>
                                                                </div>
                                                                <h5 class="font-size-15 mb-3">당원</h5>
                                                                 <br>
                                                                <fmt:formatNumber value="${total }" pattern="#,###" />
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6">
                                                        <div class="border p-3 text-center rounded mb-4">
                                                            <a href="#">
                                                                <div class="my-3">
                                                                    <i class="dripicons-tags h2 text-primary"></i>
                                                                </div>
                                                                <h5 class="font-size-15 mb-3">입당</h5>
                                                                 <br>
                                                                 <fmt:formatNumber value="${districtTotal }" pattern="#,###" />
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
  						<sec:authorize access="hasRole('ROLE_ADMIN') || hasRole('ROLE_전국')">
  						
						<div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">
                                            
                                                <div class="row justify-content-center">
													<ul class="nav nav-pills nav-justified" role="tablist">
														<c:forEach var="item" items="${citys}" varStatus="status">
																<%-- <c:if test="${cityCode == item.cityCode}">
																	<button type="button" class="btn btn-danger waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickCity(${item.cityCode})">${item.cityN}<br>${item.cityCount}</button>
																</c:if>
																<c:if test="${cityCode != item.cityCode}">
																	<button type="button" class="btn btn-danger waves-effect waves-light" style="margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickCity(${item.cityCode})">${item.cityN}<br>${item.cityCount}</button>
																</c:if>
																<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},'','')">확인</button> --%>

																 <sec:authorize access="hasRole('ROLE_ADMIN')">
																	<c:if test="${cityCode == item.cityCode}">
																		<button type="button" class="btn btn-danger waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickCity(${item.cityCode})">${item.cityN}<br>${item.cityCount}</button>
																	</c:if>
																	<c:if test="${cityCode != item.cityCode}">
																		<button type="button" class="btn btn-danger waves-effect waves-light" style="margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickCity(${item.cityCode})">${item.cityN}<br>${item.cityCount}</button>
																	</c:if>
																	<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},'','')">확인</button>
																															    		
																</sec:authorize> 
																 <sec:authorize access="hasRole('ROLE_USER') && hasRole('ROLE_전국')">
																	<c:if test="${cityCode == item.cityCode}">
																		<button type="button" class="btn btn-danger waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickCity(${item.cityCode})">${item.cityN}<br>${item.cityCount}</button>
																		<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},'','')">확인</button>
																	</c:if>
																</sec:authorize>	
																 <sec:authorize access="hasRole('ROLE_USER') && hasRole('ROLE_시군구')">
																	<c:if test="${cityCode == item.cityCode}">
																		<button type="button" class="btn btn-danger waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickCity(${item.cityCode})">${item.cityN}<br>${item.cityCount}</button>
																		<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},'','')">확인</button>
																	</c:if>
																</sec:authorize>	
																 <sec:authorize access="hasRole('ROLE_USER') && hasRole('ROLE_읍면동')">
																	<c:if test="${cityCode == item.cityCode}">
																		<button type="button" class="btn btn-danger waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickCity(${item.cityCode})">${item.cityN}<br>${item.cityCount}</button>
																		<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},'','')">확인</button>
																	</c:if>
																</sec:authorize>
																<sec:authorize access="hasRole('ROLE_USER') && !hasRole('ROLE_전국') && !hasRole('ROLE_시군구') && !hasRole('ROLE_읍면동')">
																	<c:if test="${cityCode == item.cityCode}">
																		<button type="button" class="btn btn-danger waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickCity(${item.cityCode})">${item.cityN}<br>${item.cityCount}</button>
																		<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},'','')">확인</button>
																	</c:if>
																</sec:authorize>
														</c:forEach>
			                                        </ul> 
                                                </div>
                                                <!-- end row -->
                                    </div>
                                </div>
                            </div>
                        </div>
  						
  						</sec:authorize>
						
						<sec:authorize access="hasRole('ROLE_ADMIN') || hasRole('ROLE_전국') || hasRole('ROLE_시군구') ">
						
<div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">
                                                <div class="row justify-content-center">
													<ul class="nav nav-pills nav-justified" role="tablist">
														<c:forEach var="item" items="${districts}" varStatus="status">
														
														
															<%-- <c:if test="${districCode == item.districtCode}">
																<button type="button" class="btn btn-info waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickDistrict(${item.cityCode},${item.districtCode})">${item.districtName}<br>${item.districtCount}</button>
															</c:if>
															<c:if test="${districCode != item.districtCode}">
																<button type="button" class="btn btn-info waves-effect waves-light" style="margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickDistrict(${item.cityCode},${item.districtCode})">${item.districtName}<br>${item.districtCount}</button>
															</c:if>
															
																<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},${item.districtCode},'')">확인</button> --%>
																
																<sec:authorize access="hasRole('ROLE_ADMIN')">
																	<c:if test="${districCode == item.districtCode}">
																		<button type="button" class="btn btn-info waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickDistrict(${item.cityCode},${item.districtCode})">${item.districtName}<br>${item.districtCount}</button>
																	</c:if>
																	<c:if test="${districCode != item.districtCode}">
																		<button type="button" class="btn btn-info waves-effect waves-light" style="margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickDistrict(${item.cityCode},${item.districtCode})">${item.districtName}<br>${item.districtCount}</button>
																	</c:if> 
																		<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},${item.districtCode},'')">확인</button>
																</sec:authorize> 
																 <sec:authorize access="hasRole('ROLE_USER') && hasRole('ROLE_전국')">
																	<c:if test="${districCode == item.districtCode}">
																		<button type="button" class="btn btn-info waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickDistrict(${item.cityCode},${item.districtCode})">${item.districtName}<br>${item.districtCount}</button>
																	</c:if>
																	<c:if test="${districCode != item.districtCode}">
																		<button type="button" class="btn btn-info waves-effect waves-light" style="margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickDistrict(${item.cityCode},${item.districtCode})">${item.districtName}<br>${item.districtCount}</button>
																	</c:if> 
																		<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},${item.districtCode},'')">확인</button>
																</sec:authorize>	
																 <sec:authorize access="hasRole('ROLE_USER') && hasRole('ROLE_시군구')">
																	<c:if test="${districCode == item.districtCode}">
																		<button type="button" class="btn btn-info waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickDistrict(${item.cityCode},${item.districtCode})">${item.districtName}<br>${item.districtCount}</button>
																		<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},${item.districtCode},'')">확인</button>
																	</c:if>
																</sec:authorize>	
																 <sec:authorize access="hasRole('ROLE_USER') && hasRole('ROLE_읍면동')">
																	<c:if test="${districCode == item.districtCode}">
																		<button type="button" class="btn btn-info waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickDistrict(${item.cityCode},${item.districtCode})">${item.districtName}<br>${item.districtCount}</button>
																		<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},${item.districtCode},'')">확인</button>
																	</c:if>																 
																</sec:authorize>
																<sec:authorize access="hasRole('ROLE_USER') && !hasRole('ROLE_전국') && !hasRole('ROLE_시군구') && !hasRole('ROLE_읍면동')">
																	<c:if test="${districCode == item.districtCode}">
																		<button type="button" class="btn btn-info waves-effect waves-light" style="border-width:10px; border-color:yellow; margin-bottom: 20px; border-bottom-right-radius: 0; border-top-right-radius: 0;" onclick="clickDistrict(${item.cityCode},${item.districtCode})">${item.districtName}<br>${item.districtCount}</button>
																		<button type="button" class="btn btn-light" style="margin-right:20px; margin-bottom: 20px;  border-bottom-left-radius: 0; border-top-left-radius: 0; " onclick="goMember(${item.cityCode},${item.districtCode},'')">확인</button>
																	</c:if>	
																</sec:authorize>
																
																
														</c:forEach>
			                                        </ul>
                                                </div>
                                                <!-- end row -->
                                    </div>
                                </div>
                            </div>
                        </div>
						
						</sec:authorize>
  						
  						<div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">
                                                <div class="row justify-content-center">
													<ul class="nav nav-pills nav-justified" role="tablist">
														<c:forEach var="item" items="${dongs}" varStatus="status"> 
																
														
																<sec:authorize access="hasRole('ROLE_ADMIN')">
																	<c:if test="${dongCode == item.dongCode}">
																		<button type="button" class="btn btn-light waves-effect dong" onclick="goMember(${item.cityCode},${item.districtCode},${item.dongCode})">${item.dongN}<br>${item.dongCount}</button>
																	</c:if>
																	<c:if test="${dongCode != item.dongCode}">
																		<button type="button" class="btn btn-light waves-effect dong" onclick="goMember(${item.cityCode},${item.districtCode},${item.dongCode})">${item.dongN}<br>${item.dongCount}</button>
																	</c:if>
																</sec:authorize> 
																 <sec:authorize access="hasRole('ROLE_USER') && hasRole('ROLE_전국')">
																	<c:if test="${dongCode == item.dongCode}">
																		<button type="button" class="btn btn-light waves-effect dong" onclick="goMember(${item.cityCode},${item.districtCode},${item.dongCode})">${item.dongN}<br>${item.dongCount}</button>
																	</c:if>
																	<c:if test="${dongCode != item.dongCode}">
																		<button type="button" class="btn btn-light waves-effect dong" onclick="goMember(${item.cityCode},${item.districtCode},${item.dongCode})">${item.dongN}<br>${item.dongCount}</button>
																	</c:if>																</sec:authorize>	
																 <sec:authorize access="hasRole('ROLE_USER') && hasRole('ROLE_시군구')">
																	<c:if test="${dongCode == item.dongCode}">
																		<button type="button" class="btn btn-light waves-effect dong" onclick="goMember(${item.cityCode},${item.districtCode},${item.dongCode})">${item.dongN}<br>${item.dongCount}</button>
																	</c:if>
																	<c:if test="${dongCode != item.dongCode}">
																		<button type="button" class="btn btn-light waves-effect dong" onclick="goMember(${item.cityCode},${item.districtCode},${item.dongCode})">${item.dongN}<br>${item.dongCount}</button>
																	</c:if>
																</sec:authorize>	
																 <sec:authorize access="hasRole('ROLE_USER') && hasRole('ROLE_읍면동')">
																	<c:if test="${dongCode == item.dongCode}">
																		<button type="button" class="btn btn-light waves-effect dong" onclick="goMember(${item.cityCode},${item.districtCode},${item.dongCode})">${item.dongN}<br>${item.dongCount}</button>
																	</c:if>
																</sec:authorize>
																<sec:authorize access="hasRole('ROLE_USER') && !hasRole('ROLE_전국') && !hasRole('ROLE_시군구') && !hasRole('ROLE_읍면동')">
																	<c:if test="${dongCode == item.dongCode}">
																		<button type="button" class="btn btn-light waves-effect dong" onclick="goMember(${item.cityCode},${item.districtCode},${item.dongCode})">${item.dongN}<br>${item.dongCount}</button>
																	</c:if>
																</sec:authorize>
														
														
														</c:forEach>
														
														
														<%-- <h1>test ${dongCode} , ${dongs.size()} </h1> --%>
			                                        </ul>
                                                </div>
                                                <!-- end row -->
                                    </div>
                                </div>
                            </div>
                        </div>
						</div>

                        
		            <!-- ============================================================== -->
		            <!-- End right Content here -->
		            <!-- ============================================================== -->

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
	var url = '/admin/district/getcity'
			   
	ajaxPage(url,inputform,function(result){
		console.log("ajaxpage");
		console.dir(result);
		$("#target").html(result);
	})

}

function clickDistrict(cityCode,districtCode){
	
	var inputform = {};
	inputform.cityCode = cityCode;
	inputform.districtCode = districtCode;
	var url = '/admin/district/getdongs'
			   
	ajaxPage(url,inputform,function(result){
		console.log("ajaxpage");
		console.dir(result);
		$("#target").html(result);
	})

}

/* function goMember(cityCode,districtCode,dongCode){
	var url ="/admin/district/member.do?cityCode="+cityCode+"&districtCode="+districtCode+"&dongCode="+dongCode+"&page=0";
	//location.href =url;
	$(location).attr('href',url)
} */
function goMember(cityCode,districtCode,dongCode){

 	var parm = new Array();
	parm.push( ['cityCode', cityCode] );
    parm.push( ['districtCode',  districtCode] );
    parm.push( ['dongCode', dongCode] );
    goPage("/admin/district/member.do",parm);
    		
}

window.onload = function() {


	
};



</script>
    