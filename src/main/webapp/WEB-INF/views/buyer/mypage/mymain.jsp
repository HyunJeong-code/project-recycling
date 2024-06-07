<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myMain</title>

<script type="text/javascript">
$(document).ready(function() {
	$('form').on('submit', function(event) {
		event.preventDefault(); // 폼 기본 제출 방지

		var password = $('#password').val();
		
		$.ajax({
			url: '${pageContext.request.contextPath }/buyer/mypage/mymain',
			type: 'POST',
			data: $(this).serialize(), // 폼 데이터 직렬화
			success: function(response) {
				var parsedHtml = $.parseHTML(response);
				var errorMessage = $(parsedHtml).find('#errorMessage').text();
				var redirectUrl = $(parsedHtml).find('#redirectUrl').text();
				
				if (errorMessage) {
					alert(errorMessage);
				} else if (redirectUrl) {
					window.location.href = redirectUrl;
				}
			},
			error: function() {
				alert('비밀번호 확인 중 오류가 발생했습니다.');
			}
		});
	});
});
</script>

<style type="text/css">
<<<<<<< HEAD
.page {
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
	align-items: center;
	height: 100vh;
	padding-top: 50px;
	text-align: center;
}

.form-group {
	display: flex;
	flex-direction: row;
	align-items: center;
	margin-bottom: 15px;
}

label {
	margin-right: 10px;
	width: 150px;
	text-align: right;
=======
body {
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}
.full {
    display: flex;
    justify-content: center;
    padding: 20px;
}
.page {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    height: 100vh;
    padding-top: 50px;
    text-align: center;
}
.page h3 {
    margin-bottom: 30px;
    color: #333;
}
.form-group {
    display: flex;
    flex-direction: row;
    align-items: center;
    margin-bottom: 15px;
}
label {
    margin-right: 10px;
    width: 150px;
    text-align: right;
}
.button-group {
    text-align: center;
    margin-top: 20px;
}
.button-group button {
    margin-right: 10px;
    padding: 10px 20px;
    background-color: #0066cc;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
.button-group button:hover {
    background-color: #005bb5;
>>>>>>> TEST
}
</style>

</head>
<body>
	<div class="full">
		<div class="wrap">
		
			<c:import url="/WEB-INF/views/layout/buyer/buyermymenu.jsp"/>
		
			<div class="page">
				<form action="${pageContext.request.contextPath }/buyer/mypage/mymain" method="post">
					<h3>개인 정보 변경을 위해 비밀번호를 입력해주세요.</h3>
					<label for="password">비밀번호 </label>
					<input type="password" id="password" name="password" required>
					<div class="button-group">
						<button class="btn">확인</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<c:import url="/WEB-INF/views/layout/buyer/buyerfooter.jsp"/>
	
	<c:if test="${not empty error}">
		<div id="errorMessage" style="display:none;">${error}</div>
	</c:if>
	
	<c:if test="${buyerLogin != null && buyerLogin.bCtCode == 'P'}">
		<div id="redirectUrl" style="display:none;">${pageContext.request.contextPath}/buyer/mypage/mypagepri</div>
	</c:if>
	
	<c:if test="${buyerLogin != null && buyerLogin.bCtCode == 'C'}">
		<div id="redirectUrl" style="display:none;">${pageContext.request.contextPath}/buyer/mypage/mypagecmp</div>
	</c:if>
	
</body>
</html>