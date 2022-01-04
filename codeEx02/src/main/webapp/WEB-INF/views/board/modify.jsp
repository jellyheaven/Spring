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
                    <h1 class="h3 mb-2 text-gray-800">Board Modify Page</h1>
                    <p class="mb-4"></p>
                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">                            
                            <!-- <h6 class="m-0 font-weight-bold text-primary">새글 등록</h6> -->
                        </div>
                        <div class="card-body">
                        	<form role="form" action="/board/modify" method="post">
                        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        		
                        		<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
        						<input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
        						<input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
								<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
								
                        		<div class="form-group">
						      		<label for="bno" style="font-weight:bold">글번호</label>
						      		<input type="text" class="form-control" id="bno" name="bno" readonly="readonly" value="<c:out value="${board.bno}" />">
						    	</div>
                        		<div class="form-group">
						      		<label for="title" style="font-weight:bold">제목</label>
						      		<input type="text" class="form-control" id="title" name="title"  value="<c:out value="${board.title}" />">
						    	</div>
						    	<div class="form-group">
						      		<label for="content" style="font-weight:bold">내용</label>
						      		<textarea class="form-control" rows="3" id="content" name='content'><c:out value="${board.content}" /></textarea>
						    	</div>
                        		<div class="form-group">
						      		<label for="writer" style="font-weight:bold">작성자</label>
						      		<input type="text" class="form-control" id="writer" name="writer" value="<c:out value="${board.writer}"/>">
						    	</div>
						    	<div class="form-group">
								  <label for="regDate">등록일</label> 
								  <input class="form-control" id="regDate" name='regDate' value='<fmt:formatDate pattern = "yyyy/MM/dd hh:mm:ss" value = "${board.regdate}" />'  readonly="readonly">            
								</div>								
								<div class="form-group">
								  <label for="updateDate" >수정일</label> 
								  <input class="form-control" id="updateDate" name='updateDate' value='<fmt:formatDate pattern = "yyyy/MM/dd hh:mm:ss" value = "${board.updatedate}" />'  readonly="readonly">            
								</div>
						    	<div class="float-right">
						    		<sec:authentication property="principal" var="pinfo" />
						    		<sec:authorize access="isAuthenticated()">
						    			<c:if test="${pinfo.member.userid eq board.writer}">
						    				<button type="submit" data-oper='modify' class="btn btn-primary">수정</button>
						    				<button type="submit" data-oper='remove' class="btn btn-danger">삭제</button>
						    			</c:if>
						    		</sec:authorize>
						    		<button type="submit" data-oper="list" class="btn btn-info">목록</button></a> 
						    	</div>
						    	
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
                        	<div class="mb-2 uploadDiv">
                        		<input type="file" name="uploadFile" multiple="multiple">
                        	</div>
                       		<div class="uploadResult">
								<ul></ul>
							</div>
                        </div>
					</div>
                    <!-- 첨부파일 보기 end -->

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
    
    
<script type="text/javascript">
	$(document).ready(function(){
		var formObj = $("form");
		
		$('button').on("click", function(e){
			e.preventDefault();
			//a 태그나 submit 태그는 누르게 되면 href 를 통해 이동하거나 , 창이 새로고침하여 실행됩니다.
			//preventDefault 를 통해 이러한 동작을 막아줄 수 있습니다.

			var operation = $(this).data("oper");
			
			console.log("operation : "+operation);
			
			if(operation === 'remove'){
				formObj.attr("action", "/board/remove");
			}else if(operation === 'list'){
				//self.location = "/board/list";
				//return;
				formObj.attr("action", "/board/list").attr("method", "get");
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();				
				var typeTag = $("input[name='type']").clone();
				var keywordTag = $("input[name='keyword']").clone();
				
				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(typeTag);
				formObj.append(keywordTag);
				
			}else if(operation === 'modify'){
				console.log("submit clicked");
			        
		        var str = "";
		        
		        $(".uploadResult ul li").each(function(i, obj){
		          
		          var jobj = $(obj);
		          
		          console.dir(jobj);
		          
		          str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
		          str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
		          str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
		          str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";
		          
		        });
		        
		        formObj.append(str).submit();
			}	
			
			formObj.submit();
		}); //end button
		
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
			             
			             str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
			             str +=" data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
			             str += "<span> "+ attach.fileName+"</span>";
			             str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' "
			             str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
			             str += "<img src='/display?fileName="+fileCallPath+"'>";
			             str += "</div>";
			             str +"</li>";
			           }else{
			               
			             str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
			             str += "data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
			             str += "<span> "+ attach.fileName+"</span><br/>";
			             str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' "
			             str += " class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
			             str += "<img src='/resources/img/attach.png'></a>";
			             str += "</div>";
			             str +"</li>";
			           }
			       });
			       
			       $(".uploadResult ul").html(str);
			       
			 });//end getjson
			
		})(); //첨부파일 조회 end 
		
		
		$(".uploadResult").on("click", "button", function(e){
		    
		    console.log("delete file");
		    if(confirm("파일을 삭제하겠습니까?")){
		    	var targetLi = $(this).closest("li");
			    targetLi.remove();	
		    }
		});
		
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880; //5MB
		  
		function checkExtension(fileName, fileSize){
		    
		    if(fileSize >= maxSize){
		      alert("파일 사이즈 초과");
		      return false;
		    }
		    
		    if(regex.test(fileName)){
		      alert("해당 종류의 파일은 업로드할 수 없습니다.");
		      return false;
		    }
		    return true;
		} //end checkExtension
		
		var csrfHeaderName ="${_csrf.headerName}"; 
		var csrfTokenValue="${_csrf.token}";
		
		$("input[type='file']").change(function(e){
	
		    var formData = new FormData();
		    
		    var inputFile = $("input[name='uploadFile']");
		    
		    var files = inputFile[0].files;
		    
		    for(var i = 0; i < files.length; i++){
	
		      if(!checkExtension(files[i].name, files[i].size) ){
		        return false;
		      }
		      formData.append("uploadFile", files[i]);
		      
		    }
		    
		    $.ajax({
		      url: '/uploadAjaxAction',
		      processData: false, 
		      contentType: false,data: 
		      formData,type: 'POST',
		      beforeSend: function(xhr) {
			    	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			  },
		      dataType:'json',
		        success: function(result){
		          console.log(result); 
				  showUploadResult(result); //업로드 결과 처리 함수 
	
		      }
		    }); //$.ajax
		    
		}); //end file change   

		function showUploadResult(uploadResultArr){
			    
		    if(!uploadResultArr || uploadResultArr.length == 0){ return; }
		    
		    var uploadUL = $(".uploadResult ul");
		    
		    var str ="";
		    
		    $(uploadResultArr).each(function(i, obj){
				
				if(obj.image){
					var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
					str += "<li data-path='"+obj.uploadPath+"'";
					str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'"
					str +" ><div>";
					str += "<span class='text-white'> "+ obj.fileName+"</span>";
					str += "<button type='button' data-file=\'"+fileCallPath+"\' "
					str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/display?fileName="+fileCallPath+"'>";
					str += "</div>";
					str +"</li>";
				}else{
					var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);			      
				    var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
				      
					str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' ><div>";
					str += "<span class='text-white'> "+ obj.fileName+"</span>";
					str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' " 
					str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/resources/img/attach.png'></a>";
					str += "</div>";
					str +"</li>";
				}

		    });
		    
		    uploadUL.append(str);
		    
		} // end showUploadResult
		
	}); //end ready
</script>

        
</body>
</html>