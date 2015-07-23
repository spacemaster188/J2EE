<c:if test = "${!empty usr}">
<span style="float:left; font-weight:bold; padding-left:40px"><a href="#">User: <c:out value="${usr.login}" /></a></span> <span style="float:right; font-weight:bold; padding-right:40px"><a href="logout">Logout</a></span>
</c:if>
<c:if test = "${empty usr}">
<div class="header">
<span style="float:left; font-weight:bold;padding-left:40px"><a href="#">User: There's no user in context!</a></span style="float:right; font-weight:bold;padding-right:60px"><a href="login" > Login</a></div>
</c:if>