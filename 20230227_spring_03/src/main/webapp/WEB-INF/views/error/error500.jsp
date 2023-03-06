<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
</head>
<body>
<div>
${msg }
오류가 발생하였습니다.		 <br>
잠시 후 다시 시도해 주세요.	 <br>
<a href="<%=request.getContextPath()%>/">메인 메이지로 이동</a>
</div>

</body>
</html>