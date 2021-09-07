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
                                            <img src="/resources/assets/images/logo.png" alt="" height="24" class="auth-logo logo-dark mx-auto">
                                            <img src="/resources/assets/images/logo.png" alt="" height="24" class="auth-logo logo-light mx-auto">
                                        </a>
                                    </div>
                                    <!-- end row -->
                                    <h4 class="font-size-18 text-muted mt-2 text-center">Welcome Back !</h4>
                                    <p class="mb-5 text-center">Sign in to continue to Upzet.</p>
                                    <form class="form-horizontal" action="index.html">

                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="mb-4">
                                                    <label class="form-label" for="username">Username</label>
                                                    <input type="text" class="form-control" id="username" placeholder="Enter username">
                                                </div>
                                                <div class="mb-4">
                                                    <label class="form-label" for="userpassword">Password</label>
                                                    <input type="password" class="form-control" id="userpassword" placeholder="Enter password">
                                                </div>

                                                <div class="row">
                                                    <div class="col">
                                                        <div class="form-check">
                                                            <input type="checkbox" class="form-check-input" id="customControlInline">
                                                            <label class="form-label" class="form-check-label" for="customControlInline">Remember me</label>
                                                        </div>
                                                    </div>
                                                    <div class="col-7">
                                                        <div class="text-md-end mt-3 mt-md-0">
                                                            <a href="auth-recoverpw.html" class="text-muted"><i class="mdi mdi-lock"></i> Forgot your password?</a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="d-grid mt-4">
                                                    <button class="btn btn-primary waves-effect waves-light" type="submit">Log In</button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="mt-5 text-center">
                            <p class="text-white-50">Don't have an account ? <a href="auth-register.html" class="fw-medium text-primary"> Register </a> </p>
                            <p class="text-white-50">© <script>document.write(new Date().getFullYear())</script> Upzet. Crafted with <i class="mdi mdi-heart text-danger"></i> by Themesdesign</p>
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
</html>
