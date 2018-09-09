package com.yc.model;

import java.io.Serializable;

public class FilmType implements Serializable {

	private static final long serialVersionUID = 1L;
	private int typeid;
	private String typename;

	public FilmType(int typeid, String typename) {
		this.typeid = typeid;
		this.typename = typename;
	}

	public FilmType(int typeid) {
		this.typeid = typeid;
	}

	public FilmType() {
	}

	public FilmType(String typename) {
		this.typename = typename;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Override
	public String toString() {
		return "FilmType [typeid=" + typeid + ", typename=" + typename + "]";
	}

}
