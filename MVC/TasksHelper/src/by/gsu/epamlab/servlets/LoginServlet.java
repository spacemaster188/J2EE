package by.gsu.epamlab.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.utils.Constants;
import by.gsu.epamlab.utils.StringUtils;
import by.gsu.epamlab.beans.UserBean;
import by.gsu.epamlab.dao.DaoException;
import by.gsu.epamlab.dao.IUserDAO;
import by.gsu.epamlab.dao.factory.UserFactory;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();	
		String login = request.getParameter(Constants.LOGIN);
		String pass = request.getParameter(Constants.PASSWORD);
		
		IUserDAO userDAO = UserFactory.getUserClassFromFactory();
	    UserBean incomingUser;
	    if(StringUtils.checked(login) && StringUtils.checked(pass)){
	    	try {
				incomingUser = userDAO.checkLoginUser(login, pass);
				if(incomingUser!=null){
					session.setAttribute(Constants.CURR_USER, incomingUser);
					RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.GET_LISTS_SERVLET);
					dispatcher.forward(request, response);
				}else{
					DaoException.jump(request, response, Constants.IS_NOT_EXISTS);
				}
			} catch (DaoException e) {
				DaoException.jump(request, response, Constants.DAO_ERR);
			}	
	    }else{
	    	DaoException.jump(request, response, Constants.IS_NOT_EXISTS);	
	    }
	}

}