package com.belhard.dao;

import com.belhard.beans.SocialBean;

public interface IUserDAO {
	SocialBean checkLoginUser(String email, String pass) throws DaoException;
	SocialBean registerUser(String firstname, String lastname, String email, String pass, String country, boolean gender) throws DaoException;	
	SocialBean getValidUserById(int id) throws DaoException;
}
