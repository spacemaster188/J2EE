<c:if test = "${!empty usr}">
<span style="float:left; font-weight:bold; padding-left:40px"><a href="#"><spring:message code="label.user" /> <c:out value="${usr.login}" /></a></span> <span style="float:right; font-weight:bold; padding-right:40px"><a href="logout"><spring:message code="label.logout" /></a></span>
</c:if>
<c:if test = "${empty usr}">
<div class="header">
<span style="float:left; font-weight:bold;padding-left:40px"><a href="#">User: There's no user in context!</a></span style="float:right; font-weight:bold;padding-right:60px"><a href="login" > <spring:message code="label.login" /></a></div>
</c:if>