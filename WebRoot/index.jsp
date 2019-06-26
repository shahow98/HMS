<%@page import="com.general.util.HibernateSessionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
System.out.println(basePath);
HibernateSessionFactory.getSession();
HibernateSessionFactory.closeSession();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="index">
		<meta http-equiv="description" content="This is welcome page">
		<meta http-equiv="content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="Refresh" content="0;url=<%=basePath %>/HMS/login.jsp" />
        <title>Basic Struts 2 Application - Welcome</title>
    </head>
    <body>
        <h1>Welcome To Struts 2!</h1>
        <p>Hello World</p>
        <a href="<%=basePath %>/HMS/login.jsp">login</a>
    </body>
</html>