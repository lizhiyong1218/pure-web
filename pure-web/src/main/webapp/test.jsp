<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/jquery-easyui-1.3.4/demo.css">
	<script type="text/javascript" src="<%=basePath %>resources/js/jquery-1.9.0.min.js"></script> 
	<script type="text/javascript" src="<%=basePath %>resources/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>  
<script type="text/javascript" src="<%=basePath %>resources/js/demo/demo.js"></script>
<title>Insert title here</title>
</head>
<body>
	<table id="productSearchList" ></table>
</body>
</html>