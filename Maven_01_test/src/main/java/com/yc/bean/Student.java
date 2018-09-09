package com.yc.bean;

public class Student {

	private Integer sid;
	private String sname;

	public Student(Integer sid, String sname) {
		System.out.println("构造方法");
		this.sid = sid;
		this.sname = sname;
	}

	public Student() {
		super();
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		System.out.println(sid);
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		System.out.println(sname);
		this.sname = sname;
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + "]";
	}

}
