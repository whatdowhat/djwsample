<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
<style>

.city{

	margin: 10px;
	margin-top: 10px;
}

.gu{

	margin: 10px;
	margin-top: 10px;
}

.dong{
	
	margin: 10px;
	margin-top: 10px;
}
</style>
    <body data-topbar="colored">

        <!-- Begin page -->
        <div id="layout-wrapper">
        	<jsp:include page="../template/menu.jsp"></jsp:include>
        	<jsp:include page="../template/header.jsp"></jsp:include>
        	
        	
        	<!--  page content -->
        	
            <div class="main-content">

                <div class="page-content" id="target">
                    <div class="container-fluid">

	            <!-- ============================================================== -->
	            <!-- Start right Content here -->
	            <!-- ============================================================== -->
	            
                        <div >
                        
                        
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">

                                        <div class="row justify-content-center mt-3">
                                            <div class="col-lg-8">
                                                <div class="row">
                                                    <div class="col-lg-3" style="font-size: 20px;">
                                                        <div class="border p-3 text-center rounded mb-4">
                                                            <a href="#">
                                                                <div class="my-3">
                                                                    <!-- <i class="dripicons-question h2 text-primary"></i> -->
                                                                    <img alt="" src="/resources/assets/images/four.png" width="150px;" height="150px;">
                                                                </div>
                                                                <h5 class="font-size-15 mb-3">????????????</h5>
                                                                 <br>
                                                                <fmt:formatNumber value="${total }" pattern="#,###" />
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3" style="font-size: 20px;">
                                                        <div class="border p-3 text-center rounded mb-4">
                                                            <a href="#">
                                                                <div class="my-3">
                                                                    <!-- <i class="dripicons-tags h2 text-primary"></i> -->
                                                                    <img alt="" src="/resources/assets/images/two.png" width="150px;" height="150px;">
                                                                </div>
                                                                <h5 class="font-size-15 mb-3">????????????</h5>
                                                                 <br>
                                                                <fmt:formatNumber value="${dangwonCount00 }" pattern="#,###" />
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-3" style="font-size: 20px;">
                                                        <div class="border p-3 text-center rounded mb-4">
                                                            <a href="#">
                                                                <div class="my-3">
                                                                    <!-- <i class="dripicons-help h2 text-primary"></i> -->
                                                                    <img alt="" src="/resources/assets/images/one.jpg" width="150px;" height="150px;">
                                                                </div>
                                                                <h5 class="font-size-15 mb-3">????????????</h5>
                                                                 <br>
                                                               <fmt:formatNumber value="${dangwonCount01 }" pattern="#,###" />
                                                            </a>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <!-- end row -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        
  						<div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">
                                            
                                                <div class="row justify-content-center">
													<ul class="nav nav-pills nav-justified" role="tablist">
			                                           
														<c:forEach var="item" items="${cities}" varStatus="status"> 
														
																	<button type="button" class="btn btn-danger waves-effect waves-light city" onclick="clickCity(${item.cityCode})">${item.cityN}(${item.cityCount})</button>
				 												
														</c:forEach>
			                                        </ul>
                                                </div>
                                                <!-- end row -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="mb-3 row">
				             <div class="col-md-4">
				             	<label for="example-date-input" class="col-md-2 col-form-label">?????? ??????</label>
				                 <input class="form-control" type="date" value="${startDate}" id="startDate" >
				             </div>
				             <div class="col-md-4">
				             	<label for="example-date-input" class="col-md-2 col-form-label">?????? ??????</label>
				                 <input class="form-control" type="date" value="${endDate}" id="endDate" >
				             </div>
	                    </div>
						
	            		<div>

							<div class="row">
	                            <div class="col-12">
	                                <div class="page-title-box d-sm-flex align-items-center justify-content-between">
	                                    <h4 class="mb-sm-0">??????????????????</h4>
	
										<%-- ${chartDate }
										${total }
										${dangwonCount00 }
										${dangwonCount01 } --%>
	
	                                    <div class="page-title-right">
	                                        <ol class="breadcrumb m-0">
	                                            <li class="breadcrumb-item"><a href="javascript: void(0);">????????????</a></li>
	                                            <li class="breadcrumb-item active">????????????</li>
	                                        </ol>
	                                    </div>
	
	                                </div>
	                            </div>
	                        </div>
	                        <!-- end page title -->
	                        <div class="row">
	                            <div class="col-lg-12">
	                                <div class="card">
	                                    <div class="card-body">
	                                        <h4 class="card-title mb-4">?????? ?????? ??????</h4>
	
											<!-- <div id="chart" class="apex-charts" dir="ltr"></div> -->
	                                        <div id="line_chart_datalabel" class="apex-charts" dir="ltr"></div>                              
	                                    </div>
	                                </div><!--end card-->
	                            </div>
	
	                        </div>
	                        <!-- end row -->
	                        <div class="row">
	                            <div class="col-lg-12">
	                                <div class="card">
	                                    <div class="card-body">
	                                        <h4 class="card-title mb-4">???????????? ?????? ??????</h4>
	
											<!-- <div id="chart" class="apex-charts" dir="ltr"></div> -->
	                                        <div id="line_chart_datalabel2" class="apex-charts" dir="ltr"></div>                              
	                                    </div>
	                                </div><!--end card-->
	                            </div>
	
	                        </div>
	                        <!-- end row -->
	            		
	            		
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


function clickCity(cityCode){
	
	var inputform = {};
	inputform.cityCode = cityCode;
	inputform.startDate = $("#startDate").val();
	inputform.endDate = $("#endDate").val();
	var url = '/admin/member/chart/member/ajax.do'
	
	ajaxPage(url,inputform,function(result){
		console.log("ajaxpage");
		console.dir(result);
		$("#target").html(result);
		makeChart();
//		makeChart();
	})

}


window.onload = function() {

	
	var chartData = ${chartDate };
	var chartData2 = ${chartDate2 };
	
	
	var colum00 ={};
	var colum01 ={};
	var colum02 ={};
	
	colum00.name="??????";
	colum01.name="??????";
	colum02.name="?????????";
	
	colum00.data=[];
	colum01.data=[];
	colum02.data=[];
	
	
	var dataColums =[];
	for(var i=0; i<chartData.length; i++){
		dataColums.push(chartData[i].selectedDate);
		
		/* Integer selectedDateCount;
		Integer selectedDateCountC;
		Integer selectedDateCountN; */
		
		colum00.data.push(Math.floor(chartData[i].selectedDateCount));
		colum01.data.push(Math.floor(chartData[i].selectedDateCountC));
		colum02.data.push(Math.floor(chartData[i].selectedDateCountN));
		
	}
  
	var options, chart, chart2;
options = {
    chart: {
        height: 380,
        type: "line",
        zoom: {
            enabled: false
          },
          //download
/*         toolbar: {
            show: !1
        } */
    },
    /* colors: ["#0db4d6", "#f1b44c","#f1b44d"], */
    dataLabels: {
    	 enabled: false
    },
    stroke: {
        width: [3,3,3],
        curve: "straight"
    },
    series: [
    	colum00,
    
    	colum01,
    
    	colum02,
    ],
     title: {
        text: "??????",
        align: "left"
    }, 
    grid: {
        row: {
            colors: ["transparent", "transparent","transparent"],
            opacity: .1
        },
        borderColor: "#f1f1f1"
    },
    markers: {
        style: "inverted",
        size: 6
    },
    xaxis: {
        categories: dataColums,
        title: {
            text: "??????"
        }
    },
    yaxis: {
    	 type: 'numeric',
        title: {
            text: "??????"
        },
        decimalsInFloat: 0,
        floating: false,
        /* min: 5,
        max: 40 */
    },
    legend: {
        position: "top",
        horizontalAlign: "right",
        offsetY: -5,
        offsetX: -15
    },
    responsive: [{
        breakpoint: 600,
        options: {
            chart: {
                toolbar: {
                    show: !1
                }
            },
            legend: {
                show: !1
            }
        }
    }]
};


	chart = new ApexCharts(document.querySelector("#line_chart_datalabel"), options).render();
	
	
	var colum00 ={};
	colum00.name="??????";
	colum00.data=[];
	
	
	var dataColums =["20???","30???","40???","50???","60???",];
		
	colum00.data.push(Math.floor(chartData2[0].age_20));
	colum00.data.push(Math.floor(chartData2[0].age_30));
	colum00.data.push(Math.floor(chartData2[0].age_40));
	colum00.data.push(Math.floor(chartData2[0].age_50));
	colum00.data.push(Math.floor(chartData2[0].age_60));



	
  
	var options, chart, chart2;
options = {
    chart: {
        height: 380,
        type: "line",
        zoom: {
            enabled: false
          },
          //download
/*         toolbar: {
            show: !1
        } */
    },
    /* colors: ["#0db4d6", "#f1b44c","#f1b44d"], */
    dataLabels: {
    	 enabled: false
    },
    stroke: {
        width: [3],
        curve: "straight"
    },
    series: [
    	colum00,
    ],
     title: {
        text: "??????",
        align: "left"
    }, 
    grid: {
        row: {
            colors: ["transparent"],
            opacity: .2
        },
        borderColor: "#f1f1f1"
    },
    markers: {
        style: "inverted",
        size: 6
    },
    xaxis: {
        categories: dataColums,
        title: {
            text: "?????????"
        }
    },
    yaxis: {
    	
    	title: {
            text: "??????"
        },
        decimalsInFloat: 2,
        floating: false,
        
        /* min: 5,
        max: 40 */
    },
    legend: {
        position: "top",
        horizontalAlign: "right",
        /* floating: !0, */ 
        offsetY: -5,
        offsetX: -15
    },
    responsive: [{
        breakpoint: 600,
        options: {
            chart: {
                toolbar: {
                    show: !1
                }
            },
            legend: {
                show: !1
            }
        }
    }]
};
	
	chart2 = new ApexCharts(document.querySelector("#line_chart_datalabel2"), options).render();
	
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

	
	inputform.groupJikham = $("#groupJikham").val(); //????????????
	
	inputform.mrank = $("#mrank").val();
	inputform.level = $("#level option:selected").val();
	inputform.dangwon = $("#dangwon option:selected").val();
	inputform.church = $("#church").val();
	inputform.churchRank = $("#churchRank option:selected").val();
	inputform.adminAuth = $("#adminAuth option:selected").val();
	
	
	
	if(inputform.groupKey == ""){
		  Swal.fire({
              title: "????????? ?????? ??????????????????",
              text: "",
              icon: "error",
              confirmButtonColor: "#ff3d60",
              confirmButtonText: "??????"
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
	                title: "?????? ???????????? ??????????????????",
	                text: "",
	                icon: "error",
	                confirmButtonColor: "#ff3d60",
	                confirmButtonText: "??????"
	            })
			  
			  return false;
		  }
	}); 

 	if(!validation) return false;
	
	
	Swal.fire({
        title: "????????? ?????????????????????????",
        text: "",
        icon: "warning",
        showCancelButton: !0,
        confirmButtonText: "??????",
        cancelButtonText: "??????",
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
		        title: "??????",
		        text: "",
		        icon: "success"
		    });	
		}else{
			Swal.fire({
		        title: "??????",
		        text: "?????? ????????? ???????????? ?????????.",
		        icon: "error"
		    });
		}
		
		
	})
	
	  
	

}


</script>
    