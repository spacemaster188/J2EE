<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; UTF-8">
    <title><spring:message code="label.registerTitle" /></title>
    <link rel="stylesheet" href="resources/css/login.css"/>
</head>
<body>

<form:form commandName="users" method="POST" action="getRegister" id="registrationForm">
<div class="field">
        <label><spring:message code="label.loginDot" /></label>
        <div class="input"><input type="text" name="login" value="" id="login" /></div>
</div>
<div class="field">
        <label><spring:message code="label.passwordDot" /></label>
        <div class="input"><input type="text" name="password" value="" id="password" /></div>
</div>
<div class="field">
        <label><spring:message code="label.email" /></label>
        <div class="input"><input type="text" name="email" value="" id="email" /></div>
</div>
<div class="field">
        <label><spring:message code="label.firstname" /></label>
        <div class="input"><input type="text" name="firstname" value="" id="firstname" /></div>
</div>
<div class="field">
        <label><spring:message code="label.lastname" /></label>
        <div class="input"><input type="text" name="lastname" value="" id="lastname" /></div>
</div>
<br>
<div class="submit">
        <button type="submit"><spring:message code="label.rerister" /></button>
    </div>
<a href="login" ><spring:message code="label.goToLoginPage" /></a><br>
</form:form>

</body>
</html>