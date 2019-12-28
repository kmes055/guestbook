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
비밀번호를 입력해주세요
<form action='check.do' method='post'>
Password: <input type='password' name='passwd' style='width: 100px;'/>
<input type='hidden' name='fno' value='${fno}' />
<input type='submit' value='확인' />  ${wrongMessage} <br>
</form>
</body>
</html>