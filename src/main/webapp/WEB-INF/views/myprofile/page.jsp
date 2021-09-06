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
        	
        	<header>
				<script src="/resources/assets/js/pages/form-mask.init.js"></script>
			</header>
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
                                    <h4 class="mb-sm-0">내정보</h4>


                                </div>
                            </div>
                        </div>
                        <!-- end page title -->


                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
	                                    <div class="mb-3 row">
										 <label for="example-date-input" class="col-md-1 col-form-label">가입날짜</label>
 										 <div class="col-md-5">
                                                <input class="form-control" type="date" value="" id="endDate"  readonly="readonly">
                                         </div>
						                 <label class="col-md-1 col-form-label" >패스워드 변경</label>
                                            <div class="col-md-2">
                                                <input class="form-control" type="password" value="" id="password1">
                                            </div>
                                            <div class="col-md-2">
                                                <input class="form-control" type="password" value="" id="password2">
                                            </div>
	                                    </div>

                                        
                                        <div class="mb-3 row">
                                            <label for="example-search-input" class="col-md-1 col-form-label">이름</label>
                                            <div class="col-md-5">
                                                <input class="form-control" type="search"  id="name" readonly="readonly">
                                            </div>
                                            <label class="col-md-1 col-form-label">성별</label>
                                            <div class="col-md-5">
                                                <select class="form-select" id="sex" disabled="disabled">
													<option value="남자">남자</option>
													<option value="여자">여자</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                        
                                            <label for="example-email-input" class="col-md-1 col-form-label">생년월일</label>
                                            <div class="col-md-5">
                                                <!-- <input class="form-control" type="email"  id="example-email-input"> -->
                                                <!-- <input id="yyyymmdd" class="form-control input-mask" data-inputmask="'alias': 'datetime'" data-inputmask-inputformat="yyyy/mm/dd" inputmode="numeric"> -->
                                                <input id="yyyymmdd" class="form-control input-mask" data-inputmask="'alias': 'datetime'" data-inputmask-inputformat="yyyy/mm/dd" readonly="readonly">
                                            </div>
                                            
											<label for="example-email-input" class="col-md-1 col-form-label">연락처</label>
                                            <div class="col-md-5">
                                                <!-- <input class="form-control" type="email"  id="example-email-input"> -->
                                                <input id="phone" class="form-control input-mask"  data-inputmask="'mask': '999-9999-9999'" readonly="readonly">
                                            </div>

                                        </div>
                                        <div class="mb-3 row">
                                            <label class="col-md-1 col-form-label">주소</label>
                                            <div class="col-md-3">
                                                <select class="form-select" onchange="clickCity(this)" id="city" >
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
                                                <input class="form-control" type="text" id="mrank" readonly="readonly">
                                            </div>
 											<label class="col-md-1 col-form-label">등급</label>
                                            <div class="col-md-3">
                                                <select class="form-select" id="level" disabled="disabled">
													<option value="읍면동">읍면동</option>
													<option value="시군구">시군구</option>
													<option value="전국">전국</option>
													
                                                </select>
                                            </div>
 											<label class="col-md-1 col-form-label">당원</label>
                                            <div class="col-md-3">
                                                <select class="form-select" id="dangwon" disabled="disabled">
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

	                                        <div class="mb-3 row">
	                                            <label class="col-md-1 col-form-label">단체명</label>
	                                            <div class="col-md-5">
	                                                <select class="form-select" id="groupKey" disabled="disabled">
	                                                <option value="0">소속단체없음.</option>
														<c:forEach var="item" items="${groups}" varStatus="status"> 
																	<option value="${item.groupKey}:${item.name}">${item.groupKey}:${item.name}</option>
														</c:forEach>
	                                                </select>
	                                            </div>
  												<label for="example-search-input" class="col-md-1 col-form-label">단체직함</label>
	                                            <div class="col-md-5">
	                                                <input class="form-control" type="search"  id="groupJikham" readonly="readonly">
	                                            </div>
											</div>
                                        </div>
                                        
                                        <div class="mb-3 row">
                                            <label for="example-tel-input" class="col-md-1 col-form-label">추천인 이름</label>
                                            <div class="col-md-5">
                                                <input class="form-control" type="text" value="" id="recommandName" readonly="readonly">
                                            </div>
                                            <label for="example-tel-input" class="col-md-1 col-form-label">추천인 연락처</label>
                                            <div class="col-md-5">
                                                <input class="form-control" type="text" value="" id="recommandPhone" readonly="readonly">
                                            </div>
                                        </div>
                                        
                                        <div class="mb-3 row">
                                            <label class="col-md-1 col-form-label">관리권한</label>
                                            <div class="col-md-5">
                                                <select class="form-select" id="adminAuth" disabled="disabled">
													<option value="01">비허용</option>
													<option value="02">허용</option>
                                                </select>
                                            </div>
                                        </div>
                                        
										<div class="text-center mt-4">
                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="make()" id="make">정보 수정</button>
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
	
	init();
};

function init(){
	
	var regDt = new Date("${member.regDt}");
	regDt = formatDate(regDt);
	$("#endDate").val(regDt);
	
	$("#name").val("${member.name}");
	$("#sex").val("${member.sex}");
	$("#yyyymmdd").val("${member.yyyymmdd}");
	$("#phone").val("${member.phone}");
	$("#city").val("${member.cityCode}");
	$("#gun").val("${member.gunCode}");
	$("#dong").val("${member.dongCode}");
	$("#detailAddress").val("${member.detailAddress}");
	$("#mrank").val("${member.mrank}");
	$("#level").val("${member.level}");
	
	$("#dangwon").val("${member.dangwon}");
	$("#church").val("${member.church}");
	$("#churchRank").val("${member.churchRank}");
	$("#groupKey").val("${member.groupKey}:${member.groupName}");
	$("#groupJikham").val("${member.groupJikham}");
	
	$("#recommandName").val("${member.recommandName}");
	$("#recommandPhone").val("${member.recommandPhone}");
	
	$("#adminAuth").val("${member.adminAuth}");
	
	
}

    

function make(){
	var inputform = {};
	
	
	inputform.cityCode = $("#city").val();
	inputform.gunCode = $("#gun").val();
	inputform.dongCode = $("#dong").val();
	inputform.cityN = $("#city option:selected").text();
	inputform.gunN = $("#gun option:selected").text();
	inputform.dongN = $("#dong option:selected").text();
	inputform.detailAddress = $("#detailAddress").val();
	
	inputform.church = $("#church").val();
	inputform.churchRank = $("#churchRank option:selected").val();
	inputform.phone = "${member.phone}";
	
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	


	if(password1.replaceAll(" ","") == "" && password2.replaceAll(" ","") ==""){
		
	}else{
		if(password1 != password2){
			
			  Swal.fire({
	              title: "패스워드를 확안하세요",
	              text: "",
	              icon: "error",
	              confirmButtonColor: "#ff3d60",
	              confirmButtonText: "확인"
	          })
	          return false;
		}
		inputform.phonePassword = password1;
		inputform.checked = true;
	}
	
	
 	var validation = true;
	
 	$.each( inputform, function( key, value ) {
		  console.log("key : " + key  + ", value :" + value);

		  if(key == "phonePassword"){
			  
		  }else{
			 
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
		  }
		  

	}); 

 	if(!validation) return false;
	
	
	Swal.fire({
        title: "정보를 수정 하시겠습니까?",
        text: "",
        icon: "warning",
        showCancelButton: !0,
        confirmButtonText: "수정",
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
	var url = "/admin/myprofile/edit.do";
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
    