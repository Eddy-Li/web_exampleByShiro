<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
<h3>用户登录</h3>
<span style="color: red;">${errorMsg}</span>
<form method="post" action="/login">
<p><input type="text" name="username" value="" placeholder="账号"></p>
<p><input type="password" name="password" value="" placeholder="密码"></p>
<input type="submit" value="登录">
</form>
</body>
</html>