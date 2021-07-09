package project.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	
	private static Connection conn;
	private static Statement stmt;

	public static void db() {
		try {
			//����Ŭ ����̹� ��ġ
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//����̹� �Ŵ��� ����
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"tokki", "11386013");
			stmt = conn.createStatement();
			System.out.println("DB ���� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB ���� ���� �Ǵ� ���� ����");
			e.printStackTrace();
		}
		
	}
	
	//��ȸ
	public static ResultSet select(String sql) {
		try {
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	//����, ����, ����
	public static void update(String sql) {
		try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
