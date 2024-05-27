<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="/WEB-INF/views/layout/manager/managerhrmenu.jsp"/>
<c:import url="/WEB-INF/views/layout/manager/managerslsmenu.jsp"/>
<c:import url="/WEB-INF/views/layout/manager/managercsmenu.jsp"/>
<sec:authorize access="hasAnyRole('ROLE_MANAGER')">
	<button><a href="./logout">로그아웃</a></button>
</sec:authorize>
</body>
</html>
