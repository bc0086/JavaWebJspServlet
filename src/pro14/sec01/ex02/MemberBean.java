package pro14.sec01.ex02;

import java.util.Date;

public class MemberBean {
	// 회원 테이블의 컬럼 이름과 동일하게 이름과 자료형을 선언
	private String id;
	private String pwd;
	private String name;
	private String email;
	private Date joinDate;
	
	// 주소 정보를 저장하는 Address 클래스 타입속성을 선언
	private Address addr;
	
	public MemberBean() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}
	
	
	
}
