<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
<form action='upload.do' method='post'>
Email: <input type='text' name='email' style='width: 200px;'>
 Password: <input type='password' name='pwd' style='width: 100px;'>
 <br><textarea name='content' style='width:600px;height:200px;'></textarea><br>
<input type='hidden' value=${fno} />
<input type='submit' value='글쓰기' />
</form></div>