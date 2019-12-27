<%@ page import="java.util.ArrayList" %>
<%@ page import="spms.vo.Feed" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guest Book</title>
</head>
<body>
<h1>게시글 수정</h1>
<form action='modify' method='post'>
<br><textarea name='content' style='width:600px;height:200px;'></textarea><br>
<input type='submit' value='완료' />
</form>
</body>
</html>