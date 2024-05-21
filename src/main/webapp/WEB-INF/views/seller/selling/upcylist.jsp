<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 결제 API -->
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.js"></script>

<script type="text/javascript">

let pdtList = {0:"플라스틱", 1:"유리", 2:"종이", 3:"캔", 4:"천", 5:"기타"}

let sttList = {900: "결제 완료", 910: "배송 준비 중", 920: "배송 중", 930: "배송 완료" 
		, 940: "구매 확정", 950: "거래 완료", 960: "환불", 970: "반품", 980: "취소"}



	$(function() {
		$("#del_btn").click(function() {
			var arr = new Array();
			$('input:checkbox[name=checkList]').each(function () {
		        if($(this).is(":checked")==true){
		        	let res = $(this).val();
		        	arr.push(res);
		        }
		    });
			
			// 체크된 상품이 없을 때 알림
			if(arr.length == 0){
				alert("선택된 상품이 없습니다.");
			}else{
				$.ajax({
					type: "post"
					, url: "./cydel"
					, data: {
						arr: arr 
					}
					, dataType : "Json"
					, success: function(res) {
						console.log("AJAX 성공");
						
						location.href="./upcylist";
						
						alert("상품이 삭제되었습니다.");
					}
					, error: function() {
						console.log("AJAX 실패");
					}
				}) 
			}
		    console.log(arr);
		}); // #dlt_btn click end
		
		
		$(".updateSttBtn").click(function(e){
			
			//버튼의 주문 상태
			var sttNo = $(this).attr('id');
			var arr = new Array();
			var flag = true;
			$('input:checkbox[name=ordCheckList]').each(function () {
		        if($(this).is(":checked")==true){
		        	if($(this).val() == sttNo){
		        		alert("선택된 주문의 주문 상태를 확인 해주세요.");
		        		flag = false;
		        		return false;
		        	}
		        	let res = $(this).attr('id');
		        	arr.push(res);
		        }
		    });
			
			//주문상태가 같을 때
			if(!flag){
				return false;
			}
			// 체크된 상품이 없을 때 알림
			if(arr.length == 0){
				alert("선택된 상품이 없습니다.");
			}else{
				$.ajax({
					type: "get"
					, url: "./updatestt"
					, data: {
						arr: arr
						, sttNo: sttNo
					}
					, dataType : "Json"
					, success: function(res) {
						console.log("AJAX 성공");
						
						location.href="./upcylist";
						
						alert("주문"+res.Msg +"되었습니다.");
					}
					, error: function() {
						console.log("AJAX 실패");
					}
				}) 
			}
		}); //cencelBtn 클릭
		
		
	}); //$ end

</script>

</head>
<body>

<h1>새활용 상품 관리</h1>

<table border="1">
    <thead>
        <tr>
            <th></th>
            <th>상품 번호</th>
            <th>제품 분류</th>
            <th>상품 이름</th>
            <th>재고</th>
            <th>가격</th>
            <th>등록일</th>
            <th>조회수</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${plist}" var="prd">
            <tr>
                <td>
                	<input type="checkbox" class="checkList" name="checkList" value="${prd.prdCode }">
                </td>
                <td>${prd.prdCode}</td>
                <td>
                	<script>document.write(pdtList[${prd.ctPdtNo}])</script>
                </td>
                <td><a href="./upcydetail?prdCode=${prd.prdCode}">${prd.prdName}</a></td>
                <td>${prd.prdCnt}</td>
                <td>${prd.price}</td>
                <td>
                	<fmt:parseDate value="${prd.prdDate}" var="prdDate" pattern="yyyy-MM-dd HH:mm:ss" />
               		<fmt:formatDate value="${prdDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td>${prd.prdHit}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<button id="del_btn">삭제하기</button>


<h1>새활용 판매 관리</h1>
<div>
	<button class="updateSttBtn" id="900">결제 완료</button>
	<button class="updateSttBtn" id="910">배송 대기</button>
	<button class="updateSttBtn" id="920">배송중</button>
	<button class="updateSttBtn" id="940">구매 확정</button>
	<button class="updateSttBtn" id="970">반품</button>
	<button class="updateSttBtn" id="980">취소</button>
</div>
<table border="1">
	<thead>
		<tr>
			<th></th>
			<th>주문번호</th>
			<th>상품 이름</th>
			<th>가격</th>
			<th>총금액</th>
			<th>주문일</th>
			<th>주문 상태</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="ord" items="${olist }">
		<tr>
			<td>
            	<input type="checkbox" class="ordCheckList" name="ordCheckList" id="${ord.orddtCode }" value="${ord.sttNo}">
            </td>
	 		<td>${ord.orddtCode }</td>
	 		<td>${ord.ordName }</td>
	 		<td>${ord.ordPrice }</td>
	 		<td>${ord.ordSum }</td>
	 		<td>
            	<fmt:parseDate value="${ord.ordDate}" var="ordDate" pattern="yyyy-MM-dd HH:mm:ss" />
           		<fmt:formatDate value="${ordDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
	 		<td id="sttNo"><script>document.write(sttList[${ord.sttNo}])</script></td>
	 	</tr>
	</c:forEach>
	</tbody>
</table>

</body>
</html>