<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.RequestDispatcher" %>

<html>
<header>
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
        <script src="/resources/assets/js/siginPad.js"></script>
        <!-- form mask init -->
</header>
<body>
                                                <div class="col-12 mb-4">
                                                    <label class="form-label" for="userpassword">서명</label>
														<div class="wrapper" style="border-color: black;max-width: 100% ">
														  <%-- <canvas id="signature-pad" class="signature-pad" width=400 height=200></canvas> --%>
														  <canvas id="signature-pad" class="signature-pad" style="max-width: 100%;border: 1px solid #0bb197"></canvas>
														</div>                                                    
                                                </div>

<!-- <button id="save-png">Save as PNG</button>
<button id="save-jpeg">Save as JPEG</button> -->

<button id="clear" onclick="clearPad(this)">Clear</button>

<script type="text/javascript">
	
	function clearPad(event){
		var canvas = document.getElementById('signature-pad');
	    var ratio =  Math.max(window.devicePixelRatio || 1, 1);
	    canvas.width = canvas.offsetWidth * ratio;
	    canvas.height = canvas.offsetHeight * ratio;
	    canvas.getContext("2d").scale(ratio, ratio);
	}
	function test(){
		alert("test");
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

		var signaturePad = new SignaturePad(canvas, {
		  backgroundColor: 'rgb(255, 255, 255)' // necessary for saving image as JPEG; can be removed is only saving as PNG or SVG
		});

/* 		document.getElementById('save-png').addEventListener('click', function () {
		  if (signaturePad.isEmpty()) {
		    return alert("Please provide a signature first.");
		  }
		  
		  var data = signaturePad.toDataURL('image/png');
		  console.log(data);
		  window.open(data);
		});
	

		document.getElementById('draw').addEventListener('click', function () {
		  var ctx = canvas.getContext('2d');
		  console.log(ctx.globalCompositeOperation);
		  ctx.globalCompositeOperation = 'source-over'; // default value
		}); */

/* 		document.getElementById('erase').addEventListener('click', function () {
		  var ctx = canvas.getContext('2d');
		  ctx.globalCompositeOperation = 'destination-out';
		}); */

		
/* 		document.getElementById('erase').addEventListener('click', function () {
			var data = signaturePad.toData();
		  if (data) {
		    data.pop(); // remove the last dot or line
		    signaturePad.fromData(data);
		  }
		});
		 */
	});
	
	
	
	</script>

</body>
</html>



