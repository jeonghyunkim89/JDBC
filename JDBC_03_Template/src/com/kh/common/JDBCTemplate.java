package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	// 매번 반복적으로 사용한 코드들을 메소드로 정의하기 위한 용도 (공통 템플릿)
	
	// 객체를 매번 생성하지 않고 메소드를 호출할 수 있도록,
	//	 모든 메소드를 static 메소드로 정의!
	// => 싱글톤 패턴 : 메모리영역에 한번만 올려두고 매번 재사용하는 패턴
	
	// 1. Connection 객체 생성 메소드
	/**
	 * Connection 객체 생성 메소드
	 *   DB접속 후 해당 Connection 객체를 반환
	 * @return 생성된 Connection 객체
	 */
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
										, "C##JDBC"
										, "JDBC");
			conn.setAutoCommit(false);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * commit 처리를 해주는 메소드
	 * @param conn 생성된 Connection 객체
	 */
	public static void commit(Connection conn) {
		try {
			
			// Connection 객체가 생성되어있고(null이 아니고), 닫혀있지 않을때
			if (conn != null && !conn.isClosed())	
				conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * rollback 처리해주는 메소드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			
			// Connection 객체가 생성되어있고(null이 아니고), 닫혀있지 않을때
			if (conn != null && !conn.isClosed())	
				conn.rollback();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Connection 객체를 close 처리해주는 메소드
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			
			// Connection 객체가 생성되어있고(null이 아니고), 닫혀있지 않을때
			if (conn != null && !conn.isClosed())	
				conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Statement 관련 객체를 전달받아서 close 처리해주는 메소드
	 * @param stmt Statement 객체 또는 그 자식 객체 (PreparedStatement...)
	 */
	public static void close(Statement stmt) {
		try {
			
			if (stmt != null && !stmt.isClosed())
				stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ResultSet 객체를 close 처리해주는 메소드
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		try {
			
			if (rset != null && !rset.isClosed())
				rset.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}




