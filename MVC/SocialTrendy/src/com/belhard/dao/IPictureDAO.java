package com.belhard.dao;

public interface IPictureDAO {
	String getPicturesById(int idUser) throws DaoException;
	void setPicturesById(int idUser, String pictureStr) throws DaoException;
	void setDefaultPictureById(int idUser, String pictureStr) throws DaoException;	
}