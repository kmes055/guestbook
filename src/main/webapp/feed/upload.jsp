<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
$("#email").on("change keyup paste", function(){
	if (!emailChecker.emailTest(feed.getEmail())) {
		errorMessage = "이메일 형식이 잘못됐습니다";
	}else if (feed.getPwd().length() < 4){
		errorMessage = "패스워드는 4자 이상이어야 합니다";
	}else if (feed.getContent().equals("")) {
		errorMessage = "내용을 1자 이상 입력해주세요";
	}
})
</script>
<div>
	<form action='upload.do' method='post'>
		Email: <input type='text' name='email' style='width: 200px;'>
		Password: <input type='password' name='pwd' style='width: 100px;'>
		<br><textarea name='content' style='width: 600px; height: 200px;'></textarea><br> 
		<input type='hidden' value=${fno } /> 
		<button id='uploadFeed'>게시</button>
		<input type='submit' value='게시' /> ${errorMessage}
	</form>
</div>