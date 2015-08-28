package com.belhard.utils;

public enum Queries {
		 
	 GetActive("SELECT active FROM users WHERE email=? ;"),
	 GetUserByCookies("SELECT * FROM users WHERE email=? AND password=? ;"),
	 GetUserByEmail("SELECT * FROM users WHERE email=? ;"),
	 GetUserById("SELECT * FROM users WHERE idUsers=? ;"),
	 RegisterUser1("SELECT * FROM users WHERE email=? ;"),
	 RegisterUser2("INSERT INTO users (firstname,lastname,email,password,country,gender) VALUES(?,?,?,?,?,?) ;"),
	 GetNewsListById("SELECT * FROM news WHERE usersId=? ;"),
	 GetPicturesById("SELECT * FROM photos WHERE usersId=? ;"),
	 GetMusicById("SELECT * FROM music WHERE usersId=? ;"),
	 AddMusicById("UPDATE music SET musicStr=? WHERE usersId=? ;"),
	 SetMusicById("INSERT INTO music (usersId,musicStr) VALUES(?,?);"),
	 SetPicturesById("UPDATE photos SET photosStr=? WHERE usersId=? ;"),
	 SetDefaultPictureById("INSERT INTO photos (usersId,photosStr) VALUES(?,?);"),
	 GetFriendsListById("SELECT * FROM friends WHERE usersId=? ;"),
	 GetStatusBeanById("SELECT * FROM status WHERE usersId=? ;"),
	 RegisterUser3("SELECT * FROM users WHERE email=? ;");
   
	 String query;

	 private Queries(String query) {
	  this.query = query;
	 }

	 public String getQuery() {
	  return query;
	 }
	 
	}