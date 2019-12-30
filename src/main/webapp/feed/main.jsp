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
<h1>게시글 목록</h1>
새 글을 올려주세요<br>
<jsp:include page='upload.jsp'/><br>
<jsp:include page='nextFeed.jsp'/>
</body>
</html>



<!-- <script>
 var page = 1;

$(window).scroll(function() {
    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
      $("#enters").append("");
    }
});
 */
/* $(document).ready(function() {
    var win = $(window);

    // Each time the user scrolls
    win.scroll(function() {
        // End of the document reached?
        if ($(document).height() - win.height() == win.scrollTop()) {
            $('#loading').show();

            $.ajax({
                url: 'get-post.aspx',
                dataType: 'html',
                success: function(html) {
                    $('#posts').append(html);
                    $('#loading').hide();
                }
            });
        }
    });
}); 
</script> -->