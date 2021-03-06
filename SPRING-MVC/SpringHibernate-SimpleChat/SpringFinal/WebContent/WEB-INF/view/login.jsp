<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; UTF-8">
    <title><spring:message code="label.loginTitle" /></title>
    <link rel="stylesheet" href="resources/css/login.css"/>
</head>
<body>

<form:form commandName="users" id="loginForm" action="getLogin" method="POST">
    <div class="field">
        <label><spring:message code="label.loginDot" /></label>
        <div class="input"><input type="text" name="login" value="" id="login" /></div>
    </div>

    <div class="field">
    <a href="register" ><spring:message code="label.haveNoLogin" /></a>
         <label><spring:message code="label.passwordDot" /></label>
        <div class="input"><input type="password" name="password" value="" id="pass" /></div>
    </div>

    <div class="submit">
        <button type="submit"><spring:message code="label.login" /></button>
    </div>
</form:form>

</body>
</html>