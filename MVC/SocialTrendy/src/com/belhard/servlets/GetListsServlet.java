package com.belhard.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.services.SocialListsService;
import com.belhard.services.SocialLoginService;
import com.belhard.utils.Constants;

public class GetListsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetListsServlet() {
    }
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SocialBean user = (SocialBean)session.getAttribute(Constants.USR);
		try {
			SocialBean currentUser = SocialLoginService.getUpdatedUserByBean(user);
        	session.setAttribute(Constants.USR, currentUser);
			List<SocialBean> newsList = SocialListsService.getNewsList(user);
			request.setAttribute(Constants.NEWS_LIST, newsList);
			SocialBean statusBean = SocialListsService.getStatusBean(user);
			request.setAttribute(Constants.STATUS_BEAN, statusBean);
			List<SocialBean> friendsList = SocialListsService.getFriendsList(user);
			request.setAttribute(Constants.FRIENDS_LIST, friendsList);
			List<SocialBean> photosList = SocialListsService.getPicturesList(user);
			request.setAttribute(Constants.PICTURES_LIST, photosList);
			List<SocialBean> musicList = SocialListsService.getMusicList(user);
			request.setAttribute(Constants.MUSIC_LIST, musicList);		
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.INDEX_PAGE);
			dispatcher.forward(request, response);
		} catch (DaoException e) {
			DaoException.jump(request, response, Constants.DAO_ERR);
		}	
	}
}
