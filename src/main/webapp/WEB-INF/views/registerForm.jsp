<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!--스프링이 제공하는 커스텀 태그 라이브러리 사용   -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" 
integrity="sha512-rqQltXRuHxtPWhktpAZxLHUVJ3Eombn3hvk9PHjV/N5DMUYnzKPC1i3ub0mEXgFzsaZNeJcoE0YHq0j/GFsdGg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />

<style type="text/css">
*{
box-sizing: border-box;
}
.title{
font-size: 50px;
margin: 40px 0 30px 0;
}
form{
width:400px;
height: 600px;
display: flex;
flex-direction: column;
align-items: center;
position: absolute;
top:  50%;
left: 50%;
transform: translate(-50%,-50%);
border:  1px solid rgb(89,117,196) ;
border-radius: 5px;
}

.msg{
height: 30px;
text-align: center;
font-size: 16px;
color: red;
margin-bottom: 20px;
}
label {
	width: 300px;
	height: 30px;
	margin-top: 4px;
}
.input-field{
width:300px;
height: 40px;
border:  1px solid rgb(89,117,196) ;
border-radius: 5px;
padding: 0 10px;
margin-bottom: 10px;
} 
.sns-chk{
margin-top: 5px;
margin-left: 50px;
}
button {
	background-color: rgb(89,117,196);
	color: white;
	width: 300px;
	height: 50px;
	font-size: 17px;
	border: none;
	border-radius: 5px;
	margin: 20px 0 30px 0;
	
}
</style>
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="user"><!--form 태그 대신 form:form 사용함 , user는 검증할 객체임  -->
		<div class="title">Register</div>
		<div id="msg" class="msg">
			<form:errors path="id"/> <!--에러   -->
		</div>	
		
		
		<label for="">아이디</label>
		<input class ="input-field" type="text" name="id" placeholder="8~12자리의 영대소문자, 숫자 조합 "> 
		
		<label for="">비밀번호</label>
		<input class ="input-field" type="password" name="pwd" placeholder="8~12자리의 영대소문자, 숫자 조합 ">
		
		<label for="">이름</label>
		<input class ="input-field" type="text" name="name" placeholder="홍길동"> 
		
		<label for="">이메일</label>
		<input class ="input-field" type="text" name="email" placeholder="hong@gmail.com ">
		  
		<label for="">생일</label>
		<input class ="input-field" type="text" name="birth" placeholder="2000/01/01 ">
		
		<div class="sns-chk">
			<label><input type="checkbox" name = "sns" value="facebook"/>페이스북</label>
			<label><input type="checkbox" name = "sns" value="kakaotalk"/>카카오톡</label>
			<label><input type="checkbox" name = "sns" value="instagram"/>인스타그램</label>
		</div>
		<button>회원가입</button>  
	</form:form>
	<script type="text/javascript">
		function formCheck(frm){
			let msg = 	'';
			//msg 변수 설정
			if(frm.id.value.length<8){
				setMessage('id의 길이는 8자리 이상이어야 합니다.',frm.id)
				return false;
			}
			if(frm.pwd.value.length<8){
				setMessage('pwd의 길이는 8자리 이상이어야 합니다.',frm.pwd)
				return false;
			}
			return true;			
		}
		/* 서버가 먼적 해석함.${msg}이 부분을 EL로 해석함  그 결과를 브라우저에 보내줌 
			ES6는 템플릿 리터럴이라는 새로운 문자열 표기법으로 해석함 
		*/
			function setMessage(msg,element){
				document.getElementById("msg").innerHTML =`<i class="fa-exclamation-circle">${msg}</i>`; 
				if(element){
					element.select();	//값을 잘못 입력되었을 때 그 요소를 선택되게 하는 것임.
				}
			}
	</script>
</body>
</html>