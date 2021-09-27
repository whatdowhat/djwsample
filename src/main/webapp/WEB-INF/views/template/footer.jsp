<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<footer class="footer">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6">
                <script>document.write(new Date().getFullYear())</script> © whatdo.
            </div>
            <div class="col-sm-6">
                <div class="text-sm-end d-none d-sm-block">
                    This is footer information. 
                </div>
            </div>
        </div>
    </div>
</footer>
                                                                    <!-- sample modal content -->
                                                            <div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="myModalLabel">쪽지 보내기</h5>
                                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <h5 class="font-size-16">받는 사람</h5>
                                                                            
	                                                                          <div class="" id="messageTarget">
										                                            <div class="alert alert-success alert-dismissible fade show" role="alert">
										                                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
										                                                <strong>Well done!</strong> You successfully read this important alert message.
										                                            </div>
										                                            <div class="alert alert-info alert-dismissible fade show" role="alert">
										                                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
										                                                <strong>Heads up!</strong> This alert needs your attention, but it's not super important.
										                                            </div>
										                                            <div class="alert alert-warning alert-dismissible fade show" role="alert">
										                                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
										                                                <strong>Warning!</strong> Better check yourself, you're not looking too good.
										                                            </div>
										                                            <div class="alert alert-danger alert-dismissible fade show mb-0" role="alert">
										                                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
										                                                <strong>Oh snap!</strong> Change a few things up and try submitting again.
										                                            </div>
	                                        								</div>
	                                        								 <h5 class="font-size-16" style="margin-bottom: 10px;">제목</h5>
	                                        								<div>
	                                        									<input type="text" id="messageTitle" class="form-control" style="margin-bottom: 10px;" >
	                                        								</div>
	                                        								
	                                        								 <h5 class="font-size-16">보낼 내용</h5>
	                                        								<div>
	                                        									<textarea id="messageTxt" class="form-control" rows="4"></textarea>
	                                        								</div>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-secondary waves-effect" data-bs-dismiss="modal">닫기</button>
                                                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="snedMessageCommit()">보내기</button>
                                                                        </div>
                                                                    </div><!-- /.modal-content -->
                                                                </div><!-- /.modal-dialog -->
                                                            </div><!-- /.modal -->
    
<script type="text/javascript">

var messageList =[];
var finalmessageList = [];
var sendType;

$(document).ready(function() {
	
	$('#myModal').on('hidden.bs.modal', function () {
			messageList = [];
		})
		
});


function selectAll(){
	
	if($("#selectBoxTargetAll").prop("checked")) { 
		$("input[name=selectBoxTarget]").prop("checked",true); // 전체선택 체크박스가 해제된 경우
 		
	} else { 
		$("input[name=selectBoxTarget]").prop("checked",false);
		messageList = [];
	}

 }
function snedMessage(type){
	sendType = type;
	
	$.each($("input[name=selectBoxTarget]"), function(index, value) {
		if(value.checked){
			messageList.push(value.value);
		}else{
			
		} 
	});
	
	
	$("#messageTarget").empty();
	var messagehtml = '';
	for(var i =0 ; i<messageList.length; i++){
		
		var name = messageList[i].split(',');
		messagehtml += '<div class="alert alert-success alert-dismissible fade show" role="alert">                            ';
        messagehtml += '	<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" onclick="deleteMessageTarget('+name[0]+')"></button>      ';
        messagehtml += '	<strong>이름:'+name[1]+',		핸드폰번호:'+name[2]+' </strong>                  ';
   		messagehtml += ' </div>                                                                                               ';
	}
	$("#messageTarget").html(messagehtml);
	if(type == 1){
		$("#myModalLabel")[0].innerHTML= "쪽지 보내기"
	}else{
		$("#myModalLabel")[0].innerHTML= "문자 보내기"
	}
	 $("#myModal").modal("show");
}

function deleteMessageTarget(seq){
	
	var toRemove = seq;
	messageList = messageList.filter(function(item) {
		item = item.split(',');
		return item[0] !== toRemove+'';
	    
	});
}
function snedMessageCommit(){
	if(messageList.length < 1){
		
		Swal.fire({
	        title: "실패",
	        text: "한명 이상이 선택되어야 합니다.",
	        icon: "error"
	    });
	}else if($("#messageTitle").val().replaceAll(" ","") == ""){
		Swal.fire({
	        title: "실패",
	        text: "제목은 필수입니다.",
	        icon: "error"
	    });		
	}
	else if($("#messageTxt").val().replaceAll(" ","") == ""){
		Swal.fire({
	        title: "실패",
	        text: "내용은 필수입니다.",
	        icon: "error"
	    });		
	}
	
	else{
		
		
		
		
		var url="";
		var list = [];
		for(var i =0 ; i<messageList.length; i++){
			var item ={};
			var name = messageList[i].split(',');
			
			item.seq = name[0];
			item.name = name[1];
			item.phone = name[2];
			item.messageTxt = $("#messageTxt").val();
			item.messageTitle = $("#messageTitle").val();
			
			
			list.push(item);
		}
		
		if(sendType == 1){
			url="/admin/send/message.do";
			ajaxSendData(url,list,function(result){
				console.dir(result);
				if(result.result ==true){
					Swal.fire({
			        title: "성공",
			        text: "전송완료되었습니다",
			        icon: "success"
			    });	 
			    $("#messageTxt").val('');
			    $("#messageTitle").val('');
					
				}else{
					Swal.fire({
				        title: "실패",
				        text: "관리자에게 문의하세요.",
				        icon: "error"
				    });
				}
				
				$("#myModal").modal("hide");
				
			});
			
			pageload();
			
			
		}else if(sendType == 2){
			url="/admin/sms/send.do";
			ajaxSendData(url,list,function(result){
				console.dir(result);
				if(result.result ==true){
					Swal.fire({
			        title: "성공",
			        text: "전송완료되었습니다",
			        icon: "success"
			    });	 
				    $("#messageTxt").val('');
				    $("#messageTitle").val('');
					
				}else{
					Swal.fire({
				        title: "실패",
				        text: "관리자에게 문의하세요.",
				        icon: "error"
				    });
				}
				
				$("#myModal").modal("hide");
				
			});
		}

		
	}
}


</script>