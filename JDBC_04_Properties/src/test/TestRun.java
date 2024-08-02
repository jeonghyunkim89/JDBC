package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class TestRun {
	/*
	 * Properties 특징
	 * - Map계열의 컬렉션 => key:value형태로 데이터 저장
	 * - 문자열(String) 형태로 데이터를 저장
	 * 
	 * - 값을 저장할 때 : setProperty(key, value)
	 * - 값을 꺼내올 때 : getProperty(key)
	 * 
	 * - 저장되는 파일 종류 : .properties/ .xml
	 */
	public static void main(String[]args) {
		//propTest();
		//jdbcSettingTest();
		queryTest();
	}
	
	public static void jdbcSettingTest() {
		// JDBC 관련 설정 파일 만들기
		Properties prop = new Properties();
		
		/*
		 * - driver 정보 : oracle.jdbc.driver.OracleDriver
		 * - url		: jdbc:oracle:thin:@localhost:1521:xe
		 * - username	: C##JDBC
		 * - password	: JDBC
		 
		prop.setProperty("driver", "oracle.jdbc.driver.OracleDriver");
		prop.setProperty("url", "jdbc:oracle:thin:@localhost:1521:xe");
		prop.setProperty("username", "C##JDBC");
		prop.setProperty("password", "JDBC");
		
		try {
			prop.store(new FileOutputStream("resources/driver.properties"), "JDBC Settings");
			
			System.out.println("JDBC 설정 파일 저장 완료!");
			
		}  catch (FileNotFoundException e) {
			System.out.println("[ERR] 파일을 찾지 못했다고 한다! 경로를 확인하자!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[ERR] 입출력 관련 오류가 발생했다! 불석해보자!");
			e.printStackTrace();
		}
		*/
		// 파일 읽어오기 (입력)
		
			try {
				
				prop.load(new FileInputStream("resources/driver.properties"));
			
				String driver = prop.getProperty("driver");
				System.out.println("driver --> "+ driver);
				System.out.println("url --> "+ prop.getProperty("url"));
				System.out.println("username --> "+prop.getProperty("username"));
				System.out.println("password --> "+prop.getProperty("password"));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	
	public static void queryTest() {
		Properties prop = new Properties();
		
		//resources/query.xml 파일 읽어오기
		try {
			
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
			
			System.out.println(prop.getProperty("selectMemberlist"));
			System.out.println(prop.getProperty("insertMember"));
			System.out.println(prop.getProperty("deleteMember"));
			System.out.println(prop.getProperty("updateMember"));
			
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void propTest() {
		// Properties 객체 생성
		Properties prop = new Properties();
		
		//key 밸류 데이터 저장하기
		prop.setProperty("C", "INSERT"); // Create : 데이터 추가 / 게시글 작성
		prop.setProperty("R", "SELECT"); // Read : 데이터 조회 / 게시글 목록 조회, 검색, 상세페이지 
		prop.setProperty("U", "UPDATE"); // Update : 데이터 수정 / 게시글 수정
		prop.setProperty("D", "DELETE"); // Delete : 데이터 삭제 / 게시글 삭제
	
		//저장된 데이터를 파일에 출력
		try {
			
			// .properties 형식 => 설정들을 저장하기 위한 용도
			prop.store(new FileOutputStream("resources/test.properties"), "Properties Test");
			
			prop.storeToXML(new FileOutputStream("resources/test.xml"), "Properties Test");
			
		} catch (FileNotFoundException e) {
			System.out.println("[ERR] 파일을 찾지 못했다고 한다! 경로를 확인하자!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("[ERR] 입출력 관련 오류가 발생했다! 불석해보자!");
			e.printStackTrace();
		}
		
	}
		
}
