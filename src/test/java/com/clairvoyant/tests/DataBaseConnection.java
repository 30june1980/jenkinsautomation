package com.clairvoyant.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class DataBaseConnection {

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	public Object ArrayResult[][] = null;
	String db = "";

	public DataBaseConnection(String dbName) {
		db = dbName;
		if (db.equalsIgnoreCase("mysql")) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String jumpserverHost = "52.26.65.139";
				String jumpserverUsername = "ec2-user";
				String databaseHost = "lopescgc-prod.civy79capl7c.us-west-2.rds.amazonaws.com";
				int databasePort = 3306;
				String databaseUsername = "lopescan";
				String databasePassword = "PlagiarismChecker";
				JSch jsch = new JSch();
				jsch.addIdentity("D:\\RnD\\JenkinsProj\\src\\main\\resources\\Data\\lopescgc-prod.pem");

				Session session = jsch.getSession(jumpserverUsername, jumpserverHost);
				session.setConfig("StrictHostKeyChecking", "no");
				session.connect();
				int forwardedPort = session.setPortForwardingL(0, databaseHost, databasePort);
				String url = "jdbc:mysql://localhost:" + forwardedPort + "/plagiarism";
				con = DriverManager.getConnection(url, databaseUsername, databasePassword);
				System.out.println("connected succesfully");

			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
				System.out.println(("DB Error: " + db + "\n" + se.getStackTrace()));
			} catch (Exception e) {

				System.out.println("DB Error: " + db + "\n" + e.getStackTrace());
			}
		} else {
			System.out.println("DB Unknown- Please check the DB name passed: " + db);
		}
	}

	public ArrayList<String> Student_login(String strQuery) throws SQLException {

		ArrayList<String> userdata = executeDBQuery(strQuery);
		closeDBConnection();
		return userdata;
	}

	public ArrayList<String> executeDBQuery(String strQuery) throws SQLException {
		st = con.createStatement();
		rs = st.executeQuery(strQuery);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		ArrayList<String> values = new ArrayList<>();
		int rowCount = getRows(rs);
		ArrayResult = new Object[rowCount][columnCount];

		while (rs.next()) {
			for (int j = 1; j <= columnCount; j++) {
				values.add(rs.getString(j));
			}
		}

		return values;
	}

	private int getRows(ResultSet rs) {
		int totalRows = 0;
		try {
			rs.last();
			totalRows = rs.getRow();
			rs.beforeFirst();
		} catch (Exception ex) {
			return 0;
		}
		return totalRows;
	}

	public void closeDBConnection() {
		ArrayResult = null;
		try {
			if (rs != null)
				rs.close();

			if (st != null)
				st.close();

			if (con != null)
				con.close();
		} catch (SQLException se) {

		}
	}
}
