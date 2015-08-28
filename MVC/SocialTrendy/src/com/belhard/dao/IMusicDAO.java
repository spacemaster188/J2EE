package com.belhard.dao;

public interface IMusicDAO {
	String getMusicById(int idUser) throws DaoException;
	void addMusicById(int idUser, String musicStr) throws DaoException;
	void setMusicById(int idUser, String musicStr) throws DaoException;
}