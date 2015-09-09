package com.belhard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.dao.IUserDAO;
import com.belhard.db.ConnectionPool;
import com.belhard.utils.Constants;
import com.belhard.utils.Queries;

public class DbUserImpl implements IUserDAO {
	public SocialBean checkLoginUser(String email, String pass)
			throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SocialBean sb = new SocialBean();
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.GetUserByCookies
					.getQuery());
			ps.setString(1, email);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if (rs.next()) {
				sb.setId(rs.getInt(Constants.IDUSERS));
				sb.setFirstName(rs.getString(Constants.FIRSTNAME));
				sb.setLastName(rs.getString(Constants.LASTNAME));
				sb.setEmail(rs.getString(Constants.EMAIL));
				sb.setGender(rs.getBoolean(Constants.GENDER));
				sb.setCountry(rs.getString(Constants.COUNTRY));
				sb.setPermissions(rs.getBoolean(Constants.PERMISSIONS));
				sb.setActive(rs.getBoolean(Constants.ACTIVE));
				return sb;
			}
			return null;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}
	}

	public SocialBean registerUser(String firstname, String lastname,
			String email, String pass, String country, boolean gender)
			throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.RegisterUser1.getQuery());
			ps.setString(1, email);
			ps2 = connection.prepareStatement(Queries.RegisterUser2.getQuery());
			ps2.setString(1, firstname);
			ps2.setString(2, lastname);
			ps2.setString(3, email);
			ps2.setString(4, pass);
			ps2.setString(5, country);
			ps2.setBoolean(6, gender);
			ps3 = connection.prepareStatement(Queries.RegisterUser3.getQuery());
			ps3.setString(1, email);
			synchronized (DbUserImpl.class) {
				rs = ps.executeQuery();
				if (rs.next()) {
					return null;
				}
				ps2.executeUpdate();
				/** get user's id */
				rs = ps3.executeQuery();
				int idUser = 0;
				if (rs.next()) {
					idUser = rs.getInt(Constants.IDUSERS);
					SocialBean sb = new SocialBean();
					sb.setId(idUser);
					sb.setFirstName(firstname);
					sb.setLastName(lastname);
					sb.setEmail(email);
					sb.setPassword(pass);
					sb.setCountry(country);
					sb.setGender(gender);
					return sb;
				}
			}
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}
		return null;
	}

	public SocialBean getValidUserById(int idUser) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SocialBean sb = new SocialBean();
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.GetUserById.getQuery());
			ps.setInt(1, idUser);
			rs = ps.executeQuery();
			if (rs.next()) {
				sb.setId(idUser);
				sb.setFirstName(rs.getString(Constants.FIRSTNAME));
				sb.setLastName(rs.getString(Constants.LASTNAME));
				sb.setEmail(rs.getString(Constants.EMAIL));
				sb.setGender(rs.getBoolean(Constants.GENDER));
				sb.setCountry(rs.getString(Constants.COUNTRY));
				sb.setPermissions(rs.getBoolean(Constants.PERMISSIONS));
				sb.setActive(rs.getBoolean(Constants.ACTIVE));
				return sb;
			}
			return null;
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}
	}
	
	public List<SocialBean> UserSearchList(String keyword, List<SocialBean> lst, String column, int currId) throws DaoException {
    	Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement("SELECT * FROM USERS WHERE  "+column+" LIKE '%"+keyword+"%' AND ACTIVE=TRUE ;");
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(Constants.IDUSERS);
				String firstname = rs.getString(Constants.FIRSTNAME);
				String lastname = rs.getString(Constants.LASTNAME);
				String email = rs.getString(Constants.EMAIL);
				boolean gender = rs.getBoolean(Constants.GENDER);
				String country = rs.getString(Constants.COUNTRY);
				SocialBean sb = new SocialBean();
				sb.setId(id);
                sb.setFirstName(firstname);
                sb.setLastName(lastname);
                sb.setEmail(email);
                sb.setGender(gender);
                sb.setCountry(country); 
                if(currId != id){
                	lst.add(sb);	
				} 
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
