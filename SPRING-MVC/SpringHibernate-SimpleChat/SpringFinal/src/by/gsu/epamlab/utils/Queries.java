package by.gsu.epamlab.utils;

public enum Queries {
	 WhereLast10Id("WHERE idMsg BETWEEN (SELECT MAX(idMsg) FROM MessageEntity)-10 AND (SELECT MAX(idMsg) FROM MessageEntity)"),
	 WhereUserId("WHERE usersId="),
	 TodayQuery(" AND dt <= DATE(NOW()) AND fixed=0 AND recycled=0"),
	 TomorrowQuery(" AND dt = (DATE(NOW())+1) AND fixed=0 AND recycled=0"),
	 SomedayQuery(" AND dt>(DATE(NOW())+1) AND fixed=0 AND recycled=0"),
	 FixedQuery(" AND fixed=1 AND recycled=0"),
	 RecycledQuery(" AND recycled=1");
	 
	 String query;

	 private Queries(String query) {
	  this.query = query;
	 }

	 public String getQuery() {
	  return query;
	 }
	 
	}