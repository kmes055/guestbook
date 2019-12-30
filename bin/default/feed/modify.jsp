<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guest Book</title>
</head>
<body>
<jsp:include page='Header.jsp'/>
<h1>게시글 수정</h1>
<form action='modify.do' method='post'>
<br><textarea name='content' style='width:600px;height:200px;'></textarea><br>
<input type='hidden' name='fno' value='${feed.fno}' />
<input type='submit' value='완료' />
</form>
</body>
</html>