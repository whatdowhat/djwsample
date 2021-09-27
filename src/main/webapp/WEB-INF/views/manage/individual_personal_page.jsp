<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<%
/*     NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
    
    String sSiteCode = "BV284";			// NICE로부터 부여받은 사이트 코드
    String sSitePassword = "ElInK3g8bwwu";		// NICE로부터 부여받은 사이트 패스워드
    String sRequestNumber = "REQ0000000001";        	// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 
                                                    	// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
    sRequestNumber = niceCheck.getRequestNO(sSiteCode);
  	//session.setAttribute("REQ_SEQ" , sRequestNumber);	// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
   	String sAuthType = "M";      	// 없으면 기본 선택화면, M(휴대폰), X(인증서공통), U(공동인증서), F(금융인증서), S(PASS인증서), C(신용카드)
	String customize 	= "";		//없으면 기본 웹페이지 / Mobile : 모바일페이지
	
    // CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
	//리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
    String sReturnUrl = "http://localhost:8080//public/authReturnS.do";      // 성공시 이동될 URL
    String sErrorUrl = "http://localhost:8080/public/authReturnF.do";            // 실패시 이동될 URL

    // 입력될 plain 데이타를 만든다.
    String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
                        "8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
                        "9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
                        "7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
                        "7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
                        "9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;
    
    String sMessage = "";
    String sEncData = "";
    
    int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
    if( iReturn == 0 )
    {
        sEncData = niceCheck.getCipherData();
    }
    else if( iReturn == -1)
    {
        sMessage = "암호화 시스템 에러입니다.";
    }    
    else if( iReturn == -2)
    {
        sMessage = "암호화 처리오류입니다.";
    }    
    else if( iReturn == -3)
    {
        sMessage = "암호화 데이터 오류입니다.";
    }    
    else if( iReturn == -9)
    {
        sMessage = "입력 데이터 오류입니다.";
    }    
    else
    {
        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
    } */
%>

<html lang="en">

    <head>
        	<!-- 본인인증 서비스 팝업을 호출하기 위해서는 다음과 같은 form이 필요합니다. -->
	<form name="form_chk" method="post">
		<input type="hidden" name="m" value="checkplusService">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<%-- <input type="hidden" name="EncodeData" value="<%= sEncData %>">	 --%>	<!-- 위에서 업체정보를 암호화 한 데이타입니다. -->
		<input type="hidden" name="EncodeData" value="${sEncData }">		<!-- 위에서 업체정보를 암호화 한 데이타입니다. -->
	    
		<!-- <a href="javascript:fnPopup();"> CheckPlus 안심본인인증 Click</a> -->
	</form>
        <meta charset="utf-8" />
        <title>Login | Upzet - Admin & Dashboard Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
        <!-- App favicon -->

        <link rel="shortcut icon" href="/resources/assets/images/favicon.ico">

        <!-- jvectormap -->
        <link href="/resources/assets/libs/jqvmap/jqvmap.min.css" rel="stylesheet" />

        <!-- Bootstrap Css -->
        <link href="/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
        <!-- Icons Css -->
        <link href="/resources/assets/css/icons.min.css" rel="stylesheet" />
        <!-- App Css-->
        <link href="/resources/assets/css/app.min.css"  rel="stylesheet"/>
    	<!-- Sweet Alert-->
        <link href="/resources/assets/libs/sweetalert2/sweetalert2.min.css" rel="stylesheet" type="text/css" />

        <!-- Bootstrap Css -->
        <link href="/resources/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="/resources/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="/resources/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />
        <!-- CANVAS SIGN -->
        <script src="/resources/assets/js/siginPad.js"></script>
		<style type="text/css">
		
.wrapper {
  position: relative;
  width: 400px;
  height: 200px;
  -moz-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.signature-pad {
  position: absolute;
  left: 0;
  top: 0;
  width:400px;
  height:200px;
  background-color: white;
}
		</style>
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
                                    <h4 class="font-size-18 text-muted mt-4 text-center">회원 가입 신청서</h4>

										<hr>
                                        <div class="row" style="margin-top: 40px">
                                                <div class="col-6 mb-4">
                                                    <label class="form-label" for="username">이름 </label>
                                                    <input type="text" class="form-control" id="auth_name" >
                                                </div>
                                                <div class="col-6 mb-4">
                                                    <label class="form-label" for="userpassword">생년월일</label>
                                                    <input type="text" class="form-control" id="auth_yyyymmdd" placeholder="ex:19990101" >
                                                </div>
                                                <div class="col-6 mb-4">
                                                    <label class="form-label" for="username">핸드폰번호</label>
                                                    <div class="input-group date">
                                                    	<%-- <input type="text" class="form-control" id="phone" value="${sMobileNo }" ><button type="button" class="btn btn-primary" onclick="selft_authentic()">인증 요청</button> --%>
                                                    	<input type="text" class="form-control" id="auth_phone" value="" placeholder="ex:01031113222" ><button type="button" class="btn btn-primary" onclick="selft_authentic()" id=authBtn>인증 요청</button>
                                                    </div>
                                                </div>
                                                <input type="hidden" id="sex" value="">
                                                <!-- <div class="col-6">
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
                                                </div> -->
                                                
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
                                                    <input type="text" class="form-control" id="recommandName" >
                                                </div>
                                                <div class="col-6 mb-4">
                                                    <label class="form-label" for="userpassword">추천인 연락처</label>
                                                    <input type="text" class="form-control" id="recommandPhone" >
                                                </div>
                                                <div class="col-12 mb-4">
                                                    <label class="form-label" for="userpassword">서명</label>
														<div class="wrapper" style="border-color: black;max-width: 100% ">
														  <%-- <canvas id="signature-pad" class="signature-pad" width=400 height=200></canvas> --%>
														  <canvas id="signature-pad" class="signature-pad" style="max-width: 100%;border: 1px solid #0bb197"></canvas>
														</div>
														<button class="btn btn-primary waves-effect waves-light" id="clear">지우기</button>
														<!-- <button class="btn btn-primary waves-effect waves-light" id="save-png">저장</button> -->     
														                                              
                                                </div>
												
												<!-- <button id="save-png">Save as PNG</button>
												<button id="save-jpeg">Save as JPEG</button> -->
												
												
                                                
                                                <hr>
                                                <div class="d-grid mt-4">
                                                    <button class="btn btn-primary waves-effect waves-light" id="apply" onclick="submit()" style="display: none;">당원가입</button>
                                                </div>
                                        </div>
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
	
	//pad
	//init
	var signaturePad;
	var sex ="남";
	//init
	
	function clearPad(event){
		event.preventDefault();
		var canvas = document.getElementById('signature-pad');
	    var ratio =  Math.max(window.devicePixelRatio || 1, 1);
	    canvas.width = canvas.offsetWidth * ratio;
	    canvas.height = canvas.offsetHeight * ratio;
	    canvas.getContext("2d").scale(ratio, ratio);
	}
	
	function submit(){
		var inputform = {};
		
 		
		inputform.groupKey = "0";
		//inputform.groupName = "${vo.name}";
		
		
		inputform.name = $("#auth_name").val();
		inputform.yyyymmdd = $("#auth_yyyymmdd").val();
		inputform.phone = $("#auth_phone").val();

		inputform.cityCode = $("#city").val();
		inputform.gunCode = $("#gun").val();
		inputform.dongCode = $("#dong").val();
		inputform.cityN = $("#city option:selected").text();
		inputform.gunN = $("#gun option:selected").text();
		inputform.dongN = $("#dong option:selected").text();
		inputform.detailAddress = $("#detailAddress").val();
		
		inputform.recommandName = $("#recommandName").val();
		inputform.recommandPhone = $("#recommandPhone").val();
		 
		inputform.representiveName = $("#representiveName").val();
		inputform.representiveCode = $("#representiveCode").val();

		inputform.signPad = signaturePad.toDataURL('image/png');
		inputform.sex = $("#sex").val();
		
		var validation = true;
		
		$.each( inputform, function( key, value ) {
			  //console.log("key : " + key  + ", value :" + value);
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
		
		
		
		if(signaturePad.isEmpty()){
			  Swal.fire({
	                title: "서명은 필수입니다.",
	                text: "",
	                icon: "error",
	                confirmButtonColor: "#ff3d60",
	                confirmButtonText: "확인"
	            })
			  
			  return false;
		}
		
		Swal.fire({
	        title: "가입 하시겠습니까?",
	        text: "",
	        icon: "warning",
	        showCancelButton: !0,
	        confirmButtonText: "예",
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
	
	$(document).ready(function() {
		
		//canvas
		var canvas = document.getElementById('signature-pad');

		// Adjust canvas coordinate space taking into account pixel ratio,
		// to make it look crisp on mobile devices.
		// This also causes canvas to be cleared.
		function resizeCanvas() {
		    // When zoomed out to less than 100%, for some very strange reason,
		    // some browsers report devicePixelRatio as less than 1
		    // and only part of the canvas is cleared then.
		    var ratio =  Math.max(window.devicePixelRatio || 1, 1);
		    canvas.width = canvas.offsetWidth * ratio;
		    canvas.height = canvas.offsetHeight * ratio;
		    canvas.getContext("2d").scale(ratio, ratio);
		}

		window.onresize = resizeCanvas;
		resizeCanvas();

		signaturePad = new SignaturePad(canvas, {
		  backgroundColor: 'rgb(255, 255, 255)' // necessary for saving image as JPEG; can be removed is only saving as PNG or SVG
		});

 		document.getElementById('clear').addEventListener('click', function () {
 			 signaturePad.clear();
		}); 
		
 	/* 	 document.getElementById('save-png').addEventListener('click', function () {
		  if (signaturePad.isEmpty()) {
		    return alert("Please provide a signature first.");
		  }
		  var data = signaturePad.toDataURL('image/png');
		}); */
		 
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
			sex = "남자";
		}else{
			$("#option2t")[0].className = "btn btn-primary active";
			$("#option1t")[0].className = "btn btn-primary";
			sex = "여자";
		}
		//console.log(selected);
		//event.removeClass("active");
		
	}
	


	function commit(inputform){
		var url = "/public/memberperson/commit";
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
			        text: "이미 가입된 핸드폰번호가 있습니다.",
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
	
	
 	function selft_authentic(){

		var openWin;
		
		window.name = "parentForm";
		
		window.open('', 'popupChk', 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
		document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
		
		document.form_chk.target = "popupChk";
		document.form_chk.submit();
	} 
	
	function sayHello(){
		alert('from children');
	}
	
	function callParentFun(result){
		
		/* result.sMobileNo = sMobileNo;
		result.sName = sName;
		result.sBirthDate = sBirthDate; */
		console.dir(result);
		if(document.getElementById("auth_name").value == result.sName &&
				document.getElementById("auth_phone").value == result.sMobileNo &&
				document.getElementById("auth_yyyymmdd").value == result.sBirthDate){

			$("#auth_name").prop("readonly",true);
			$("#auth_phone").prop("readonly",true);
			$("#auth_yyyymmdd").prop("readonly",true);
			$("#authBtn").prop("disabled",true);
			if(result.sGender == "1" || result.sGender == "3" ){
				$("#sex").val("남자");	
			}else{
				$("#sex").val("여자");
			}
			alert("본인인증 성공");
	    	$("#apply").css("display","block");
		}else{
			alert("본인인증 실패.\n 이름,생년월일,핸드폰번호를 확인해주세요.");
		}
    	

		
	}
	
	</script>

    </body>
</html>
