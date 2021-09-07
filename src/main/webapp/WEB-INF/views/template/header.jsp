<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${requestScope['javax.servlet.forward.servlet_path']}" /> 


<!doctype html>
<html lang="en">

    <head>
        
        <meta charset="utf-8" />
        <title>Light Sidebar |  Admin & Dashboard Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="/resources/assets/images/favicon.ico">

        <!-- jvectormap -->
        <link href="/resources/assets/libs/jqvmap/jqvmap.min.css" rel="stylesheet" />

        <!-- Bootstrap Css -->
        <link href="/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
        <!-- Icons Css -->
        <link href="/resources/assets/css/icons.min.css" rel="stylesheet" />
        <!-- App Css-->
        <link href="/resources/assets/css/app.min.css"  rel="stylesheet"/>

    	<!-- Sweet Alert-->
        <link href="/resources/assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
        
    </head>



            
            <header id="page-topbar">
                <div class="navbar-header">
                    <div class="d-flex">
                        <!-- LOGO -->
                        <div class="navbar-brand-box text-center"> 
                            <a href="/admin/loginAfter.do" class="logo logo-dark">
                                <span class="logo-sm">
                                    <img src="/resources/assets/images/logo.png" alt="logo-sm-dark" height="60">
                                </span>
                                <span class="logo-lg">
                                    <img src="/resources/assets/images/logo.png" alt="logo-sm-dark" height="60">
                                </span>
                            </a>

                            <a href="/admin/loginAfter.do" class="logo logo-light">
                                <span class="logo-sm">
                                    <img src="/resources/assets/images/logo.png" alt="logo-sm-light" height="22">
                                </span>
                                <span class="logo-lg">
                                    <img src="/resources/assets/images/logo.png" alt="logo-light" height="24">
                                </span>
                            </a>
<!--                            		 <br>
                           		  노출할 이미지 또는 텍스트를 주세요. -->		
                        </div>

                        <button type="button" class="btn btn-sm px-3 font-size-24 header-item waves-effect" id="vertical-menu-btn">
                            <i class="ri-menu-2-line align-middle"></i>
                        </button>

                    </div>

                    <div class="d-flex">

                        <div class="dropdown d-inline-block d-lg-none ms-2">
                            <button type="button" class="btn header-item noti-icon waves-effect" id="page-header-search-dropdown"
                                data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="ri-search-line"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-end p-0"
                                aria-labelledby="page-header-search-dropdown">
                    
                                <form class="p-3">
                                    <div class="mb-3 m-0">
                                        <div class="input-group">
                                            <input type="text" class="form-control" placeholder="Search ...">
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" type="submit"><i class="ri-search-line"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
						
						<div class="dropdown d-inline-block">
						
                            <button type="button" class="btn header-item noti-icon waves-effect" id="page-header-notifications-dropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                
                                <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                                
                                </sec:authorize>
					            <sec:authorize access="hasAuthority('ROLE_USER')">
	                                <c:if test="${member.noticeChecked}">
	                        		
	                        		</c:if>
	                        		<c:if test="${!member.noticeChecked}">
	                        			<span class="noti-dot"></span> <!-- 알람 표시 -->
	                        		</c:if>
                                </sec:authorize>
                                <i class="ri-notification-3-line"></i>
                        		                
                                
                            </button>
                            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-end p-0" aria-labelledby="page-header-notifications-dropdown" style="">
                                <div class="p-3">
                                    <div class="row align-items-center">
                                        <div class="col">
                                            <h6 class="m-0"> 공지사항 </h6>
                                        </div>
                                        <div class="col-auto">
                                            <a href="/admin/notice/innernotice.do" class="small"> 바로가기</a>
                                        </div>
                                    </div>
                                </div>

                                <div data-simplebar="init" style="max-height: 230px;"><div class="simplebar-wrapper" style="margin: 0px;"><div class="simplebar-height-auto-observer-wrapper"><div class="simplebar-height-auto-observer"></div></div><div class="simplebar-mask"><div class="simplebar-offset" style="right: 0px; bottom: 0px;"><div class="simplebar-content-wrapper" style="height: auto; overflow: hidden;"><div class="simplebar-content" style="padding: 0px;">
                                    
                                    
                                 <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                                
                                </sec:authorize>
 								<sec:authorize access="hasAuthority('ROLE_USER')">
	                                <c:forEach var="item" items="${notices}" varStatus="status">
											
									<a href="/admin/notice/innernotice.do" class="text-reset notification-item">
                                        <div class="d-flex">
                                            <div class="flex-shrink-0 me-3">
                                                <div class="avatar-xs">
                                                    <span class="avatar-title bg-primary rounded-circle font-size-16">
                                                        <i class="ri-shopping-cart-line"></i>
                                                    </span>
                                                </div>
                                            </div>                                
                                            <div class="flex-grow-1">
                                                <h6 class="mb-1">${item.noticeTitle }</h6>
                                                <div class="font-size-12 text-muted">
                                                    <p class="mb-1">${item.noticeText}</p>
                                                    <p class="mb-0"><i class="mdi mdi-clock-outline"></i> ${item.regDt}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
															
									</c:forEach>
                                </sec:authorize>

                                </div></div></div></div><div class="simplebar-placeholder" style="width: 0px; height: 0px;"></div></div><div class="simplebar-track simplebar-horizontal" style="visibility: hidden;"><div class="simplebar-scrollbar" style="transform: translate3d(0px, 0px, 0px); display: none;"></div></div><div class="simplebar-track simplebar-vertical" style="visibility: hidden;"><div class="simplebar-scrollbar" style="transform: translate3d(0px, 0px, 0px); display: none; height: 135px;"></div></div></div>
                            </div>
                        </div>

                        <div class="dropdown d-none d-lg-inline-block ms-1">
                            <button type="button" class="btn header-item noti-icon waves-effect" data-toggle="fullscreen">
                                <i class="ri-fullscreen-line"></i>
                            </button>
                        </div>
                          <div class="dropdown d-inline-block user-dropdown">
		                   <c:if test="${path  eq '/admin/member/list.do' }">
		                                <button type="button" class="btn header-item waves-effect" style="margin-left: auto; border: solid;" onclick="snedMessage()">쪽지 보내기</button>
		                                
		                   </c:if> 
		                   <c:if test="${path  eq '/admin/message/innerMessage.do' }">
		                                <button type="button" class="btn header-item waves-effect" style="margin-left: auto; border: solid;" onclick="snedMessage()">쪽지 보내기</button>
		                                
		                   </c:if> 	           
		                   <c:if test="${path  eq '/admin/main/member.do' }">
		                                <button type="button" class="btn header-item waves-effect" style="margin-left: auto; border: solid;" onclick="snedMessage()">쪽지 보내기</button>
		                   </c:if> 
	                   </div>
                        <div class="dropdown d-inline-block user-dropdown">
                            <button type="button" class="btn header-item waves-effect" id="page-header-user-dropdown"
                                data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						
                                <span class="d-none d-xl-inline-block ms-1">더보기</span>
                                <i class="mdi mdi-chevron-down d-none d-xl-inline-block"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-end">
                                
                                <a class="dropdown-item text-danger" href="/logout.do"><i class="ri-shut-down-line align-middle me-1 text-danger"></i> Logout</a>
                            </div>
                        </div>


                    </div>
                </div>
            </header>

        <!-- END layout-wrapper -->

        <!-- Right Sidebar -->

        <!-- JAVASCRIPT -->
        <!-- 메뉴 작동을 위한 js -->
        <!-- JAVASCRIPT -->
        <!-- Plugin Js-->
        <script src="/resources/assets/libs/apexcharts/apexcharts.min.js"></script>
        <!-- demo js-->
        <script src="/resources/assets/js/pages/apex.init.js"></script>
        <script src="/resources/assets/js/app.js"></script>

    
<script type="text/javascript">

var errorMessage = '${errorMessage}';

$(document).ready(function() {
	if(errorMessage!=''){
		alert(errorMessage);	
	}else{
		
	}
	
});

</script>
    
</html>
