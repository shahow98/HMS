<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>/HMS/" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="login">
	<meta http-equiv="description" content="This is login page">
	<meta http-equiv="content-Type" content="text/html; charset=utf-8">
	
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="css/all.css" />
	<link rel="stylesheet" type="text/css" href="css/login.css" />
	<script src="js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
			$(document).ready(function() {
				$("input").each(function(){
					$(this).attr("autocomplete","off");
				});
			});
	</script>
</head>
<body>
	<div id="main">
		<form action="<%=basePath %>/servlet/loginvaild" method="post">
			<input type="hidden" name="func" value="login" /> 
			<div id="form">
				<div class="inform">
					<p>
						账号：<input type="text" name="account" />
					</p>
				</div>
				<div id="account_msg">
					<em><c:out value="${message}"></c:out></em>
				</div>
				<div class="inform">
					<p>
						密码：<input type="password" name="passwd" />
					</p>
				</div>
				<div id="passwd_msg">
					<em><c:out value="${message}"></c:out></em>
				</div>
				<div>
					<p>
						<input type="radio" name="category" id="admin" value="admin" />管理员
						<input type="radio" name="category" id="resident" value="resident" checked="checked"/>居民
					</p>
				</div>
				<div id="submit">
					<input type="submit" value="登录" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>