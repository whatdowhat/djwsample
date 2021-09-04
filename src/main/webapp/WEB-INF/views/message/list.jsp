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
                                    <h4 class="mb-sm-0">쪽지</h4>

                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">메세지관리</a></li>
                                            <li class="breadcrumb-item active">쪽지</li>
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
	                                        <div style="margin-bottom: 10px">
	                                        <!-- table start -->
		                                        <table id="datatable"    class="table table-bordered dt-responsive nowrap display select" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
		                                            <thead>
		                                            <tr>
		                                             	<th><input type="checkbox" name="selectBoxTargetAll" id="selectBoxTargetAll" value="all" onclick="selectAll()"></th>
		                                                <th>#</th>
		                                                <th>발신자 이름</th>
		                                                <th>수신자 이름</th>
		                                                <th>제목</th>
		                                                <th>내용</th>
		                                                <th>발송일</th>
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


$(document).ready(function() {
    
	

	
	
var inputform = {};
	
	inputform.frommemberName = "";
	inputform.tomemberName = "";
	inputform.messageTitle = "";
	inputform.messageTxt = "";
	inputform.regDt = "";

	console.dir(inputform);
	
    var url = "/admin/message/listtable.do";
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
     	       return '<input type="checkbox" name="selectBoxTarget" id="selectBoxTarget"  value="'+full.frommemberseq+','+full.frommemberName+','+full.frommemberPhone+'">'
        	}},
        	
        	{"data" : "seq",render:function(a,b,c,d){
        		return d.row + d.settings._iDisplayStart +1;
        	}},        	
        	{"data" : "frommemberName"},
        	{"data" : "tomemberName"},
        	{"data" : "messageTitle"},
        	{"data" : "messageTxt"},
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
       	   },
       	   /* {
               "targets": [ 3 ],
               "visible": false
           } */
       	}],
        "ajax" : {
        	type : "POST",
            url : url,
            "data" : {
                "columnsize" : 7,
                "vo" : inputform,
            }
        },
    });



});



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
    