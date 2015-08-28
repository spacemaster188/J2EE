package com.belhard.services;

import java.util.ArrayList;
import java.util.List;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.dao.IFriendsDAO;
import com.belhard.dao.IMusicDAO;
import com.belhard.dao.INewsDAO;
import com.belhard.dao.IPictureDAO;
import com.belhard.dao.IStatusDAO;
import com.belhard.dao.factory.SocialFactory;
import com.belhard.utils.Constants;

public final class SocialListsService {
	
	public static List<SocialBean> getNewsList (SocialBean currentUser) throws DaoException {
		int idUser = currentUser.getId();
		INewsDAO newsDAO = SocialFactory.getNewsFromFactory();
		List<SocialBean> newsList = newsDAO.getNewsList(idUser);	
		return newsList.size()!=0?newsList:null;
		}
	
	public static SocialBean getStatusBean (SocialBean currentUser) throws DaoException {
		int idUser = currentUser.getId();
		IStatusDAO statusDAO = SocialFactory.getStatusFromFactory();
		SocialBean statusBean = statusDAO.getStatusBean(idUser);	
		return statusBean;
		}
	
	public static List<SocialBean> getFriendsList (SocialBean currentUser) throws DaoException {
		List<SocialBean> friendsList = new ArrayList<SocialBean>();
		int idUser = currentUser.getId();
		IFriendsDAO friendsDAO = SocialFactory.getFriendsFromFactory();
		String friendsStr = friendsDAO.getFriendsList(idUser);	
		if(friendsStr!=null){
			String[] friendsMas = friendsStr.split(Constants.TWODOTS);
			for (String stringElement : friendsMas) {
				SocialBean friendUser = new SocialBean();
				int friendId = Integer.parseInt(stringElement);
				friendUser = SocialUsersService.getUserById(friendId);
				friendsList.add(friendUser);
			}
		}
		return friendsList.size()!=0?friendsList:null;
		}
	
	public static List<SocialBean> getPicturesList (SocialBean currentUser) throws DaoException {
		List<SocialBean> picturesList = new ArrayList<SocialBean>();
		int idUser = currentUser.getId();
		IPictureDAO picturesDAO = SocialFactory.getPictureFromFactory();
		String userPictures = picturesDAO.getPicturesById(idUser);
		if(userPictures!=null){
			String [] picturesMas = userPictures.split(Constants.TWODOTS);
				for(int i=1;i<picturesMas.length;i++){
					SocialBean sb = new SocialBean();
					   sb.setMainPic(picturesMas[i]);
					   picturesList.add(sb);
				}
		}else{
			picturesDAO.setDefaultPictureById(idUser, Constants.DEFAULT_PIC);
		}
		return picturesList.size()!=0?picturesList:null;
		}
	
	public static List<SocialBean> getMusicList (SocialBean currentUser) throws DaoException {
		List<SocialBean> musicList = new ArrayList<SocialBean>();
		int idUser = currentUser.getId();
		IMusicDAO musicDAO = SocialFactory.getMusicFromFactory();
		String userMusic = musicDAO.getMusicById(idUser);
		if(userMusic!=null){
			String [] musicMas = userMusic.split(Constants.TWODOTS);
			for (String stringElement : musicMas) {
				SocialBean sb = new SocialBean();
				   sb.setMus_Str(stringElement);
				   musicList.add(sb);
			}
		}
		return musicList.size()!=0?musicList:null;
		}
	
}
