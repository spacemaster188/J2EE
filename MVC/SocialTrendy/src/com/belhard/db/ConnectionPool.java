package com.belhard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.belhard.utils.Constants;

public final class ConnectionPool {

	private static ConnectionPool pool = null;

	public static ConnectionPool getPool() {
		if (pool == null) {
			try {
				pool = new ConnectionPool();
			} catch (NamingException e) {
				throw new RuntimeException(e);
			}
		}

		return pool;
	}

	private DataSource dataSource = null;

	private ConnectionPool() throws NamingException {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup(Constants.INIT_CONTEXT);
			dataSource = (DataSource) envContext.lookup(Constants.DATA_SOURCE);
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public static void closeDbResources(Connection connection, PreparedStatement statement) {
		closeStatement(statement);
		closeConnection(connection);
	}

	public static void closeDbResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
		closeResultSet(resultSet);
		closeStatement(statement);
		closeConnection(connection);
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void closeStatement(PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
			}
		}
	}
}
