package com.belhard.services;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.dao.IMessageDAO;
import com.belhard.dao.IPictureDAO;
import com.belhard.dao.IUserDAO;
import com.belhard.dao.factory.SocialFactory;
import com.belhard.utils.Constants;
import com.belhard.utils.StringUtils;

public final class SocialUsersService {
	private static final Logger logger = Logger.getLogger("SocialUsersService.class");
	public static SocialBean getUserById(int idUser) throws DaoException {
		IUserDAO userDAO = SocialFactory.getUserFromFactory();
		SocialBean user = userDAO.getValidUserById(idUser);
		IPictureDAO picturesDAO = SocialFactory.getPictureFromFactory();
		String userPictures = picturesDAO.getPicturesById(idUser);
		if(user != null && userPictures != null){
			String [] picturesMas = userPictures.split(Constants.TWODOTS);
			user.setMainPic(picturesMas[0]);
		}
		return user;
		}
	/** Message service */
    public static List<SocialBean> getInMsgsBeanList(int currId) throws UnsupportedEncodingException, DaoException {
		List<SocialBean> msgList = new ArrayList<SocialBean>();
		List<SocialBean> outlist = new ArrayList<SocialBean>();
		IMessageDAO messageDAO = SocialFactory.getMessageFromFactory();
		msgList = messageDAO.getIncomingMsgsList(currId);	
		for (SocialBean socialBean : msgList) {
			int tmpId = socialBean.getId();
			String tmpMsg = socialBean.getNewsMessage();
			Date tmpDateStr = socialBean.getDt();
			SocialBean tmp = SocialLoginService.getUserById(tmpId);
			tmp.setId(tmpId);
            tmp.setNewsMessage(tmpMsg);
            tmp.setDt(tmpDateStr);
            outlist.add(tmp);
		}
		Collections.reverse(outlist);
		return outlist;
	}
	/** Message service */
    public static void sendMessage(int currentId, int sendId, String message, HttpServletRequest req) throws UnsupportedEncodingException, DaoException {
    	HttpSession session = req.getSession();
    	IMessageDAO messageDAO = SocialFactory.getMessageFromFactory();
	    
    	Date now = new Date();
    	try {
    		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    	    String curStringDateTime = format.format(now);
    		if(StringUtils.isStrPassed(message)){
    			messageDAO.addNewMessage(currentId, sendId, curStringDateTime, message);
    	        session.setAttribute("report", "You just sended a new message");	
    		}
		} catch (IllegalArgumentException e) {
			logger.error(e);
		}
   }
	/** Message service */
	public static List<SocialBean> getOutMsgsBeanList(int currId) throws UnsupportedEncodingException, DaoException {
		List<SocialBean> msg = new ArrayList<SocialBean>();
		List<SocialBean> outlist = new ArrayList<SocialBean>();
		IMessageDAO messageDAO = SocialFactory.getMessageFromFactory();
		
        msg = messageDAO.getSendedMsgsList(currId);
		
		for (SocialBean socialBean : msg) {
			int tmpId = socialBean.getId();
			String tmpMsg = socialBean.getNewsMessage();
			Date tmpDateStr = socialBean.getDt();
			SocialBean tmp = SocialLoginService.getUserById(tmpId);
			tmp.setId(tmpId);
            tmp.setNewsMessage(tmpMsg);
            tmp.setDt(tmpDateStr);
            outlist.add(tmp);
		}
		Collections.reverse(outlist);
		return outlist;
	}
	/** Message service */
    public static List<SocialBean> getLastMessages(int currId) throws UnsupportedEncodingException, DaoException {
        List<SocialBean> outList = new ArrayList<SocialBean>();
        List<SocialBean> cuttedOutList = new ArrayList<SocialBean>();
        IMessageDAO messageDAO = SocialFactory.getMessageFromFactory();
		IUserDAO userDAO = SocialFactory.getUserFromFactory();
        /** get id of user's last incoming message */
    	int lastIncId = messageDAO.getLastIncomingId(currId);
        
    	if (lastIncId == 0){
    	return null;	
    	}
    	outList = messageDAO.getLastMsgsList(currId, lastIncId);
    	SocialBean sb = userDAO.getValidUserById(lastIncId);
    	String incomingFirstName = sb.getFirstName(); 	
    	if(Constants.EMPTY_STR.equals(incomingFirstName)){
    		incomingFirstName = Constants.USER;
    	}
    	Collections.reverse(outList);
    	
    	if(outList.size() < 5){
    	for (SocialBean socialBean : outList) {
    	cuttedOutList.add(socialBean);	
		}	
    	}else{
    		for(int i = 0; i < 4;i++){
        		SocialBean tmpSb = outList.get(i);
        		cuttedOutList.add(tmpSb);
        	}	
    	}
    	
    	for (SocialBean socialBean : cuttedOutList) {
    		if(socialBean.getFirstName() == null){
    		socialBean.setFirstName(incomingFirstName);
    		}
		}
    	Collections.reverse(cuttedOutList);
    	return cuttedOutList;
    }
    
	public static List<SocialBean> getSearchList4Users(String keyword, int currId) throws UnsupportedEncodingException, DaoException {
		List<SocialBean> outList = new ArrayList<SocialBean>();
		IUserDAO userDAO = SocialFactory.getUserFromFactory();
		outList = userDAO.UserSearchList(keyword, outList, Constants.FIRSTNAME, currId);
		outList = userDAO.UserSearchList(keyword, outList, Constants.LASTNAME, currId);
		outList = userDAO.UserSearchList(keyword, outList, Constants.EMAIL, currId);
		return outList;
	}
	
}
