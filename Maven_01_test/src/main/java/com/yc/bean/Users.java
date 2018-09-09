package com.yc.bean;

import java.io.Serializable;

public class Users implements  Serializable {
 
	private static final long serialVersionUID = 1L;
	
	
	private int userid;
	private String username;
	private String password;
	private String tel;
	private String email;

	public Users() {
		super();
	}

	public Users(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Users(String username, String password, String tel, String email) {
		super();
		this.username = username;
		this.password = password;
		this.tel = tel;
		this.email = email;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username + ", password=" + password + ", tel=" + tel
				+ ", email=" + email + "]";
	}

}