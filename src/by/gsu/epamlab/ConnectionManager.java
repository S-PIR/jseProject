package by.gsu.epamlab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.gsu.epamlab.constants.Constants;

public final class ConnectionManager {
	static {
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}
	
	public static void closeConnection(Connection cn) {
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeStatement(Statement st){
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closePreparedStatement(PreparedStatement ps){
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
