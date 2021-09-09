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
                                    <h4 class="mb-sm-0">단체현황</h4>
									
                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">단체관리</a></li>
                                            <li class="breadcrumb-item active">단체목록</li>
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
                                        <table id="datatable"    class="table table-bordered dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">
                                            <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>단체명</th>
                                                <th>단체장</th>
                                                <th>단체연락처</th>
                                                <th>단체장연락처</th>
                                                <th>단체회원수</th>
                                                <th>단체주소</th>
                                                <th>초대url</th>
                                                
                                                <!-- <th>당원가입수</th>
                                                <th>수정삭제</th>  -->
                                            </tr>
                                            </thead>
                                            <%-- <tbody>
                                           
		                                        <c:if test="${page.getContent().size()>=1 }">
														<c:forEach var="item" items="${page.getContent()}" varStatus="status"> 
	                                                    <tr>
	                                                    	<td>${status.count }</td>
	                                                        <td>${item.name }</td>
	                                                        <td>${item.representiveName }</td>
	                                                        <td>${item.phone }</td>
	                                                        <td>${item.representiveCode }</td>
	                                                        <td>${item.totalCount }</td>
	                                                        <td>${item.detailAddress }</td>
	                                                        <td>-</td>
	                                                        <td>-</td>
	                                                        
	                                                    </tr>
														</c:forEach>
			                                        
		                                        </c:if>
												<c:if test="${page.getContent().size()==0 }">
													<tr >
														<td colspan="9">데이터가 없습니다.</td>	                                                       
													</tr>
												</c:if>
                                           
                                            </tbody> --%>
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
        	{"data" : "phone",render:function(a,b,c,d){
        		return d.row + d.settings._iDisplayStart +1;
        	}},
        	{"data" : "name",render:function(a,b,c,d){
        		
        		var str = "<a href='/admin/group/memberlist.do?groupKey="+c.groupKey+"'>";
        		str+=c.name;
        		return str;
        	}},
        	/* {"data" : "name"}, */
        	
        	
        	{"data" : "representiveName"},
        	{"data" : "phone"},
        	{"data" : "representiveCode"},
        	{"data" : "totalCount"},
        	{"data" : "detailAddress"},
        	{"data" : "phone",render:function(a,b,c,d){
        		return "/public?groupKey="+c.groupKey;
        	}},
        	/* {"data" : "phone",render:$.fn.dataTable.render.number(',','.',0,'$')}, */

       	],
        "ajax" : {
        	type : "POST",
            url : "/admin/group/listtable.do",
            "data" : {
                "columnsize" : 8,
            }
        },
    });



});

	function make() {
		var inputform = {};

		var group = $("#groupKey").val();
		var groups = [];
		if (group == "" || group == undefined) {
			groups[0] = "";
			groups[1] = "";
		} else {
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

		if (inputform.groupKey == "") {
			Swal.fire({
				title : "단체를 먼저 선택해주세요",
				text : "",
				icon : "error",
				confirmButtonColor : "#ff3d60",
				confirmButtonText : "확인"
			})
			return false;
		}

		var validation = true;

		$.each(inputform, function(key, value) {
			console.log("key : " + key + ", value :" + value);
			if (value == "") {
				validation = false;
				console.log("empty");
				Swal.fire({
					title : "모든 데이터를 입력해주세요",
					text : "",
					icon : "error",
					confirmButtonColor : "#ff3d60",
					confirmButtonText : "확인"
				})

				return false;
			}
		});

		if (!validation)
			return false;

		Swal.fire({
			title : "회원을 등록하시겠습니까?",
			text : "",
			icon : "warning",
			showCancelButton : !0,
			confirmButtonText : "생성",
			cancelButtonText : "취소",
			confirmButtonClass : "btn btn-success mt-2",
			cancelButtonClass : "btn btn-danger ms-2 mt-2",
			buttonsStyling : !1
		}).then(function(t) {

			if (t.isConfirmed) {

				commit(inputform);

			} else {
				//nothing
			}

		});

	}

	function commit(inputform) {
		var url = "/admin/member/commit";
		ajax(url, inputform, function(result) {
			console.log("ajax");
			console.dir(result);

			if (!result.already) {
				Swal.fire({
					title : "성공",
					text : "",
					icon : "success"
				});
			} else {
				Swal.fire({
					title : "실패",
					text : "이미 생성된 단체코드 입니다.",
					icon : "error"
				});
			}

		})

	}

	function clickCity(param) {
		var inputform = {};
		inputform.cityCode = param.value;
		//console.log(cityCode);
		var url = '/admin/member/getcity'

		ajax(url, inputform, function(result) {
			console.log("ajax");
			console.dir(result);
			$("#gun").empty();
			for (var i = 0; i < result.gus.length; i++) {
				$("#gun").append(
						"<option value="+result.gus[i].gunCode+">"
								+ result.gus[i].gunN + "</option>");
			}
			$("#dong").empty();
			for (var i = 0; i < result.dongs.length; i++) {
				$("#dong").append(
						"<option value="+result.dongs[i].dongCode+">"
								+ result.dongs[i].dongN + "</option>");
			}
		})
	}

	function clickGun(param) {
		var inputform = {};
		inputform.cityCode = $("#city").val();
		inputform.gunCode = param.value;
		//console.log(cityCode);
		var url = '/admin/member/getgun'
		ajax(url, inputform, function(result) {
			console.log("ajax");
			console.dir(result);

			$("#dong").empty();
			for (var i = 0; i < result.dongs.length; i++) {
				$("#dong").append(
						"<option value="+result.dongs[i].dongCode+">"
								+ result.dongs[i].dongN + "</option>");
			}
		})

	}
</script>
    