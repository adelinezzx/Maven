package com.yc.bean;

public class City {

	private Integer cid;
	private String cname;
	private Province province;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "City [cid=" + cid + ", cname=" + cname + ", province=" + province + "]";
	}

}
