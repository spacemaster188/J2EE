package com.belhard.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.belhard.utils.Constants;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LogoutServlet() {
    }
    
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cookie cookie1 = new Cookie (Constants.MAIL,Constants.MAIL);            
		Cookie cookie2 = new Cookie (Constants.PASS,Constants.PASS);
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		response.addCookie(cookie1);
	    response.addCookie(cookie2);
		session.invalidate();
		response.sendRedirect(Constants.LOGIN_PAGE);
	}

}
