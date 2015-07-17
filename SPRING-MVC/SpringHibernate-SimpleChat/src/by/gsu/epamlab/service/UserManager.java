package by.gsu.epamlab.service;

import by.gsu.epamlab.entity.UserEntity;

public interface UserManager {

	public void addUser(UserEntity user);
	public boolean hasLogin(UserEntity user);
	public UserEntity getFullyUser(UserEntity user);
}
