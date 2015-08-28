package by.gsu.epamlab.servlets;

import java.io.IOException;

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

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RegistrationServlet() {
        super();
    }
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String login = request.getParameter(Constants.LOGIN);
		String pass = request.getParameter(Constants.PASSWORD);
		String email = request.getParameter(Constants.EMAIL);
		StringUtils.getSafe(email);
		String firstname = request.getParameter(Constants.FIRSTNAME);
		StringUtils.getSafe(firstname);
		String lastname = request.getParameter(Constants.LASTNAME);
		StringUtils.getSafe(lastname);
		
		IUserDAO userDAO = UserFactory.getUserClassFromFactory();
	    UserBean registeredUser;
	    if(StringUtils.checked(login) && StringUtils.checked(pass)){
	    	try {
		    	registeredUser = userDAO.registerUser(login, pass, email, firstname, lastname);
		    	if(registeredUser==null){
		    		DaoException.jump(request, response, Constants.USER_EXISTS);
		    	}else{
			    	session.setAttribute(Constants.CURR_USER, registeredUser);
			    	response.sendRedirect(Constants.INDEX_PAGE);
		    	}
			} catch (DaoException e) {
				DaoException.jump(request, response, Constants.DAO_ERR);	
			}
	    } else{
	    	DaoException.jump(request, response, Constants.IS_NOT_EXISTS);	
	    } 
	}

}