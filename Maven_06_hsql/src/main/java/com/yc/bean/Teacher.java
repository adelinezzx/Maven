package com.yc.bean;

public class Teacher {

	private Integer teacherId;
	private String teacherName;
	private Integer teachYear;
	private String professional;

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getTeachYear() {
		return teachYear;
	}

	public void setTeachYear(Integer teachYear) {
		this.teachYear = teachYear;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherName=" + teacherName + ", teachYear=" + teachYear
				+ ", professional=" + professional + "]";
	}

}
