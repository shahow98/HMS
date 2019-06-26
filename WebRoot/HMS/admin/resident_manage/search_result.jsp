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
	<base href="<%=basePath %>/HMS/resident_manage/" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="search">
	<meta http-equiv="description" content="This is search result page">
	<meta http-equiv="content-Type" content="text/html; charset=utf-8">
	<title>居民搜索表</title>
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
		
		h1 em {
			color: #FF0000;
		}
		
		th, td {
			border: 1px solid black;
		}
	</style>
</head>
<body>
	<div id="result">
			<h1>搜索结果：<em><c:out value="${message}"></c:out></em></h1>
			<table>
				<thead>
					<tr>
						<th>身份证</th>
						<th>姓名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>出生日期</th>
						<!--还可以写-->
						<th>详细信息</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="resident" items="${ requestScope.residents }">
					<tr>
						<td>${resident.id} </td>
						<td>${resident.name} </td>
						<c:if test="${resident.sex == 1}"><td>男</td></c:if>
						<c:if test="${resident.sex == 0}"><td>女</td></c:if>
						<td>${resident.age} </td>
						<td>${resident.birthday} </td>
						<td><a href="<%=basePath %>/servlet/resident?func=searchResidentById&id=${resident.id}">详细信息</a></td>
						<td><a href="javascript:void(0);">删除</a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
</body>
</html>