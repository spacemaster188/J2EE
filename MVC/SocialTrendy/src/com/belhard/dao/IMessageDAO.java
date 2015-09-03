package com.belhard.dao;

import java.util.List;

import com.belhard.beans.SocialBean;

public interface IMessageDAO {
	List<SocialBean> getIncomingMsgsList(int currId) throws DaoException;
	void addNewMessage(int currId, int sendId, String curStringDateTime, String message) throws DaoException;
	List<SocialBean> getSendedMsgsList(int currId) throws DaoException;
	int getLastIncomingId(int currentId) throws DaoException;
	List<SocialBean> getLastMsgsList(int currentId, int incomingId) throws DaoException;
}
