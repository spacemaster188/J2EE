package com.belhard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.belhard.dao.DaoException;
import com.belhard.dao.IMusicDAO;
import com.belhard.db.ConnectionPool;
import com.belhard.utils.Constants;
import com.belhard.utils.Queries;

public class DbMusicImpl implements IMusicDAO {

	@Override
	public String getMusicById(int idUser) throws DaoException{
	    Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String musicStr = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.GetMusicById.getQuery());
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			if(rs.next()){
				musicStr = rs.getString(Constants.MUSIC_STR);
			}
			return musicStr;
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}	
    }
	
	@Override
	public void addMusicById(int idUser, String musicStr) throws DaoException{
	    Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.AddMusicById.getQuery());
			ps.setString(1, musicStr);
			ps.setInt(2, idUser);
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}	
    }
	
	@Override
	public void setMusicById(int idUser, String musicStr) throws DaoException{
	    Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.SetMusicById.getQuery());
			ps.setInt(1, idUser);
			ps.setString(2, musicStr);
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}	
    }
	
}
