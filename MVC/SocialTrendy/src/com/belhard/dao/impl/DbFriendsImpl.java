package com.belhard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.belhard.dao.DaoException;
import com.belhard.dao.IFriendsDAO;
import com.belhard.db.ConnectionPool;
import com.belhard.utils.Constants;
import com.belhard.utils.Queries;

public class DbFriendsImpl implements IFriendsDAO {

	@Override
	public String getFriendsList(int idUser) throws DaoException{
	    Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String friendsStr=null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.GetFriendsListById.getQuery());
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			if(rs.next()){
				friendsStr = rs.getString(Constants.FRIENDS_ID);
			}
			return friendsStr;
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}	
    }
}
