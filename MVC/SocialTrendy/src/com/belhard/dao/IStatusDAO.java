package com.belhard.dao;

import com.belhard.beans.SocialBean;

public interface IStatusDAO {
	SocialBean getStatusBean(int idUser) throws DaoException;
}