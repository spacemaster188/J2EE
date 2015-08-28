package by.gsu.epamlab.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.dao.DaoException;
import by.gsu.epamlab.dao.ITaskDAO;
import by.gsu.epamlab.dao.factory.UserFactory;
import by.gsu.epamlab.utils.Constants;
import by.gsu.epamlab.utils.StringUtils;


public class FixTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FixTaskServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ITaskDAO taskDAO = UserFactory.getTaskClassFromFactory();
		String taskIsStr = request.getParameter(Constants.TASK_ID);
		StringUtils.getSafe(taskIsStr);
		try {
			int taskId = Integer.parseInt(request.getParameter(Constants.TASK_ID));
			taskDAO.fixTask(taskId);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.GET_LISTS_SERVLET);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			DaoException.jump(request, response, Constants.DAO_ERR);
		}	
	}

}
