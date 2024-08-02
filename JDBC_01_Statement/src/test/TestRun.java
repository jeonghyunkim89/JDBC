package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestRun {
	
	/*
	 * JDBC용 객체
	 * - Connection : DB의 연결정보를 담고있는 객체
	 * - [Prepared]Statement : 연결된 DB에 sql문을 전달하여 실행하고
	 * 						   그 결과를 받아주는 객체
	 * - ResultSet	: DQL(SELECT)문 실행 후 조회결과를 담고있는 객체
	 * 
	 * JDBC 과정(*순서중요*)
	 * 1) jdbc driver 등록 : 해당 DBMS(오라클)가 제공하는 클래스 등록
	 * 2) Connection 생성 : 연결하고자 하는 DB정보를 입력해서
	 * 						해당 DB와 연결하면서 생성
	 * 		- DB정보 : 접속 주소(url), 사용자이름(username), 사용자비밀번호(password)
	 * 3) Statement 생성 : Connection 객체를 이용하여 생성
	 * 					  sql문을 실행하고 결과를 받아주는 역할
	 * 4) sql문을 DB에 전달하여 실행 (Statement 객체 사용)
	 * 5) 실행 결과를 받기
	 * 	 - SELECT문 실행 : ResultSet 객체 (조회된 데이터들이 담겨져있음)
	 * 	 - 	  DML문 실행 : int (처리된 행 수)
	 * 6) 결과 처리
	 * 	 - ResultSet에 담겨져 있는 데이터들을 하나하나 꺼내서 vo객체에 옮겨 담기
	 * 	 - 트랜잭션 처리 (실행을 성공했으면 commit,실패했으면 rollback)
	 * 7) 사용 후 JDBC용 객체들을 반납(close) --> 생성 역순으로!
	 */
	
	public static void main(String[] args) {
		
		//insertTest();
		selectTest();
		
		
	}
	
	public static void selectTest() {
		// DQL(SELECT) --> ResultSet 객체 --> 데이터를 하나하나 추출해서 저장
		
		// 필요한 객체들...
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String username = "C##JDBC";
			String password = "JDBC";
			conn = DriverManager.getConnection(url, username, password);
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4),5) sql문 실행 후 결과받기
			String sql = "SELECT * FROM TEST";
			rset = stmt.executeQuery(sql);
			
			// 6) sql문 실행 결과를 하나하나 추출하기
			// * 데이터가 있는지 여부 확인 => rset.next():boolean (데이터가 있으면 true, 없으면 false)
			while (rset.next()) {
				//*데이터를 가지고 올때, '컬럼명' 또는 '컬럼순번'을 사용하여 추출
				int tno = rset.getInt("TNO");	// 컬럼명으로 추출
				String tname = rset.getString("TNAME"); // 컬럼명"TNAME"대신에 컬럼순번으로 추출 ->(2);
				Date tdate = rset.getDate("TDATE");	//java.sql.Date 객체!!
				
				System.out.println(tno+", "+tname + ", "+tdate);
				
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 7) 자원반납(close) --> *생성역순
			try {
			rset.close();
			stmt.close();
			conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void insertTest() {
		// DML(INSERT)	--> int(처리된 행수) --> 트랜잭션 처리
		
		Connection conn = null;
		Statement stmt = null;
		try {
			
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("<< 오라클 드라이버 등록 완료 >>");
			
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe"
												, "C##JDBC"
												, "JDBC");
			System.out.println("<< 오라클 DB접속 성공 >>");
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4), 5) sql문 실행하고 결과 받기 (결과 : 처리된 행수 - int)
			String sql = "INSERT INTO TEST VALUES(2, '김인창', SYSDATE)";
			//=> 완성된 형태의 SQL문 작성 (이대로 DEVELOPER에서 실행해도 정상작동되는 형태)
			//*SQL문장 끝에는 세미콜론이 없어야함!!
			int result =  stmt.executeUpdate(sql);
			/*
			 * DML 실행 시 => stmt.executeUpdate(sql):int
			 * Select 실행 시 => stmt.executeQuery(sql):ResultSet
			 *
			 */
			
			// 6) 트랜잭션 처리 (DML 실행 시 트랜잭션 처리)
			conn.setAutoCommit(false); //자동 커밋 옵션 false로 설정(jdbc6 버전 이후 autocommit)
			
			if(result>0) {
				//sql문 실행이 성공했다면 --> commt
				conn.commit();
				System.out.println("추가 성공!");
			} else { 
				//sql문 실행이 실패했다면 --> rollback
				conn.rollback();
				System.out.println("추가 실패!");
			}
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("[ERROR] 오라클 드라이버 등록 실패!!"+ e.getMessage());
		} catch (SQLException e) {
			System.out.println("[ERROR] SQL 오류 발생!!"+ e.getMessage());
		} finally {
			// 7) 자원반납(close) *생성 역순으로!
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
