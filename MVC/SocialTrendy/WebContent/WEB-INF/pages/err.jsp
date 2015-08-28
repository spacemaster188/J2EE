<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="refresh" content="4; url=login.jsp">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
      SocialTrendy | Social network error
    </title>
    <link href="css/error.css" media="all" rel="stylesheet" type="text/css">  
  </head>
  <body>
    <div class="wrapper error">
      <h1>
        <a href="#" title="Social Trendy">SocialTrendy</a>
      </h1>
      <h3>
        Social network
      </h3>
      <div class="dev-line">
        Oops .. something going wrong .. <c:out value="${sessionScope.err}"/>
      </div>
      <a href="#" title="Social Trendy" class="dev-logo">SocialTrendy</a>
    </div>
</body>
</html>