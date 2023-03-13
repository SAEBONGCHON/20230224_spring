<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>board insert</h1>
<!-- 파일첨부 : encType -->
<form action="insert" method="post" enctype="multipart/form-data">
	<input type="text" name="boardTitle" placeholder="제목"><br>
	<input type="text" name="boardContent" placeholder="내용"><br>
	<!-- 파일의 경우 name은 vo와 다른 이름으로 해야한다. 이름이 같으면 파싱 실패함 -->
	<input type="file" name="report" placeholder="첨부파일" ><br>
	
	<input type="file" name="report" placeholder="첨부파일" multiple="multiple"><br>
	<button>+</button>
	<button type="submit">게시글 등록</button>
</form>
</body>
</html>