package com.belhard.services;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.dao.IPictureDAO;
import com.belhard.dao.IUserDAO;
import com.belhard.dao.factory.SocialFactory;
import com.belhard.utils.Constants;

public final class SocialUsersService {
	
	public static SocialBean getUserById(int idUser) throws DaoException {
		IUserDAO userDAO = SocialFactory.getUserFromFactory();
		SocialBean user = userDAO.getValidUserById(idUser);
		IPictureDAO picturesDAO = SocialFactory.getPictureFromFactory();
		String userPictures = picturesDAO.getPicturesById(idUser);
		if(user!=null && userPictures!=null){
			String [] picturesMas = userPictures.split(Constants.TWODOTS);
			user.setMainPic(picturesMas[0]);
		}
		return user;
		}
	
}
