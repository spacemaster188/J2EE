package by.gsu.epamlab.utils;

public enum Queries {
	 WhereLast10Id("WHERE idMsg BETWEEN (SELECT MAX(idMsg) FROM MessageEntity)-10 AND (SELECT MAX(idMsg) FROM MessageEntity)"),
	 WhereUserId("WHERE usersId=");
	 
	 String query;

	 private Queries(String query) {
	  this.query = query;
	 }

	 public String getQuery() {
	  return query;
	 }
	 
	}