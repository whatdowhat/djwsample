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
                                    <h4 class="mb-sm-0">당원관리</h4>

                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">당원관리</a></li>
                                            <li class="breadcrumb-item active">당원등록</li>
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

	                                        <div class="mb-3 row">
	                                            <label class="col-md-1 col-form-label">단체명</label>
	                                            <div class="col-md-5">
	                                                <select class="form-select" id="groupKey">
	                                                <option value="0">소속단체없음.</option>
														<c:forEach var="item" items="${groups}" varStatus="status"> 
																	<option value="${item.groupKey}:${item.name}">${item.groupKey}:${item.name}</option>
														</c:forEach>
	                                                </select>
	                                            </div>
  												<label for="example-search-input" class="col-md-1 col-form-label">단체직함</label>
	                                            <div class="col-md-5">
	                                                <input class="form-control" type="search"  id="groupJikham">
	                                            </div>
											</div>
                                        </div>
                                        
                                        <div class="mb-3 row">
                                            <label for="example-search-input" class="col-md-1 col-form-label">이름</label>
                                            <div class="col-md-5">
                                                <input class="form-control" type="search"  id="name">
                                            </div>
                                            <label class="col-md-1 col-form-label">성별</label>
                                            <div class="col-md-5">
                                                <select class="form-select" id="sex">
													<option value="남자">남자</option>
													<option value="여자">여자</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
											<label for="example-email-input" class="col-md-1 col-form-label">연락처</label>
                                            <div class="col-md-5">
                                                <!-- <input class="form-control" type="email"  id="example-email-input"> -->
                                                <input id="phone" class="form-control input-mask" data-inputmask="'alias': 'datetime'" data-inputmask-inputformat="yyyy/mm/dd" inputmode="numeric">
                                            </div>
                                            <label for="example-email-input" class="col-md-1 col-form-label">생년월일</label>
                                            <div class="col-md-5">
                                                <!-- <input class="form-control" type="email"  id="example-email-input"> -->
                                                <input id="yyyymmdd" class="form-control input-mask" data-inputmask="'alias': 'datetime'" data-inputmask-inputformat="yyyy/mm/dd" inputmode="numeric">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label class="col-md-1 col-form-label">주소</label>
                                            <div class="col-md-3">
                                                <select class="form-select" onchange="clickCity(this)" id="city">
													<c:forEach var="item" items="${cities}" varStatus="status"> 
																<option value="${item.cityCode}">${item.cityN}</option>
													</c:forEach>
                                                </select>
 											</div>
	                                        <div class="col-md-4">
	                                                <select class="form-select" onchange="clickGun(this)" id="gun">
														<c:forEach var="item" items="${gus}" varStatus="status"> 
																	<option value="${item.gunCode}">${item.gunN}</option>
														</c:forEach>
	                                                </select>
	                                            </div>
	                                        <div class="col-md-4">
	                                                <select class="form-select" id="dong">
														<c:forEach var="item" items="${dongs}" varStatus="status"> 
																	<option value="${item.dongCode}">${item.dongN}</option>
														</c:forEach>
	                                                </select>
	                                        </div>
                                           

                                        </div>
                                        <div class="mb-3 row">
                                            <label for="example-tel-input" class="col-md-1 col-form-label">상세주소</label>
                                            <div class="col-md-11">
                                                <input class="form-control" type="text"  id="detailAddress">
                                            </div>
                                        </div>
                                        

                                        <div class="mb-3 row">
                                            <label for="example-tel-input" class="col-md-1 col-form-label">직책</label>
                                            <div class="col-md-3">
                                                <input class="form-control" type="text"  id="mrank">
                                            </div>
 											<label class="col-md-1 col-form-label">등급</label>
                                            <div class="col-md-3">
                                                <select class="form-select" id="level">
													<option value="읍면동">읍면동</option>
													<option value="시군구">시군구</option>
													<option value="전국">전국</option>
													
                                                </select>
                                            </div>
 											<label class="col-md-1 col-form-label">당원</label>
                                            <div class="col-md-3">
                                                <select class="form-select" id="dangwon">
													<option value="일반">일반</option>
													<option value="책임">책임</option>
                                                </select>
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="mb-3 row">
                                            <label for="example-tel-input" class="col-md-1 col-form-label">교회</label>
                                            <div class="col-md-5">
                                                <input class="form-control" type="text"  id="church">
                                            </div>
 											<label class="col-md-1 col-form-label">교회직분</label>
                                            <div class="col-md-5">
                                                <select class="form-select" id="churchRank">
													<option value="기타">기타</option>
													<option value="청년">청년</option>
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
                                                </select>
                                            </div>
                                        </div>
                                        
                                        
                                        <div class="mb-3 row">
                                            <label for="example-tel-input" class="col-md-1 col-form-label">추천인 이름</label>
                                            <div class="col-md-5">
                                                <input class="form-control" type="text" value="" id="recommandName">
                                            </div>
                                            <label for="example-tel-input" class="col-md-1 col-form-label">추천인 연락처</label>
                                            <div class="col-md-5">
                                                <input class="form-control" type="text" value="" id="recommandPhone">
                                            </div>
                                        </div>
                                        
                                        <div class="mb-3 row">
                                            <label class="col-md-1 col-form-label">관리권한</label>
                                            <div class="col-md-5">
                                                <select class="form-select" id="adminAuth">
													<option value="01">비허용</option>
													<option value="02">허용</option>
                                                </select>
                                            </div>
                                        </div>
                                        
										<div class="text-center mt-4">
                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="make()" id="make">회원생성</button>
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

    

function make(){
	var inputform = {};
	
	var group = $("#groupKey").val();
	var groups = [];
	if(group == "" || group == undefined){
		groups[0] = "";
		groups[1] = "";
	}else{
		groups = group.split(":");	
	}
	
	
	inputform.groupKey = groups[0];
	inputform.groupName = groups[1];
	
	inputform.name = $("#name").val();
	inputform.yyyymmdd = $("#yyyymmdd").val();
	inputform.phone = $("#phone").val();
	inputform.sex = $("#sex option:selected").val();
	
	inputform.cityCode = $("#city").val();
	inputform.gunCode = $("#gun").val();
	inputform.dongCode = $("#dong").val();
	inputform.cityN = $("#city option:selected").text();
	inputform.gunN = $("#gun option:selected").text();
	inputform.dongN = $("#dong option:selected").text();
	inputform.detailAddress = $("#detailAddress").val();
	
	inputform.recommandName = $("#recommandName").val();
	inputform.recommandPhone = $("#recommandPhone").val();

	
	inputform.groupJikham = $("#groupJikham").val(); //단체직함
	
	inputform.mrank = $("#mrank").val();
	inputform.level = $("#level option:selected").val();
	inputform.dangwon = $("#dangwon option:selected").val();
	inputform.church = $("#church").val();
	inputform.churchRank = $("#churchRank option:selected").val();
	inputform.adminAuth = $("#adminAuth option:selected").val();
	
	
	
	if(inputform.groupKey == ""){
		  Swal.fire({
              title: "단체를 먼저 선택해주세요",
              text: "",
              icon: "error",
              confirmButtonColor: "#ff3d60",
              confirmButtonText: "확인"
          })
          return false;
	}
	
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
	
	
	Swal.fire({
        title: "회원을 등록하시겠습니까?",
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
	var url = "/admin/member/commit";
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
		        text: "이미 생성된 단체코드 입니다.",
		        icon: "error"
		    });
		}
		
		
	})
	
	  
	

}

function clickCity(param){
		var inputform = {};
		inputform.cityCode = param.value;
		//console.log(cityCode);
		var url = '/admin/member/getcity'
		
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
	var url = '/admin/member/getgun'
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
    