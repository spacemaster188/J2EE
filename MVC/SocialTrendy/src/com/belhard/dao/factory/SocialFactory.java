package com.belhard.dao.factory;

import com.belhard.dao.IFriendsDAO;
import com.belhard.dao.IMessageDAO;
import com.belhard.dao.IMusicDAO;
import com.belhard.dao.INewsDAO;
import com.belhard.dao.IPictureDAO;
import com.belhard.dao.IStatusDAO;
import com.belhard.dao.IUserDAO;
import com.belhard.dao.impl.DbFriendsImpl;
import com.belhard.dao.impl.DbMessageImpl;
import com.belhard.dao.impl.DbMusicImpl;
import com.belhard.dao.impl.DbNewsImpl;
import com.belhard.dao.impl.DbPictureImpl;
import com.belhard.dao.impl.DbStatusImpl;
import com.belhard.dao.impl.DbUserImpl;

public abstract class SocialFactory {

	public static IUserDAO getUserFromFactory() {
		 return new DbUserImpl();
	}
	public static IMessageDAO getMessageFromFactory() {
		 return new DbMessageImpl();
	}
	
	public static IMusicDAO getMusicFromFactory() {
		 return new DbMusicImpl();
	}
	
	public static IPictureDAO getPictureFromFactory() {
		 return new DbPictureImpl();
	}
	
	public static INewsDAO getNewsFromFactory() {
		 return new DbNewsImpl();
	}
	
	public static IFriendsDAO getFriendsFromFactory() {
		 return new DbFriendsImpl();
	}
	
	public static IStatusDAO getStatusFromFactory() {
		 return new DbStatusImpl();
	}
}