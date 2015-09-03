package com.belhard.dao;

import java.util.List;

import com.belhard.beans.SocialBean;

public interface IUserDAO {
	SocialBean checkLoginUser(String email, String pass) throws DaoException;
	SocialBean registerUser(String firstname, String lastname, String email, String pass, String country, boolean gender) throws DaoException;	
	SocialBean getValidUserById(int id) throws DaoException;
	List<SocialBean> UserSearchList(String keyword, List<SocialBean> lst, String column, int currId)  throws DaoException;
}
