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
	<title>管理员详细表</title>
	<link rel="stylesheet" type="text/css" href="../../css/all.css" />
	<link rel="stylesheet" type="text/css" href="../css/base.css" />
	<script src="../../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../js/input_setting.js" type="text/javascript" charset="UTF-8"></script>
	<script src="../../js/id_vaild.js" type="text/javascript" charset="UTF-8"></script>
	<script>
		$().ready(function(){
			$("input").attr("readonly","true");
			
			$("#update_user").click(function(){
				if($(this).val() == "修改"){
					$("form").attr("action", "javascript:void(0);");
					$("input").removeAttr("readonly");
					$("[name='authority_level']").attr("readonly","true");
					$(this).val("保存");
				}else{
					var flag = false;
					$(this).val("修改");
					$(".nonull").each(function(){
						if($(this).val() == ""){
							$("form").attr("action","javascript:void(0);");
							flag = true;
						}else{
							flag = false;
							$("form").attr("action", "<%=basePath %>/servlet/user");
							
							$("input").attr("readonly","true");
						}
					});
					if(flag){
						alert("带*的选项不能为空");
					}
				}
			});
		});
	</script>
	<style type="text/css">
			body {
				text-align: center;
			}

			div {
				margin: auto;
				width: 1000px;
			}

			table {
				width: 1000px;
				border: 1px solid black;
			}
			table input{
				width: 99%;
				height: 99%;
			}
			td {
				height: 40px;
				border: 1px solid black;
			}
			#update_user{
				float: right;
				margin-top: 10px;
				width: 80px;
				height: 40px;
			}
		</style>
</head>
<body>
		<h1>管理员详细信息表 <c:out value="${requestScope.message }"></c:out></h1>
		<div>
			<form action="<%=basePath %>/servlet/user" method="post">
				<input type="hidden" name="func" value="updateUser"/>
				<table>
					<tr>
						<td>身份证</td>
						<td><input type="text" name="id" value="<c:out value="${requestScope.user.id }"></c:out>" class="nonull"/></td>
						<td>账号</td>
						<td><input type="text" name="account" value="<c:out value="${requestScope.user.account }"></c:out>" class="nonull"/></td>
					</tr>
					<tr>
						<td>权限</td>
						<td><input type="text" name="authority_level" value="<c:out value="${requestScope.user.authority_level }"></c:out>" class="nonull"/></td>
						<td>密码</td>
						<td><input type="password" name="passwd" value="<c:out value="${requestScope.user.passwd }"></c:out>" class="nonull"/></td>
					</tr>
				</table>

				<input type="submit" name="update_user" id="update_user" value="修改" />
			</form>
		</div>
</body>
</html>