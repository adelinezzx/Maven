package com.yc.bean;

import java.util.ArrayList;
import java.util.List;

public class Engneer {
	
	private int eid;
	private String ename;
	private List<Project> projects = new ArrayList<Project>();

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Engneer [eid=" + eid + ", ename=" + ename + ", projects=" + projects + "]";
	}

}
