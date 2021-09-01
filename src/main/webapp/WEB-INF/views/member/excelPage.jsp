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
                                    <h4 class="mb-sm-0">엑셀 당원 등록</h4>

                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">당원관리</a></li>
                                            <li class="breadcrumb-item active">엑셀 등록</li>
                                        </ol>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!-- end page title -->
                        <div class="row">
                        	<%-- <form action="/admin/member/excel/upload.do" method="POST" enctype="multipart/form-data" id="uploadForm"> --%>
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
									
                                        <div class="mb-3 row">
                                            <label class="col-md-2 col-form-label">엑셀 파일 등록</label>
                                            <div class="col-md-10">
	 											<div class="mb-3">
	                                                <label class="form-label">파일선택</label>
	                                                <!-- <input type="file" name="file" id="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"> -->
	                                                <input id="file" type="file" name="file" >
	                                                <input type="button" value="업로드"  id="clickupload" onclick="upload()">
	                                                <!-- <input type="file" class="filestyle" data-buttonname="btn-secondary"> -->
	                                            </div>
                                            </div>
										</div>
 										
										<div class="text-center mt-4">
                                            <button type="button" class="btn btn-primary waves-effect waves-light" id="download" >샘플파일 다운로드</button>
                                            <button type="button" class="btn btn-primary waves-effect waves-light" id="commit" >excel 반영</button>
                                        </div>
                                       
                                    </div>
                                </div>
                                
                            </div> <!-- end col -->
                            <%-- </form> --%>
                        </div>
                        <!-- end row -->
                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="table-responsive">
	                                        <div style="margin-bottom: 10px">
	                                        <!-- table start -->
		                                        <table id="datatable"    class="table table-bordered dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
		                                            <thead>
		                                            <tr>
		                                                <th>#</th>
		                                                <th>가입날짜</th>
		                                                <th>이름</th>
		                                                <th>생년월일</th>
		                                                <th>연락처</th>
		                                                <th>성별</th>
		                                                <th>지역</th>
		                                                <th>구역1</th>
		                                                <th>구역2</th>
		                                                <th>상세주소</th>
		                                                <th>직책</th>
		                                                <th>등급</th>
		                                                <th>당원</th>
		                                                <th>교회</th>
		                                                <th>교회직분</th>
		                                                <th>단체</th>
		                                                <th>단체직함</th>
		                                                <th>추천인 이름</th>
		                                                <th>추천인 연락처</th>
		                                                <th>관리권한</th>
		                                                
		                                            </tr>
		                                            </thead>
		                                        </table>
	                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                        
                        </div>
		            <!-- ============================================================== -->
		            <!-- End right Content here -->
		            <!-- ============================================================== -->

                    </div> <!-- container-fluid -->
                </div>
                <!-- End Page-content -->
        	</div>
        	
        	<jsp:include page="../template/footer.jsp"></jsp:include>

        </div>

    </body>
    
<script type="text/javascript">
var targetFile;

$("#download").on("click",function(event){
	location.href = "/admin/member/example/filedownload.do";
});


$("#commit").on("click",function(event){
	var table = $('#datatable').DataTable();

	var validation = true;
	var list =[];
	
	
	table.rows().every( function () {
	    var d = this.data();
	 	list.push(d);
	    console.dir(d);
	    if(!d.validationYn){
	    	validation = false;
	    	return;
	    }
	});
	
	if(!validation){
		Swal.fire({
	        title: "데이터를 확인해주세요.",
	        text: "",
	        icon: "error",
	        confirmButtonColor: "#ff3d60",
	        confirmButtonText: "확인"
	    })	
	    return false;
	} 

	var url = "/admin/member/excelcommit.do";
	
	
	ajaxSendData(url,list,function(result){
		console.dir(result);
		Swal.fire({
	        title: "성공",
	        text: "총 "+result.incomcount + "갯 수중 "+ result.outcount + "개 성공",
	        icon: "success"
	    });	
		
		
	});
	
  return false;
	
});


 

$('#file').change(function(e) {
	
	targetFile = e.target.files[0];
});

function upload(){
	
				var formData = new FormData();
			    var file = document.getElementById("file");
					formData.append("file", file.files[0]);
					//formData.append("fromJSP", fromcontroller.TOCOMP+":"+fromcontroller.ITEMWRTNO);
					
					$.ajax({
						url: '/admin/member/excel/upload.do',
						data: formData,
						processData: false,
						contentType: false,
						type: 'post',
						success: function(data){

						    $('#datatable').DataTable({
						    	paging : false,
						    	data:data.data,
						    	searching: false,
						    	"dataSrc": "",
						    	"pagingType" : "full_numbers",
						    	"processing" : true,
						    	"destroy" : true,
						        "columns" : [ 
						        	{"data" : "name",render:function(a, b, c,d){
						        		return d.row + d.settings._iDisplayStart +1;
						        	}},        	
						        	{"data" : "endDate",render:function(data, type, row ){
						        		if(row.endDateval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "name",render:function(data, type, row ){
						        		if(row.nameval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "yyyymmdd",render:function(data, type, row ){
						        		if(row.yyyymmddval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "phone",render:function(data, type, row ){
						        		if(row.phoneval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "sex",render:function(data, type, row ){
						        		if(row.sexval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "cityN",render:function(data, type, row ){
						        		if(row.cityCodeval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "gunN",render:function(data, type, row ){
						        		if(row.gunCodeval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "dongN",render:function(data, type, row ){
						        		if(row.dongCodeval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "detailAddress",render:function(data, type, row ){
						        		if(row.detailAddressval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "mrank",render:function(data, type, row ){
						        		if(row.mrankval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "level",render:function(data, type, row ){
						        		if(row.levelval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "dangwon",render:function(data, type, row ){
						        		if(row.dangwonval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "church",render:function(data, type, row ){
						        		if(row.churchval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "churchRank",render:function(data, type, row ){
						        		if(row.churchRankval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "groupName",render:function(data, type, row ){
						        		if(row.groupKeyval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "groupJikham",render:function(data, type, row ){
						        		if(row.groupJikhamval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "recommandName",render:function(data, type, row ){
						        		if(row.recommandNameval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "recommandPhone",render:function(data, type, row ){
						        		if(row.recommandPhoneval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						        	{"data" : "adminAuth",render:function(data, type, row ){
						        		if(row.adminAuthval !=true){
						        			return '<p style="color: red">'+data+'</p>';	
						        		}else{
						        			return data;	
						        		}
						        	}},
						       	],
						       	
                                /* <th>단체</th>
                                <th>단체직함</th>
                                <th>이름</th>
                                <th>생년월일</th>
                                <th>연락처</th>
                                <th>성별</th>
                                <th>지역</th>
                                <th>구역1</th>
                                <th>구역2</th>
                                <th>상세주소</th>
                                <th>직책</th>
                                <th>등급</th>
                                <th>당원</th>
                                <th>교회</th>
                                <th>교회직분</th>
                                <th>추천인 이름</th>
                                <th>추천인 연락처</th>
                                <th>관리권한</th> */
						       	

						    });
						
						},error: function(XMLHttpRequest, textStatus, errorThrown) { 
							 alert("file size를 확인하세요. 최대 : "+byteCalculation(fileuploadMax));
			            }       
					});// ajax
					
		
}



</script>
    