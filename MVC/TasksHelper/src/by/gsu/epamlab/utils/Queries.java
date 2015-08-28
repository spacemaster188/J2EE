package by.gsu.epamlab.utils;

public enum Queries {
	
	 Today("SELECT * FROM tasks WHERE usersId=? AND dt <= DATE(NOW()) AND fixed=0 AND recycled=0 ;"),
	 Tomorrow("SELECT * FROM tasks WHERE usersId=? AND dt = (DATE(NOW())+1) AND fixed=0 AND recycled=0 ;"),
	 Someday("SELECT * FROM tasks WHERE usersId=? AND dt>(DATE(NOW())+1) AND fixed=0 AND recycled=0 ;"),
	 Fixed("SELECT * FROM tasks WHERE usersId=? AND fixed=1 AND recycled=0 ;"),
	 Recycled("SELECT * FROM tasks WHERE usersId=? AND recycled=1 ;"),
	 AddTask("INSERT INTO tasks (usersId,task,dt) VALUES(?,?,?);"),
	 RecycleTask("UPDATE tasks SET recycled=true WHERE idTasks=? ;"),
	 DeleteTask("DELETE FROM tasks WHERE idTasks=? ;"),
	 DeleteFile("UPDATE tasks SET file='' WHERE idTasks=? ;"),
	 GetFilenameDeleteFile("SELECT * FROM tasks WHERE idTasks=? ;"),
	 RestoreTask("UPDATE tasks SET recycled=false WHERE idTasks=? ;"),
	 FixTask("UPDATE tasks SET fixed=true WHERE idTasks=? ;"),
	 AddFile("UPDATE tasks SET file=? WHERE idTasks=? ;"),
	 CheckLoginUser("SELECT * FROM users WHERE login=? AND password=? ;"),
	 RegisterUser1("SELECT * FROM users WHERE login=? OR email=? AND email!=? ;"),
	 RegisterUser2("INSERT INTO USERS (login,password,email,firstname,lastname) VALUES(?,?,?,?,?) ;"),
	 RegisterUser3("SELECT * FROM users WHERE login=? ;");
	 
	 String query;

	 private Queries(String query) {
	  this.query = query;
	 }

	 public String getQuery() {
	  return query;
	 }
	 
	}