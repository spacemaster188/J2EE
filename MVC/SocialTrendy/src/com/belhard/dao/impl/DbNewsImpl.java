package com.belhard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.dao.INewsDAO;
import com.belhard.db.ConnectionPool;
import com.belhard.utils.Constants;
import com.belhard.utils.Queries;

public class DbNewsImpl implements INewsDAO {

	@Override
	public List<SocialBean> getNewsList(int idUser) throws DaoException{
	    Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List <SocialBean> newsList = new ArrayList<SocialBean>();
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.GetNewsListById.getQuery());
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			while(rs.next()){
				SocialBean sb = new SocialBean();
				sb.setId(rs.getInt(Constants.ID));
			    sb.setNewsMessage(rs.getNString(Constants.NEWS_MESSAGE));
			    sb.setDt(rs.getDate(Constants.DT));
			    newsList.add(sb);
			}
			return newsList;
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}	
    }
	
}
