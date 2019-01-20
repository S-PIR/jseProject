import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;

import by.gsu.epamlab.ConnectionManager;
import static by.gsu.epamlab.constants.Constants.*;
import static by.gsu.epamlab.constants.SqlQuery.*;

public class Runner1 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			sc = new Scanner(new FileReader(INPUT_CSV));
			sc.useLocale(Locale.ENGLISH);
			con = ConnectionManager.createConnection();
			st = con.createStatement();
			st.executeUpdate(CLEAR_LOGINS + CLEAR_TESTS + CLEAR_RESULTS);
			ps = con.prepareStatement(INSERT_INTO_LOGINS + ""
				+ INSERT_INTO_TESTS 
				+ GET_LOGIN_ID 
				+ GET_TEST_ID 
				+ INSERT_INTO_RESULTS);
			while (sc.hasNext()) {
				System.out.println("WAF!");
				String[] tmp = sc.nextLine().split(DELIMITER);
				String student = tmp[STUDENT_INDEX];
				String test = tmp[TEST_INDEX];
				Date date = Date.valueOf(tmp[DATE_INDEX]);
				int mark = Integer.parseInt(tmp[MARK_INDEX]);
				ps.setString(LOGIN_NAME_POSITION, student);
				ps.setString(TEST_NAME_POSITION, test);
				ps.setString(DESIRED_LOGIN_NAME_POSITION, student);
				ps.setString(DESIRED_TEST_NAME_POSITION, test);
				ps.setDate(DATE_POSITION, date);
				ps.setInt(MARK_POSITION, mark);
				ps.addBatch();
			}
			ps.executeBatch();
			
			rs = st.executeQuery(GET_AVG_MARK);
//			while (rs.next()) {
//				System.out.printf("s.10f.2", rs.getString(1), rs.getDouble(2));
//			}

		} catch (FileNotFoundException e) {
			System.err.println(FILE_NOT_FOUND);
		} catch (SQLException e) {
			System.err.println(DB_CONNECTION_ERROR);
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
			ConnectionManager.closeResultSet(rs);
			ConnectionManager.closeStatement(st);
			ConnectionManager.closePreparedStatement(ps);
			ConnectionManager.closeConnection(con);
		}

	}

}