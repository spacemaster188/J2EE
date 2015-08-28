package com.belhard.dao;

import java.util.List;

import com.belhard.beans.SocialBean;

public interface INewsDAO {
	List<SocialBean> getNewsList(int idUser) throws DaoException;
}