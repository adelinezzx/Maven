package com.yc.bean;

import java.util.ArrayList;
import java.util.List;

public class Province {

	private Integer pid;
	private String pname;
	
	List<City> cities = new ArrayList<City>() ;

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	@Override
	public String toString() {
		return "Province [pid=" + pid + ", pname=" + pname + ", cities=" + cities + "]";
	}
 
}
