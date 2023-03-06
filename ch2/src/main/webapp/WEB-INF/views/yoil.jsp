<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>year=<%=request.getParameter("year") %></h1>
<P> ${year }년 ${month }월 ${day }일은 ${yoil }요일 입니다. </P>
</body>
</html>
