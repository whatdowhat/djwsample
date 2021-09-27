<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!doctype html>
<html lang="en">

    <head>
        
        <meta charset="utf-8" />
        <title>Login | Upzet - Admin & Dashboard Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="/resources/assets/images/favicon.ico">

        <!-- Bootstrap Css -->
        <link href="/resources/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="/resources/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="/resources/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />

    </head>

    <body class="bg-pattern">
        <div class="bg-overlay"></div>
        <div class="account-pages my-5 pt-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-4 col-lg-6 col-md-8">
                        <div class="card">
                            <div class="card-body p-4">
                                <div class="">
                                    <div class="text-center">
                                        <a href="index.html" class="">
                                            <img src="/resources/assets/images/logo.png" alt="" height="100" class="auth-logo logo-dark mx-auto">
                                            <img src="/resources/assets/images/logo.png" alt="" height="100" class="auth-logo logo-light mx-auto">
                                        </a>
                                    </div>
                                    <!-- end row -->
                                    <!-- <h4 class="font-size-18 text-muted mt-2 text-center">Welcome Back !</h4> -->
                                    <!-- <p class="mb-5 text-center">Sign in to continue to Upzet.</p> -->
                                    
                                    
			
			                                    <div class="form-horizontal">
			
			                                        <div class="row">
			                                            <div class="col-md-12">
			                                            <form:form class="form-signin" method="post" action="/login.do">
			                                                <div class="mb-4">
			                                                    <label class="form-label" for="username">로그인 아이디</label>
			                                                    <input type="text" class="form-control" name="id"  id="id" placeholder="Enter username">
			                                                </div>
			                                                <div class="mb-4">
			                                                    <label class="form-label" for="userpassword">패스워드</label>
			                                                    <input type="password" class="form-control" name="password" id="password" placeholder="Enter password">
			                                                </div>
			
			<!--                                                 <div class="row">
			                                                    <div class="col">
			                                                        <div class="form-check">
			                                                            <input type="checkbox" class="form-check-input" id="customControlInline">
			                                                            <label class="form-label" class="form-check-label" for="customControlInline">로그인 아이디 기억하기</label>
			                                                        </div>
			                                                    </div>
			                                                    
			                                                </div> -->
			                                                <div class="d-grid mt-4">
			                                                    <button class="btn btn-primary waves-effect waves-light" style="margin-bottom: 10px" onclick="login()">로그인</button>
			                                                </div>
			                                                </form:form>
			                                                <div class="d-grid mt-4">
			                                                       <button class="btn btn-danger waves-effect waves-light" onclick="signUp()">가입하기</button>
			                                                </div>
			                                            </div>
			                                        </div>
			                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end row -->
            </div>
        </div>
        <!-- end Account pages -->

        <!-- JAVASCRIPT -->
        <script src="/resources/assets/libs/jquery/jquery.min.js"></script>
        <script src="/resources/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="/resources/assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="/resources/assets/libs/simplebar/simplebar.min.js"></script>
        <script src="/resources/assets/libs/node-waves/waves.min.js"></script>

        <script src="/resources/assets/js/app.js"></script>

    </body>
    
<script type="text/javascript">

function functionname(event){
	
	$("#userpassword").val(event.value);
}


var errorMessage = '${errorMessage}';

$(document).ready(function() {
	if(errorMessage!=''){
		alert(errorMessage);	
	}else{
		
	}
	
});

function login(){
	window.location.href ="${data}";
}

function signUp(){
	window.location.href ="${signUp}";
	 
}
</script>
    
</html>
