<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<title>공지사항</title>

<style type="text/css">
.wrap {
	width: 1000px;
	margin: auto;
}

thead{
	text-align: center;
}

td, th {
	border-right: 1px solid #444444;
	height: 50px;
}

a {
	text-decoration: none;
	color: #444444;
}
</style>
</head>
<body>
<div class="wrap">
<h1>공지사항</h1>
    	<div class="search">
            <form action="./noticelist" method="get">
                <input type="hidden" name="sCtg" value="UP">
                <input type="text" id="uppersearch" name="search" placeholder="검색어를 입력해주세요." class="search">
                <button>검색</button>
            </form>
     	</div>
    <table border="1" class="table table-hover table-sm" style="width: 1000px; border: 2px solid #444444;">
    
    	<colgroup>
		<col style="width: 15%;">
		<col style="width: 55%;">
		<col style="width: 10%;">
		<col style="width: 20%;">
	</colgroup>
	
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>조회수</th>
                <th>등록일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${noticeList}" var="notice">
                <tr>
                    <td>${notice.ntcCode}</td>
                    <td><a href="./noticedetail?ntcCode=${notice.ntcCode}">${notice.ntcTitle}</a></td>
                    <td>${notice.ntcHit}</td>
                    <td>${notice.ntcDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:import url="/WEB-INF/views/layout/upperpaging.jsp"/>
</div>
</body>
</html>