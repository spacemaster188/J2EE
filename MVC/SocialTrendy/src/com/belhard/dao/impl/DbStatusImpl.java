package com.belhard.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.belhard.beans.SocialBean;
import com.belhard.dao.DaoException;
import com.belhard.dao.IStatusDAO;
import com.belhard.db.ConnectionPool;
import com.belhard.utils.Constants;
import com.belhard.utils.Queries;

public class DbStatusImpl implements IStatusDAO {

	@Override
	public SocialBean getStatusBean(int idUser) throws DaoException {
	    Connection connection = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			SocialBean statusBean = new SocialBean();
			try {
				connection = ConnectionPool.getPool().getConnection();
				ps = connection.prepareStatement(Queries.GetStatusBeanById.getQuery());
				ps.setInt(1, idUser);
				rs = ps.executeQuery();
				if(rs.next()){
					statusBean.setStatus(rs.getString(Constants.STATUS));
				    return statusBean;
				}
				return null;
			}catch (SQLException e) {
				throw new DaoException();
			} finally {
				ConnectionPool.closeDbResources(connection, ps, rs);
			}	
	    }

}
