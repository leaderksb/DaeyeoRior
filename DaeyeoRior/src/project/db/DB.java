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
			//오라클 드라이버 설치
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//드라이버 매니저 연결
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"tokki", "11386013");
			stmt = conn.createStatement();
			System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결 오류 또는 쿼리 오류");
			e.printStackTrace();
		}
		
	}
	
	//조회
	public static ResultSet select(String sql) {
		try {
			Statement stmt = conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	//삽입, 수정, 삭제
	public static void update(String sql) {
		try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
