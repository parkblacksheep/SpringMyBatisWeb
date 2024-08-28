<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    <c:set var="loginId" value="${sessionScope.id}"/>
    <c:set var="loginout" value="${sessionScope.id==null ?'Login':'id:'+=loginId}"></c:set>
    <c:set var="loginoutlink" value="${sessionScope.id==null ?'/login/login':'/login/logout'}"></c:set>
<!DOCTYPE html>
<html>
	<head>
	<link rel="stylesheet" href="<c:url value='/resources/css/menu.css'/>"/>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	
	<style>
	*{
		box-sizing: border-box;
		margin: 0;
		padding: 0;
		font-family: "Noto Sans KR", sans-serif
	}
	a{
		text-decoration: none;
		color: black;
	}
	.board-container{
		width: 60%;
		height: 1200px; 
		margin:0 auto; 
		}
	.search-container{
		background-color: rgb(253,253,250);
		width: 100%;
		border: 1px solid #ddd;
		margin-top: 10px;
		margin-bottom: 30px;
		display: flex;
		justify-content: center;
		align-items: center;
		}
	table{
		boarder-collapse: collapse;
		width:  100%;
		border-top: 2px solid rgb(39,39,39);
	}
	tr:nth-child(even){
	background-color:  #f0f0f070
	};
	
	th,td{
		width:300px;
		text-align: center;
		padding:  10px 12px;
		border-bottom: 1px solid #ddd;
				}
	td{
		color: rgb(53,53,53)
	}
	no{
		width: 150px;
	}
	.title{	width: 50%;}
	
	td.title{text-align: left;}
	td.writer{	text-align: left;	}
	td.viewcnt{text-align: right;}
	td.title:hover{
		text-decoration: underline;
	}
	.paging-container{
		width: 100%;
		height: 70px;
		display: flex;
		margin-top: 50px;
		margin: auto;
	}
	.paging{
		color: black;
		width:100%;
		align-items: center;
	}
	.page{
		color: black;
		padding: 6px;
		margin-right: 10px;
	}
	
	.container{
		width: 50%;
		margin: auto;
	}
	.writing-header{
		position: relative;
		margin: 20px 0 0 0 ;
		padding-bottom: 10px;
		border-bottom: 1px solid #323232;
	}
	.frm{
	width: 100%;
	}
	input{
		width: 100%;
		height: 35px;
		margin: 5px 0px 10px 0px;
		border: 1px solid #f8f8f8;
		padding: 8px;
		background: #f8f8f8;
		outline-color: #e6e6e6; 
	}
	textarea {
		width: 100%;
		background: #f8f8f8;
		margin: 5px 0px 10px 0px;
		border: 1px solid #e9e8e8;
		resize: none;
	}
	.btn{
		background-color: rgb(236,236,236);
	 	border: none;
	 	color: black;
	 	padding: 6px 12px;
	 	font-size: 16px;
	 	cursor: pointer;
	 	border-radius: 5px; 
	}
	.btn:hover {
		text-decoration: underline;
	}
	</style>
<body>
	<div id="menu">
		<ul>
			<li id="logo">kosta</li>
			<li><a href="<c:url value = "/"/>">Home</a></li>
			<li><a href="<c:url value = "/board/list"/>">Board</a></li>
			<li><a href="<c:url value = "${loginoutlink}"/>">${loginout}</a></li>
			<li><a href="<c:url value = "/register/add"/>">SignUp</a></li>
			<li><a href=""><i class="fa fa-search small" aria-hidden="true"></i></a></li>
		</ul>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#listBtn").on("click",function(){
				location.href="<c:url value="/board/list?page=${page}&pageSize=${pageSize}"/>"
			})
			$("#removeBtn").on("click",function(){
				if(!confirm("정말로 삭제하시겠습니까?")) return;
				let form =$("#form")
				form.attr("action","<c:url value="/board/remove?page=${page}&pageSize=${pageSize}"/>")
				form.attr("method","post")
				form.submit()
			})
		})
	</script>
	
	<div class="container">
		<h2>게시판 ${mode=="new"?"글쓰기":"읽기"}</h2>
		<form action = "" id = "form" class = "frm" method = "post">
			<input type = "hidden" name = "bno" value="${boardDTO.bno }"/>
			<input type = "text" name = "title" value="${boardDTO.title}" ${mode=="new" ?"":"readonly='readonly' "}/>
			<textarea rows="20	" cols="" name="content" ${mode=="new" ?"":"readonly='readonly' "}>
				${boardDTO.content}
			</textarea>
			<br/>
			
			<c:if test="${mode eq 'new' }">
				<button type="button" id="writeBtn" class="btn btn-write"><i class="fa fa-pencil-alt" aria-hidden="true"></i>등록</button>
			</c:if>
			
			<c:if test="${mode ne 'new' }">
				<button type="button" id="writeNewBtn" class="btn btn-write"><i class="fa fa-pencil-alt" aria-hidden="true"></i>글쓰기</button>
			</c:if>
			
			<c:if test="${boardDTO.writer eq loginId}">
				<button type="button" id="modifyBtn" class="btn btn-modify"><i class="fa fa-edit" aria-hidden="true"></i>수정</button>
				<button type="button" id="removeBtn" class="btn btn-remove"><i class="fa fa-trash" aria-hidden="true"></i>삭제</button>
			</c:if>
			
			<button type="button" id="listBtn" class="btn btn-list"> <i class="fa fa-list" aria-hidden="true"></i>목록</button>
		</form>
	</div>
</body>
</html>














