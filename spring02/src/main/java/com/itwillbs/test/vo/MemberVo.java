package com.itwillbs.test.vo;



public class MemberVo {
	private String id;
	private String passwd;
	
	public MemberVo(String id, String passwd) {
		super();
		this.id = id;
		this.passwd = passwd;
	}
	
	public MemberVo() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
	


}
