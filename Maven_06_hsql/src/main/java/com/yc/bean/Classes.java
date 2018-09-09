package com.yc.bean;

import java.io.Serializable;

public class Classes implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer classId;
	private String className;
	private Teacher teacherId;

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Teacher getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Teacher teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public String toString() {
		return "Class [classId=" + classId + ", className=" + className + ", teacherId=" + teacherId + "]";
	}

}
