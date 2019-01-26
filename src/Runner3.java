import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Scanner;

import by.gsu.epamlab.ConnectionManager;
import by.gsu.epamlab.beans.Result;

import static by.gsu.epamlab.constants.Constants.*;
import static by.gsu.epamlab.constants.SqlQuery.*;

public class Runner3 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			sc = new Scanner(new FileReader(INPUT_CSV_3));
			sc.useLocale(Locale.ENGLISH);
			con = ConnectionManager.createConnection();
			st = con.createStatement();
			st.executeUpdate(CLEAR_LOGINS + CLEAR_TESTS + CLEAR_RESULTS);
			ps = con.prepareStatement(
					INSERT_INTO_LOGINS + "" + INSERT_INTO_TESTS + GET_LOGIN_ID + GET_TEST_ID + INSERT_INTO_RESULTS);
			while (sc.hasNext()) {
				String[] tmp = sc.nextLine().split(DELIMITER);
				Result result = new Result();
				result.setStudent(tmp[0]);
				result.setTest(tmp[1]);
				result.setDate(tmp[2]);
				result.setMark(tmp[3]);
				ps.setString(LOGIN_NAME_POSITION, result.getStudent());
				ps.setString(TEST_NAME_POSITION, result.getTest());
				ps.setString(DESIRED_LOGIN_NAME_POSITION, result.getStudent());
				ps.setString(DESIRED_TEST_NAME_POSITION, result.getTest());
				ps.setDate(DATE_POSITION, result.getDate());
				ps.setInt(MARK_POSITION, result.getMark());
				ps.addBatch();
			}
			ps.executeBatch();
			ps.close();
			rs = st.executeQuery(GET_AVG_MARK);
			while (rs.next()) {
				System.out.printf("%-10s%.2f\n", rs.getString(1), rs.getDouble(2));
			}
			rs.close();
			List<Result> testResults = new LinkedList<>();
			System.out.println();
			ps = con.prepareStatement(GET_RESULTS_BY_DATE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Result result = new Result(rs.getString(LOGIN_NAME_POSITION), 
						rs.getString(TEST_NAME_POSITION), 
						rs.getDate(RESULT_DATE_POSITION), 
						rs.getInt(RESULT_MARK_POSITION));
				testResults.add(result);
				System.out.println(result);
			}
			
			System.out.println();
			ListIterator<Result> it = testResults.listIterator(testResults.size());
			if (it.hasPrevious()) {
				Result res = it.previous();
				System.out.println(res);
				while (it.hasPrevious()) {
					Result previousRes = it.previous();
					if (res.getDate().compareTo(previousRes.getDate()) == 0) {
						System.out.println(previousRes);
					}
					else return;
				}
			}

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
