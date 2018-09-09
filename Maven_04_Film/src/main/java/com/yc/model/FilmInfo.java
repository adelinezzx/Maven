package com.yc.model;

import java.io.Serializable;

public class FilmInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int filmid;
	private String filmname;

	private String actor;
	private String director;
	private double ticketprice;


	public FilmInfo() {
		super();
	}

	public FilmInfo(String filmname, String actor, String director, double ticketprice, FilmType filmtype) {
		this.filmname = filmname;
		this.actor = actor;
		this.director = director;
		this.ticketprice = ticketprice;
		this.filmtype = filmtype;
	}

	// 映射类型
	public FilmType filmtype;

	public FilmType getFilmtype() {
		return filmtype;
	}

	public void setFilmtype(FilmType filmtype) {
		this.filmtype = filmtype;
	}

	public int getFilmid() {
		return filmid;
	}

	public void setFilmid(int filmid) {
		this.filmid = filmid;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public double getTicketprice() {
		return ticketprice;
	}

	public void setTicketprice(double ticketprice) {
		this.ticketprice = ticketprice;
	}

	public String getFilmname() {
		return filmname;
	}

	public void setFilmname(String filmname) {
		this.filmname = filmname;
	}

	@Override
	public String toString() {
		return "FilmInfo [filmid=" + filmid + ", filmname=" + filmname + ", actor=" + actor + ", director=" + director
				+ ", ticketprice=" + ticketprice + ", filmtype=" + filmtype + "]";
	}

	 

}
