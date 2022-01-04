<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
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
	}
	
	.bigPicture {
	  position: relative;
	  display:flex;
	  justify-content: center;
	  align-items: center;
	}
</style>
</head>
<body>
	<h1>Upload with Ajax</h1>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple="multiple">		
	</div>
	
	<div class="uploadResult">
		<ul></ul>
	</div>
	
	<button id="uploadBtn">파일업로드</button>
	
	<div class="bigPictureWrapper">
		<div class="bigPicture"></div>	
	</div>
	
	<script>
	
		//원본이미지 보여주기
		function showImage(fileCallPath){
			//alert(fileCallPath);
		  $(".bigPictureWrapper").css("display","flex").show();
			  
		  $(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
		  .animate({width:'100%', height: '100%'}, 1000);
		}
		
		//원복이미지 숨기기
		$(".bigPictureWrapper").on("click", function(e){
			  $(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
			  setTimeout(function(){
				  $('.bigPictureWrapper').hide();
			  },1000);
		});
		
		//X 클릭시 
		$(".uploadResult").on("click","span", function(e){
			   
			  var targetFile = $(this).data("file");
			  var type = $(this).data("type");
			  console.log(targetFile);
			  
			  $.ajax({
			    url: '/deleteFile',
			    data: {fileName: targetFile, type:type},
			    dataType:'text',
			    type: 'POST',
			      success: function(result){
			         alert(result);
			       }
			  }); //$.ajax
			  
		});
	
		$(document).ready(function(){
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
			
			var cloneObj = $(".uploadDiv").clone();			
			
			//첨부파일 전송
			$("#uploadBtn").on("click",function(e){
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
					data:formData,
					type:'POST',
					dataType:'json',
					success:function(result){
						//alert("uploaded");
						console.log(result);
						
						showUploadeFile(result);
						
						$(".uploadDiv").html(cloneObj.html());
					}
				});//end ajax
				
			});
			//end uploadBtn
			
			var uploadResult = $(".uploadResult ul");
			//업로드 보기
			function showUploadeFile(uploadResultArr){
				
				var str="";
				
				$(uploadResultArr).each(function(i,obj){
					
					//str += "<li><img src='/resources/img/attach.png'>"+ obj.fileName + "</li>";
			        
			        if(!obj.image){
				          
			          var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);
			          
			          str += "<li><a href='/download?fileName="+fileCallPath+"'>" 
			        		  +"<img src='/resources/img/attach.png'>"+obj.fileName+"</a></li>"
			        }else{
			          
			          var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
			          
			          var originPath = obj.uploadPath +"\\"+obj.uuid+"_"+obj.fileName;
			          
			          originPath = originPath.replace(new RegExp(/\\/g),"/");
			          str += "<li><a href=\"javascript:showImage(\'"+originPath+"\')\">"+
		              		 "<img src='display?fileName="+fileCallPath+"'></a>"+
		              		 "<span data-file=\'"+fileCallPath+"\' data-type='image'> x </span>"+
		              		 "<li>";
			        }
				});
				
				uploadResult.append(str);
			} //end showUploadeFile
			
		});
		
	</script>
</body>
</html>