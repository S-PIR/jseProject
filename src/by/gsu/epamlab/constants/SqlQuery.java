package by.gsu.epamlab.constants;

public class SqlQuery {
	public static final String CLEAR_RESULTS = "TRUNCATE TABLE results; ";
	public static final String CLEAR_TESTS = "TRUNCATE TABLE tests; ";
	public static final String CLEAR_LOGINS = "TRUNCATE TABLE logins; ";

	public static final String INSERT_INTO_LOGINS = "INSERT IGNORE INTO logins VALUES(null, ?);";
	public static final String INSERT_INTO_TESTS = "INSERT IGNORE INTO tests VALUES (null, ?);";
	public static final String GET_LOGIN_ID = "set @loginID = (SELECT idLogin FROM logins WHERE name = ?);";
	public static final String GET_TEST_ID = "set @testID = (SELECT idTest FROM tests WHERE name = ?);";
	public static final String INSERT_INTO_RESULTS = "INSERT INTO results VALUES (@loginID, @testID, ?, ?);";
	
	public static final int LOGIN_NAME_POSITION = 1;
	public static final int TEST_NAME_POSITION = 2;
	public static final int DESIRED_LOGIN_NAME_POSITION = 3;
	public static final int DESIRED_TEST_NAME_POSITION = 4;
	public static final int DATE_POSITION = 5;
	public static final int MARK_POSITION = 6;

	public static final String GET_AVG_MARK = "SELECT logins.name AS login, AVG(mark) AS avgMark"  
			+ "FROM results JOIN logins ON idLogin=loginId"   
			+ "GROUP BY login"  
			+ "ORDER BY avgMark DESC";


}
