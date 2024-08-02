package com.kh.model.vo;

import java.sql.Date;

/*
 * VO (Value Object)
 * : 한 명의 회원에 대한 데이터를 저장할 수 있는 객체
 *   --> DB 테이블에서 한 행의 데이터
 */
public class Member {
	// 필드부  => 변수
	//  --> DB 테이블의 컬럼명과 매칭시켜서 정의
	private int userNo;		// USERNO	NUMBER
	private String userId;	// USERID	VARCHAR2(20 BYTE)
	private String userPw;	//USERPW	VARCHAR2(20 BYTE)
	private String userName;// USERNAME	VARCHAR2(20 BYTE)
	private String gender;	//GENDER	CHAR(1 BYTE)
	private int age;		//AGE	NUMBER
	private String email;	//EMAIL	VARCHAR2(30 BYTE)
	private String address;	// ADDRESS	VARCHAR2(100 BYTE)
	private String phone;	// PHONE	VARCHAR2(13 BYTE)
	private String hobby;	// HOBBY	VARCHAR2(50 BYTE)
	private Date enrollDate;// ENROLLDATE	DATE --> java.sql.Date
	
	// 생성자부
	public Member() {
		super();
	}
	
	

	public Member(String userId, String userPw, String userName, String gender) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.gender = gender;
	}



	public Member(int userNo, String userId, String userPw, String userName, String gender, int age, String email,
			String address, String phone, String hobby, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}

	// 메소드부
	// => getter/setter, toString
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}	
	

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", gender=" + gender + ", age=" + age + ", email=" + email + ", address=" + address + ", phone="
				+ phone + ", hobby=" + hobby + ", enrollDate=" + enrollDate + "]";
	}
}
