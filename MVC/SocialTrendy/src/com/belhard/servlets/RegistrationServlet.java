package com.belhard.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.services.SocialLoginService;
import com.belhard.utils.Constants;
import com.belhard.utils.StringUtils;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RegistrationServlet() {
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(Constants.UTF8);
		HttpSession session = request.getSession();
		
		String email = request.getParameter(Constants.E_MAIL);
		String pass = request.getParameter(Constants.PASSWORD);
		String firstname = request.getParameter(Constants.FIRSTNAME);
		String lastname = request.getParameter(Constants.LASTNAME);
		String country = request.getParameter(Constants.COUNTRY);
		String gender = request.getParameter(Constants.GENDER);	
		StringUtils.getSafe(firstname);
		StringUtils.getSafe(lastname);
		StringUtils.getSafe(gender);
		
	    if(StringUtils.checked(email) && StringUtils.checked(pass)) {
	    	try {
		    	SocialBean registeredUser = SocialLoginService.RegisterUser(firstname, lastname, email, pass, country, gender);
		    	if(registeredUser == null) {
		    		DaoException.jump(request, response, Constants.USER_EXISTS);
		    	}else{
			    	session.setAttribute(Constants.USR, registeredUser);
			    	response.sendRedirect(Constants.INDEX_PAGE);
		    	}
			} catch (DaoException e) {
				DaoException.jump(request, response, Constants.DAO_ERR);	
			}
	    }else{
	    	DaoException.jump(request, response, Constants.IS_NOT_EXISTS);
	    } 
	}

}