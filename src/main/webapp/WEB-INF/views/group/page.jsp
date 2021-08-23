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
                                    <h4 class="mb-sm-0">단체생성</h4>

                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">관리메뉴</a></li>
                                            <li class="breadcrumb-item active">단체생성</li>
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
                                        <div class="mb-3 row">
                                            <label for="example-text-input" class="col-md-2 col-form-label">단체아이디</label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="text"  id="groupKey">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                        
                                            <label for="example-text-input" class="col-md-2 col-form-label">단체명</label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="text"  id="name">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="example-search-input" class="col-md-2 col-form-label">단체연락처</label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="search"  id="phone">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="example-search-input" class="col-md-2 col-form-label">단체장 이름</label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="search"  id="representiveName">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="example-email-input" class="col-md-2 col-form-label">단체장 연락처</label>
                                            <div class="col-md-10">
                                                <!-- <input class="form-control" type="email"  id="example-email-input"> -->
                                                <input id="representiveCode" class="form-control input-mask">
                                            </div>
                                        </div>
                                        
                                        <div class="mb-3 row">
                                            <label for="example-tel-input" class="col-md-2 col-form-label">단체주소</label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="text" value="" id="detailAddress">
                                            </div>
                                        </div>

										<div class="text-center mt-4">
                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="makeGroup()" id="make">단체생성</button>
                                        </div>
										
                                       
                                    </div>
                                </div>
                            </div> <!-- end col -->
                        </div>
                        <!-- end row -->
                        
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

window.onload = function() {
	$("#make").click(function() {
	})
};

    

function makeGroup(){
	var inputform = {};
	
	inputform.name = $("#name").val();
	inputform.representiveName = $("#representiveName").val();
	inputform.representiveCode = $("#representiveCode").val();
	inputform.yyyymmdd = $("#yyyymmdd").val();
	inputform.detailAddress = $("#detailAddress").val();
	inputform.phone = $("#phone").val();
	
	inputform.groupKey = $("#groupKey").val();
	
	
	var validation = true;
	
	$.each( inputform, function( key, value ) {
		  console.log("key : " + key  + ", value :" + value);
		  if(value == ""){
			  validation = false;
			  console.log("empty");
			  Swal.fire({
	                title: "모든 데이터를 입력해주세요",
	                text: "",
	                icon: "error",
	                confirmButtonColor: "#ff3d60",
	                confirmButtonText: "확인"
	            })
			  
			  return false;
		  }
	});

	if(!validation) return false;
	inputform.totalCount = 1; //단체장 default로 들어감.
	
	Swal.fire({
        title: "단체를 생성하시겠습니까?",
        text: "",
        icon: "warning",
        showCancelButton: !0,
        confirmButtonText: "생성",
        cancelButtonText: "취소",
        confirmButtonClass: "btn btn-success mt-2",
        cancelButtonClass: "btn btn-danger ms-2 mt-2",
        buttonsStyling: !1
    }).then(function(t) {
    	
    	if(t.isConfirmed){
    		
    		commit(inputform);
    		
    	}else{
    		//nothing
    	}
        
    });

}
	

function commit(inputform){
	var url = "/admin/group/commit";
	ajax(url,inputform,function(result){
		console.log("ajax");
		console.dir(result);

		if(!result.already){
			Swal.fire({
		        title: "성공",
		        text: "",
		        icon: "success"
		    });	
		}else{
			Swal.fire({
		        title: "실패",
		        text: "이미 생성된 단체아이디 입니다.",
		        icon: "error"
		    });
		}
		
		return;
		
		if(result.exist){
			Swal.fire({
		        title: "성공",
		        text: "",
		        icon: "success"
		    });	
		}else{
			Swal.fire({
		        title: "실패",
		        text: "단체 대표 정보가 존재하지 않습니다.",
		        icon: "error"
		    });
		}
				
		
		
	})
	
}

function clickCity(param){
		var inputform = {};
		inputform.cityCode = param.value;
		//console.log(cityCode);
		var url = '/admin/group/getcity'
		
		ajax(url,inputform,function(result){
			console.log("ajax");
			console.dir(result);
			$("#gun").empty();
			for(var i = 0; i<result.gus.length;i++){
				$("#gun").append("<option value="+result.gus[i].gunCode+">"+result.gus[i].gunN+"</option>");	
			}
			$("#dong").empty();
			for(var i = 0; i<result.dongs.length;i++){
				$("#dong").append("<option value="+result.dongs[i].dongCode+">"+result.dongs[i].dongN+"</option>");	
			}
		})
}

function clickGun(param){
	var inputform = {};
	inputform.cityCode = $("#city").val();
	inputform.gunCode = param.value;
	//console.log(cityCode);
	var url = '/admin/group/getgun'
	ajax(url,inputform,function(result){
		console.log("ajax");
		console.dir(result);
		
		$("#dong").empty();
		for(var i = 0; i<result.dongs.length;i++){
			$("#dong").append("<option value="+result.dongs[i].dongCode+">"+result.dongs[i].dongN+"</option>");	
		}
	})

}

</script>
    