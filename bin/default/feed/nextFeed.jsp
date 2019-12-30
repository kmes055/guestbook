<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<div>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:forEach var='feed' items='${feeds}'>
<br><br><div style='width:1000px;'>
No. ${feed.fno}<br>
Email: ${feed.email}<br>
${fn:replace(feed.content, newLine, "<br>")}
<br><br>
<form action='check' method='get'>
<button type='button' onclick="location.href='check.do?fno=${feed.fno}'">수정</button>
</form></div>
</c:forEach>
</div>