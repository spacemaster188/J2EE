package by.gsu.epamlab.dao;

import java.sql.Date;
import java.util.List;
import by.gsu.epamlab.utils.Queries;
import by.gsu.epamlab.beans.TaskBean;

public interface ITaskDAO {

	void addTask(String task, int userId, Date date) throws DaoException;
	
	List<TaskBean> getTaskslistByDay(Queries when, int userId) throws DaoException;
	
	void recycleTask(int idTask) throws DaoException;
	
	void deleteTask(int idTask) throws DaoException;
	
	void restoreTask(int idTask) throws DaoException;
	
	void fixTask(int idTask) throws DaoException;

	void deleteFileRecord(int taskId, String path) throws DaoException;
	
	void addFile(int idTask, String filename) throws DaoException;
	
	void deleteFile(String filename) throws DaoException;
}