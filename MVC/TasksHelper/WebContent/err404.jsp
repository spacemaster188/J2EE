<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="4; url=index.jsp">
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title> Personal tasks helper Error</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/scripts.js"></script>
</head>
<body>
 <div class="vladmaxi-top">
<!--start Header-->
<div class="vladmaxi-top"><c:set var="user_var" scope="session" value="${sessionScope.usr}" />
<c:if test = "${user_var!=null}">
<span style="float:left; font-weight:bold; padding-left:8px">User: <c:out value="${user_var.login}" /></span> <span style="float:right; font-weight:bold; padding-right:20px"><a href="logout.do">Logout</a></span>
</c:if><c:if test = "${user_var==null}">
<span style="float:left; font-weight:bold;padding-left:8px">User: Guest</span></c:if>
</div>
</div>
<!--end Header-->
<div style="height:35px"></div>
<span style="float:left;padding-left:25px" ><img src='img/logo.png' title="Task assistant" class="element90"></span>
<div style="height:200px"></div>
<div align="center" style="width:80%;">
<span style="font-family:Tahoma, Geneva, sans-serif;font-size:30px; font-weight:bold; color:#CCC; text-align:center">Sorry. Requested page is not found</span>
</div>
<div class="vladmaxi-bottom"><a href="mailto:limitvadim@ya.ru">by Salai Vadzim &copy;</a></div>
</body>
</html>