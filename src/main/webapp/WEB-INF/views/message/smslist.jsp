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
                                    <h4 class="mb-sm-0">SMS 이력</h4>
                                    
                                </div>
                            </div>
                        </div>
                        <!-- end page title -->
                        <div class="row">
                        
				                                    <div class="mb-3 row">
				                                            <label for="example-date-input" class="col-md-1 col-form-label">날자 조건</label>
				                                            <div class="col-md-5">
				                                                <input class="form-control" type="date" value="${startDate}" id="startDate" >
				                                                
				                                            </div>
															<div class="col-md-5">
				                                                <input class="form-control" type="date" value="${endDate}" id="endDate" >
				                                            </div>
				                                            <div class="col-md-1">
				                                            	<button type="button" class="btn btn-primary" onclick="search()">검색</button>
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
		                                                <th>발신자</th>
		                                                <th>내용</th>
		                                                <th>발신일자</th>
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
var edt = new Date(edate);
var sdt = new Date(sdate);

var dateDiff = Math.ceil((edt.getTime()-sdt.getTime())/(1000*3600*24));

if(dateDiff >7){
	
	Swal.fire({
		title: "최대 1주일 검색 가능합니다.",
        text: "",
        icon: "error",
        confirmButtonColor: "#ff3d60",
        confirmButtonText: "확인"
    });
	return false;
}

var list =${list};



	
var inputform = {};
	
	inputform.sDate = $("#startDate").val();
	inputform.eDate = $("#endDate").val();

	
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
        "data":list.list,
        "columns" : [
        	{"data" : "mid",render:function(a,b,c,d){
        		return d.row + d.settings._iDisplayStart +1;
        	}},   
        	{"data" : "sender"},
        	{"data" : "msg"},
        	{"data" : "reg_date"},
/*          	{"data" : "payDate",     	render: function (data, type, full, meta){
         		return full.payDate.substr(0,4)+"-"+full.payDate.substr(4,2)+"-"+full.payDate.substr(6,2)
         	}}, */
        ]
    	
    });
	
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
	
	sdate = sdate.replaceAll("-","");
	edate = edate.replaceAll("-","");
	var inputform = {};
	inputform.sdate = sdate;
	inputform.edate = edate;
	ajax("/admin/message/smslist.do",inputform,function(result){
		
		console.log("result!!");
		console.dir(result);
		//JSON.string
		
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
	        "data":result.list,
	        "columns" : [
	        	{"data" : "mid",render:function(a,b,c,d){
	        		return d.row + d.settings._iDisplayStart +1;
	        	}},   
	        	{"data" : "sender"},
	        	{"data" : "msg"},
	        	{"data" : "reg_date"},
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
    