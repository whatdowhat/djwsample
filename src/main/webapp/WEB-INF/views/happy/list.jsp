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
        	
            <div class="main-content" id="loading">

                <div class="page-content">
                    <div class="container-fluid">

	            <!-- ============================================================== -->
	            <!-- Start right Content here -->
	            <!-- ============================================================== -->
						<div class="row">
                            <div class="col-12">
                                <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                                    <h4 class="mb-sm-0">해피나눔</h4>
                                    
                                </div>
                            </div>
                        </div>
                        <!-- end page title -->
                        <div class="row">
                        
				                                    <div class="mb-3 row">
				                                            <label for="example-date-input" class="col-md-1 col-form-label">조회 기간:</label>
				                                            <div class="col-md-3">
				                                                <input class="form-control" type="date" value="${startDate}" id="startDate" >
				                                                
				                                            </div>
				                                            ~
															<div class="col-md-3">
				                                                <input class="form-control" type="date" value="${endDate}" id="endDate" >
				                                            </div>
				                                            (최대 90일 검색가능)
				                                            
				                                            <div class="col-md-1">
				                                            	<button type="button" class="btn btn-primary" onclick="search()">조회</button>
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
		                                                <th>#</th>
		                                                <th>멤버이름</th>
		                                                <th>연락처</th>
		                                                <th>결제방법</th>
		                                                <th>결제금액</th>
		                                                <th>결제일</th>
		                                                <th>결제결과</th>
		                                                <th>결제타입</th>
		                                                <th>멤버번호</th>
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
    
var sdate = "${startDate}"; 
var edate = "${endDate}";
var url = "${happyAPI}";
var code = "${happyAPICode}";

var edt = new Date(edate);
var sdt = new Date(sdate);

var dateDiff = Math.ceil((edt.getTime()-sdt.getTime())/(1000*3600*24));

if(dateDiff >90){
	
	Swal.fire({
		title: "최대 1주일 검색 가능합니다.",
        text: "",
        icon: "error",
        confirmButtonColor: "#ff3d60",
        confirmButtonText: "확인"
    });
	return false;
}


sdate = sdate.replaceAll("-","");
edate = edate.replaceAll("-","");
var requestUrl = url + "?" + "code="+code + "&sdate="+sdate+"&edate="+edate;
console.log(requestUrl);
var inputform = {};
inputform.happyurl = requestUrl;
inputform.sdate = sdate;
inputform.edate = edate;
ajax("/admin/happy/listdata.do",inputform,function(result){
	
	console.dir(result);
	//JSON.string

	
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
	
    $.fn.dataTable.ext.errMode = 'none';
    table = $('#datatable').DataTable({
    	select: true,
    	 
    	paging : true,
    	info: true,
    	searching: true,
    	"pageLength": 10,
    	"serverSide" : false,
    	"pagingType" : "full_numbers",
    	"processing" : true,
    	"destroy" : true,
    	"lengthChange" : true,
        "data":result.RESULT_MESSAGE,
        "columns" : [
        	{"data" : "orgaName",render:function(a,b,c,d){
        		return d.row + d.settings._iDisplayStart +1;
        	}},   
        	{"data" : "memNm"},
        	{"data" : "userTel1"},
        	{"data" : "paywayCode"},
        	{"data" : "applyAmount",   	render: function (data, type, full, meta){
      	       return numberWithCommas(full.applyAmount);
         	}},
         	{"data" : "payDate",     	render: function (data, type, full, meta){
         		return full.payDate.substr(0,4)+"-"+full.payDate.substr(4,2)+"-"+full.payDate.substr(6,2)
         	}},
        	{"data" : "payNorCode"},
        	{"data" : "payType"},
        	{"data" : "memNo"},
        ]
    	
    });
	
	}
);
	
});

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}



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
	
	var sdate =  $("#startDate").val(); 
	var edate = $("#endDate").val();
	
	var edt = new Date(edate);
	var sdt = new Date(sdate);

	var dateDiff = Math.ceil((edt.getTime()-sdt.getTime())/(1000*3600*24));

	if(dateDiff >90){
		
		Swal.fire({
	        title: "최대 90일 검색 가능합니다.",
	        text: "",
	        icon: "error",
	        confirmButtonColor: "#ff3d60",
	        confirmButtonText: "확인"
	    });
		return false;
	}
	
	
	var url = "${happyAPI}";
	var code = "${happyAPICode}";

	sdate = sdate.replaceAll("-","");
	edate = edate.replaceAll("-","");
	var requestUrl = url + "?" + "code="+code + "&sdate="+sdate+"&edate="+edate;
	console.log(requestUrl);
	var inputform = {};
	inputform.happyurl = requestUrl;
	inputform.sdate = sdate;
	inputform.edate = edate;
	ajax("/admin/happy/listdata.do",inputform,function(result){
		
		console.dir(result);
		//JSON.string

		if(result.RESULT_CODE == "9999"){
			alert(result.RESULT_MESSAGE);
		}
		
		
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
		
	    $.fn.dataTable.ext.errMode = 'none';
	    table = $('#datatable').DataTable({
	    	select: true,
	    	 
	    	paging : true,
	    	info: true,
	    	searching: true,
	    	"pageLength": 10,
	    	"serverSide" : false,
	    	"pagingType" : "full_numbers",
	    	"processing" : true,
	    	"destroy" : true,
	    	"lengthChange" : true,
	        "data":result.RESULT_MESSAGE,
	        "columns" : [
	        	{"data" : "orgaName",render:function(a,b,c,d){
	        		return d.row + d.settings._iDisplayStart +1;
	        	}},   
	        	{"data" : "memNm"},
	        	{"data" : "userTel1"},
	        	{"data" : "paywayCode"},
	        	{"data" : "applyAmount",   	render: function (data, type, full, meta){
	        		var result ="";
	        		try{
	        			result = numberWithCommas(full.applyAmount);;	
	        		}catch(e){
	        			result = full.applyAmount;
	        		}
	        		
	      	       return result
	         	}},
	         	
	        	{"data" : "payDate",     	render: function (data, type, full, meta){
	        		
	        		var result ="";
	        		try{
	        			result = full.payDate.substr(0,4)+"-"+full.payDate.substr(4,2)+"-"+full.payDate.substr(6,2);	
	        		}catch(e){
	        			result = full.payDate;
	        		}
	        		
	        		return result;
	        	}},
	        	{"data" : "payNorCode"},
	        	{"data" : "payType"},
	        	{"data" : "memNo"},
	        ]
	    	
	    });
		
		}
	);
	
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
    