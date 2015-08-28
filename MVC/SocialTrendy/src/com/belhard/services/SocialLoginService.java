package com.belhard.services;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.dao.IPictureDAO;
import com.belhard.dao.IUserDAO;
import com.belhard.dao.factory.SocialFactory;
import com.belhard.utils.Constants;


public final class SocialLoginService {
	
	public static SocialBean checkLoginUser(String email, String pass) throws DaoException {
		SocialBean updatedUser1 = new SocialBean();
		IUserDAO userDAO = SocialFactory.getUserFromFactory();
		updatedUser1 = userDAO.checkLoginUser(email,pass);
		if(updatedUser1 != null){
			return getUpdatedUserByBean(updatedUser1);
		}
		return null;
		}
	
	public static SocialBean getUpdatedUserByBean(SocialBean user) throws DaoException {
		SocialBean updatedUser = new SocialBean();
		int idUser = user.getId();
		IUserDAO userDAO = SocialFactory.getUserFromFactory();
		updatedUser = userDAO.getValidUserById(idUser);              //chk! it
		if(updatedUser != null){
			updatedUser.setMainPic(getMainUserPicture(idUser));      //add update status'n'lastSeen!
		}
		return updatedUser;
		}
	
	public static SocialBean RegisterUser(String firstname, String lastname, String email, String pass,String country, String gender) throws DaoException {
		SocialBean registeredUser = new SocialBean();
		IUserDAO userDAO = SocialFactory.getUserFromFactory();
		boolean genderBool = getBoolean(gender);
		registeredUser = userDAO.registerUser(firstname, lastname, email, pass, country, genderBool);
		if(registeredUser!=null){
			int idUser = registeredUser.getId();
			IPictureDAO picturesDAO = SocialFactory.getPictureFromFactory();
			picturesDAO.setDefaultPictureById(idUser, Constants.DEFAULT_PIC);
			registeredUser.setMainPic(Constants.DEFAULT_PIC);
		}
		return registeredUser;
		}
	
	private static boolean getBoolean(String str) throws DaoException{
		try {
			if(str.equals(Constants.TRUE)){
				return true;
			}
		} catch (Exception e) {
			throw new DaoException();
		}
		
		return false;
	}
	
	public static String getMainUserPicture(int idUser) throws DaoException{
		IPictureDAO picturesDAO = SocialFactory.getPictureFromFactory();
		String userPictures = picturesDAO.getPicturesById(idUser);
		
		String [] picturesMas = userPictures.split(Constants.TWODOTS);
		String mainPic = picturesMas[0];

		return mainPic;
	}
	
	public static SocialBean getUserById(int idUser) throws DaoException {          //get permissions from DB by id
		IUserDAO userDAO = SocialFactory.getUserFromFactory();
		SocialBean user = userDAO.getValidUserById(idUser);
		
		return user;		
		}
}
