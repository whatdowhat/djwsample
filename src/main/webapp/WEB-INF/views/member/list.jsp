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
        	
        	<link href="/resources/assets/css/select/select.dataTables.scss" rel="stylesheet" type="text/css" />
		 	<script src="/resources/assets/js/select/dataTables.select.js"></script>
        	<link href="/resources/assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />
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
<!--                                     <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">당원관리</a></li>
                                            <li class="breadcrumb-item active">당원현황</li>
                                        </ol>
                                    </div> -->
                                    <button type="button" class="btn btn-primary"  onclick="goEditPage()">정보 수정</button>
                                </div>
                            </div>
                        </div>
                        <!-- end page title -->
                        <div class="row">
                        
                        

                            <div class="col-xl-12">
                                <div class="card">
                                    <div class="card-body">
                                    
										<p class="card-title-desc">검색조건을 입력하지 않는 경우 전체 찾기 입니다.</p>
                                        <div id="accordion">
                                            <div class="card mb-0">
                                                <div class="card-header" id="headingTwo">
                                                    <h5 class="m-0 font-size-14">
                                                        <a class="collapsed text-dark" data-bs-toggle="collapse"
                                                            data-parent="#accordion" href="#collapseTwo"
                                                            aria-expanded="false" aria-controls="collapseTwo">
                                                            	검색조건 필요시 클릭
                                                        </a>
                                                    </h5>
                                                </div>
                                                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                                                    <div class="card-body">
			                                        <div class="mb-3 row">
			                                            <label for="example-tel-input" class="col-md-2 col-form-label">이름</label>
			                                            <div class="col-md-10">
			                                                <input type="text" class="form-control" id="name" >
			                                            </div>
			                                        </div>
			                                        <div class="mb-3 row">
			                                            <label for="example-tel-input" class="col-md-2 col-form-label">연락처</label>
			                                            <div class="col-md-10">
			                                                 <input type="text" class="form-control" id="phone">
			                                            </div>
			                                        </div>
			                                        
			                                        
			                                        <div class="mb-3 row">
			                                            <label class="col-md-2 col-form-label">당원(일반/책임)</label>
			                                            <div class="col-md-10">
			                                                <select class="form-select" id="dangwon">
																<option value="0">전체</option>
																<option value="일반">일반</option>
																<option value="책임">책임</option>
			                                                </select>
			                                            </div>
			                                        </div>
		                                        	                                        
		
			                                        <div class="mb-3 row">
			                                            <label for="example-tel-input" class="col-md-2 col-form-label">지역</label>
			                                            <div class="col-md-10">
			                                                 <input type="text"  class="form-control" id="cityN">
			                                            </div>
			                                        </div>
			                                        <div class="mb-3 row">
			                                            <label for="example-tel-input" class="col-md-2 col-form-label">구역1</label>
			                                            <div class="col-md-10">
			                                                 <input type="text"  class="form-control" id="gunN">
			                                            </div>
			                                        </div>
			                                        <div class="mb-3 row">
			                                            <label for="example-tel-input" class="col-md-2 col-form-label">지역2</label>
			                                            <div class="col-md-10">
			                                                 <input type="text"  class="form-control" id="dongN">
			                                            </div>
			                                        </div>
			                                        
			                                        <div class="mb-3 row">
			                                            <label for="example-tel-input" class="col-md-2 col-form-label">주소</label>
			                                            <div class="col-md-10">
			                                                 <input type="text" class="form-control" id="detailAddress">
			                                            </div>
			                                        </div>
			                                        
			                                        <div class="mb-3 row">
			                                            <label class="col-md-2 col-form-label">단체</label>
			                                            <div class="col-md-10">
															<select class="form-select"  id="groupName">
																<option value="0">전체</option>
																<c:forEach var="item" items="${groups}" varStatus="status"> 
																		<option value="${item.name}">${item.name}</option>
																</c:forEach>
			                                                </select>
			                                            </div>
			                                        </div>
		                                        
			                                        <div class="mb-3 row">
			                                            <label class="col-md-2 col-form-label">등급</label>
			                                            <div class="col-md-10">
			                                                <select class="form-select" id="level">
																<option value="0">전체</option>
																<option value="전국">전국</option>
																<option value="시군구">시군구</option>
																<option value="읍면동">읍면동</option>
			                                                </select>
			                                            </div>
			                                        </div>
			                                        <div class="mb-3 row">
			                                            <label for="example-tel-input" class="col-md-2 col-form-label">추천인</label>
			                                            <div class="col-md-10">
			                                                 <input type="text" class="form-control" id="recommandName">
			                                            </div>
			                                        </div>
			                                        <div class="mb-3 row">
			                                            <label for="example-tel-input" class="col-md-2 col-form-label">교회</label>
			                                            <div class="col-md-10">
			                                                 <input type="text" class="form-control" id="church">
			                                            </div>
			                                        </div>
			                                        
				                                    <div class="mb-3 row">
				                                        <label class="col-md-2 col-form-label">교회직분</label>
				                                            <div class="col-md-10">
				                                                <select class="form-select" id="churchRank">
																	<option value="0">전체</option>
																	<option value="청년">청년</option>
																	<option value="집사">집사</option>
																	<option value="안수집사">안수집사</option>
																	<option value="권사">권사</option>
																	<option value="장로">장로</option>
																	<option value="전도사">전도사</option>
																	<option value="강도사">강도사</option>
																	<option value="목사">목사</option>
																	<option value="사모">사모</option>
																	<option value="선교사">선교사</option>
																	<option value="기타">기타</option>
				                                                </select>
				                                            </div>
				                                    </div>
				                                    <div class="mb-3 row">
				                                            <label for="example-date-input" class="col-md-2 col-form-label">시작 날짜</label>
				                                            <div class="col-md-10">
				                                                <input class="form-control" type="date" value="${startDate}" id="startDate" >
				                                            </div>
				                                    </div>
				                                    <div class="mb-3 row">
				                                            <label for="example-date-input" class="col-md-2 col-form-label">종료 날짜</label>
				                                            <div class="col-md-10">
				                                                <input class="form-control" type="date" value="${endDate}" id="endDate" >
				                                            </div>
				                                    </div>
				                                    <div style="display: flex;"><button type="button" class="btn btn-primary" style="margin-left: auto;" onclick="search()">검색</button></div>
	                                            </div>
                                                </div>
                                            </div>
                                        </div>
                                    
                                    </div>
								</div>
							</div>
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="table-responsive">
	                                        <div style="margin-bottom: 10px">
	                                        <!-- table start -->
		                                        <table id="datatable"    class="table table-bordered dt-responsive nowrap display select" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
		                                            <thead>
		                                            <tr>
		                                             	<th><input type="checkbox" name="selectBoxTargetAll" id="selectBoxTargetAll" value="all" onclick="selectAll()"></th>
		                                                <th>#</th>
		                                                <th>당원(일반/책임)</th>
		                                                <th>이름</th>
		                                                <th>연락처</th>
		                                                <th>지역</th>
		                                                <th>구역1</th>
		                                                <th>구역2</th>
		                                                <th>생년월일</th>
		                                                <th>주소</th>
		                                                <th>등급</th>
		                                                <th>단체</th>
		                                                <th>추천인</th>
		                                                <th>추천인연락처</th>
		                                                <th>가입날짜</th>
		                                            </tr>
		                                            </thead>
		                                        </table>
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
<script type="text/javascript">

var selectedData;
var table;
$(document).ready(function() {
    
var inputform = {};
	
	inputform.name = $("#name").val();
	inputform.yyyymmdd = $("#yyyymmdd").val();
	inputform.phone = $("#phone").val();

	inputform.cityN = $("#cityN").val();
	inputform.gunN = $("#gunN").val();
	inputform.dongN = $("#dongN").val();

	inputform.dangwon = $("#dangwon").val();
	inputform.recommandName = $("#recommandName").val();
	inputform.recommandPhone = $("#recommandPhone").val();
	
	inputform.groupName = $("#groupName").val();
	inputform.detailAddress = $("#detailAddress").val();
	inputform.level = $("#level").val();
	
	
	inputform.church = $("#church").val();
	inputform.churchRank = $("#churchRank").val();

	inputform.startDate = $("#startDate").val();
	inputform.endDate = $("#endDate").val();

	
	console.dir(inputform);
	
	
	
    var url = "/admin/member/listtable.do";
    $.fn.dataTable.ext.errMode = 'none';
    table = $('#datatable').DataTable({
    	select: true,
    	 
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
        	{"data" : "checked",     	render: function (data, type, full, meta){
        		$("#selectBoxTargetAll").prop("checked",false);
     	       return '<input type="checkbox" name="selectBoxTarget" id="selectBoxTarget"  value="'+full.seq+','+full.name+','+full.phone+'">'
        	}},
        	
        	{"data" : "seq",render:function(a,b,c,d){
        		return d.row + d.settings._iDisplayStart +1;
        	}},        	
        	{"data" : "dangwon"},
        	{"data" : "name"},
        	{"data" : "phone"},
        	{"data" : "cityN"},
        	{"data" : "gunN"},
        	{"data" : "dongN"},
        	{"data" : "yyyymmdd"},
        	
        	{"data" : "detailAddress"},
        	{"data" : "level"},
        	{"data" : "groupName"},
        	{"data" : "recommandName"},
        	{"data" : "recommandPhone"},
        	/* {"data" : "regDt"}, */
        	{"data" : "regDt",render:function(a,b,c,d){
        		if(c.regDt != '' && c.regDt != undefined){
        			//var d = new Date(c.yyyymmdd);
        			return formatDate(c.regDt);
        			
        		}else{
        			return "-";
        		}
        		
        		
        	}},  
       	],
       	'columnDefs': [{
       	   'targets': 0,
       	   'searchable':false,
       	   'orderable':false,
       	   'className': 'dt-body-center',
       	   'render': function (data, type, full, meta){
       	       return '<input type="checkbox" name="id[]" value="' + $('<div/>').text(data).html() + '">';
       	   }
       	}],
        "ajax" : {
        	type : "POST",
            url : url,
            "data" : {
                "columnsize" : 14,
                "vo" : inputform,
            }
        },
    });
    

   	$('#datatable tbody').on( 'click', 'tr', function () {
  		selectedData = table.rows('.selected').data()[0].name
  		console.dir(table.rows('.selected').data()[0].name);
    } );  
    
    
	
    
});


function goEditPage(){
	
	var data = table.rows('.selected').data();
	if(data[0] ==undefined){
		 
		Swal.fire({
             title: "수정 할 회원을 선택하세요",
             text: "",
             icon: "error",
             confirmButtonColor: "#ff3d60",
             confirmButtonText: "확인"
         })
         return false;
	}
	var phone = data[0].phone;
	goMember(phone);
	
}

function goMember(phone){
 	var parm = new Array();
	parm.push( ['phone', phone] );
    goPage("/admin/myprofile/pageAdmin.do",parm);
    		
}


function search(){
	
	var inputform = {};
	inputform.name = $("#name").val();
	inputform.yyyymmdd = $("#yyyymmdd").val();
	inputform.phone = $("#phone").val();

	inputform.cityN = $("#cityN").val();
	inputform.gunN = $("#gunN").val();
	inputform.dongN = $("#dongN").val();

	inputform.dangwon = $("#dangwon").val();
	inputform.recommandName = $("#recommandName").val();
	inputform.recommandPhone = $("#recommandPhone").val();
	
	inputform.groupName = $("#groupName").val();
	inputform.detailAddress = $("#detailAddress").val();
	inputform.level = $("#level").val();
	
	
	inputform.church = $("#church").val();
	inputform.churchRank = $("#churchRank").val();

	inputform.startDate = $("#startDate").val();
	inputform.endDate = $("#endDate").val();
	
    var url = "/admin/member/listtable.do";
    $('#datatable').DataTable({
    	select: true,
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
        	{"data" : "checked",     	render: function (data, type, full, meta){
        		$("#selectBoxTargetAll").prop("checked",false);
     	       return '<input type="checkbox" name="selectBoxTarget" id="selectBoxTarget"  value="'+full.seq+','+full.name+','+full.phone+'">'
        	}},
        	{"data" : "seq",render:function(a,b,c,d){
        		return d.row + d.settings._iDisplayStart +1;
        	}},        	
        	{"data" : "dangwon"},
        	{"data" : "name"},
        	{"data" : "phone"},
        	{"data" : "cityN"},
        	{"data" : "gunN"},
        	{"data" : "dongN"},
        	{"data" : "yyyymmdd"},
        	
        	{"data" : "detailAddress"},
        	{"data" : "level"},
        	{"data" : "groupName"},
        	{"data" : "recommandName"},
        	{"data" : "recommandPhone"},
        	/* {"data" : "regDt"}, */
        	{"data" : "regDt",render:function(a,b,c,d){
        		
        		if(c.regDt != '' && c.regDt != undefined){
        			//var d = new Date(c.yyyymmdd);
        			return formatDate(c.regDt);
        			
        		}else{
        			return "-";
        		}
        		
        		
        	}},  
       	],
       	'columnDefs': [{
        	   'targets': 0,
        	   'searchable':false,
        	   'orderable':false,
        	   'className': 'dt-body-center',
        	   'render': function (data, type, full, meta){
        	       return '<input type="checkbox" name="id[]" value="' + $('<div/>').text(data).html() + '">';
        	   }
        	}],
        "ajax" : {
        	type : "POST",
            url : url,
            "data" : {
                "columnsize" : 14,
                "vo" : inputform,
            }
        },
    });
	
	
}


function formatDate(date) {
    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}
</script>
    