<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
    
<script type="text/javascript">



</script>
    