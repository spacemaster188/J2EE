<%@page import="by.gsu.epamlab.utils.Constants"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="datetag" uri="http://javacore.jvmhost.net" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
	<title> Personal tasks helper </title>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/jquery.tabslideout.v1.2.js"></script>
    <script type="text/javascript" src="js/scripts.js"></script>
    <script type="text/javascript" src="js/calendar_ru.js"></script>
    <script type='text/javascript'>
 var $jq = jQuery.noConflict(true);  
    </script>
<script type="text/javascript" src="js/request_header.js"></script>
<script type="text/javascript" src="js/request_parameters.js"></script>
</head>
<body>
<!--start Header-->
<%@include file="WEB-INF/pages/header.jsp"  %>
<!--end Header-->
<!--start Main layer-->
<div style="height:45px"></div>
<span style="float:left;padding-left:25px" ><img src='img/logo.png' title="Task assistant" class="element90"></span><br><br>
<!--start Login form-->
<div style="display:none" id="authorization_form">
<form method="post" action="login.do" class="login">
    <p>
      <label for="login">Login</label>
      <input type="text" name="login" id="login" value="" placeholder="enter your login">
    </p>

    <p>
      <label for="password">Password</label>
      <input type="password" name="password" id="password" value="" placeholder="enter your pass">
    </p>

    <p class="login-submit">
      <button type="submit" class="login-button"></button>
    </p>
  </form>
</div>
<!--end Login form-->
<!--start Registration form-->
<div style="display:none" id="registration_form">
<form method="post" action="registration.do" class="login">
    <p>
      <label for="login">Login*</label>
      <input type="text" name="login" value="" placeholder="login">
    </p>
    <p>
      <label for="password">Password*</label>
      <input type="password" name="password" id="password" value="" placeholder="password">
    </p>
<p>
      <label for="login">Email</label>
      <input type="text" name="email" id="email" value="" placeholder="limitvadim@ya.ru">
    </p>
    <p>
      <label for="login">Firstname</label>
      <input type="text" name="firstname" id="firstname" value="" placeholder="Ivan">
    </p>
        <p>
      <label for="login">Lastname</label>
      <input type="text" name="lastname" id="lastname" value="" placeholder="Ivanovich">
    </p>
    <p class="login-submit">
      <button type="submit" class="login-button"></button>
    </p>
  </form>
</div>
<!--end Registration form-->
<c:if test = "${sessionScope.usr != null}">
<div align="center">
<span style="float:left; padding-left:11%">
<a href="#" onclick="get_fixed_form()"><img src='img/fixed.png' title="Get fixed list" height="65" class="element60"></a></span><span style="float:right; padding-right:28%"><a href="#" onclick="get_recycled_form()"><img src='img/recycle.png' title="Get recycled list" height="65" class="element60"></a></span>
<!--start Recycled layer-->
<div style="width:80%; padding-top:10px; display:none" id="recycledForm"><span class="daysClass">Recycled tasks&nbsp;&nbsp;</span><br>
<div class="taskDiv"><table width="98%" border="1px solid" bordercolor="#33CC99">
<c:forEach items="${sessionScope.recycled_list}" var="recycled">
<tr>
<td width="90%">
<div style="padding:3px">
<span class="dateStyle"><c:out value="${recycled.date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="tasks"><c:out value="${recycled.task}"/></span><span style="float:right">
<c:if test = "${recycled.file == ''}">
<a href="#" onclick="uploadFileAction(<c:out value="${recycled.taskId}" />)"><img src='img/file_upl.png' title="Upload new file" width="16" class="element90"></a>
</c:if>
<c:if test = "${recycled.file != ''}">
<a href="<%=Constants.FILE_PATH%>/<c:out value="${recycled.file}"/>" target="_blank" /><img src='img/file_downl.png' title="Download file" width="15" class="element90"></a><a href="#" onclick="uploadFileAction(<c:out value="${recycled.taskId}" />)"><img src='img/file_upl.png' title="Upload new file" width="16" class="element90"></a>
<a href="#" onclick="RemoveFileAction(<c:out value="${recycled.taskId}" />)"><img src='img/file_del.png' title="Remoove file" width="14" class="element90"></a>
</c:if>
<a href="#" onclick="recycleRestoreAction(<c:out value="${recycled.taskId}" />)"><img src='img/restore.png' title="Restore task" width="15" class="element80"></a>&nbsp;&nbsp;<a href="#" onclick="recycleRemoveAction(<c:out value="${recycled.taskId}" />)"><img src='img/del_blck.png' title="Delete" width="14" class="element90"></a></span>
</div>
</td>
</tr>
</c:forEach>
</table></div>
</div>
<!--end Recycled layer-->
<!--start Fixed layer-->
<div style="width:80%; padding-top:10px; display:none" id="fixedForm"><span class="daysClass">Fixed tasks&nbsp;&nbsp;</span><br>
<div class="taskDiv"><table width="98%" border="1px solid" bordercolor="#33CC99">
<c:forEach items="${sessionScope.fixed_list}" var="fixed">
<tr>
<td width="90%">
<div style="padding:3px">
<span class="dateStyle"><c:out value="${fixed.date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="tasks"><c:out value="${fixed.task}"/></span><span style="float:right">
<c:if test = "${fixed.file == ''}">
<a href="#" onclick="uploadFileAction(<c:out value="${fixed.taskId}" />)"><img src='img/file_upl.png' title="Upload new file" width="16" class="element90"></a>
</c:if>
<c:if test = "${fixed.file != ''}">
<a href="<%=Constants.FILE_PATH%>/<c:out value="${fixed.file}"/>" target="_blank" /><img src='img/file_downl.png' title="Download file" width="15" class="element90"></a><a href="#" onclick="uploadFileAction(<c:out value="${fixed.taskId}" />)"><img src='img/file_upl.png' title="Upload new file" width="16" class="element90"></a>
<a href="#" onclick="RemoveFileAction(<c:out value="${fixed.taskId}" />)"><img src='img/file_del.png' title="Remoove file" width="14" class="element90"></a>
</c:if>
<a href="#" onclick="fixedRemoveAction(<c:out value="${fixed.taskId}" />)"><img src='img/del_blck.png' title="Delete" width="14" class="element90"></a></span>
</div>
</td>
</tr>
</c:forEach>
</table></div>
</div>
<!--end Fixed layer-->
<div class="inv" id="upload_form">
<form action="upload.do" method="post" enctype="multipart/form-data">
<input type="hidden" id="upload_taskId_id" name="hdn_taskId" value="" />
<input type="text" value="" name="destination" placeholder="filepath"><br>
<input id="file" name="file" type="file"><br>
<input type="submit" value="upload" name="upload" id="upload"><br>
</form>
</div>
<div style="height:30px"></div>
<!--start Today layer-->
<div style="width:80%; padding-top:10px"><span class="daysClass">Today
<datetag:show when="today"/></span>&nbsp;&nbsp;
<a href="#" onclick="get_todayAdd_form()"><img src='img/add.png' title="Add new one" height="20" class="element60"></a><br>
<div align="center" style="padding-top:7px;display:none; padding-bottom:7px" id="todayForm">
<form id="add_today" action="addtask.do" method="post">
<input type="hidden" name="hdn_action_type" value="today" />
<input type="hidden" name="hdn_todo" value="add_task" />
<input class="textfield" name="task" value="" size="50" />
<input type="submit" value="Add task" ></form>
</div>
<div class="taskDiv"><table width="98%" border="1px" bordercolor="#33CC99">
<c:forEach items="${sessionScope.today_list}" var="today">
<tr>
<td width="90%">
<div style="padding:3px"><span class="dateStyle"><c:out value="${today.date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="tasks"><c:out value="${today.task}"/></span><span style="float:right">
<c:if test = "${today.file == ''}">
<a href="#" onclick="uploadFileAction(<c:out value="${today.taskId}" />)"><img src='img/file_upl.png' title="Upload new file" width="16" class="element90"></a>
</c:if>
<c:if test = "${today.file != ''}">
<a href="<%=Constants.FILE_PATH%>/<c:out value="${today.file}"/>" target="_blank" /><img src='img/file_downl.png' title="Download file" width="15" class="element90"></a><a href="#" onclick="uploadFileAction(<c:out value="${today.taskId}" />)"><img src='img/file_upl.png' title="Upload new file" width="16" class="element90"></a>
<a href="#" onclick="RemoveFileAction(<c:out value="${today.taskId}" />)"><img src='img/file_del.png' title="Remoove file" width="14" class="element90"></a>
</c:if>
<a href="#" onclick="todayFixAction(<c:out value="${today.taskId}" />)"><img src='img/fix.png' title="Fix task" width="14" class="element90"></a>&nbsp;&nbsp;<a href="#" onclick="todayRemoveAction(<c:out value="${today.taskId}" />)"><img src='img/del_blck.png' title="Delete" width="14" class="element90"></a></span>
</div>
</td>
</tr>
</c:forEach>
</table></div>
</div>
<!--end Today layer-->
<!--start Tomorrow layer-->
<div style="width:80%; padding-top:10px"><span class="daysClass">Tomorrow
<datetag:show when="tomorrow"/></span>&nbsp;&nbsp;<a href="#" onclick="get_tomorrowAdd_form()"><img src='img/add.png' title="Add new one" height="20" class="element60"></a><br>
<div align="center" style="padding-top:7px;display:none; padding-bottom:7px" id="tomorrowForm">
<form id="add_tomorrow" action="addtask.do" method="post">
<input type="hidden" name="hdn_action_type" value="tomorrow" />
<input type="hidden" name="hdn_todo" value="add_task" />
<input class="textfield" name="task" value="" size="50" />
<input type="submit" value="Add task" ></form>
</div>
<div class="taskDiv"><table width="98%" border="1px" bordercolor="#CCCCCC">
<c:forEach items="${sessionScope.tomorrow_list}" var="tomorrow">
<tr>
<td width="90%">
<div style="padding:3px">
<span class="dateStyle"><c:out value="${tomorrow.date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="tasks"><c:out value="${tomorrow.task}"/></span><span style="float:right">
<c:if test = "${tomorrow.file == ''}">
<a href="#" onclick="uploadFileAction(<c:out value="${tomorrow.taskId}" />)"><img src='img/file_upl.png' title="Upload new file" width="16" class="element90"></a>
</c:if>
<c:if test = "${tomorrow.file != ''}">
<a href="<%=Constants.FILE_PATH%>/<c:out value="${tomorrow.file}"/>" target="_blank" /><img src='img/file_downl.png' title="Download file" width="15" class="element90"></a><a href="#" onclick="uploadFileAction(<c:out value="${tomorrow.taskId}" />)"><img src='img/file_upl.png' title="Upload new file" width="16" class="element90"></a>
<a href="#" onclick="RemoveFileAction(<c:out value="${tomorrow.taskId}" />)"><img src='img/file_del.png' title="Remoove file" width="14" class="element90"></a>
</c:if>
<a href="#" onclick="tomorrowFixAction(<c:out value="${tomorrow.taskId}" />)"><img src='img/fix.png' title="Fix task" width="13" class="element90"></a>&nbsp;&nbsp;<a href="#" onclick="tomorrowRemoveAction(<c:out value="${tomorrow.taskId}" />)"><img src='img/del_blck.png' title="Delete task" width="14" class="element90"></a></span>
</div>
</td>
</tr>
</c:forEach>
</table></div>
</div>
<!--end Tomorrow layer-->
<!--start SomeDay layer-->
<div style="width:80%; padding-top:10px"><span class="daysClass">SomeDay (by choice)&nbsp;</span><a href="#" onclick="get_somedayAdd_form()"><img src='img/add.png' title="Add new one" height="20" class="element60"></a><br>
<div align="center" style="padding-top:7px;display:none; padding-bottom:7px" id="somedayForm">
<form id="add_someday" action="addtask.do" method="post">
   <input type="hidden" name="hdn_action_type" value="someday" />
   <input type="hidden" name="hdn_todo" value="add_task" />
   <input class="textfield" name="task" value="" size="40" /><input type="submit" value="Add task" placeholder="enter new task"><br>
   <input type="text" value="dd-mm-yy" name="someday_date" onfocus="this.select();lcs(this)"
    onclick="event.cancelBubble=true;this.select();lcs(this)" size="4"></form>
</div>
<div class="taskDiv"><table width="98%" border="1px" bordercolor="#CCCCCC">
<c:forEach items="${sessionScope.someday_list}" var="someday">
<tr>
<td width="90%">
<div style="padding:3px">
<span class="dateStyle"><c:out value="${someday.date}"/>&nbsp;&nbsp;&nbsp;&nbsp;</span><span class="tasks"><c:out value="${someday.task}"/></span><span style="float:right">
<c:if test = "${someday.file == ''}">
<a href="#" onclick="uploadFileAction(<c:out value="${someday.taskId}" />)"><img src='img/file_upl.png' title="Upload new file" width="16" class="element90"></a>
</c:if>
<c:if test = "${someday.file != ''}">
<a href="<%=Constants.FILE_PATH%>/<c:out value="${someday.file}"/>" target="_blank" /><img src='img/file_downl.png' title="Download file" width="15" class="element90"></a><a href="#" onclick="uploadFileAction(<c:out value="${someday.taskId}" />)"><img src='img/file_upl.png' title="Upload new file" width="16" class="element90"></a>
<a href="#" onclick="RemoveFileAction(<c:out value="${someday.taskId}" />)"><img src='img/file_del.png' title="Remoove file" width="14" class="element90"></a>
</c:if>
<a href="#" onclick="somedayFixAction(<c:out value="${someday.taskId}" />)"><img src='img/fix.png' title="Fix task" width="13" class="element90"></a>&nbsp;&nbsp;<a href="#" onclick="somedayRemoveAction(<c:out value="${someday.taskId}" />)"><img src='img/del_blck.png' title="Delete" width="14"  class="element90"></a></span>
</div>
</td>
</tr>
</c:forEach>
</table></div>
</div>
<!--end SomeDay layer-->
</div>
<c:if test = "${sessionScope.request_parameters_map != null}">
<!--start Panel_2 layer-->
<div class="panel2">
<a class="handle2"></a>
<span class="daysClass"> Parameters request body:</span><br>
<c:forEach items="${request_parameters_map}" var="parameters_list">
<c:out value="${parameters_list.key}"/> : <c:out value="${parameters_list.value}"/>
<br>
</c:forEach>
</div>
<!--end Panel_2 layer-->
</c:if>
</c:if>
<c:if test = "${sessionScope.request_header_map != null}">
<!--start Panel_1 layer-->
<div class="panel1">
<a class="handle"></a>
<span class="daysClass">Request Header:</span><br>
<c:forEach items="${request_header_map}" var="header_list">
<c:out value="${header_list.key}"/> : <c:out value="${header_list.value}"/>
<br>
</c:forEach>
</div>
<!--end Panel_1 layer-->
</c:if>
<!--end Main layer-->
<div class="inv">
<form id="hidden" action="tasks.do" method="post">
<input type="hidden" id="hidden_action_type_id" name="hdn_action_type" value="" />
<input type="hidden" id="hdn_todo_id" name="hdn_todo" value="" />
<input type="hidden" id="hdn_taskId_id" name="hdn_taskId" value="" /></form>
</div>
<jsp:include page="WEB-INF/pages/bottom.jsp" />
</body>
</html>