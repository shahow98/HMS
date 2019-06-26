<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>/HMS/admin/admin_manage/" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="login">
	<meta http-equiv="description" content="This is login page">
	<meta http-equiv="content-Type" content="text/html; charset=utf-8">
	<title>管理员搜索表</title>
	<link rel="stylesheet" type="text/css" href="../../css/all.css" />
	<link rel="stylesheet" type="text/css" href="../css/base.css" />
	<script src="../../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script>
			$(document).ready(function(){
						$("tbody tr").hover(function(){
							$(this).css("background-color", "rgb(212,212,212)");
						},function(){
							$(this).css("background-color", "white");
						});
					});
				</script>
		<style type="text/css">
			#result {
				text-align: center;
			}

			table {
				margin: auto;
				width: 1000px;
				border: 1px solid black;
			}

			th,
			td {
				border: 1px solid black;
			}
		</style>
</head>
<body>
		<div id="result">
			<h1>搜索结果</h1>
			<table>
				<thead>
					<tr>
						<th>账号</th>
						<th>身份证</th>
						<th>权限</th>
						<!--还可以写-->
						<th>详细信息</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${requestScope.users }">
						<tr>
							<td>${user.account }</td>
							<td>${user.id }</td>
							<td>${user.authority_level }</td>
							<td><a href="<%=basePath %>/servlet/user?func=searchUserByAccount&account=${user.account }">详细信息</a></td>
							<td><a href="javascript:void(0);">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
</body>
</html>