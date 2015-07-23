package by.gsu.epamlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.gsu.epamlab.dao.UserDAO;
import by.gsu.epamlab.entity.UserEntity;

@Service
public class UserManagerImpl implements UserManager {
	
	@Autowired
    private UserDAO userDAO;

	@Override
	@Transactional
	public void addUser(UserEntity usr) {
    userDAO.addUser(usr);
	}

	@Override
	@Transactional
	public boolean hasLogin(UserEntity usr) {
		return userDAO.hasLogin(usr);
	}
	
	@Override
	@Transactional
	public UserEntity getFullyUser(UserEntity user) {
		return userDAO.getFullyUser(user);
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


}
