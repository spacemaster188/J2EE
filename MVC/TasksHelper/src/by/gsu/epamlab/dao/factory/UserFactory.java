package by.gsu.epamlab.dao.factory;

import by.gsu.epamlab.dao.ITaskDAO;
import by.gsu.epamlab.dao.IUserDAO;
import by.gsu.epamlab.dao.impl.*;

public abstract class UserFactory {

	public static IUserDAO getUserClassFromFactory() {
		 return new DbUserImpl();
		//return new HardcodedUserImpl();
	}
	public static ITaskDAO getTaskClassFromFactory() {
		 return new DbTaskImpl();
		 //return new AnyOtherImpl();
	}
	
}