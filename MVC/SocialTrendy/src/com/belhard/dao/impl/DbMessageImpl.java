package com.belhard.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.dao.IMessageDAO;
import com.belhard.db.ConnectionPool;
import com.belhard.utils.Constants;
import com.belhard.utils.Queries;

public class DbMessageImpl implements IMessageDAO {
	@Override
	public List<SocialBean> getIncomingMsgsList(int currId) throws DaoException {
		List <SocialBean> lst = new ArrayList<SocialBean>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(Queries.GetMessagesByIdTo.getQuery());
			ps.setInt(1, currId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id_from = rs.getInt(Constants.IDFROM);
				String message = rs.getString(Constants.MESSAGE);
				Date msg_date = rs.getDate(Constants.DT);
				SocialBean sb = new SocialBean();
				sb.setId(id_from);
                sb.setNewsMessage(message);
                sb.setDt(msg_date);
                lst.add(sb);
			}
			connection.commit();
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}
		return lst;
	}
	
	public void addNewMessage(int currId, int sendId, String curStringDateTime, String message) throws DaoException{
    	Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			connection.setAutoCommit(false);
			synchronized (DbMessageImpl.class) {
				ps=connection.prepareStatement(Queries.InsertMessage.getQuery());
				ps.setInt(1, currId);
				ps.setInt(2, sendId);
				ps.setString(3, curStringDateTime);
				ps.setString(4, message);
				ps.executeUpdate();
				connection.commit();
			}
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps);
		}
    }

	@Override
	public List<SocialBean> getSendedMsgsList(int currId) throws DaoException {
		ArrayList<SocialBean> lst = new ArrayList<SocialBean>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	
		try {
			connection = ConnectionPool.getPool().getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(Queries.GetMessagesByIdFrom.getQuery());
			ps.setInt(1, currId);
			rs = ps.executeQuery();	
			while (rs.next()) {
				int id_to = rs.getInt(Constants.IDTO);
				String message = rs.getString(Constants.MESSAGE);
				Date msg_date = rs.getDate(Constants.DT);
				SocialBean sb = new SocialBean();
				sb.setId(id_to);
                sb.setNewsMessage(message);
                sb.setDt(msg_date);
                lst.add(sb);
			}
			connection.commit();
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}
		return lst;
	}
	
	public int getLastIncomingId(int currentId) throws DaoException {
		int lastIncomingId = 0;
    	Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		try {
			connection = ConnectionPool.getPool().getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(Queries.GetMessagesByIdTo.getQuery());
			ps.setInt(1, currentId);
			rs = ps.executeQuery();	
			while (rs.next()) {	
			lastIncomingId = rs.getInt(Constants.IDFROM);	    
			}	
			connection.commit();
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}
		return lastIncomingId;
	}

	public List<SocialBean> getLastMsgsList(int currentId, int incomingId) throws DaoException {
		List<SocialBean> lst = new ArrayList<SocialBean>();
    	Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = ConnectionPool.getPool().getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(Queries.GetMessages.getQuery());
			ps.setInt(1, currentId);
			ps.setInt(2, incomingId);
			ps.setInt(3, incomingId);
			ps.setInt(4, currentId);
			rs = ps.executeQuery();
			while (rs.next()) {
		    SocialBean sb = new SocialBean();
			int idFrom = rs.getInt(Constants.IDFROM);
			String msg = rs.getString(Constants.MESSAGE);
			Date msg_date = rs.getDate(Constants.DT);
			
			if(idFrom==currentId){
			sb.setFirstName(Constants.ME);
			}
			sb.setId(incomingId);
			sb.setNewsMessage(msg);
			sb.setDt(msg_date);
			lst.add(sb);   
			}
			connection.commit();
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}
		return lst;
	}
	
}
