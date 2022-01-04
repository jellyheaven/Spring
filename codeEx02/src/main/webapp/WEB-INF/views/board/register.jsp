<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" 
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <style type="text/css">
    	.uploadResult {
			width: 100%;
			background-color: gray;
		}
		
		.uploadResult ul {
			display: flex;
			flex-flow: row;
			justify-content: center;
			align-items: center;
		}
		
		.uploadResult ul li {
			list-style: none;
			padding: 10px;
		}
		
		.uploadResult ul li img {
			width: 100px;
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
                    <h1 class="h3 mb-2 text-gray-800">Board Register</h1>
                    <p class="mb-4"></p>
                    
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">                            
                            <!-- <h6 class="m-0 font-weight-bold text-primary">새글 등록</h6> -->
                        </div>
                        <div class="card-body">
                        	<form role="form" action="/board/register" method="post">
                        		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        		<div class="form-group">
						      		<label for="title" style="font-weight:bold">제목</label>
						      		<input type="text" class="form-control" id="title" name="title">
						    	</div>
						    	<div class="form-group">
						      		<label for="content" style="font-weight:bold">내용</label>
						      		<textarea class="form-control" rows="3" id="content" name='content'></textarea>
						    	</div>
                        		<div class="form-group">
						      		<label for="writer" style="font-weight:bold">작성자</label>
						      		<input type="text" class="form-control" id="writer" name="writer" value='<sec:authentication property="principal.member.userid"/>' readonly="readonly">
						    	</div>
						    	<div class="float-right">
						    		<button type="submit" class="btn btn-primary">글저장</button>
						    		<button type="reset" class="btn btn-danger ">취소</button> 
						    	</div>
						    	
                        	</form>
                            
                        </div>
                    </div>
                    
                    <!-- 첨부파일 start -->
                    <p class="mb-2"></p>
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<i class="fas fa-file-alt"></i> <span class="m-0 font-weight-bold text-primary">첨부파일</span>
                        </div>
                        <div class="card-body">
                       		<input type="file" name="uploadFile" multiple="multiple">
                        </div>
                        <div class="uploadResult">
                        	<ul></ul>
                        </div>
					</div>
                    <!-- 첨부파일 end -->

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
    
    <script>    	
    	$(document).ready(function(){
    		
    		var formObj = $("form[role='form']");
    		
    		$("button[type='submit']").on("click",function(e){
    			e.preventDefault();
    			console.log("submit clicked");
    			
    			var str = "";
    		    
    		    $(".uploadResult ul li").each(function(i, obj){
    		      
    		      var jobj = $(obj);
    		      
    		      console.dir(jobj);
    		      console.log("-------------------------");
    		      console.log(jobj.data("filename"));
    		      
    		      
    		      str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
    		      str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
    		      str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
    		      str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";
    		      
    		    });
    		    
    		    console.log(str);
    		    
    		    formObj.append(str).submit();
    		});
    		
    		
    		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
			var maxSize = 5242880; //5M (1024*1024*5)
			
			//확장자, 파일 크기 클라이언트 사전 체크
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
				
				//add filedate to fordata
				for (var i = 0; i < files.length; i++) {
					if(!checkExtension(files[i].name, files[i].size)){
						return false;
					}
					formData.append("uploadFile", files[i]);
				}//end for
				
				$.ajax({
					url:'/uploadAjaxAction',
					processData:false,
					contentType:false,
					beforeSend: function(xhr) {
				    	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
				    },
					data:formData,
					type:'POST',
					dataType:'json',
					success:function(result){
						
						console.log(result);
						showUploadResult(result);
					}
				});//end ajax
			});
			
			//업로드 결과 
			function showUploadResult(uploadResultArr){
			    
			    if(!uploadResultArr || uploadResultArr.length == 0){ return; }
			    
			    var uploadUL = $(".uploadResult ul");
			    
			    var str ="";
			    
			    $(uploadResultArr).each(function(i, obj){
			        //image type
			    	if(obj.image){
						var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
						str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'>";
						str += "<div><span class='text-white'> "+ obj.fileName+"</span> ";
						str += "<button type='button' data-file=\'"+fileCallPath+"\' "
						str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
						str += "<img src='/display?fileName="+fileCallPath+"'>";
						str += "</div>";
						str +"</li>";
					}else{
						
						var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);			      
					    var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
					      
						str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' >";
						str += "<div><span class='text-white'> "+ obj.fileName+"</span> ";
						str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' " 
						str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
						str += "<img src='/resources/img/attach.png'></a>";
						str += "</div>";
						str +"</li>";
					} 
			    });
			    
			    uploadUL.append(str);
			  } //end showUploadResult
			  
			$(".uploadResult").on("click", "button", function(e){
			    
			    var targetFile = $(this).data("file");
			    var type = $(this).data("type");
			    var targetLi = $(this).closest("li");
			    
			    $.ajax({
			      url: '/deleteFile',
			      data: {fileName: targetFile, type:type},
			      beforeSend: function(xhr) {
				    	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
				  },
			      dataType:'text',
			      type: 'POST',
			        success: function(result){
			           alert(result);
			           
			           targetLi.remove();
			         }
			    }); //$.ajax
			});
			
			
    	}); //end ready
    </script> 
    
    
        
</body>
</html>