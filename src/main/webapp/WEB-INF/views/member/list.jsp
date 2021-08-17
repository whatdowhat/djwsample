<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">

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
                            <div class="col-12">
                                <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                                    <h4 class="mb-sm-0">당원현황</h4>
									
                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">당원관리</a></li>
                                            <li class="breadcrumb-item active">당원현황</li>
                                        </ol>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!-- end page title -->
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">

                                        <div class="table-responsive">
                                        <table id="datatable"    class="table table-bordered dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                            <thead>
                                            <tr>
                                                <th>조직직함</th>
                                                <th>관리레벨</th>
                                                <th>지역</th>
                                                <th>지역구</th>
                                                <th>세부지역</th>
                                                <th>소속단체</th>
                                                <th>이름</th>
                                                <th>전화번호</th>
                                                <th>생년월일</th>
                                                <th>교회</th>
                                                <th>직분</th>
                                                <th>주소</th>
                                            </tr>
                                            </thead>
        
        
                                            <%-- <tbody>
                                           
		                                        <c:if test="${page.getContent().size()>=1 }">
														<c:forEach var="item" items="${page.getContent()}" varStatus="status"> 
	                                                    <tr>
	                                                    	<td>${status.count }</td>
	                                                        <td>${item.name }</td>
	                                                        <td>${item.representiveName }</td>
	                                                        <td>${item.phone }</td>
	                                                        <td>${item.representiveCode }</td>
	                                                        <td>${item.totalCount }</td>
	                                                        <td>${item.detailAddress }</td>
	                                                        <td>-</td>
	                                                        <td>-</td>
	                                                        
	                                                    </tr>
														</c:forEach>
			                                        
		                                        </c:if>
												<c:if test="${page.getContent().size()==0 }">
													<tr >
														<td colspan="9">데이터가 없습니다.</td>	                                                       
													</tr>
												</c:if>
                                           
                                            </tbody> --%>
                                        </table>
                                        <c:if test="${page.getContent().size()==0 }">
													<tr >
														<td colspan="9">데이터가 없습니다.</td>	                                                       
													</tr>
										</c:if>
                                        </div>
        
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                        <!-- end row -->

                       
                </div>
                <!-- End Page-content -->
        	</div>
        	</div>
        	
        	<jsp:include page="../template/footer.jsp"></jsp:include>

        </div>
        

    </body>
<script src="/resources/assets/libs/jquery/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
/*     $("#datatable").DataTable({
       language: {
            paginate: {
                previous: "<i class='mdi mdi-chevron-left'>",
                next: "<i class='mdi mdi-chevron-right'>"
            }
        },
        drawCallback: function() {
            $(".dataTables_paginate > .pagination").addClass("pagination-rounded")
        }
        
    }); */
    
    var hangNumber = 1;
    var url = "/admin/member/listtable.do";
    $('#datatable').DataTable({
    	paging : true,
    	info: true,
    	searching: false,
    	"pageLength": 10,
    	"serverSide" : true,
    	"pagingType" : "full_numbers",
    	"processing" : true,
    	"destroy" : true,
    	"lengthChange" : true,
        "columns" : [ 
        	{"data" : "rank"},
        	{"data" : "level"},
        	{"data" : "cityN"},
        	{"data" : "gunN"},
        	{"data" : "dongN"},
        	{"data" : "groupName"},
        	{"data" : "name"},
        	{"data" : "phone"},
        	{"data" : "yyyymmdd"},
        	{"data" : "church"},
        	{"data" : "churchRank"},
        	{"data" : "detailAddress"},
        	
       	],
        "ajax" : {
        	type : "POST",
            url : url,
            "data" : {
                "columnsize" : 12,
            }
        },
        
        "createdRow": function ( row, data, index ) {
        	console.log("지역!!");
        	console.dir(row);
        	console.dir(data);
        	console.dir(index);
        }
       
    });



});

</script>
    