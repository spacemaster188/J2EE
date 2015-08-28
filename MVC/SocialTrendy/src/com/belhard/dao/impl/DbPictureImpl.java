package com.belhard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.belhard.dao.DaoException;
import com.belhard.dao.IPictureDAO;
import com.belhard.db.ConnectionPool;
import com.belhard.utils.Constants;
import com.belhard.utils.Queries;

public class DbPictureImpl implements IPictureDAO {

	@Override
	public String getPicturesById(int idUser) throws DaoException{
	    Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String picturesStr = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.GetPicturesById.getQuery());
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			if(rs.next()){
				picturesStr = rs.getString(Constants.PHOTOS_STR);
			}
			return picturesStr;
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}	
    }
	
	@Override
	public void setPicturesById(int idUser, String pictureStr) throws DaoException{
	    Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.SetPicturesById.getQuery());
			ps.setString(1, pictureStr);
			ps.setInt(2, idUser);
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}	
    }
	
	@Override
	public void setDefaultPictureById(int idUser, String pictureStr) throws DaoException{
	    Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.SetDefaultPictureById.getQuery());
			ps.setInt(1, idUser);
			ps.setString(2, pictureStr);
			ps.executeUpdate();
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}	
    }

}
