<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title> Personal tasks helper </title>
	<meta name="keywords" content="J2EE SpringMVC, jQuery, AJAX, chat" />
	<script src="resources/js/jquery-1.9.1.js"></script>
    <link rel="stylesheet" href="resources/css/style.css"/>
</head>
<body>
<!--start Header-->
<div class="header">
<%@include file="header.jsp"%>
</div>
<!--end Header-->
<!--start Content layer-->
<div class="logo-style"><img src='resources/img/logo.png' title="Space chat" class="element80">
</div>
<div class="polling-switch"><p><img src='resources/img/fishing-start-empty.png' class="element90" id="fishing-start">Pooling<img src='resources/img/fishing-end-empty.png' class="element90" id="fishing-end"></p><img src='resources/img/short-polling.png' class="element90" id="polling-img" onClick="changePolling()">
</div>
<div class="content">
<c:if test = "${!empty usr}">
<div class="content-chat-wr">
<div class="content-chat" id="displayMessages">
</div>
<div class="content-bottom-chat">
<div class="content-bottom-chat-left">
<div class="content-bottom-chat-left-msgform">
<form:form method="GET" action="#" commandName="messages" id="sendMsgForm">
        <input type="text" name="msg" value="" id="msgField" />
        <button type="submit">Submit</button>
</form:form>
</div>
<textarea id="messageArea"></textarea>
</div>
<div class="content-bottom-chat-right-wr">
<div class="content-bottom-chat-right">
<div class="content-bottom-chat-right-button">
<img src="resources/img/btn-add.png" onmouseover="this.src='resources/img/btn-add-pr.png'" onmouseout="this.src='resources/img/btn-add.png'" onclick="sendNewMessage()" class="element80" style="cursor:pointer" >
</div>
</div>
</div>
</div>
</div>
</c:if>
</div>
<!--end Content layer-->
<div class="bottom-logo-wr">
  <div class="bottom-logo">
<img src="resources/img/bottom-logo.png" class="element30"/>
  </div>
</div>
<!--start Footer-->
<div class="footer">
<%@include file="footer.jsp"%>
</div>
<!--end Footer-->
<script src="resources/js/basic.js"></script>
</body>
</html>