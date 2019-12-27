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
<h1>게시글 목록</h1>

<!--
Infinite Scroll!? 
<script>
$(window).scroll(function() {
    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
      console.log(++page);
      $("#enters").append("MY_HTML");
      
    }
});
</script> -->
새 글을 올려주세요<br>
<jsp:include page="upload.jsp"/>
<%
ArrayList<Feed> feeds = (ArrayList<Feed>)request.getAttribute("feeds");
for(Feed feed : feeds) {
%>
<br><div style='width:1000px;'>
No. <%=feed.getFno()%><br>
Email: <%=feed.getEmail() %><br>
<%=feed.getContent() %><br>
<form action='check' method='get'>
<button type='button' onclick="location.href='check?fno=<%=feed.getFno()%>'">수정</button>
</form></div>
<% } %>
</body>
</html>