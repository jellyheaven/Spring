<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"
    %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Tables</title>
        
    <%@include file="../includes/header.jsp" %>
    
	<style>
		.uploadResult {
		  width:100%;
		  background-color: gray;
		}
		.uploadResult ul{
		  display:flex;
		  flex-flow: row;
		  justify-content: center;
		  align-items: center;
		}
		.uploadResult ul li {
		  list-style: none;
		  padding: 10px;
		  align-content: center;
		  text-align: center;
		}
		.uploadResult ul li img{
		  width: 100px;
		}
		.uploadResult ul li span {
		  color:white;
		}
		.bigPictureWrapper {
		  position: absolute;
		  display: none;
		  justify-content: center;
		  align-items: center;
		  top:0%;
		  width:100%;
		  height:100%;
		  background-color: gray; 
		  z-index: 100;
		  background:rgba(255,255,255,0.5);
		}
		.bigPicture {
		  position: relative;
		  display:flex;
		  justify-content: center;
		  align-items: center;
		}
		
		.bigPicture img {
		  width:600px;
		}
	</style>    
</head>
<body id="page-top">
	<!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <%@include file="../includes/sidebar.jsp" %>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <%@include file="../includes/topbar.jsp" %>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Board Read Page</h1>
                    <p class="mb-4"></p>
                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">                            
                            <!-- <h6 class="m-0 font-weight-bold text-primary">새글 등록</h6> -->
                        </div>
                        <div class="card-body">
                       		<div class="form-group">
					      		<label for="bno" style="font-weight:bold">글번호</label>
					      		<input type="text" class="form-control" id="bno" name="bno" readonly="readonly" value="<c:out value="${board.bno}" />">
					    	</div>
                       		<div class="form-group">
					      		<label for="title" style="font-weight:bold">제목</label>
					      		<input type="text" class="form-control" id="title" name="title" readonly="readonly" value="<c:out value="${board.title}" />">
					    	</div>
					    	<div class="form-group">
					      		<label for="content" style="font-weight:bold">내용</label>
					      		<textarea class="form-control" rows="3" id="content" name='content' readonly="readonly" ><c:out value="${board.content}" /></textarea>
					    	</div>
                       		<div class="form-group">
					      		<label for="writer" style="font-weight:bold">작성자</label>
					      		<input type="text" class="form-control" id="writer" name="writer" readonly="readonly" value="<c:out value="${board.writer}" />">
					    	</div>
					    	<div class="float-right">
					    		<sec:authentication property="principal" var="pinfo" />
					    		<sec:authorize access="isAuthenticated()">
					    			<c:if test="${pinfo.member.userid eq board.writer }">
					    				<button data-oper='modify' class="btn btn-primary">수정</button>
					    			</c:if>
					    		</sec:authorize>
								<button data-oper='list' class="btn btn-info">목록</button> 
					    	</div>
					    	
					    	<form id='operForm' action="/boad/modify" method="get">
  								<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
  								<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
  								<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
  								<input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
								<input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
							</form>
                        </div>
                    </div>
                    
                    <!-- 첨부파일 보기 -->
                    <div class="bigPictureWrapper">
						<div class="bigPicture"></div>	
					</div>
                    <p class="mb-2"></p>
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<i class="fas fa-file-alt"></i> <span class="m-0 font-weight-bold text-primary">첨부파일</span>
                        </div>
                        <div class="card-body">
                       		<div class="uploadResult">
								<ul></ul>
							</div>
                        </div>
					</div>
                    <!-- 첨부파일 보기 end -->
                    
					<!-- 댓글 -->
					<p class="mb-2"></p>
					<div class="card shadow mb-4">
                        <div class="card-header py-3">                            
                            <i class="fa fa-comments fa-fw"></i> <span class="m-0 font-weight-bold text-primary">댓글</span>
                            <sec:authorize access="isAuthenticated()">
                            	<button id='addReplyBtn' type="button" class="btn btn-primary btn-sm float-right">댓글 등록</button>
                            </sec:authorize>
                        </div>
                        <div class="card-body">
                       		<ul class="chat list-group" style="list-style: none;">
                       			<!-- 댓글 목록 -->
                       		</ul>
                       		
                        </div>
                        <div class="card-footer m-0">
                          	<!-- 페이지 목록 -->
                        </div>
                    </div>
                    <!-- 댓글 종료 -->
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <%@include file="../includes/footer.jsp" %>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->
	
    <!-- Scroll to Top Button , Logout Modal, Script-->
    <%@include file="../includes/footer_src.jsp" %>
    <!-- End Scroll to Top Button , Logout Modal, Script-->
    
    <!-- Modal start -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
				</div>
				<div class="modal-body">
					<div class="form-group">
		            	<label>Reply</label> 
		                <input class="form-control" name='reply' value='New Reply!!!!'>
		            </div>      
	              	<div class="form-group">
		                <label>Replyer</label> 
		                <input class="form-control" name='replyer' value='replyer' readonly="readonly">
	              	</div>
	              	<div class="form-group">
		                <label>Reply Date</label> 
		                <input class="form-control" name='replyDate' value='2018-01-01 13:13'>
	              	</div>
				</div>
				<div class="modal-footer">
					<button id='modalModBtn' type="button" class="btn btn-warning btn-sm">수정</button>
        			<button id='modalRemoveBtn' type="button" class="btn btn-danger btn-sm">삭제</button>
        			<button id='modalRegisterBtn' type="button" class="btn btn-primary btn-sm">등록</button>
					<button id='modalCloseBtn' type="button" class="btn btn-info btn-sm" data-dismiss="modal">닫기</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
    <!-- Modal end -->
    
    <script type="text/javascript" src="/resources/js/reply.js?version=20211224"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			
			var formObj = $("#operForm");
			
			$('button').on("click", function(e){
				e.preventDefault();
				//a 태그나 submit 태그는 누르게 되면 href 를 통해 이동하거나 , 창이 새로고침하여 실행됩니다.
				//preventDefault 를 통해 이러한 동작을 막아줄 수 있습니다.

				var operation = $(this).data("oper");
				
				console.log(operation);
				
				if(operation === 'modify'){
					formObj.attr("action", "/board/modify");
					formObj.submit();
				}else if(operation === 'list'){
					formObj.find("#bno").remove();
					formObj.attr("action", "/board/list");
					formObj.submit();
				}	
				
			});
			
			
			//댓글 start			
			var bnoValue = '<c:out value="${board.bno}"/>'
			var replyUL = $(".chat");
			
			showList(1);
			
			//댓글 목록
			function showList(page){
				console.log("show list :"+page);
				
				replyService.getList({bno:bnoValue, page:page||1}, function(replyCnt,list){			
					
					console.log("replycnt:"+replyCnt);
					console.log("list:"+list);
					
					
					if(page == -1){
						pageNum = Math.ceil(replyCnt/10.0);
						showList(pageNum);
						return;
					}
					
					var str= "";
					
					if(list == null || list.length == 0){
						return;
					}else{
						for (var i = 0; i < list.length; i++) {
							console.log(list[i]);
							str +="<li class='clearfix list-group-item' data-rno='"+list[i].rno+"'>";
							str +="	<div>";
							str +="		<div class='header'>";
							str +="			<strong class='text-primary'>"+list[i].replyer+"</strong>";
							str +="			<small class='float-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small>";
							str +="		</div>";
							str +="		<p class='m-0'>"+list[i].reply+"</p>";
							str +="	</div>";
							str +="</li>";
						}
						replyUL.html(str);
						
						showReplyPage(replyCnt);
					}
				}); 
			}
			
			
			var pageNum = 1;
		    var replyPageFooter = $(".card-footer");
		    
		    function showReplyPage(replyCnt){
		    	
		    	var endNum = Math.ceil(pageNum / 10.0) * 10;  
		        var startNum = endNum - 9; 
		        
		        var prev = startNum != 1;
		        var next = false;
		        
		        if(endNum * 10 >= replyCnt){
		          endNum = Math.ceil(replyCnt/10.0);
		        }
		        
		        if(endNum * 10 < replyCnt){
		          next = true;
		        }
		        
		        var str="";
		        
		        str+= "<ul class='pagination pagination-sm justify-content-end' >";
		        if(prev){
		        	str+= "    <li class='page-item'><a class='page-link' href='"+(startNum - 1)+">Previous</a></li>";	
		        }
		        
		        for(var i = startNum ; i <= endNum; i++){
		        	var active = pageNum == i? "active":"";
		        	str+= "    <li class='page-item "+active+"' ><a class='page-link' href='"+i+"'>"+i+"</a></li>";
		        }
		        
		        if(next){
		            str+= "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
		        }
		        str+= "</ul>";
		        
		        replyPageFooter.html(str);
		    }
		    
		    replyPageFooter.on("click","li a", function(e){
		        e.preventDefault();
		        console.log("page click");
		        
		        var targetPageNum = $(this).attr("href");
		        
		        console.log("targetPageNum: " + targetPageNum);
		        
		        pageNum = targetPageNum;
		        
		        showList(pageNum);
		     });
			
			//모달 start			
			
			var modal = $(".modal");
		    var modalInputReply = modal.find("input[name='reply']");
		    var modalInputReplyer = modal.find("input[name='replyer']");
		    var modalInputReplyDate = modal.find("input[name='replyDate']");
		    
		    var modalModBtn = $("#modalModBtn");
		    var modalRemoveBtn = $("#modalRemoveBtn");
		    var modalRegisterBtn = $("#modalRegisterBtn");
		    
		    $("#modalCloseBtn").on("click", function(e){
		    	modal.modal('hide');
		    });
		    
		    
		    $("#addReplyBtn").on("click", function(e){
		    	modal.find("input").val("");
		    	modal.find("input[name='replyer']").val(replyer);
		    	modalInputReplyDate.closest("div").hide();
		    	modal.find("button[id !='modalCloseBtn']").hide();
		    	
		    	modalRegisterBtn.show();
		    	
		    	$("#myModal").modal("show");
		    	
		    	
		    });
		    
		    
		    $(document).ajaxSend(function(e, xhr, options) { 
		        xhr.setRequestHeader(csrfHeaderName, csrfTokenValue); 
		     }); 
		    
		    //댓글 추가
		    modalRegisterBtn.on("click",function(e){
			    var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
			     	  
			     	  if(!replyer){
			   		  alert("로그인후 수정이 가능합니다.");
			   		  modal.modal("hide");
			   		  return;
			   	  }
		   	  
			   	  var originalReplyer = modalInputReplyer.val();
			   	  
			   	  console.log("Original Replyer: " + originalReplyer);
			   	  
			   	  if(replyer  != originalReplyer){
			   		  
			   		  alert("자신이 작성한 댓글만 수정이 가능합니다.");
			   		  modal.modal("hide");
			   		  return;
			   		  
			   	  }
			     	  
		     	  replyService.update(reply, function(result){
		     	        
		     	    alert(result);
		     	    modal.modal("hide");
		     	    showList(pageNum);
		     	    
		     	  }); 
		    });
		    
		    $(".chat").on("click", "li",function(e){
		    	var rno = $(this).data("rno");
		    	replyService.get(rno, function(data){
		    		modalInputReply.val(data.reply);
		    		modalInputReplyer.val(data.replyer);
		    		modalInputReplyDate.val(replyService.displayTime(data.replyDate)).attr("readonly","readonly");
		    		modal.data("rno",data.rno);
		    		
		    		modal.find("button[id !='modalCloseBtn']").hide();
		    		modalModBtn.show();
		    		modalRemoveBtn.show();
		    		
		    		$("#myModal").modal("show");
			 	});
		    });
		    
		    //수정
		    modalModBtn.on("click",function(e){
		    	replyService.update(
	    			{rno:modal.data("rno"), reply:modalInputReply.val(), replyer:modalInputReplyer.val()},
	    			function(result){
	    				
	    				alert(result);
	    				modal.modal("hide");
	    				showList(pageNum); 
	    			}
				);  
		    });
		    
		    //삭제
		    modalRemoveBtn.on("click",function(e){
		    	
		    	var rno = modal.data("rno");
		    	
		    	console.log("RNO: " + rno);
		     	console.log("REPLYER: " + replyer);
		     	  
	     	  	if(!replyer){
	     			alert("로그인후 삭제가 가능합니다.");
	     		  	modal.modal("hide");
	     		 	return;
	     	  	}
	     	  
	     	  	var originalReplyer = modalInputReplyer.val();
	     	  
	     	  	console.log("Original Replyer: " + originalReplyer);
	     	  
	     	  	if(replyer  != originalReplyer){
	     		  alert("자신이 작성한 댓글만 삭제가 가능합니다.");
	     		  modal.modal("hide");
	     		  return;
	     		  
	     	  	}
		    	
		    	replyService.remove(rno, function(result) {
		    		alert(result);
		    		modal.modal("hide");
		    		showList(pageNum); 
				}); 
		    });
		    
		    
		    var replyer = null;
		    
		    <sec:authorize access="isAuthenticated()">
		    
		    replyer = '<sec:authentication property="principal.username"/>';   
		    
			</sec:authorize>
		 
		    var csrfHeaderName ="${_csrf.headerName}"; 
		    var csrfTokenValue="${_csrf.token}";
		    
			//댓글 end
			
			
			//첨부파일 조회 
			(function(){
				var bno = '<c:out value="${board.bno}"/>';
				
				 $.getJSON("/board/getAttachList", {bno: bno}, function(arr){
				        
				       console.log(arr);
				       
				       var str = "";
				       
				       $(arr).each(function(i, attach){
				       
				         //image type
				         if(attach.fileType){
				           var fileCallPath =  encodeURIComponent( attach.uploadPath+ "/s_"+attach.uuid +"_"+attach.fileName);
				           
				           str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
				           str += "<img src='/display?fileName="+fileCallPath+"'>";
				           str += "</div>";
				           str +"</li>";
				         }else{
				             
				           str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
				           str += "<span> "+ attach.fileName+"</span><br/>";
				           str += "<img src='/resources/img/attach.png'></a>";
				           str += "</div>";
				           str +"</li>";
				         }
				       });
				       
				       $(".uploadResult ul").html(str);
				       
				 });//end getjson
				
			})(); //end function 
			
			 $(".uploadResult").on("click","li", function(e){
			      
			    console.log("view image");
			    
			    var liObj = $(this);
			    
			    var path = encodeURIComponent(liObj.data("path")+"/" + liObj.data("uuid")+"_" + liObj.data("filename"));
			    
			    if(liObj.data("type")){
			      showImage(path.replace(new RegExp(/\\/g),"/"));
			    }else {
			      //download 
			      self.location ="/download?fileName="+path
			    }
			    
			    
			  });
			  
			  function showImage(fileCallPath){
			    //alert(fileCallPath);
			    $(".bigPictureWrapper").css("display","flex").show();
			    
			    $(".bigPicture")
			    .html("<img src='/display?fileName="+fileCallPath+"' >")
			    .animate({width:'100%', height: '100%'}, 1000);
			  }
			  
			  $(".bigPictureWrapper").on("click", function(e){
			    $(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
			    setTimeout(function(){
			      $('.bigPictureWrapper').hide();
			    }, 1000);
			  });
		});
	</script>
	
	<script>
		/* console.log("===============");
		console.log("JS TEST"); */
		
		/* var bnoValue = '<c:out value="${board.bno}"/>' */
		
		/* replyService.add(
		    {reply:"JS Test", replyer:"tester", bno:bnoValue},
		    function(result){ 
		      alert("RESULT: " + result);
		    }
		);  */
		
		/* replyService.getList({bno:bnoValue, page:1}, function(list){			
			for (var i = 0; i < list.length; i++) {
				console.log(list[i]);
			}
		}); */
		
		/* replyService.remove(24, function(count) {

			   console.log(count);

			   if (count === "success") {
			     alert("REMOVED");
			   }
			 }, function(err) {
			   alert('ERROR...');
		 }); */
		 
		 /* replyService.update(
			{rno:32, bno:bnoValue, reply:"Modified Reply..", replyer:"user00"},
			function(result){
				alert("수정완료..");	
			}
		 ); */
		 
		 /* replyService.get(33, function(data){
			console.log(data); 
		 }); */
		 
		
	</script>     
        
</body>
</html>