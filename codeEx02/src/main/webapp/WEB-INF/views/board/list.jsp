<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" 
    %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
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
                    <h1 class="h3 mb-2 text-gray-800">Board Tables</h1>
                    <p class="mb-4"></p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3"> 
                        	<div class='row'>	                        	
	                        	<!-- <h6 class="m-0 font-weight-bold text-primary">메뉴 > Tables</h6> -->
	                        	<div class="col-lg-6 col-sm-12">	                        	
		                        	<form id='searchForm' action="/board/list" method='get'>
			                        	<select name='type'>
			                        		<option value="" <c:out value="${pageMaker.cri.type == null ? 'selected':'' }" /> >검색 선택</option>
			                        		<option value="T" <c:out value="${pageMaker.cri.type == 'T' ? 'selected':'' }" /> >제목</option>
			                        		<option value="C" <c:out value="${pageMaker.cri.type == 'C' ? 'selected':'' }" />>내용</option>
			                        		<option value="W" <c:out value="${pageMaker.cri.type == 'W' ? 'selected':'' }" />>작성자</option>
			                        		<option value="TC" <c:out value="${pageMaker.cri.type == 'TC' ? 'selected':'' }" />>제목 or 내용</option>
			                        		<option value="TW" <c:out value="${pageMaker.cri.type == 'TW' ? 'selected':'' }" />>제목 or 작성자</option>
			                        		<option value="TWC" <c:out value="${pageMaker.cri.type == 'TWC' ? 'selected':'' }" />>제목 or 내용 or 작성자</option>
			                        	</select>
			                        	<input type='text' name='keyword' value="${pageMaker.cri.keyword}" />
			                        	<button type="button" class="btn btn-primary">검색</button>
			                        	<input type='hidden' name='pageNum' value='<c:out value="${pageMaker.cri.pageNum}"/>' /> 
			                        	<input type='hidden' name='amount' value='<c:out value="${pageMaker.cri.amount}"/>' />
		                        	</form>
	                        	</div>
	                        	<div class="col-lg-6 col-sm-12 ">	                        		
	                        		<button id='regBtn' type="button" class="btn btn-primary float-right">새글 등록</button>
	                        	</div>
                        	</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
                                    <thead class="thead-light text-center">
                                        <tr>
                                            <th style="width: 6%">번호</th>
                                            <th style="width: 54%">제목</th>
                                            <th style="width: 10%">작성자</th>
                                            <th style="width: 15%">작성일</th>
                                            <th style="width: 15%">수정일</th>
                                        </tr>
                                    </thead>
                                    <tfoot class="thead-light text-center">
                                        <tr>
                                            <th style="width: 5%">번호</th>
                                            <th style="width: 55%">제목</th>
                                            <th style="width: 10%">작성자</th>
                                            <th style="width: 15%">작성일</th>
                                            <th style="width: 15%">수정일</th>
                                        </tr>
                                    </tfoot>
                                    
                                    <tbody>
                                    	<c:choose>
                                    		<c:when test="${fn:length(list) == 0}">
                                    			<tr>
	                                            	<th colspan="5" style="text-align: center;">데이타가 존재 하지 않습니다.</th>
                                        		</tr>
                                    		</c:when>
                                    		<c:otherwise>
                                    			<c:forEach var="board" items="${list}">
		                                    		<tr>
			                                            <th style="text-align: center;"><c:out value="${board.bno}"/></th>
			                                            <%-- <td><a href='/board/get?bno=<c:out value="${board.bno}"/>'><c:out value="${board.title}"/></a></td> --%>
			                                            <th><a class="move" href="<c:out value="${board.bno}"/>">
			                                            	<c:out value="${board.title}"/> <b>[<c:out value="${board.replyCnt}"></c:out>]</b></a>
		                                            	</th>
			                                            <th><c:out value="${board.writer}"/></th>
			                                            <th><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${board.regdate}"/></th>
			                                            <th><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${board.updatedate}"/></th>
			                                        </tr>
		                                    	</c:forEach>
                                    		
                                    		</c:otherwise>
                                    	</c:choose>	
                                    	
                                    </tbody>
                                </table>
                            </div>
                            
                            <!-- 페이징 start-->
                            <div id='divpage'>
                            	<div class="row">
                            		<h6 class="m-0 font-weight-bold ">총 <c:out value="${pageMaker.total}"/>건수</h6>
                            	</div>
                            	<div class="row justify-content-center">
                            		<ul class="pagination " style="margin:20px 0">
									    <c:if test="${pageMaker.prev}" >
									    	<li class="page-item"><a class="page-link" href="${pageMaker.startPage - 1}">Previous</a></li>
									    </c:if>
									    <c:forEach var="num"  begin="${pageMaker.startPage}" end="${pageMaker.endPage}" >
									    	<li class="page-item ${pageMaker.cri.pageNum == num ? "active":""}"><a class="page-link" href="${num}">${num}</a></li>
									    </c:forEach>
									    <li class="page-item"><a class="page-link" href="${pageMaker.endPage + 1}">Next</a></li>
								    </ul>
                            	</div>
							</div>
                            <!-- 페이징 end-->
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->
                
                <form id='actionForm' action="/board/list" method='get'>
					<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
					<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
					<input type='hidden' name='type' value='${pageMaker.cri.type}'>
					<input type='hidden' name='keyword' value='${pageMaker.cri.keyword}'>
				</form>

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
    
    <!-- Modal 추가 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">알림</h4>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
				</div>
				<div class="modal-body">정상 처리 되었습니다.</div>
				<div class="modal-footer">
					<!-- <button type="button" class="btn btn-primary" href="login.html">Save changes</button> -->
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- end Modal 추가 -->
    

<script type="text/javascript">
	$(document).ready(function(){
		
		var result = '<c:out value="${result}"/>';

		checkModal(result);
		
		history.replaceState({},null,null);
		
		function checkModal(result) {

			if (result === '' || history.state) {
				return;
			}

			if (parseInt(result) > 0) {
				$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.");
			}
			$("#myModal").modal("show");
		}
		
		$("#regBtn").on("click", function(){
			self.location = "/board/register";
		});
		
		var actionForm = $("#actionForm");
		
		$(".page-item a").on("click", function(e){
			e.preventDefault();
			//a 태그나 submit 태그는 누르게 되면 href 를 통해 이동하거나 , 창이 새로고침하여 실행됩니다.
			//preventDefault 를 통해 이러한 동작을 막아줄 수 있습니다.
			
			console.log("click");
			
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
		
		$(".move").on("click",function(e){
			e.preventDefault();
			//a 태그나 submit 태그는 누르게 되면 href 를 통해 이동하거나 , 창이 새로고침하여 실행됩니다.
			actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
			actionForm.attr("action","/board/get");
			actionForm.submit();
		});
		
		
		var searchForm = $("#searchForm");
		
		$("#searchForm button").on("click", function(e){
			
			if(!searchForm.find("select").val() == ""){
				if(!searchForm.find("option:selected").val()){
					alert("검색항목를 선택하세요.");
					return false;
				}
				
				if(!searchForm.find("input[name = 'keyword']").val()){
					alert("키워드를 입력하세요.");
					return false;
				}
			}
			
			searchForm.find("input[name = 'pageNum']").val("1");
			
			e.preventDefault();
			
			searchForm.submit();
		});
		
		$("select[name='type']").change(function(e){
			if(searchForm.find("select").val() == ""){
				searchForm.find("input[name = 'keyword']").val("");
			}
		});
		
		
	});
</script>
    
</body>
</html>