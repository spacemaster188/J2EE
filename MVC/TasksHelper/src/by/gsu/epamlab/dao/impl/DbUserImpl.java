package by.gsu.epamlab.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.gsu.epamlab.beans.UserBean;
import by.gsu.epamlab.dao.DaoException;
import by.gsu.epamlab.dao.IUserDAO;
import by.gsu.epamlab.db.ConnectionPool;
import by.gsu.epamlab.utils.Constants;
import by.gsu.epamlab.utils.Queries;

public class DbUserImpl implements IUserDAO{
	
	public UserBean checkLoginUser(String login, String pass) throws DaoException{
    	Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserBean tBean = new UserBean();
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.CheckLoginUser.getQuery());
			ps.setString(1, login);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if(rs.next()){
				tBean.setId(rs.getInt(Constants.IDUSERS));
				tBean.setLogin(rs.getString(Constants.LOGIN));
				tBean.setEmail(rs.getString(Constants.EMAIL));
				tBean.setFirstname(rs.getString(Constants.FIRSTNAME));
				tBean.setLastname(rs.getString(Constants.LASTNAME));
				return tBean;
			}else{
				return null;
			}
		}catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}	
    }
 
 public UserBean registerUser(String login, String pass, String email,String firstname, String lastname) throws DaoException{
    	Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		
			try {
				connection = ConnectionPool.getPool().getConnection();
				ps = connection.prepareStatement(Queries.RegisterUser1.getQuery());
				ps.setString(1, login);
				ps.setString(2, email);
				ps.setString(3, Constants.EMPTY_STR);
				ps2 = connection.prepareStatement(Queries.RegisterUser2.getQuery());
				ps2.setString(1, login);
				ps2.setString(2, pass);
				ps2.setString(3, email);
				ps2.setString(4, firstname);
				ps2.setString(5, lastname);
				ps3 = connection.prepareStatement(Queries.RegisterUser3.getQuery());
				ps3.setString(1, login);
				synchronized (DbUserImpl.class) {
				rs = ps.executeQuery();
				if(rs.next()){
				  return null;
				}
                ps2.executeUpdate();                                     //register new user
				}
				rs = ps3.executeQuery();                                 //get new user updated id
				if(rs.next()){
						int idUser = rs.getInt(Constants.IDUSERS);
						UserBean bean = new UserBean();
						bean.setId(idUser);
						bean.setLogin(login);
						bean.setEmail(email);
						bean.setFirstname(firstname);
						bean.setLastname(lastname);
						return bean;
					}
			}catch (SQLException e) {
				throw new DaoException();
			} finally {
				ConnectionPool.closeDbResources(connection, ps, rs);
			}	
		return null;
    }
 
}