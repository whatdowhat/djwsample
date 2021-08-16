<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                                    <h4 class="mb-sm-0">개별 당원 등록</h4>

                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">당원관리</a></li>
                                            <li class="breadcrumb-item active">개별 당원 등록</li>
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
        
                                        <h4 class="card-title">가입 신청서</h4>
                                        <div class="mb-3 row">
                                            <label for="example-text-input" class="col-md-2 col-form-label">이름</label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="text"  id="example-text-input">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="example-search-input" class="col-md-2 col-form-label">생년월일(6자리)</label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="search"  id="example-search-input">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="example-email-input" class="col-md-2 col-form-label">연락처</label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="email"  id="example-email-input">
                                            </div>
                                        </div>

                                        
                                        <div class="mb-3 row">
                                            <label class="col-md-2 col-form-label">주소</label>
                                            <div class="col-md-4">
                                                <select class="form-select">
                                                    <option>Select</option>
                                                    <option>Large select</option>
                                                    <option>Small select</option>
                                                </select>
                                            </div>
                                            <div class="col-md-3">
                                                <select class="form-select">
                                                    <option>Select</option>
                                                    <option>Large select</option>
                                                    <option>Small select</option>
                                                </select>
                                            </div>
                                            <div class="col-md-3">
                                                <select class="form-select">
                                                    <option>Select</option>
                                                    <option>Large select</option>
                                                    <option>Small select</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="example-tel-input" class="col-md-2 col-form-label">상세 주소</label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="text" value="" id="example-tel-input">
                                            </div>
                                        </div>
                                        
                                        <div class="mb-3 row">
                                            <label for="example-tel-input" class="col-md-2 col-form-label">추천인 이름(선택)</label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="text" id="example-tel-input">
                                            </div>
                                        </div>
                                        <div class="mb-3 row">
                                            <label for="example-password-input" class="col-md-2 col-form-label">추천인 연락처(핸드폰 번호 11자리)</label>
                                            <div class="col-md-10">
                                                <input class="form-control" type="tel" id="example-password-input">
                                            </div>
                                        </div>
                                        
                                        <div class="mb-3 row">
                                        
	                                        <label for="example-password-input" class="col-md-2 col-form-label">성별</label>
	                                        <div class="col-md-10">
												<div class="form-check form-check-inline">
	                                            	<input class="form-check-input" type="radio" name="inlineRadios" id="inlineRadios1" value="option1" checked>
		                                            <label class="form-check-label" for="inlineRadios1">남자</label>
												</div>
	                                            <div class="form-check form-check-inline">
	                                                <input class="form-check-input" type="radio" name="inlineRadios" id="inlineRadios2" value="option2">
	                                                <label class="form-check-label" for="inlineRadios2">여자</label>
	                                            </div>
	                                        </div>
                                        </div>
										<div class="text-center mt-4">
                                            <button type="button" class="btn btn-primary waves-effect waves-light">등록</button>
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


</script>
    