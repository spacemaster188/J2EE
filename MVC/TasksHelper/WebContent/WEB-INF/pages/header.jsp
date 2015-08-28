<div class="vladmaxi-top"><c:set var="user_var" scope="session" value="${sessionScope.usr}" />
<c:if test = "${user_var!=null}">
<span style="float:left; font-weight:bold; padding-left:8px">User: <c:out value="${user_var.login}" /></span> <span style="float:right; font-weight:bold; padding-right:20px"><a href="logout.do">Logout</a></span>
</c:if><c:if test = "${user_var==null}">
<span style="float:left; font-weight:bold;padding-left:8px"> User: Guest </span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="get_login_form()" > Login</a>&nbsp;&nbsp;&nbsp;<a href="#" onclick="get_registration_form()"> Registration</a></c:if>
</div>