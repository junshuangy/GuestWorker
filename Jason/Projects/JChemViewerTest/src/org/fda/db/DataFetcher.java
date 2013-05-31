/**
 *  $Author: Jason Yang
 *  $Date: 05/17/2013
 *  Used for fetch data record and column title from specific table in database
 *  The data results are used for creating the JTable instance
 */
package org.fda.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataFetcher {
	private final static String url = "jdbc:mysql://localhost:3306/test";
	private final static String user = "root";
	private final static String password = "123456";
	private final static String tableName = "tb_compound";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;	
	}
		
	public Object[] getColumnNames() {
		List<String> names = new ArrayList<String>();
		Connection conn = getConnection();
		try {
			Statement statement = conn.createStatement();
			ResultSet rs=statement.executeQuery("select * from " + tableName);
			ResultSetMetaData data=rs.getMetaData(); 
        	System.out.println(data.getColumnCount());
        	for (int i = 1; i <= data.getColumnCount(); i++) {
        		System.out.println(data.getColumnName(i)); 
        		//index start from 1
        		names.add(data.getColumnName(i));
        	}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return names.toArray();
	}
	
	public Object[][] getDataRecords() {
		ArrayList<Object[]> res = new ArrayList<Object[]>();
		Connection conn = getConnection();
		int columnCount = 0;
		try {
			Statement statement = conn.createStatement();
			ResultSet rs=statement.executeQuery("select * from " + tableName);
	        while (rs.next()) {
	    		List<String> compounds = new ArrayList<String>();
	    		columnCount = rs.getMetaData().getColumnCount();
	        	for (int i = 1; i <= columnCount; i++) {
	        		compounds.add(rs.getString(i));
	        	}
	        	res.add(compounds.toArray());
	        }  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Object[][] result = new Object[res.size()][columnCount];
		for (int i = 0; i < res.size(); i++) {
			for (int j = 0; j < columnCount; j++) {
				result[i][j] = res.get(i)[j];
				System.out.println(result[i][j]);
			}
		}
				
		return result;
	}
}
