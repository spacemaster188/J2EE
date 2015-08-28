package by.gsu.epamlab.beans;

import java.io.Serializable;
import java.sql.Date;

public class TaskBean implements Serializable{
private static final long serialVersionUID = 1L;

    private int taskId;
	
	private String file;
	
	private String task;
	
    private Date date;
	
	public TaskBean() {
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
