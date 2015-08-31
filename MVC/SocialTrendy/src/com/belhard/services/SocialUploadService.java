package com.belhard.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.fileupload.FileItemStream;

import com.belhard.dao.DaoException;
import com.belhard.dao.IMusicDAO;
import com.belhard.dao.IPictureDAO;
import com.belhard.dao.factory.SocialFactory;
import com.belhard.utils.Constants;

public class SocialUploadService {
	
	public static boolean processFile(String path, FileItemStream item, String fieldName){
		String upload_folder = Constants.EMPTY_STR;	
		switch (fieldName) {
		case Constants.MUSIC_ADD:{
		upload_folder = Constants.MUSIC;
		break;	
		}
		default:{
		upload_folder = Constants.IMAGES;	
		break;	
		}
		}
		try{
			File f = new File(path + File.separator+upload_folder);	
			if(!f.exists())	f.mkdir();	
				File savedFile = new File(f.getAbsolutePath() + File.separator + item.getName());
				FileOutputStream fos = new FileOutputStream(savedFile);
				InputStream is = item.openStream();
				int x = 0;
				byte[]b = new byte[1024];
				while((x = is.read(b))!= -1){
				fos.write(b, 0, x);	
				}
				fos.flush();
				fos.close();
				return true;				
		}catch(Exception e){	
		}
		return false;	
	}
	
	public static void doUploadFileProcess(int idUser, String fileName, String fieldName) throws UnsupportedEncodingException, DaoException {  
    	switch (fieldName) {
		case Constants.MUSIC_ADD:{
			addMusic(idUser, fileName);
			break;	
		}
		case Constants.FILE_ADD:{
			addPicture(idUser, fileName);
			break;	
		}
		case Constants.FILE_MAIN:{
			setMainPicture(idUser, fileName);
			break;	
		}
		default:{
			break;	
		}
		}
	}
	
    public static void addMusic(int idUser, String musicStr) throws UnsupportedEncodingException, DaoException {
    	String finalMusicStr;
    	IMusicDAO musicDAO = SocialFactory.getMusicFromFactory();
    	String getMusicStr = musicDAO.getMusicById(idUser);
    	
    	if(getMusicStr!=null){
    		finalMusicStr = getMusicStr + Constants.TWODOTS + musicStr;
    		musicDAO.addMusicById(idUser, finalMusicStr);
    	}else{
    		musicDAO.setMusicById(idUser, musicStr);
    	}
 	}
    
    public static void addPicture(int idUser, String pictureStr) throws UnsupportedEncodingException, DaoException {
    	String finalPictureStr;
    	IPictureDAO pictureDAO = SocialFactory.getPictureFromFactory();
    	String getPicturesStr = pictureDAO.getPicturesById(idUser);
    	finalPictureStr = getPicturesStr + Constants.TWODOTS + pictureStr;
    	pictureDAO.setPicturesById(idUser, finalPictureStr);
	}
    
    public static void setMainPicture(int idUser, String mainPicStr) throws UnsupportedEncodingException, DaoException {
    	IPictureDAO pictureDAO = SocialFactory.getPictureFromFactory();
    	String getPicturesStr = pictureDAO.getPicturesById(idUser);
    	String finalPicturesStr = Constants.EMPTY_STR;
    	if(getPicturesStr.equals(Constants.DEFAULT_PIC)){
    		finalPicturesStr = mainPicStr;
    	}else{
    		String [] picturesMas = getPicturesStr.split(Constants.TWODOTS);
        	picturesMas[0] = mainPicStr;
        	for (String stringElement : picturesMas) {
        		finalPicturesStr = finalPicturesStr + stringElement + Constants.TWODOTS;
    		}	
    	}
    	pictureDAO.setPicturesById(idUser, finalPicturesStr);
	}
}
