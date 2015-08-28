package by.gsu.epamlab.dao;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.utils.Constants;

public class DaoException extends Exception {
	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public static void jump(HttpServletRequest request, HttpServletResponse response, String msg) throws IOException{
		HttpSession session = request.getSession();
		session.setAttribute(Constants.ERROR, msg);
		response.sendRedirect(Constants.ERROR_PAGE);
	}
}
