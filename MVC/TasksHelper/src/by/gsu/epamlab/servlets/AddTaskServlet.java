package by.gsu.epamlab.servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
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
import by.gsu.epamlab.utils.StringUtils;

public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Pattern pattern;
	private Matcher matcher;
	
    public AddTaskServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute(Constants.CURR_USER);
		int userId = user.getId();
		String ActionType = request.getParameter(Constants.HDN_ACTION_TYPE);
		String newTask = request.getParameter(Constants.TASK);
		StringUtils.getSafe(ActionType);
		StringUtils.getSafe(newTask);
		ITaskDAO taskDAO = UserFactory.getTaskClassFromFactory();
		switch (ActionType) {
		case Constants.TODAY:{
			try {
				long curTime = System.currentTimeMillis();             
				Date curDate = new Date(curTime); 
				taskDAO.addTask(newTask, userId, curDate);
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.GET_LISTS_SERVLET);
				dispatcher.forward(request, response);
			} catch (DaoException e) {
				DaoException.jump(request, response, Constants.DAO_ERR);
			}
			break;	
		}
        case Constants.TOMORROW:{
        	try {
				long tomorrowTime = System.currentTimeMillis()+24*60*60*1000;
				Date tomorrowDate = new Date(tomorrowTime);
				taskDAO.addTask(newTask, userId, tomorrowDate);
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.GET_LISTS_SERVLET);
				dispatcher.forward(request, response);
			} catch (DaoException e) {
				DaoException.jump(request, response, Constants.DAO_ERR);
			}
			break;	
		}
        case Constants.SOMEDAY:{
        	String dateTask = request.getParameter(Constants.SOMEDAY_DATE);
        	try {
        		pattern = Pattern.compile(Constants.DATE_PATTERN);
        		matcher = pattern.matcher(dateTask);
        		if(matcher.matches()){
        			SimpleDateFormat format = new SimpleDateFormat(Constants.OUTPUT_FORMAT);
        		    java.util.Date parsed = format.parse(dateTask);
        		    java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        		    taskDAO.addTask(newTask, userId, sqlDate);
        		    RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.GET_LISTS_SERVLET);
        			dispatcher.forward(request, response);
        		}else{
        			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.INDEX_PAGE);
        			dispatcher.forward(request, response);
        		}
			} catch (DaoException e) {
				DaoException.jump(request, response, Constants.DAO_ERR);
			} catch (ParseException e) {             
			}
	    break;	
        }
		default:{
			break;	
		}
		}
	}

}
