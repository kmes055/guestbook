<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div>
<c:forEach var='feed' items='${feeds }'>
<br><br><div style='width:1000px;'>
No. ${feed.fno}<br>
Email: ${feed.email}<br>
${feed.content}<br><br>
<form action='check' method='get'>
<button type='button' onclick="location.href='check?fno=${feed.fno}'">수정</button>
</form></div>
</c:forEach>
</div>