package by.gsu.epamlab.dao;

import by.gsu.epamlab.entity.UserEntity;

public interface UserDAO {

	public void addUser(UserEntity usr);
	public boolean hasLogin(UserEntity usr);
	public UserEntity getFullyUser(UserEntity usr);
}
