<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!doctype html>
<html lang="en">

    <head>
        
        <meta charset="utf-8" />
        <title>Login | Upzet - Admin & Dashboard Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="/resources/assets/images/favicon.ico">

        <!-- Bootstrap Css -->
        <link href="/resources/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="/resources/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="/resources/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />

    </head>

    <body class="bg-pattern">
        <div class="bg-overlay"></div>
        <div class="account-pages my-5 pt-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-8 col-lg-6 col-md-8">
                        <div class="card">
                            <div class="card-body p-4">
                                <div class="">
                                    <div class="text-center">
                                        <h1>${vo.name}</h1>
                                    </div>
                                    <!-- end row -->
                                    <h4 class="font-size-18 text-muted mt-4 text-center">당가입신청서</h4>
                                    <form class="form-horizontal" action="index.html">

										<hr>
                                        <div class="row" style="margin-top: 40px">
                                                <div class="col-6 mb-4">
                                                    <label class="form-label" for="username">이름 </label>
                                                    <input type="text" class="form-control" id="username" >
                                                </div>
                                                <div class="col-6 mb-4">
                                                    <label class="form-label" for="userpassword">생년월일</label>
                                                    <input type="password" class="form-control" id="userpassword" >
                                                </div>
                                                <div class="col-6 mb-4">
                                                    <label class="form-label" for="username">연락처</label>
                                                    <div class="input-group date">
                                                    	<input type="text" class="form-control" id="username" ><button type="button" class="btn btn-primary">인증 요청</button>
                                                    </div>
                                                </div>
                                                <div class="col-6">
                                                    <label class="form-label" for="userpassword">성별</label>
                                                    <div class="col-lg-6">
		                                                <div class="btn-group btn-group-toggle mt-2 mt-lg-0" data-bs-toggle="buttons">
		                                                    <label class="btn btn-primary active" id="option1t">
		                                                        <input class="btn-check" type="radio" name="options" id="option1" onclick="selectFunction(this)"> <i class="mdi mdi-human-male"></i> 남자
		                                                    </label>
		                                                    <label class="btn btn-primary" id="option2t">
		                                                        <input class="btn-check" type="radio" name="options" id="option2" onclick="selectFunction(this)"> <i class="mdi mdi-human-female"></i> 여자
		                                                    </label>
		                                                </div>
                                            		</div>
                                                </div>
                                                
                                                <div class="col-12">
                                                <label class="col-md-2 col-form-label">주소</label>
			                                        <div class="mb-3 row">
			                                            <div class="col-md-4">
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
                                                </div>
 												<div class="col-12 mb-4">
                                                	<label class="col-md-2 col-form-label">상세주소</label>
													<div class="col-md-12">
	                                                	<input class="form-control" type="text"  id="detailAddress">
	                                            	</div>
                                                </div>
                                                
                                                <div class="col-6 mb-4">
                                                    <label class="form-label" for="username">추천인 이름 </label>
                                                    <input type="text" class="form-control" id="username" >
                                                </div>
                                                <div class="col-6 mb-4">
                                                    <label class="form-label" for="userpassword">추천인 연락처</label>
                                                    <input type="password" class="form-control" id="userpassword" >
                                                </div>
                                                <hr>
                                                <div class="d-grid mt-4">
                                                    <button class="btn btn-primary waves-effect waves-light" type="submit">당원가입</button>
                                                </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end row -->
            </div>
        </div>
        <!-- end Account pages -->
        <!-- App favicon -->
        
        <!-- JAVASCRIPT -->
        <script src="/resources/assets/libs/jquery/jquery.min.js"></script>
        <script src="/resources/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="/resources/assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="/resources/assets/libs/simplebar/simplebar.min.js"></script>
        <script src="/resources/assets/libs/node-waves/waves.min.js"></script>
        <!-- form mask -->
        <script src="/resources/assets/libs/inputmask/jquery.inputmask.min.js"></script>
        <script src="/resources/assets/libs/sweetalert2/sweetalert2.min.js"></script>
        <script src="/resources/assets/js/custom.js"></script>
        
                <!-- Required datatable js -->
        <script src="/resources/assets/libs/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="/resources/assets/libs/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>

       <!-- Responsive examples -->
        <script src="/resources/assets/libs/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
        <script src="/resources/assets/libs/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js"></script>
        <!-- form mask init -->

	<script type="text/javascript">
	
	$(document).ready(function() {
		
	});
	
	
	function selectFunction(event){
		
		console.dir(event);
		console.log(event.id);
		console.dir(event.labels[0]['class']);
		var selected = event.labels[0];
		selected.className = "btn btn-primary active";
		if(event.id == "option1"){
			$("#option1t")[0].className = "btn btn-primary active";
			$("#option2t")[0].className = "btn btn-primary";
		}else{
			$("#option2t")[0].className = "btn btn-primary active";
			$("#option1t")[0].className = "btn btn-primary";
		}
		//console.log(selected);
		//event.removeClass("active");
		
	}
	

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
		
		inputform.rank = $("#rank").val();
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

    </body>
</html>
