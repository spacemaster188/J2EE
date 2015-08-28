package by.gsu.epamlab.dao.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.gsu.epamlab.beans.TaskBean;
import by.gsu.epamlab.dao.DaoException;
import by.gsu.epamlab.dao.ITaskDAO;
import by.gsu.epamlab.db.ConnectionPool;
import by.gsu.epamlab.utils.Constants;
import by.gsu.epamlab.utils.Queries;

public class DbTaskImpl implements ITaskDAO {

	public void addTask(String task, int userId, Date date) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.AddTask.getQuery());
			ps.setInt(1, userId);
			ps.setString(2, task);
			ps.setDate(3, date);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps);
		}
	}

	public List<TaskBean> getTaskslistByDay(Queries when, int userId)
			throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<TaskBean> beanList = new ArrayList<TaskBean>();
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(when.getQuery());
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				TaskBean bean = new TaskBean();
				bean.setTaskId(rs.getInt(Constants.IDTASKS));
				bean.setTask(rs.getNString(Constants.TASK));
				bean.setFile(rs.getString(Constants.FILE));
				bean.setDate(rs.getDate(Constants.DT));
				beanList.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps, rs);
		}
		return beanList;
	}

	public void recycleTask(int idTask) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.RecycleTask.getQuery());
			ps.setInt(1, idTask);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps);
		}
	}

	public void deleteTask(int idTask) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.DeleteTask.getQuery());
			ps.setInt(1, idTask);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps);
		}
	}

	public void deleteFileRecord(int taskId, String path) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		String filename = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps2 = connection.prepareStatement(Queries.GetFilenameDeleteFile.getQuery());
			ps2.setInt(1,taskId);
			rs = ps2.executeQuery();
			if(rs.next()){
				filename = rs.getString(Constants.FILE);
			}
		
			ps = connection.prepareStatement(Queries.DeleteFile.getQuery());
			ps.setInt(1, taskId);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps);
		}
		try {
			File preparedFile = new File(path + File.separator + filename);
			preparedFile.delete();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void restoreTask(int idTask) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.RestoreTask.getQuery());
			ps.setInt(1, idTask);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps);
		}
	}

	public void fixTask(int idTask) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.FixTask.getQuery());
			ps.setInt(1, idTask);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps);
		}
	}

	public void addFile(int idTask, String filename) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = ConnectionPool.getPool().getConnection();
			ps = connection.prepareStatement(Queries.AddFile.getQuery());
			ps.setString(1, filename);
			ps.setInt(2, idTask);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		} finally {
			ConnectionPool.closeDbResources(connection, ps);
		}
	}

	@Override
	public void deleteFile(String filename) throws DaoException {
	}

}
