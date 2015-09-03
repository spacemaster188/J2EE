package com.belhard.dao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.belhard.utils.Constants;

public class DaoException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("DaoException.class");

	public DaoException() {
	}

	public static void jump(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException, ServletException{
		HttpSession session = request.getSession();
		logger.error(msg);
		session.setAttribute(Constants.ERROR, msg);
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ERROR_PAGE);
		dispatcher.forward(request, response);
		//response.sendRedirect(Constants.ERROR_PAGE);
	}
}
