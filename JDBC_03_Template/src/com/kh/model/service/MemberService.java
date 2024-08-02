package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

public class MemberService {
	/*
	 * [1] Connection 객체 생성
	 * 		- jdbc driver 등록
	 * 		- Connection 객체 생성
	 * [2] DML문 실행 시 트랜잭션 처리
	 * 		- commit
	 * 		- rollback
	 * [3] Connection 객체 반납
	 * 		- close
	 */
	public int insertMember(Member m) {
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) DAO에게 전달받은 데이터(Member m)와 Connection 객체를 전달하여 
		//		DB 처리 결과를 받기
		int result = new MemberDao().insertMember(conn, m);
		
		// 3) DML(INSERT) 실행 후 트랜잭션 처리
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Member> selectList() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Member> list = new MemberDao().selectList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int updateById(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateById(conn, m);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public int deleteByUserId(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteByUserId(conn, userId);
		
		if (result > 0) {
			// conn.commit();
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<Member> selectByUserName(String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Member> list = new MemberDao().selectByUserName(conn, keyword);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public Member selectByUserId(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		Member member = new MemberDao().selectByUserId(conn, userId);
		
		JDBCTemplate.close(conn);
		
		return member;
	}
}






