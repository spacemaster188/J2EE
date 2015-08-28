<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SocialTrendy Welcome page</title>
<link rel=stylesheet type=text/css href="css/style.css">
<link rel=stylesheet type=text/css href=css/home.css>
<script type='text/javascript' src='js/jquery-1.9.1.min.js'></script>
<script type="text/javascript" src="js/scripts.js"></script>
<script type="text/javascript">
	setTimeout(function(){$('.reportbox').fadeOut('fast')},3000);
</script>
</head>
<body>
<!--start Report layer-->
<div class="reportbox">
	<c:out value="${sessionScope.report}" />
</div>
<!--end Report layer-->
	<form method="POST" action="login.do" class="box login" id="logForm"> 
		<fieldset class="boxBody">
			 E-mail:<br>
			 <input type="text" name="e-mail" /> <br>
		     Password: <br>
			<input type="text" name="pass" /> <br>		
		</fieldset>
		<footer>
		<label><input type="checkbox" name="remember" tabindex="3">Keep me logged in</label>
		<input type="submit" class="btnLogin" value="Login" tabindex="4"><br><br>
		<span style="font-size:14px; font-weight:bold"><a href="#" onclick="get_registration_form()"> Register new accont</a></span>
		</footer>
	</form>
<!--start Registration layer-->
<div align="center" style="display:none" id="regForm">
			<form method="POST" action="registration.do" class="box login2"><br><span style="color:#F00; font-size:16px;"></span><br>
	<p style="font-size:16px">Please complete the form below. Required fields<em>*</em></p>
	<fieldset class="boxBody">
		<legend><h4> Personal Detail Information </h4></legend>
 FirstName&nbsp;  
			  <input type="text" name="firstname" value="" size="28"/>
 LastName&nbsp; <input type="text" name="lastname" value="" size="28"/>
 E-mail <span style="color:red">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="e-mail" size="28" />
 Password <span style="color:red">*</span><input type="text" name="password" size="28"/>
 Country&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="country" value="" size="28"/><br>
	</fieldset>
     <footer>
					<input type="radio" name="gender" value="true" checked /> male
					<input type="radio" name="gender" value="false" /> female		
	<p><input type="submit" name="butt" value="Register" /></p>
    <p><span style="font-size:14px; font-weight:bold"><a href="#" onclick="get_login_form()"> Already have a login?</a></span></p><br>
    </footer>
</form>
</div>
<!--end Registration layer-->
</body>
</html>