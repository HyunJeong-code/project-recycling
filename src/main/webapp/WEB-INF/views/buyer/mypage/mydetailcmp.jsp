<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myDetailCmp</title>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>

<script type="text/javascript">
$(document).ready(function() {
	var num;
    var originalEmail = $('#bEmail').val() + '@' + $('#emailDomain').val();
    var emailChanged = false;
    var emailVerified = false;
	
 // 이메일 입력 변경 감지
    $("#bEmail, #emailDomain").on('input', function() {
        var currentEmail = $('#bEmail').val() + '@' + $('#emailDomain').val();
        emailChanged = currentEmail !== originalEmail;
        emailVerified = false;
        $("#emailChk").hide();
        $("#emailOk").hide();
        $("#emailNo").hide();
        $("#emailNum").val('');
    });
    
 	// 이메일 인증 버튼 클릭
    $("#btnEmail").click(function() {
        if (!emailChanged) {
            alert("이메일을 변경해주세요.");
            return;
        }
        
        var email = $('#bEmail').val() + '@' + $('#emailDomain').val();
        console.log("이메일 : " + email);
        
        $.ajax({
            type: 'post',
            url: '${pageContext.request.contextPath}/buyer/mypage/EmailAuth',
            data: {email: email},
            dataType: 'json',
            success: function(res) {
                console.log("res : " + res);
                $("#emailChk").show();
                num = res;
                alert("인증번호가 발송되었습니다. 입력하신 메일의 메일함을 확인해주세요.");
            },
            error: function(xhr, status, error) {
                console.error("Ajax 오류:", status, error);
                alert("인증번호 발송에 실패했습니다. 다시 시도해주세요.");
            }
        });
    });
    
 	// 인증번호 입력 확인
    $("#emailNum").on('input', function() {
        var inputNum = $("#emailNum").val();
        
        console.log("입력 인증 번호 : " + inputNum);
        console.log("전송 인증 번호 : " + num);
        
        if (Number(inputNum) === num) {
            $("#emailOk").show();
            $("#emailNo").hide();
            emailVerified = true;
        } else {
            $("#emailNo").show();
            $("#emailOk").hide();
            emailVerified = false;
        }
    });
    
 	// 폼 제출 시 이메일 인증 확인 및 이메일 병합
    $("form").submit(function(e) {
        console.log("폼 제출 시 상태:", emailChanged, emailVerified);
        if (emailChanged && !emailVerified) {
            alert("이메일 인증을 완료해주세요.");
            e.preventDefault();
        } else {
            // 이메일 병합
            var email = $('#bEmail').val() + '@' + $('#emailDomain').val();
            $('#fullEmail').val(email);
        }
    });
    
 	// 이메일 도메인 선택에 따른 처리
    $("#emailSelect").change(function() {
        var selected = $(this).val();
        if (selected === 'custom') {
            $('#emailDomain').val('').prop('readonly', false);
        } else {
            $('#emailDomain').val(selected).prop('readonly', true);
        }
        // 도메인 선택 변경도 이메일 변경으로 처리
        var currentEmail = $('#bEmail').val() + '@' + $('#emailDomain').val();
        emailChanged = currentEmail !== originalEmail;
        emailVerified = false;
        $("#emailChk").hide();
        $("#emailOk").hide();
        $("#emailNo").hide();
        $("#emailNum").val('');
    });
 	
 	// 초기 이메일 도메인 설정
    var currentEmailDomain = "${fn:substringAfter(currentBuyer.bEmail, '@')}";
    if (["naver.com", "gmail.com", "daum.net"].includes(currentEmailDomain)) {
        $('#emailSelect').val(currentEmailDomain);
        $('#emailDomain').val(currentEmailDomain).prop('readonly', true);
    } else {
        $('#emailSelect').val('custom');
        $('#emailDomain').val(currentEmailDomain).prop('readonly', false);
    }
	
	// 전화번호 옵션 설정
	var phonePrefixes = ["010", "011", "016", "017"];
    var currentPhone = "${currentBuyer.bPhone}";
    var phoneParts = currentPhone.split("-");
	
    if (phonePrefixes.includes(phoneParts[0])) {
        $('#phoneSelect').val(phoneParts[0]);
        $('#bPhone1').val(phoneParts[0]).prop('readonly', true);
    } else {
        $('#phoneSelect').val('custom');
        $('#bPhone1').val(phoneParts[0]).prop('readonly', false);
    }
	
	$('#bPhone2').val(phoneParts[1]);
    $('#bPhone3').val(phoneParts[2]);
    
    $('#phoneSelect').change(function() {
        var selected = $(this).val();
        if (selected === 'custom') {
            $('#bPhone1').val('').prop('readonly', false);
        } else {
            $('#bPhone1').val(selected).prop('readonly', true);
        }
    })
})
</script>

<script>
var previousChecked = {};

function toggleRadioButton(element) {
	
	var name = element.name;
	
	if(previousChecked[name] === element) {
		
		element.checked = false;
		previousChecked[name] = null;
		
	} else {
		
		previousChecked[name] = element;
		
	}
	
}
	
function cancelUpdate() {
		
	window.history.back();
		
}
</script>

<style>
body {
    margin: 0;
    padding: 0;
}

.full {
    width: 1200px;
    height: auto;
    margin: 0 auto;
    padding: 50px 20px;
    display: flex;
    justify-content: center;
    align-items: flex-start;
}

.wrap {
    display: flex;
    width: 100%;
    justify-content: flex-start;
    align-items: flex-start;
    border-radius: 8px;
    padding: 20px;
}

.page {
    margin-top: 20px;
    border-bottom: 3px solid #333;
    width: 100%;
    text-align: center;
    padding-bottom: 30px;
}

h2 {
    margin-top: 0;
    color: #333;
}

hr {
    border: 0;
    border-top: 1px solid #ccc;
    margin: 20px 0;
}

.page a {
    display: block;
    margin: 10px 0;
    color: #0066cc;
    text-decoration: none;
}

.page a:hover {
    text-decoration: underline;
}

.table-container {
	text-align: center;
}

table {
    width: 85%;
    border-collapse: collapse;
    margin-top: 20px;
    display: inline-block;
}

table, td {
    border: 1px solid #dddddd;
}

td {
    padding: 15px;
    text-align: left;
}

td:first-child {
    background-color: #CEE741;
    font-weight: bold;
    text-align: center;
    width: 30%;
}

.form-group {
    margin-bottom: 20px;
}

.btnProf {
    display: inline-block;
    padding: 10px 20px;
    cursor: pointer;
    background-color: #878787;
    color: white;
    border: none;
    border-radius: 4px;
    margin-top: 10px;
    text-align: center;
}

.btnProf:hover {
    background-color: #9e9e9e;
}

.btnEmail {
    display: inline-block;
    padding: 10px 20px;
    cursor: pointer;
    background-color: #878787;
    color: white;
    border: none;
    border-radius: 4px;
    margin-top: 10px;
    text-align: center;
}

.btnEmail:hover {
    background-color: #9e9e9e;
}

.button-group {
    text-align: center;
    margin-top: 40px;
    margin-bottom: 40px;
}

.button-group button {
    margin-right: 10px;
    padding: 10px 20px;
    background-color: #878787;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.button-group button.btnRight {
    background-color: #878787;
}

.button-group button.btnLeft {
    background-color: #4CAF50;
}

.button-group button:hover {
    background-color: #58c05c;
}

.button-group button.btnRight:hover {
    background-color: #9e9e9e;
}

.button-group button.btnLeft:hover {
    background-color: #45A049;
}
</style>

</head>
<body>

	<c:import url="/WEB-INF/views/layout/buyer/buyerheader.jsp"/>

	<div class="full">
		<div class="wrap">
			
			<c:import url="/WEB-INF/views/layout/buyer/buyermymenu.jsp"/>
		
			<div class="page">
			<h3>기업 정보 수정</h3>
				<form action="${pageContext.request.contextPath }/buyer/mypage/mydetailcmp" method="post" enctype="multipart/form-data">
					<input type="hidden" name="bCode" value="${currentBuyer.bCode }">
					<input type="hidden" id="fullEmail" name="fullEmail" value="${currentBuyer.bEmail}">
					
					<div class="mydetailcmp">
						<table>
							<tr>
								<td>프로필</td>
								<td>
									<c:choose>
										<c:when test="${buyerProf != null }">
											<img src="/resources/image/${buyerProf.originName}" alt="${buyerProf.storedName}" style="width: 100px; height: 100px;"><br>
										</c:when>
										<c:otherwise>
											<img src="${pageContext.request.contextPath }/resources/image/basicProf.png" alt="기본 프로필 이미지" style="width: 100px; height: 100px;"><br>
										</c:otherwise>
									</c:choose>
									<button type="button" class="btnProf" onclick="document.getElementById('buyerProf').click();">프로필 선택</button>
									<input type="file" id="buyerProf" name="buyerProf" style="display: none;">
								</td>
							</tr>
							<tr>
								<td>담당자 이름</td>
								<td><input type="text" id="bName" name="bName" value="${currentBuyer.bName}"></td>
							</tr>
							<tr>
								<td>아이디</td>
								<td><input type="text" id="bId" name="bId" value="${currentBuyer.bId }" readonly></td>
							</tr>
							<tr>
								<td>전화번호</td>
								<td>
									<select id="phoneSelect">
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="016">016</option>
										<option value="017">017</option>
										<option value="custom">직접입력</option>
									</select>
									<input type="text" id="bPhone1" name="bPhone1" maxlength="3" style="width: 50px;">
				                    - <input type="text" id="bPhone2" name="bPhone2" maxlength="4" style="width: 70px;">
				                    - <input type="text" id="bPhone3" name="bPhone3" maxlength="4" style="width: 70px;">
								</td>
							</tr>
							<tr>
								<td>담당자 이메일</td>
								<td>
									<input type="text" id="bEmail" name="bEmail" value="${fn:substringBefore(currentBuyer.bEmail, '@')}">
				                    @ <input type="text" id="emailDomain" name="emailDomain" value="${fn:substringAfter(currentBuyer.bEmail, '@')}">
				                    <select id="emailSelect">
										<option value="naver.com">naver.com</option>
				                        <option value="gmail.com">gmail.com</option>
				                        <option value="daum.net">daum.net</option>
				                        <option value="custom">직접입력</option>
									</select>
									<button type="button" id="btnEmail" class="btnEmail">이메일 인증</button>
									<div id="emailChk" style="display: none;">
										<label for="emailNum">이메일 인증 번호</label>
										<input type="text" id="emailNum" name="emailNum" placeholder="인증번호 6자리를 입력해주세요.">
										<div id="emailOk" style="color: green; display: none;">
											인증번호가 일치합니다.
										</div>
										<div id="emailNo" style="color: red; display: none;">
											인증번호가 불일치합니다. 다시 입력해주세요.
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>광고성 정보 수신 여부</td>
								<td>
									<label for="adSms">SMS</label>
									<input type="radio" name="adSms" id="adSms" value="Y" 
									<c:if test="${currentBuyer.adSms eq 'Y' }">checked</c:if> onclick="toggleRadioButton(this)">
									
									<label for="adEmail">Email</label>
									<input type="radio" name="adEmail" id="adEmail" value="Y" 
									<c:if test="${currentBuyer.adEmail eq 'Y' }">checked</c:if> onclick="toggleRadioButton(this)">
								</td>
							</tr>
							<tr>
								<td>대표자명</td>
								<td><input type="text" id="cmpCeo" name="cmpCeo" value="${currentCmp.cmpCeo }"></td>
							</tr>
							<tr>
								<td>상호명(법인명)</td>
								<td><input type="text" id="cmpName" name="cmpName" value="${currentCmp.cmpName }"></td>
							</tr>
							<tr>
								<td>사업자 등록 번호 / 법인 등록 번호</td>
								<td><input type="text" id="cmpNo" name="cmpNo" value="${currentCmp.cmpNo }"></td>
							</tr>
							<tr>
								<td>우편번호</td>
								<td>
									<input type="text" id="cmpPostcode" name="cmpPostcode" value="${currentCmp.cmpPostcode }">
									<input type="button" value="우편번호 찾기">
								</td>
							</tr>
							<tr>
								<td>사업자 등록증 주소</td>
								<td><input type="text" id="cmpAddr" name="cmpAddr" value="${currentCmp.cmpAddr }"></td>
							</tr>
							<tr>
								<td>상세 주소</td>
								<td><input type="text" id="cmpDetail" name="cmpDetail" value="${currentCmp.cmpDetail }"></td>
							</tr>
							<tr>
								<td>사업자 등록증 첨부</td>
								<td>
									<c:if test="${not empty cmpFile }">
		    							<img src="/resources/image/${cmpFile.originName}" alt="${cmpFile.storedName}" style="width: 100px; height: 100px;">
									</c:if>
									<input type="file" id="cmpFile" name="cmpFile">
								</td>
							</tr>
						</table>
						<div class="button-group">
							<button type="submit" class="btnLeft">수정하기</button>
							<button type="button" class="btnRight" onclick="cancelUpdate()">취소하기</button>
						</div>
					</div>
				</form>
				<c:if test="${not empty success }">
					<p style="color: green;">${success }</p>
				</c:if>
				<c:if test="${not empty error }">
					<p style="color: red;">${error }</p>
				</c:if>
			</div>
		</div>
	</div>
	
	<c:import url="/WEB-INF/views/layout/buyer/buyerfooter.jsp"/>
	
</body>
</html>