package com.belhard.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.services.SocialCheckLogonService;
import com.belhard.services.SocialListsService;
import com.belhard.services.SocialLoginService;
import com.belhard.services.SocialUsersService;
import com.belhard.utils.Constants;
import com.belhard.utils.StringUtils;

public class SocialUserActionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String login_page = "login.do";
	private static final String inactiveIndex = "/WEB-INF/pages/inactive.jsp";
	private static final String userIndex = "/WEB-INF/pages/Users/index.jsp";
	private static final String adminIndex = "/WEB-INF/pages/Admins/index.jsp";

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(Constants.UTF8);
		response.setCharacterEncoding(Constants.UTF8);
		response.setContentType ("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		session.setAttribute("report", Constants.EMPTY_STR);
		String msg_id = request.getParameter("hdn_message_id");
		String msg_flag = request.getParameter("hdn_message_id2");
		if(msg_id== null){
		session.setAttribute("search_list", null);	
		session.setAttribute("in_message_list", null);
		session.setAttribute("out_message_list", null);
		}
		session.setAttribute("pictures_list", null);
		session.setAttribute("search_list_recovery", null);
		session.setAttribute("last_message_list", null);
		session.setAttribute("mus_list", null);
		
		String actionType = request.getParameter("hdn_action_type");
		String news = request.getParameter("news");
		String keyword = request.getParameter("keyword");
		String recovery_keyword = request.getParameter("recovery_keyword");

		if (actionType == null) {
			actionType = Constants.EMPTY_STR;
		}

		int currentId = SocialCheckLogonService.getLogonId(request);

		//boolean permissions = SocialLoginService.getUserById(currentId).isPermissions();
		boolean permissions = false;
		
		try {
		switch (currentId) {
		case Constants.INACTIVE_USER_INT: {
			response.sendRedirect(inactiveIndex); 
			break;
		}
		case Constants.LOGIN_REDIRECT_INT: {
			response.sendRedirect(login_page); 
			break;
		}
		default: {
			if (permissions) {                                                          //if admin logon
/*				SocialBean outBean = SocialUsersService.getUserById(currentId);
				request.setAttribute("outBean", outBean);
				
				switch (actionType) {
				case "remove_user": {
					int id = Integer.parseInt(request.getParameter("hdn_removed_product"));
					SocialUserService.deleteUser(id, request);
					break;
				}
				case "remove_action": {
					int id = Integer.parseInt(request.getParameter("hdn_removed_product"));
					SocialUserService.deleteNews(id, request);
					break;
				}
				case "make_admin": {
					int id = Integer.parseInt(request.getParameter("hdn_removed_product"));
					SocialUserService.makeAdmin(id, request);
					break;
				}
				case "add_news": {
					SocialUserService.addNews(currentId, news, request);
					break;
				}
				case "make_a_friend": {
					int id = Integer.parseInt(request.getParameter("hdn_removed_product"));
					SocialUserService.addFriend(currentId, id, request);
					break;
				}
				case "send_message": {
					int id = Integer.parseInt(msg_id);
					String message = request.getParameter("message");
					SocialUserService.sendMessage(currentId, id, message, request);
					if(msg_flag!=null){
						List<SocialBean> in_msgs_List = SocialUserService.getInMsgsBeanList(currentId);
						session.setAttribute("in_message_list", in_msgs_List);
						List<SocialBean> out_msgs_List = SocialUserService.getOutMsgsBeanList(currentId);
						session.setAttribute("out_message_list", out_msgs_List);	
					}
					break;
				}
				case "msgs_show": {
					List<SocialBean> in_msgs_List = SocialUserService.getInMsgsBeanList(currentId);
					session.setAttribute("in_message_list", in_msgs_List);
					List<SocialBean> out_msgs_List = SocialUserService.getOutMsgsBeanList(currentId);
					session.setAttribute("out_message_list", out_msgs_List);
					break;
				}
				case "show_pics": {
					List<SocialBean> picsList = SocialUserService.getPicsList(currentId);
					session.setAttribute("pictures_list", picsList);
					break;
				}
				case "recovery_action": {
					int id = Integer.parseInt(request.getParameter("hdn_removed_product"));
					SocialUserService.recoveryUser(id, request);
					break;
				 }	
				}
				if(StringUtils.isStrPassed(keyword)){
					List<SocialBean> searchList = SocialUserService.getSearchList4Admins(keyword, currentId);
				    session.setAttribute("search_list", searchList);
				    session.setAttribute("search_key", keyword);	
				}
				if(StringUtils.isStrPassed(recovery_keyword)){
					List<SocialBean> searchListRec = SocialUserService.getRecoveryList4Admins(recovery_keyword, currentId);
				    session.setAttribute("search_list_recovery", searchListRec);
				    session.setAttribute("recovery_key", recovery_keyword);
				}
				
				List<SocialBean> newsList = SocialUserService.getNewsBeanList(currentId);
				session.setAttribute("news_list", newsList);
				List<SocialBean> friendsList = SocialUserService.getFriendsStripPics(currentId);
				session.setAttribute("friends_list", friendsList);
				List<SocialBean> lastMsgsList = SocialUserService.getLastMessages(currentId);
				session.setAttribute("last_message_list", lastMsgsList);
				String musList = SocialUserService.getMusic(currentId);
				session.setAttribute("mus_list", musList);
				response.sendRedirect(adminIndex); */
			} else {                                                                        // if user logon
				SocialBean outBean = SocialLoginService.getUserById(currentId);
				request.setAttribute("outBean", outBean);
				switch (actionType) {
/*				case "remove_action": {
					int id = Integer.parseInt(request.getParameter("hdn_removed_product"));
					SocialUserService.deleteNews(id, request);
					break;
				}
				case "add_news": {
					SocialUserService.addNews(currentId, news, request);
					break;
				}
				case "make_a_friend": {
					int id = Integer.parseInt(request.getParameter("hdn_removed_product"));
					SocialUserService.addFriend(currentId, id, request);
					break;
				}
				case "send_message": {
					int id = Integer.parseInt(msg_id);
					String message = request.getParameter("message");
					SocialUserService.sendMessage(currentId, id, message, request);
					if(msg_flag!=null){
						List<SocialBean> in_msgs_List = SocialUserService.getInMsgsBeanList(currentId);
						session.setAttribute("in_message_list", in_msgs_List);
						List<SocialBean> out_msgs_List = SocialUserService.getOutMsgsBeanList(currentId);
						session.setAttribute("out_message_list", out_msgs_List);	
					}
					break;
				}
				case "msgs_show": {
					List<SocialBean> in_msgs_List = SocialUserService.getInMsgsBeanList(currentId);
					session.setAttribute("in_message_list", in_msgs_List);
					List<SocialBean> out_msgs_List = SocialUserService.getOutMsgsBeanList(currentId);
					session.setAttribute("out_message_list", out_msgs_List);
					break;
				}*/
				case "show_pics": {
					List<SocialBean> picsList = SocialListsService.getPicturesList(outBean);
					session.setAttribute("pictures_list", picsList);
					break;
				}
				}
/*				if(StringUtils.isStrPassed(keyword)){
					List<SocialBean> searchList = SocialUserService.getSearchList4Users(keyword, currentId);
					session.setAttribute("search_list", searchList);
					session.setAttribute("search_key", keyword);
				}
				List<SocialBean> newsList = SocialUserService.getNewsBeanList(currentId);
				session.setAttribute("news_list", newsList);
				List<SocialBean> friendsList = SocialUserService.getFriendsStripPics(currentId);
				session.setAttribute("friends_list", friendsList);
				List<SocialBean> lastMsgsList = SocialUserService.getLastMessages(currentId);
				session.setAttribute("last_message_list", lastMsgsList);
				String musList = SocialUserService.getMusic(currentId);
				session.setAttribute("mus_list", musList);*/
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.GET_LISTS_SERVLET);
				dispatcher.forward(request, response);
			}
		}
		}
		} catch (DaoException e) {
			DaoException.jump(request, response, Constants.DAO_ERR);
		}
	}
}
