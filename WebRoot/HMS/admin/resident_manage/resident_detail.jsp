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
	<title>居民详细信息表</title>
	<link rel="stylesheet" type="text/css" href="../../css/all.css" />
	<link rel="stylesheet" type="text/css" href="../css/base.css" />
	<script src="../../js/jquery-3.3.1.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script>
	$().ready(function(){
		for(var i=0; i<260; i++)
			$("#age").append("<option value='"+i+"'>");
		$("[name='id']").keypress(function(){
			var kcount = $(this).val().length;
			if(kcount > 17 || (event.keyCode < 48 || event.keyCode > 57)){
				if(kcount == 17 && event.keyCode == 88)
					return true;
				else if(kcount == 17 && event.keyCode == 120)
					alert("请最后一位输入写X");
				return false;
			}
		});
		$("[name='age']").keypress(function(){
			var kcount = $(this).val().length;
			if(kcount > 2){
				return false;
			}
		});
		$("#update_resident").click(function(){
			if($(this).val() == "修改"){
				$("form").attr("action", "javascript:void(0);");
				$("input").removeAttr("readonly");
				$(this).val("保存");
			}else{
				var flag = false;
				$(".nonull").each(function(){
					if($(this).val() == ""){
						$("form").attr("action","javascript:void(0);");
						flag = true;
					}
				});
				if(flag){
					alert("带*的选项不能为空");
				}else{
					$("form").attr("action", "javascript:void(0);");
					$(this).val("修改");
					$("input").attr("readonly","true");
				}
			}
		});
	});
	</script>
	<style type="text/css">
		body {
				text-align: center;
			}
			form{
				margin: auto;
				width: 1000px;
			}
			table{
				width: 1000px;
				border: 1px solid black;
			}
			td{
				height: 40px;
				border: 1px solid black;
			}
			form input{
				width: 99%;
				height: 99%;
			}
			#update_resident{
				float: right;
				margin-top: 10px;
				margin-right: 10px;
				width: 80px;
				height: 40px;
			}
			#reset{
				float: right;
				margin-top: 10px;
				width: 80px;
				height: 40px;
			}
	</style>
</head>
<body>
	<h1>居民详细信息表：<em><c:out value="${message}"></c:out></em></h1>
	<div>
		<form action="<%=basePath %>/servlet/resident" method="post">
		<input type="hidden" name="func" value="updateResident" />
		<table>
			<tr>
				<td>身份证</td>
				<td><input type="text" name="id" value="<c:out value="${resident.id }"></c:out>" class="nonull"/></td>
				<td>姓名</td>
				<td><input type="text" name="name" value="<c:out value="${resident.name }"></c:out>" class="nonull"/></td>
			</tr>
			<tr>
				<td>性别</td>
				<td><input list="sex" name="sex" value="<c:if test="${resident.sex == 1 }">男</c:if><c:if test="${resident.sex == 0 }">女</c:if>" class="nonull" />
						<datalist id="sex">
						   <option value="男"></option>
						   <option value="女"></option>
						</datalist>
				</td>
				<td>年龄</td>
				<td><input list="age" name="age" value="<c:out value="${resident.age }"></c:out>" class="nonull" />
						<datalist id="age">
						</datalist>
				</td>
			</tr>
			<tr>
				<td>出生日期</td>
				<td><input type="text" name="birthday" value="<c:out value="${resident.birthday }"></c:out>" class="nonull"/></td>
				<td>曾用名</td>
				<td><input type="text" name="former_name" value="<c:out value="${resident.former_name }"></c:out>"/></td>
			</tr>
			<tr>
				<td>宗教信仰</td>
				<td><input type="text" name="religion" value="<c:out value="${resident.religion }"></c:out>"/></td>
				<td>民族</td>
				<td><input type="text" name="national" value="<c:out value="${resident.national }"></c:out>"/></td>
			</tr>
			<tr>
				<td>身高</td>
				<td><input type="text" name="stature" value="<c:out value="${resident.stature }"></c:out>"/></td>
				<td>受教育情况</td>
				<td><input type="text" name="education" value="<c:out value="${resident.education }"></c:out>"/></td>
			</tr>
			<tr>
				<td>手机号</td>
				<td><input type="text" name="phone" value="<c:out value="${resident.phone }"></c:out>"/></td>
				<td>......</td>
				<td><input type="text" name="xxx"/></td>
			</tr>
		</table>
			<input type="submit" name="update_resident" id="update_resident"
				value="修改" />
		</form>
	</div>
</body>
</html>