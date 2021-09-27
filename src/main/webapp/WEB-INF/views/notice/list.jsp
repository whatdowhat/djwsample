<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
                                    <h4 class="mb-sm-0">공지사항</h4>

                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">메세지관리</a></li>
                                            <li class="breadcrumb-item active">공지사항</li>
                                        </ol>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!-- end page title -->
                        
						<sec:authorize access="hasAuthority('ROLE_ADMIN')">
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="card">
                                    <div class="card-body">
                                    
                                        <div id="accordion">
                                            <div class="card mb-0">
                                                <div class="card-header" id="headingTwo">
                                                    <h5 class="m-0 font-size-14">
                                                        <a class="collapsed text-dark" data-bs-toggle="collapse"
                                                            data-parent="#accordion" href="#collapseTwo"
                                                            aria-expanded="false" aria-controls="collapseTwo">
                                                            	공지사항 작성하기.
                                                        </a>
                                                    </h5>
                                                </div>
                                                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                                                    <div class="card-body">
			                                        <div class="mb-3 row">
			                                            <label for="example-tel-input" class="col-md-2 col-form-label">제목</label>
			                                            <div class="col-md-10">
			                                                <input type="text" class="form-control" id="noticeTitle" >
			                                            </div>
			                                        </div>
			                                        <div class="mb-3 row">
			                                            <label for="example-tel-input" class="col-md-2 col-form-label">내용</label>
			                                            <div class="col-md-10">
			                                                 <textarea id="noticeText" class="form-control" rows="6"></textarea>
			                                            </div>
			                                        </div>
			                                        
				                                    <div style="display: flex;"><button type="button" class="btn btn-primary" style="margin-left: auto;" onclick="noticeCommit()">공지사항 등록</button></div>
	                                            </div>
                                                </div>
                                            </div>
                                        </div>
                                    
                                    </div>
								</div>
							</div> 
										
										
						</sec:authorize>
                      
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
		                                                <th>공지사항</th>
		                                                <th>내용</th>
		                                                <th>게시자</th>
		                                                <th>게시일</th>
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


function pageload(){
	
	
	var inputform = {};
	inputform.frommemberName = "";
	inputform.noticeText = "";
	inputform.noticeTitle = "";
	inputform.regDt = "";

	
	console.dir(inputform);
	
	
	
    var url = "/admin/notice/listtable.do";
    $.fn.dataTable.ext.errMode = 'none';
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
        	{"data" : "checked",     	render: function (data, type, full, meta){
        		$("#selectBoxTargetAll").prop("checked",false);
     	       return '<input type="checkbox" name="selectBoxTarget" id="selectBoxTarget"  value="'+full.seq+'">'
        	}},
        	
        	{"data" : "seq",render:function(a,b,c,d){
        		return d.row + d.settings._iDisplayStart +1;
        	}},        	
        	{"data" : "frommemberName"},
        	{"data" : "noticeTitle"},
        	{"data" : "noticeText"},
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
       	       return '<input type="checkbox" name="id[]" value="-">';
       	   },
       	}], 
        "ajax" : {
        	type : "POST",
            url : url,
            "data" : {
                "columnsize" : 6,
                "vo" : inputform,
            }
        },
    });
	
}

$(document).ready(function() {
    
	

	
	
var inputform = {};
	
	inputform.frommemberName = "";
	inputform.noticeText = "";
	inputform.noticeTitle = "";
	inputform.regDt = "";

	
	console.dir(inputform);
	
	
	
    var url = "/admin/notice/listtable.do";
    $.fn.dataTable.ext.errMode = 'none';
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
        	{"data" : "checked",     	render: function (data, type, full, meta){
        		$("#selectBoxTargetAll").prop("checked",false);
     	       return '<input type="checkbox" name="selectBoxTarget" id="selectBoxTarget"  value="'+full.seq+'">'
        	}},
        	
        	{"data" : "seq",render:function(a,b,c,d){
        		return d.row + d.settings._iDisplayStart +1;
        	}},        	
        	{"data" : "frommemberName"},
        	{"data" : "noticeTitle"},
        	{"data" : "noticeText"},
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
       	       return '<input type="checkbox" name="id[]" value="-">';
       	   },
       	}], 
        "ajax" : {
        	type : "POST",
            url : url,
            "data" : {
                "columnsize" : 6,
                "vo" : inputform,
            }
        },
    });



});


function noticeCommit(){
	
	if($("#noticeTitle").val().replaceAll(" ","") == ""){
		Swal.fire({
	        title: "실패",
	        text: "제목은 필수입니다.",
	        icon: "error"
	    });		
	}
	else if($("#noticeText").val().replaceAll(" ","") == ""){
		Swal.fire({
	        title: "실패",
	        text: "내용은 필수입니다.",
	        icon: "error"
	    });		
	}else{
		
		var url="/admin/send/notice.do";
		var list = [];
		for(var i =0 ; i<1; i++){
			var item ={};
			item.noticeText = $("#noticeText").val();
			item.noticeTitle = $("#noticeTitle").val();
			list.push(item);
		}
		ajaxSendData(url,list,function(result){
			console.dir(result);
			if(result.result ==true){
				Swal.fire({
		        title: "성공",
		        text: "공지사항 등록완료.",
		        icon: "success"
		    });	 
		    

			$("#noticeTitle").val("");
			$("#noticeText").val("");
					
				
			pageload();
				
			}else{
				Swal.fire({
			        title: "실패",
			        text: "관리자에게 문의하세요.",
			        icon: "error"
			    });
			}
		});
		
	}
	
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
    