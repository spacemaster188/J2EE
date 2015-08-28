<%@page import="com.belhard.beans.SocialBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<c:set var="user_var" scope="session" value="${sessionScope.usr}" />
<title><c:out value="${user_var.firstName}"/> <c:out value="${user_var.lastName}"/> Home</title>
<link href="css/multibox.css" rel="stylesheet" type="text/css" /> 
<link rel="stylesheet" href="css/ie6.css" type="text/css" media="all" />
<script type="text/javascript" src="js/mootools.js"></script> 
<script type="text/javascript" src="js/overlay.js"></script> 
<script type="text/javascript" src="js/multibox.js"></script> 
<link rel="stylesheet" href="css/liMarquee.css">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/jquery.liMarquee.js"></script>
<script type="text/javascript" src="js/jquery.tabslideout.v1.2.js"></script>
<script type='text/javascript'>  
 var $jq = jQuery.noConflict(true);  
</script>
<script>
$jq(window).load(function(){
	$jq('.str3').liMarquee();
});
</script> 
<script type="text/javascript" src="js/scripts.js"></script>
<script type="text/javascript" src="js/someJs.js"></script>
<script type="text/javascript" src="js/menu1.js"></script>
<script type="text/javascript" src="js/menu2.js"></script>
<script type="text/javascript" src="js/menu3.js"></script>
<script type="text/javascript" src="js/runningRow.js"></script>
<script type="text/javascript">
	setTimeout(function(){
$jq('.reportbox').fadeOut('fast')},3000);
</script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href='http://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,800' rel='stylesheet' type='text/css'>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--hover-effect-->
<script src="js/hover_pack.js"></script>
<script type="text/javascript" src="js/jquery.mixitup.min.js"></script>
</head>
<body>
<!--start music script-->
<c:if test = "${!empty mus_list}">  
<script type="text/javascript" src="http://scmplayer.net/script.js" 
data-config="{'skin':'http://static.tumblr.com/mky4cgu/RFrmbhpc1/simplesazulfalsa-moral.css','volume':21,'autoplay':false,'shuffle':false,'repeat':1,'placement':'bottom','showplaylist':false,'playlist':[<%= request.getAttribute("mus_list") %>]}" ></script>
</c:if>
<!--end music script-->
<!--start reportBox script-->
<div class="reportbox">
	<c:out value="${sessionScope.report}" />
</div>
<!--end reportBox script-->
	<!--start header-->
	<div class="header">
	  <div class="header-top">
		<div class="container">
			<div class="logo">
			  <a href="index.html"><img src="images/logo.png" alt="logo"/></a>
			</div>
<div class="menu"><table><tr><td class="td" valign="bottom">
 <form id="edit_users" action="user_action.do" method="post">
   <input type="hidden" name="hdn_action_type" id="hdn_action_type_id2" value="" />
   <input type="hidden" name="hdn_removed_product" id="hdn_removed_product_id2" value="" />
   <input type="text" class="textfield" name="keyword" id="name1" size="11" value='<c:out value="${sessionScope.search_key}" />' form="edit_users" />&nbsp;
   <input class="button" type="submit" value="Go" form="edit_users" ></form>
</td></tr>
</table>
</div>
			<div class="menu">
			  <a class="toggleMenu"><img src="images/nav_icon.png" alt="" /></a>
				<ul class="nav" id="nav">
                   <li><a href="login.do">My profile</a></li>
				   <li><a href="logout.do?name=settings" >Settings</a></li>
				   <li><a href="user_action.do?hdn_action_type=msgs_show" class="scroll">My messages</a></li>
				   	<li><a href="user_action.do?hdn_action_type=show_pics" class="scroll">Pictures</a></li>
				   <li><a href="logout.do">log out</a></li>								
				   <div class="clear"></div>
			    </ul>
			</div>							
	        <div class="clear"></div>
	        <script type="text/javascript" src="js/responsive-nav.js"></script>
			<script type="text/javascript" src="js/move-top.js"></script>
			<script type="text/javascript" src="js/easing.js"></script>
			   <script type="text/javascript">
					jQuery(document).ready(function($) {
						$jq(".scroll").click(function(event){		
							event.preventDefault();
							$jq('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
						});
					});
				</script>
	    </div>
	  </div>
<table width="100%"><tr>
<td width="20%"><div style="padding-left:15%"><img src="images/<c:out value="${user_var.mainPic}"/>" alt="main pic" width="160" height="130" class="element"> </div></td>
<td width="30%"><div style="padding-left:5%; color:#D5D5D5;font-family:'Lucida Sans Unicode', 'Lucida Grande', sans-serif; font-size: 18px; font-weight: bold;" class="element80">          
  <p><c:out value="${user_var.firstName}"/>  <c:out value="${user_var.lastName}"/></p>
  <p><c:out value="${user_var.country}"/></p>
  <p>e-mail: <c:out value="${user_var.email}"/></p>
  <p>status: <c:out value="${statusBean.status}"/></p>
  <c:choose>
    <c:when test="${user_var.permissions}">
    <p>Administrator</p>
    </c:when>
    <c:otherwise>
    <p>User</p>
    </c:otherwise>
</c:choose>
</div></td>
<!--start Pictures layer-->
<td width="50%" align="center">

<c:if test = "${!empty sessionScope.pictures_list}">
<span style="font-size:18px; font-family:Tahoma, Geneva, sans-serif; font-weight:bold">Pictures</span>&nbsp;&nbsp;<img src="images/pics.png" alt="pictures"  class="element">
<div id="container" style="padding-top:5px"> 
<div id="example">
<c:forEach items="${pictures_list}" var="p_list">
<a href='images/<c:out value="${p_list.mainPic}" />' id="mb1" class="mb" title='<c:out value="${p_list.mainPic}" />'><img src='images/<c:out value="${p_list.mainPic}" />' alt="" border="0" width="60" height="60" class="element80"/></a> 
<div class="multiBoxDesc mb1">Photo from user gallery&quot;</div> 
</c:forEach>
</div> 
</div>
</c:if>
</td>
<!--end Pistures layer-->
</tr>
</table>

<div >
<br><br><br>
</div>

<!--start FriendsList layer-->
<div class="str3 str_wrap">
<c:forEach items="${friends_list}" var="fr_list">
<a href="view.do?name=<c:out value="${fr_list.id}"/>"><img src="images/<c:out value="${fr_list.mainPic}"/>" width="80" height="80" title="<c:out value="${fr_list.firstName}"/>  <c:out value="${fr_list.lastName}"/>"></a>				
</c:forEach>
</div>
<!--end FriendsList layer-->

<c:if test = "${!empty sessionScope.in_message_list}">

<div align="center"><span style="font-size:18px; font-family:Tahoma, Geneva, sans-serif; font-weight:bold">Incoming messages</span>&nbsp;&nbsp;
  <img src="images/msgs.png" alt="users"  class="element">
  <table border="1px" bordercolor="#333333" id="usefull_inc_msgs">

  <tr>
        <th width="38px" class="th">From</th>
		<th width="110px" class="th">FirstName</th>
		<th width="110px" class="th"> </th>
        <th width="190px" class="th">email</th>
        <th width="60px" class="th">Action</th>
        <th width="185px" class="th">Message</th>
        <th width="200px" class="th">Send message</th>
  </tr>
<c:forEach items="${in_message_list}" var="in_m_list" varStatus="status">
<c:if test = "${status.index < 10}">
<tr>
<td align="center"/>&nbsp;&nbsp;</td>
<td align="center"><a href="view.do?name=<c:out value="${in_m_list.id}" />" ><c:out value="${in_m_list.firstName}" /></a></td>
<td align="center" style="font-size:11px"><c:out value="${in_m_list.dt}" /></td>
<td align="center" style="font-size:13px"><c:out value="${in_m_list.email}" /></td>
<td align="center"><a href="#" onclick="submitFriendAction(<c:out value="${in_m_list.id}" />)"><img src='images/friend.png' alt="friend" title="Make a friend"></a>&nbsp;&nbsp;<a href="view.do?name=<c:out value="${in_m_list.id}" />" ><img src='images/home.png' alt="view" title="View Page"></a></td>
<td width="185"><table width="95%" cellpadding="0" cellspacing="0"><tr><td align="center" style="font-size:11px; font-weight:bold">
<script>
  function replacer(str) {
    return "<a href='" + str + "' target='_blank'>" + str + "</a>";
  }
  var text = '<c:out value="${in_m_list.newsMessage}" />';
  text = text.replace(/http:\/\/[^ ]+/, replacer);
  document.write(text);
</script>
</td></tr></table></td>
 <td class="td" valign="bottom" align="center">
        <div id="content">
	    <div class="post inactive">
		<div class="title">
			<img src='images/message.png' alt="view" title="Send message"></a>
		</div>
		<div class="entry" style="display:none;">
			<form method="post" action="user_action.do">
            <input type="hidden" name="hdn_action_type" value='send_message' />
            <input type="hidden" name="hdn_message_id" value='<c:out value="${in_m_list.id}" />' />
            <input type="hidden" name="hdn_message_id2" value="flag" />
            <textarea rows=2 cols=17 class="textarea" name="message" id="name1"></textarea></br>
            <input type="submit" class="button" value="send" />
            </form>
		</div>
	    </div>
        </div>
</td>     
</tr>
</c:if>		
</c:forEach>
</table>
<a href="#" onclick="show_all_inc_msgs()" id="show_all_inc_link" style="font-size:12px"> ALL incoming messages</a>
<!--Start of the table all incoming msgs -->
<div style="display:none" id="all_inc_msgs">
<table border="1px" bordercolor="#333333">
  <tr>
        <th width="38px" class="th">From</th>
		<th width="110px" class="th">FirstName</th>
		<th width="110px" class="th"> </th>
        <th width="190px" class="th">email</th>
        <th width="60px" class="th">Action</th>
        <th width="185px" class="th">Message</th>
        <th width="200px" class="th">Send message</th>
  </tr>
<c:forEach items="${in_message_list}" var="in_m_list" varStatus="status">
<tr>
<td align="center"/>&nbsp;&nbsp;</td>
<td align="center"><a href="view.do?name=<c:out value="${in_m_list.id}" />" ><c:out value="${in_m_list.firstName}" /></a></td>
<td align="center" style="font-size:11px"><c:out value="${in_m_list.dt}" /></td>
<td align="center" style="font-size:13px"><c:out value="${in_m_list.email}" /></td>
<td align="center"><a href="#" onclick="submitFriendAction(<c:out value="${in_m_list.id}" />)"><img src='images/friend.png' alt="friend" title="Make a friend"></a>&nbsp;&nbsp;<a href="view.do?name=<c:out value="${in_m_list.id}" />" ><img src='images/home.png' alt="view" title="View Page"></a></td>
<td width="185"><table width="95%" cellpadding="0" cellspacing="0"><tr><td align="center" style="font-size:11px; font-weight:bold">
<script>
  function replacer(str) {
    return "<a href='" + str + "' target='_blank'>" + str + "</a>";
  }
  var text = '<c:out value="${in_m_list.newsMessage}" />';
  text = text.replace(/http:\/\/[^ ]+/, replacer);
  document.write(text);
</script>
</td></tr></table></td>
 <td class="td" valign="bottom" align="center">
        <div id="content">
	    <div class="post inactive">
		<div class="title">
			<img src='images/message.png' alt="view" title="Send message"></a>
		</div>
		<div class="entry" style="display:none;">
			<form method="post" action="user_action.do">
            <input type="hidden" name="hdn_action_type" value='send_message' />
            <input type="hidden" name="hdn_message_id" value='<c:out value="${in_m_list.id}" />' />
            <input type="hidden" name="hdn_message_id2" value="flag" />
            <textarea rows=2 cols=17 class="textarea" name="message" id="name1"></textarea></br>
            <input type="submit" class="button" value="send" />
            </form>
		</div>
	    </div>
        </div>
</td>     
</tr>	
</c:forEach>
</table>
</div>
<!--end of the table all incoming msgs-->

</div>
</c:if> 

<c:if test = "${sessionScope.out_message_list != null}">
<div align="center">
<span style="font-size:18px; font-family:Tahoma, Geneva, sans-serif; font-weight:bold">Sended messages</span>&nbsp;&nbsp;
  <img src="images/msgs.png" alt="users"  class="element">
  <table border="1px" bordercolor="#333333" id="usefull_out_msgs">
  <tr>
        <th width="38px" class="th">To</th>
		<th width="110px" class="th">FirstName</th>
		<th width="110px" class="th"> </th>
        <th width="190px" class="th">email</th>
        <th width="60px" class="th">Action</th>
        <th width="185px" class="th">Message</th>
        <th width="200px" class="th">Send message</th>
	</tr>
<c:forEach items="${out_message_list}" var="out_m_list" varStatus="status">
<c:if test = "${status.index < 10}">
<tr>
<td />&nbsp;&nbsp;</td>
<td align="center"><a href="view.do?name=<c:out value="${out_m_list.id}" />" ><c:out value="${out_m_list.firstName}" /></a></td>
<td align="center" style="font-size:11px"><c:out value="${out_m_list.dt}" /></td>
<td align="center" style="font-size:13px"><c:out value="${out_m_list.email}" /></td>
<td align="center"><a href="#" onclick="submitFriendAction(<c:out value="${out_m_list.id}" />)"><img src='images/friend.png' alt="friend" title="Make a friend"></a>&nbsp;&nbsp;<a href="view.do?name=<c:out value="${out_m_list.id}" />" ><img src='images/home.png' alt="view" title="View Page"></a></td>
<td width="185"><table width="95%" cellpadding="0" cellspacing="0"><tr><td align="center" style="font-size:11px; font-weight:bold">
<script>
  function replacer(str) {
    return "<a href='" + str + "' target='_blank'>" + str + "</a>";
  }
  var text = '<c:out value="${out_m_list.newsMessage}" />';
  text = text.replace(/http:\/\/[^ ]+/, replacer);
  document.write(text);
</script>
</td></tr></table></td>
<td class="td" valign="bottom" align="center">
        <div id="content">
	    <div class="post inactive">
		<div class="title">
			<img src='images/message.png' alt="view" title="Send message"></a>
		</div>
		<div class="entry" style="display:none;">
			<form method="post" action="user_action.do">
            <input type="hidden" name="hdn_action_type" value='send_message' />
            <input type="hidden" name="hdn_message_id" value='<c:out value="${out_m_list.id}" />' />
            <input type="hidden" name="hdn_message_id2" value="flag" />
            <textarea rows=2 cols=17 class="textarea" name="message" id="name1"></textarea></br>
            <input type="submit" class="button" value="send" />
            </form>
		</div>
	    </div>
        </div>
</td>    
</tr>
</c:if>				
</c:forEach>
</table>
<a href="#" onclick="show_all_out_msgs()" id="show_all_out_link" style="font-size:12px"> ALL sended messages</a>

<!--Start of the table all SENDED msgs -->
<div style="display:none" id="all_out_msgs">
<table border="1px" bordercolor="#333333" >
  <tr>
        <th width="38px" class="th">To</th>
		<th width="110px" class="th">FirstName</th>
		<th width="110px" class="th"> </th>
        <th width="190px" class="th">email</th>
        <th width="60px" class="th">Action</th>
        <th width="185px" class="th">Message</th>
        <th width="200px" class="th">Send message</th>
	</tr>
<c:forEach items="${out_message_list}" var="out_m_list" varStatus="status">
<tr>
<td />&nbsp;&nbsp;</td>
<td align="center"><a href="view.do?name=<c:out value="${out_m_list.id}" />" ><c:out value="${out_m_list.firstName}" /></a></td>
<td align="center" style="font-size:11px"><c:out value="${out_m_list.dt}" /></td>
<td align="center" style="font-size:13px"><c:out value="${out_m_list.email}" /></td>
<td align="center"><a href="#" onclick="submitFriendAction(<c:out value="${out_m_list.id}" />)"><img src='images/friend.png' alt="friend" title="Make a friend"></a>&nbsp;&nbsp;<a href="view.do?name=<c:out value="${out_m_list.id}" />" ><img src='images/home.png' alt="view" title="View Page"></a></td>
<td width="185"><table width="95%" cellpadding="0" cellspacing="0"><tr><td align="center" style="font-size:11px; font-weight:bold">
<script>
  function replacer(str) {
    return "<a href='" + str + "' target='_blank'>" + str + "</a>";
  }
  var text = '<c:out value="${out_m_list.newsMessage}" />';
  text = text.replace(/http:\/\/[^ ]+/, replacer);
  document.write(text);
</script>
</td></tr></table></td>
<td class="td" valign="bottom" align="center">
        <div id="content">
	    <div class="post inactive">
		<div class="title">
			<img src='images/message.png' alt="view" title="Send message"></a>
		</div>
		<div class="entry" style="display:none;">
			<form method="post" action="user_action.do">
            <input type="hidden" name="hdn_action_type" value='send_message' />
            <input type="hidden" name="hdn_message_id" value='<c:out value="${out_m_list.id}" />' />
            <input type="hidden" name="hdn_message_id2" value="flag" />
            <textarea rows=2 cols=17 class="textarea" name="message" id="name1"></textarea></br>
            <input type="submit" class="button" value="send" />
            </form>
		</div>
	    </div>
        </div>
</td>    
</tr>			
</c:forEach>
</table>
</div>
<!--end of the table all SENDED msgs-->
</div>
</c:if>

<div><br></div>


<c:if test = "${sessionScope.search_list != null}">

<div align="center">
<span style="font-size:18px; font-family:Tahoma, Geneva, sans-serif; font-weight:bold">User List</span> <img src="images/search.png" alt="users"  class="element">
<table border="1px" bordercolor="#333333">
<tr>
		<th width="108" class="th">FirstName</th>
		<th width="94" class="th">LastName</th>
        <th width="190" class="th">email</th>
		<th width="68" class="th">Gender</th>
        <th width="108" class="th">Country</th>
        <th width="155" class="th">Edit</th>
        <th width="200px" class="th">Send message</th>
	</tr>
<c:forEach items="${search_list}" var="list" varStatus="status">
<c:if test = "${status.index<20}">
<tr>
<td align="center"><c:out value="${list.firstName}" /></td>
<td align="center"><c:out value="${list.lastName}" /></td>
<td align="center"><c:out value="${list.email}" /></td>
<td align="center"><c:out value="${list.gender}" /></td>
<td align="center"><c:out value="${list.country}" /></td>
<td align="center">
<c:if test="${!list.permissions}"> 	
<a href="#" onclick="submitRemoveUser(<c:out value="${list.id}" />)"><img src='images/del_wht.png'  alt="delete" title="Delete user"></a>&nbsp;<a href="#" onclick="submitMakeAdminAction(<c:out value="${list.id}" />)"><img src='images/admin.png' alt="Make Admin" title="Give admin's permissions"></a>
</c:if>
&nbsp;<a href="#" onclick="submitFriendAction(<c:out value="${list.id}" />)"><img src='images/friend.png' alt="friend" title="Make a friend"></a>&nbsp;<a href="view.do?name=<c:out value="${list.id}" />" ><img src='images/home.png' alt="view" title="View Page"></a></td>
<td class="td" valign="bottom" align="center">
        <div id="content">
	    <div class="post inactive">
		<div class="title">
			<img src='images/message.png' alt="view" title="Send message"></a>
		</div>
		<div class="entry" style="display:none;">
			<form id="send_message" method="post" action="user_action.do">
            <input type="hidden" name="hdn_action_type" value='send_message' />
            <input type="hidden" name="hdn_message_id" value='<c:out value="${list.id}" />' />
            <input type="hidden" name="hdn_message_id2" value="flag" />
            <textarea rows=2 cols=17 class="textarea" name="message" id="name1"></textarea></br>
            <input type="submit" class="button" value="send" />
            </form>
		</div>
	    </div>
        </div>
 
</td>  
</tr>
</c:if>		
</c:forEach>
</table>
</div>

</c:if>


<br>
<div align="center">
<span style="font-size:18px; font-family:Tahoma, Geneva, sans-serif; font-weight:bold">Deleted users search</span>
<table><tr><td class="td" valign="bottom">
<form id="recovery_users" action="user_action.do" method="post">
   <input type="hidden" name="hdn_action_type" id="hdn_action_type_id_rec" value=""/>
   <input type="hidden" name="hdn_removed_product" id="hdn_removed_product_id_rec" value="" />
   <input class="textfield" name="recovery_keyword" id="name1" size="12" value='<c:out value="${sessionScope.recovery_key}" />' />
   <input type="submit" value="Go" class="button"></form>
</td></tr></table>
</div>
<br>


<c:if test = "${sessionScope.search_list_recovery != null}">

<div align="center">
<table border="1px" bordercolor="#333333">
<tr>
		<th width="110px" class="th">FirstName</th>
		<th width="110px" class="th">LastName</th>
        <th width="190px" class="th">email</th>
		<th width="70px" class="th">Gender</th>
        <th width="110px" class="th">Country</th>
        <th width="100px" class="th">Edit</th>
	</tr>
<c:forEach items="${search_list_recovery}" var="list2">
<tr>
<td align="center"><c:out value="${list2.firstName}" /></td>
<td align="center"><c:out value="${list2.lastName}" /></td>
<td align="center"><c:out value="${list2.email}" /></td>
<td align="center"><c:out value="${list2.gender}" /></td>
<td align="center"><c:out value="${list2.country}" /></td>
<td align="center"><a href="#" onclick="submitRecoveryAction(<c:out value="${list2.id}" />)"><img src='images/recovery.png'  alt="recovery" title="Recovery user"></a>&nbsp;&nbsp;<a href="#" onclick="submitMakeAdminActionRec(<c:out value="${list2.id}" />)"><img src='images/admin.png' alt="Make Admin" title="Give admin's permissions"></a>&nbsp;&nbsp;<a href="#" onclick="submitFriendActionRec(<c:out value="${list2.id}" />)"><img src='images/friend.png' alt="friend" title="Make a friend"></a></td>
</tr>					
</c:forEach>
</table>
</div>

</c:if>

<!--start side windows-->

<div class="panelMenu2">
<a class="handle2"></a>
<span style="font-weight:bold" class="element80">Add a new sound<br></span>
<br>
<form method="post" action="file_upload.do" enctype="multipart/form-data" name="addPicForm">
<input type="file" name="musicAdd" id="mus_upload_hidden"
        style="position: absolute; display: block; overflow: hidden; width: 0; height: 0; border: 0; padding: 0;"
        onchange="document.getElementById('mus_upload_visible').value = this.value;" />
<input type="text" readonly id="mus_upload_visible" class="textfieldSmall" size="12"
        onclick="document.getElementById('mus_upload_hidden').click();" />
<input type="submit" value="add" />
</form>
</div>

<div class="panelMenu3">
<a class="handle3"></a>
<span style="font-weight:bold">Last messages</span><br/><br/>

<c:if test = "${sessionScope.last_message_list != null}">
<c:forEach items="${last_message_list}" var="last_list">
<span style="font-size:10px; font-family:'Palatino Linotype', 'Book Antiqua', Palatino, serif" class="element80"><c:out value="${last_list.dt}"/></span><br/>

<c:if test = "${last_list.firstName == 'Me'}">  
   <span style="font-size:12px; font-weight:bold"><c:out value="${last_list.firstName}"/> : </span>
</c:if>  
<c:if test = "${last_list.firstName != 'Me'}">  
  <span style="font-size:12px; font-weight:bold"><a href="view.do?name=<c:out value="${last_list.id}" />" ><c:out value="${last_list.firstName}"/></a> : </span>
</c:if> 

<span style="color:#FFF; font-size:14px; font-weight:bold">

<script>
  function replacer(str) {
    return "<a href='" + str + "' target='_blank'>" + str + "</a>";
  }
  var text = '<c:out value="${last_list.newsMessage}"/>';
  text = text.replace(/http:\/\/[^ ]+/, replacer);
  text = text.replace(/https:\/\/[^ ]+/, replacer);
  document.write(text);
</script>
</span>
<br>
</c:forEach>
<br>
<div align="center">
<form id="send_message" method="post" action="user_action.do">
            <input type="hidden" name="hdn_action_type" value='send_message' />
            <input type="hidden" name="hdn_message_id" value='<c:out value="${last_message_list.get(0).id}" />' />
            <textarea rows=2 cols=29 class="textarea" name="message" id="name1"></textarea><br/>
            <input type="submit" class="button" value=" send " />
</form>
</div>
</c:if>

</div>

<div class="panelMenu1">
<a class="handle"></a>
<span style="font-weight:bold" class="element80">Change u'r title picture</span>
<form method="post" action="file_upload.do" enctype="multipart/form-data" name="addPicForm">
<input type="file" name="fileMain" id="title_picture_upload_hidden"
        style="position: absolute; display: block; overflow: hidden; width: 0; height: 0; border: 0; padding: 0;"
        onchange="document.getElementById('title_picture_upload_visible').value = this.value;" />
<input type="text" readonly id="title_picture_upload_visible" class="textfieldSmall" size="12"
        onclick="document.getElementById('title_picture_upload_hidden').click();" />
<input type="submit" value="add" />
</form><br>
<span style="font-weight:bold" class="element80">Add a new picture<br></span>
<form method="post" action="file_upload.do" enctype="multipart/form-data" name="addPicForm">
<input type="file" name="fileAdd" id="newpicture_upload_hidden"
        style="position: absolute; display: block; overflow: hidden; width: 0; height: 0; border: 0; padding: 0;"
        onchange="document.getElementById('newpicture_upload_visible').value = this.value;" />
<input type="text" readonly id="newpicture_upload_visible" class="textfieldSmall" size="12"
        onclick="document.getElementById('newpicture_upload_hidden').click();" />
<input type="submit" value="add" />
</form>
</div>

<!--end side windows-->
	<!--end header-->
	<!--start services-->
	<div class="services" id="services">
		<div class="container">

<div align="center">

<table width="60%" border="1px" bordercolor="#CCCCCC">
<tr><td>
<div align="center" style="padding-top:7px; padding-bottom:7px">
<form id="add_news" action="user_action.do" method="post">
   <input type="hidden" name="hdn_action_type" id="hdn_action_type_id" value="add_news" />
   <input type="hidden" name="hdn_removed_product" id="hdn_removed_product_id"/>
   <input class="textfield" name="news" id="news1" size="40" />
   <input type="submit" value="Add news" onclick="setAddAction()" ></form>
</div>
</td></tr>
</table><br>


<table width="60%" border="1px" bordercolor="#CCCCCC">
<c:forEach items="${news_list}" var="news">
<tr>
<td width="10%" align="center">
<div>
<img src="images/<c:out value="${user_var.mainPic}"/>" width="45" height="45" alt="img" class="element">
</div>
</td>
<td width="90%">
<div style="padding-left:5%; padding-right:5%;padding-top:4px;padding-bottom:4px">
<span style="color:#333; font-weight:bold; font-size:12px" class="element80"><c:out value="${user_var.firstName}"/> <c:out value="${user_var.lastName}"/> </span><br>
<span style="color:#FFF"><c:out value="${news.newsMessage}"/></span>&nbsp;&nbsp;<span style="float:right"><a href="#" onclick="submitRemoveAction(<c:out value="${news.id}" />)"><img src='images/del_blck.png' alt="delete" title="Delete" width="13" height="13" class="element80"></a></span><br>
<span style="font-size:9px; color:#333" class="element80"><c:out value="${news.dt}"/></span>
</div>
</td>
</tr>
</c:forEach>
</table>

</div>
			<div class="row">
				<h3 class="m_3">wishes</h3>
				<div class="m_4"></div>
				<div class="col-md-3 top_grid">
					<i class="coffee"> </i>
				  <h3 class="m_1">Coffee</h3>
					<p class="m_2">Try to limit caffeinated drinks and sugar-containing substances. These things will not add you health. Quit Smoking, this thing will kill you..</p>
			  </div>
				<div class="col-md-3 top_grid">
					<i class="instant"> </i>
					<h3 class="m_1">knowledge</h3>
					<p class="m_2">Read more. Get more knowledge, they will be very useful to you in the future ..</p>
				</div>
				<div class="col-md-3 top_grid">
					<i class="advisor"> </i>
					<h3 class="m_1">Capture</h3>
					<p class="m_2">Try to capture more moments of his life and the lives of their loved ones and friends..</p>
				</div>
				<div class="col-md-3 top_grid1">
					<i class="frame"> </i>
					<h3 class="m_1">motion</h3>
					<p class="m_2">Do not stop on the spot. Drive more each year learn more and more countries and cultures ..</p>			
</div>
			</div>
		</div>
</div>
<div align="center"><a href="mailto:limitvadim@ya.ru" style="color:white; font-size: 11px">©Spacemaster 2014</a></div>
	 <!--end services-->
<script type="text/javascript">	
var box = {};			
window.addEvent('domready', function(){			
box = new MultiBox('mb', {descClassName: 'multiBoxDesc', useOverlay: true});
});		
</script> 
</body>
</html>