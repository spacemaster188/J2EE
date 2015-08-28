package com.belhard.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.services.SocialLoginService;
import com.belhard.utils.Constants;
import com.belhard.utils.StringUtils;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(Constants.UTF8);
		HttpSession session = request.getSession();
		session.setAttribute(Constants.REPORT, Constants.EMPTY_STR);
		String email = request.getParameter(Constants.E_MAIL);
		String pass = request.getParameter(Constants.PASS);

	    if(StringUtils.checked(email) && StringUtils.checked(pass)) {
	    	try {
	    		SocialBean incomingUser = SocialLoginService.checkLoginUser(email, pass);
				if(incomingUser != null) {
					session.setAttribute(Constants.USR, incomingUser);
					if(request.getParameter(Constants.REMEMBER)!=null){                                //when "remember me" checked  
						Cookie cookie1 = new Cookie (Constants.MAIL,email);                            //store user in cookies
						Cookie cookie2 = new Cookie (Constants.PASS,pass);
						cookie1.setMaxAge(365 * 24 * 60 * 60);
						cookie2.setMaxAge(365 * 24 * 60 * 60);
						response.addCookie(cookie1);
						response.addCookie(cookie2);
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.GET_LISTS_SERVLET);
					dispatcher.forward(request, response);
				}else{
					session.setAttribute(Constants.REPORT, Constants.IS_NOT_EXISTS);                   //add popup report
					RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.LOGIN_PAGE);
					dispatcher.forward(request, response);
				}
			} catch (DaoException e) {
				DaoException.jump(request, response, Constants.DAO_ERR);
			}	
	    }else{
	    	session.setAttribute(Constants.REPORT, Constants.IS_NOT_EXISTS);                           //add popup report
	    	RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.LOGIN_PAGE);
			dispatcher.forward(request, response);
	    }
	}

}