package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.UserBean;

public interface IUserDAO {
	
	UserBean checkLoginUser(String login, String password) throws DaoException;
	
	UserBean registerUser(String login, String pass, String email,String firstname, String lastname) throws DaoException;	
}
