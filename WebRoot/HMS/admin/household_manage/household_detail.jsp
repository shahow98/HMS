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
	<base href="<%=basePath %>/HMS/admin/household_manage/" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="login">
	<meta http-equiv="description" content="This is login page">
	<meta http-equiv="content-Type" content="text/html; charset=utf-8">
	<title>家庭户口详细表</title>
	<link rel="stylesheet" type="text/css" href="../../css/all.css" />
	<link rel="stylesheet" type="text/css" href="../css/base.css" />
	<script src="../../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../js/input_setting.js" type="text/javascript" charset="UTF-8"></script>
	<script src="../../js/province.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../js/city.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../js/area.js" type="text/javascript" charset="utf-8"></script>
	<script src="../../js/address.js" type="text/javascript" charset="utf-8"></script>
	<script>
			$().ready(function(){
				$("#update input").attr("readonly","ture");
				
				$("#update_household").click(function(){
					var param = $("[name='personal_id']").val();
					window.location.href = "add_housemember.html?personal_id="+encodeURI(param);
				});
					$("#add_address").click(function(){
							var flag = false;
							$(".nonull").each(function(){
								if($(this).val() == ""){
									$("form").attr("action","javascript:void(0);");
									flag = true;
								}
							});
							if(flag){
								alert("带*的选项不能为空");
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

			h1 em {
				color: #FF0000;
			}
			#update input{
				width: 99%;
				height: 100%;
			}
			/**/
			form input {
				width: 99%;
				height: 100%;
			}

			table {
				width: 1000px;
				border: 1px solid black;
			}

			td {
				height: 40px;
				border: 1px solid black;
			}

			#update_household {
				float: right;
				margin-top: 10px;
				width: 80px;
				height: 40px;
			}

			#address em {
				color: red;
			}

			#add_address {
				float: right;
				margin-top: 10px;
				width: 80px;
				height: 40px;
			}
		</style>
</head>
<body>
		<h1>家庭户口详细表：<em><c:out value="${requestScope.message }"></c:out></em></h1>
		<h1>户主身份证：<c:out value="${requestScope.personal_id }"></c:out>&nbsp;&nbsp;户主名称：<c:out value="${requestScope.personal_name }"></c:out></h1>
		<input type="hidden" name="personal_id" value="<c:out value="${requestScope.personal_id }"></c:out>" />
		<div id="update">
				<table>
					<!--添加户口成员-->
					<c:set var="member_count" value="0"></c:set>
					<c:forEach var="member" items="${requestScope.members }">
						<tr>
							<td>身份证</td>
							<td><input type="text" name="member_id${member_count} " value="${member.id }" /></td>
							<td>姓名</td>
							<td><input type="text" name="member_name${member_count}" value="${member.name }" /></td>
							<td>
								<a href="<%=basePath %>/servlet/householdmanage?func=deleteHouseholdMember&personal_id=<c:out value="${requestScope.personal_id }"></c:out>&member_id=${member.id }">删除</a>
							</td>
						</tr>
						<c:set var="member_count" value="${member_count+1 }"></c:set>
					</c:forEach>
				</table>
				<table>
					<c:set var="address_count" value="0"></c:set>
					<c:forEach var="address" items="${requestScope.addresses }">
						<tr>
							<td>居住地址</td>
							<td><input type="text" name="address_name${address_count}" value="${address.toString() }" /></td>
							<td>迁入时间</td>
							<td><input type="text" name="immigratory_date${address_count}" value="${address.immigratory_date }" /></td>
							<td>迁出时间</td>
							<td><input type="text" name="evacuation_date${address_count}" value="${address.evacuation_date }" /></td>
							<td>
								<a href="javascript:void(0);">删除</a>
							</td>
						</tr>
						<c:set var="address_count" value="${address_count+1 }"></c:set>
					</c:forEach>
				</table>

				<button type="button" id="update_household">添加成员</button>
		</div>
		<div>
			<form action="<%=basePath %>/servlet/householdmanage" method="post" id="address">
				<input type="hidden" name="func" value="addAddress" />
				<input type="hidden" name="category" value="household" />
				<input type="hidden" name="iden" value="<c:out value="${requestScope.personal_id }"></c:out>" />
				<table>
					<tr>
						<td>省<em>*</em></td>
						<td><input list="province" name="province" class="nonull" />
							<datalist id="province">
							</datalist>
						</td>
						<td>市<em>*</em></td>
						<td><input list="city" name="city" class="nonull" />
							<datalist id="city">
							</datalist>
						</td>
						<td>区/县<em>*</em></td>
						<td><input list="district" name="district" class="nonull" />
							<datalist id="district">
							</datalist>
						</td>
						<td>镇/乡</td>
						<td><input type="text" name="town" id="town" /></td>
						<td>村/小区</td>
						<td><input type="text" name="village" id="village" /></td>
						<td>补充</td>
						<td><input type="text" name="expand" id="expand" /></td>
					</tr>
				</table>
				<input type="submit" name="add_address" id="add_address" value="添加地址"/>
			</form>
		</div>
</body>
</html>