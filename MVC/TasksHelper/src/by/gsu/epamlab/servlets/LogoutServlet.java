package by.gsu.epamlab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.utils.Constants;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LogoutServlet() {
        super();
    }
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(Constants.INDEX_PAGE);
	}

}
