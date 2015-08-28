package by.gsu.epamlab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.beans.UserBean;
import by.gsu.epamlab.dao.DaoException;
import by.gsu.epamlab.dao.ITaskDAO;
import by.gsu.epamlab.dao.factory.UserFactory;
import by.gsu.epamlab.utils.Constants;
import by.gsu.epamlab.utils.Queries;

public class GetListsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetListsServlet() {
        super();
    }
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(Constants.CURR_USER);
		int userId = user.getId();
		ITaskDAO taskDAO = UserFactory.getTaskClassFromFactory();
		try {
			session.setAttribute(Constants.TODAY_LIST, taskDAO.getTaskslistByDay(Queries.Today, userId));
			session.setAttribute(Constants.TOMORROW_LIST, taskDAO.getTaskslistByDay(Queries.Tomorrow, userId));
			session.setAttribute(Constants.SOMEDAY_LIST, taskDAO.getTaskslistByDay(Queries.Someday, userId));
			session.setAttribute(Constants.FIXED_LIST, taskDAO.getTaskslistByDay(Queries.Fixed, userId));
			session.setAttribute(Constants.RECYCLED_LIST, taskDAO.getTaskslistByDay(Queries.Recycled, userId));
			response.sendRedirect(Constants.INDEX_PAGE);
		} catch (DaoException e) {
			DaoException.jump(request, response, Constants.DAO_ERR);
		}	
	}

}
