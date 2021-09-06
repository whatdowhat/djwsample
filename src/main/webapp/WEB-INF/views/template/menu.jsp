<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
        
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
                <!-- DataTables -->
        <link href="/resources/assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <link href="/resources/assets/libs/datatables.net-buttons-bs4/css/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        
        
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
        

        
        
<!-- ========== Left Sidebar Start ========== -->
<div class="vertical-menu">

    <div data-simplebar class="h-100">



        <!--- Sidemenu -->
        <div id="sidebar-menu">
            <!-- Left Menu Start -->
            <ul class="metismenu list-unstyled" id="side-menu">
                <li class="menu-title">Menu</li>
                <li>
                <%-- <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                	<a href="/admin/loginAfter.do" class="waves-effect">
                </sec:authorize>
				<sec:authorize access="hasAuthority('ROLE_USER')">
					<a href="/user/loginAfter.do" class="waves-effect">
                </sec:authorize> --%>
                	<a href="/admin/loginAfter.do" class="waves-effect">
                        <i class="mdi mdi-home-variant-outline"></i>
                        <span>조직현황</span>
                    </a>
                </li>
<sec:authorize access="hasAuthority('ROLE_ADMIN')">
				<li>
                    <a href="/admin/member/chart/member.do" class="waves-effect">
                        <i class="mdi mdi-calendar-outline"></i>
                        <span>당원추이현황</span>
                    </a>
                </li>
</sec:authorize>

<%-- <sec:authorize access="hasAuthority('ROLE_ADMIN')"> --%>
				<li>
                    <a href="/admin/district/chart/member.do" class="waves-effect">
                        <i class="mdi mdi-calendar-outline"></i>
                        <span>선거구별현황</span>
                    </a>
                </li>
<%-- </sec:authorize> --%>
                
<sec:authorize access="hasAuthority('ROLE_ADMIN')">
				<li>
                    <a href="javascript: void(0);" class="has-arrow waves-effect">
                        <i class="mdi mdi-gradient"></i>
                        <span>당원관리</span>
                    </a>
                    <ul class="sub-menu" aria-expanded="false">
                        <li><a href="/admin/member/list.do">당원정보</a></li>
                        <li><a href="/admin/member/page.do">당원등록</a></li>
                        <li><a href="/admin/member/excelPage.do">엑셀등록</a></li>
                        
                    </ul>
                </li>
</sec:authorize>
<sec:authorize access="hasAuthority('ROLE_ADMIN')">
                <li>
                    <a href="javascript: void(0);" class="has-arrow waves-effect">
                        <i class="mdi mdi-page-layout-header"></i>
                        <span>단체관리</span>
                    </a>
                    <ul class="sub-menu" aria-expanded="false">
                        
                        <li><a href="/admin/group/list.do">단체목록</a></li>
                        <li><a href="/admin/group/page.do">단체생성</a></li>
                        <!-- <li><a href="layouts-hori-boxed-width.html">Boxed width</a></li> -->
                    </ul>
                </li>
</sec:authorize>
 				<li>
                    <a href="javascript: void(0);" class="has-arrow waves-effect">
                        <i class="mdi mdi-account-circle-outline"></i>
                        <span>메세지관리</span>
                    </a>
                    <ul class="sub-menu" aria-expanded="false">
                        <li><a href="/admin/message/innerMessage.do">쪽지</a></li>
                        <li><a href="#">문자(SMS)</a></li>
                        <li><a href="/admin/notice/innernotice.do">공지사항</a></li>
                    </ul>
                </li>


				<li>
                    <a href="/admin/myprofile/page.do" class="waves-effect">
                        <i class="mdi mdi-calendar-outline"></i>
                        <span>내정보</span>
                    </a>
                </li>
                <!-- <li class="menu-title">Pages</li>

               

                <li>
                    <a href="javascript: void(0);" class="has-arrow waves-effect">
                        <i class="mdi mdi-format-page-break"></i>
                        <span>Utility</span>
                    </a>
                    <ul class="sub-menu" aria-expanded="false">
                        <li><a href="pages-starter.html">Starter Page</a></li>
                        <li><a href="pages-maintenance.html">Maintenance</a></li>
                        <li><a href="pages-comingsoon.html">Coming Soon</a></li>
                        <li><a href="pages-timeline.html">Timeline</a></li>
                        <li><a href="pages-faqs.html">FAQs</a></li>
                        <li><a href="pages-pricing.html">Pricing</a></li>
                        <li><a href="pages-404.html">Error 404</a></li>
                        <li><a href="pages-500.html">Error 500</a></li>
                    </ul>
                </li>

                <li class="menu-title">Components</li>

                <li>
                    <a href="javascript: void(0);" class="has-arrow waves-effect">
                        <i class="mdi mdi-briefcase-variant-outline"></i>
                        <span>UI Elements</span>
                    </a>
                    <ul class="sub-menu" aria-expanded="false">
                        <li><a href="ui-alerts.html">Alerts</a></li>
                        <li><a href="ui-badge.html">Badge</a></li>
                        <li><a href="ui-buttons.html">Buttons</a></li>
                        <li><a href="ui-cards.html">Cards</a></li>
                        <li><a href="ui-carousel.html">Carousel</a></li>
                        <li><a href="ui-dropdowns.html">Dropdowns</a></li>
                        <li><a href="ui-grid.html">Grid</a></li>
                        <li><a href="ui-images.html">Images</a></li>
                        <li><a href="ui-lightbox.html">Lightbox</a></li>
                        <li><a href="ui-modals.html">Modals</a></li>
                        <li><a href="ui-offcanvas.html">Offcavas</a></li>
                        <li><a href="ui-rangeslider.html">Range Slider</a></li>
                        <li><a href="ui-session-timeout.html">Session Timeout</a></li>
                        <li><a href="ui-pagination.html">Pagination</a></li>
                        <li><a href="ui-progressbars.html">Progress Bars</a></li>
                        <li><a href="ui-sweet-alert.html">Sweetalert 2</a></li>
                        <li><a href="ui-tabs-accordions.html">Tabs & Accordions</a></li>
                        <li><a href="ui-typography.html">Typography</a></li>
                        <li><a href="ui-video.html">Video</a></li>
                        <li><a href="ui-popover-tooltips.html">Popovers &amp; Tooltips</a></li>
                        <li><a href="ui-rating.html">Rating</a></li>
                    </ul>
                </li>

                <li>
                    <a href="javascript: void(0);" class="waves-effect">
                        <i class="ri-eraser-fill"></i>
                        <span class="badge rounded-pill bg-danger float-end">8</span>
                        <span>Forms</span>
                    </a>
                    <ul class="sub-menu" aria-expanded="false">
                        <li><a href="form-elements.html">Form Elements</a></li>
                        <li><a href="form-validation.html">Form Validation</a></li>
                        <li><a href="form-advanced.html">Form Advanced Plugins</a></li>
                        <li><a href="form-editors.html">Form Editors</a></li>
                        <li><a href="form-uploads.html">Form File Upload</a></li>
                        <li><a href="form-xeditable.html">Form X-editable</a></li>
                        <li><a href="form-wizard.html">Form Wizard</a></li>
                        <li><a href="form-mask.html">Form Mask</a></li>
                    </ul>
                </li>

                <li>
                    <a href="javascript: void(0);" class="has-arrow waves-effect">
                        <i class="ri-table-2"></i>
                        <span>Tables</span>
                    </a>
                    <ul class="sub-menu" aria-expanded="false">
                        <li><a href="tables-basic.html">Basic Tables</a></li>
                        <li><a href="tables-datatable.html">Data Tables</a></li>
                        <li><a href="tables-responsive.html">Responsive Table</a></li>
                        <li><a href="tables-editable.html">Editable Table</a></li>
                    </ul>
                </li>

                <li>
                    <a href="javascript: void(0);" class="has-arrow waves-effect">
                        <i class="ri-bar-chart-line"></i>
                        <span>Charts</span>
                    </a>
                    <ul class="sub-menu" aria-expanded="false">
                        <li><a href="charts-apex.html">Apex Charts</a></li>
                        <li><a href="charts-chartjs.html">Chartjs Charts</a></li>
                        <li><a href="charts-flot.html">Flot Charts</a></li>
                        <li><a href="charts-knob.html">Jquery Knob Charts</a></li>
                        <li><a href="charts-sparkline.html">Sparkline Charts</a></li>
                    </ul>
                </li>

                <li>
                    <a href="javascript: void(0);" class="has-arrow waves-effect">
                        <i class="ri-brush-line"></i>
                        <span>Icons</span>
                    </a>
                    <ul class="sub-menu" aria-expanded="false">
                        <li><a href="icons-remix.html">Remix Icons</a></li>
                        <li><a href="icons-materialdesign.html">Material Design</a></li>
                        <li><a href="icons-dripicons.html">Dripicons</a></li>
                        <li><a href="icons-fontawesome.html">Font Awesome </a></li>
                    </ul>
                </li>

                <li>
                    <a href="javascript: void(0);" class="has-arrow waves-effect">
                        <i class="ri-map-pin-line"></i>
                        <span>Maps</span>
                    </a>
                    <ul class="sub-menu" aria-expanded="false">
                        <li><a href="maps-google.html">Google Maps</a></li>
                        <li><a href="maps-vector.html">Vector Maps</a></li>
                    </ul>
                </li>

                <li>
                    <a href="javascript: void(0);" class="has-arrow waves-effect">
                        <i class="ri-share-line"></i>
                        <span>Multi Level</span>
                    </a>
                    <ul class="sub-menu" aria-expanded="true">
                        <li><a href="javascript: void(0);">Level 1.1</a></li>
                        <li><a href="javascript: void(0);" class="has-arrow">Level 1.2</a>
                            <ul class="sub-menu" aria-expanded="true">
                                <li><a href="javascript: void(0);">Level 2.1</a></li>
                                <li><a href="javascript: void(0);">Level 2.2</a></li>
                            </ul>
                        </li>
                    </ul>
                </li> -->
            </ul>
        </div>
        <!-- Sidebar -->
    </div>
</div>
<!-- Left Sidebar End -->

