package com.yc.bean;

import java.util.ArrayList;
import java.util.List;

public class Project {

	private int pid;
	private String pname;

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	private List<Engneer> engneers = new ArrayList<Engneer>();

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public List<Engneer> getEngneers() {
		return engneers;
	}

	public void setEngneers(List<Engneer> engneers) {
		this.engneers = engneers;
	}

	@Override
	public String toString() {
		return "Project [pid=" + pid + ", cname=" + pname + ", engneers=" + engneers + "]";
	}
	
}
