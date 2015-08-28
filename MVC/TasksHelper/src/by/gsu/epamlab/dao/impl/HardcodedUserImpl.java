package by.gsu.epamlab.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.gsu.epamlab.beans.UserBean;
import by.gsu.epamlab.dao.DaoException;
import by.gsu.epamlab.dao.IUserDAO;
import by.gsu.epamlab.utils.Constants;

public class HardcodedUserImpl implements IUserDAO{
	public static List<UserBean> lst;
	static{
		if(lst==null){
			lst = new ArrayList<UserBean>();
		}
	}
	@Override
	public UserBean checkLoginUser(String login, String pass)throws DaoException {
		if(lst.size()>0){
			for (UserBean element : lst) {
				if(element.getLogin().equals(login) && element.getPassword().equals(pass)){
					return element;
				}
			}
		}
		return null;
	}

	@Override
	public UserBean registerUser(String login, String pass, String email,String firstname, String lastname) throws DaoException {
		int id;
		if(lst.size()>0){
			id = lst.size()-1;
			 for (UserBean element : lst) {
					if(element.getLogin().equals(login) || (element.getEmail().equals(email) && !email.equals(Constants.EMPTY_STR))){
						return null;
					}
				}
		}else{
		   id=0;	
		}
		id++;
		UserBean newUser = new UserBean();
		newUser.setId(id);
		newUser.setLogin(login);
		newUser.setPassword(pass);
		newUser.setEmail(email);
		newUser.setFirstname(firstname);
		newUser.setLastname(lastname);
		lst.add(newUser);
		UserBean sessionUser = new UserBean();
		sessionUser.setId(id);
		sessionUser.setLogin(login);
		sessionUser.setEmail(email);
		sessionUser.setFirstname(firstname);
		sessionUser.setLastname(lastname);
		return sessionUser;
	}

}
