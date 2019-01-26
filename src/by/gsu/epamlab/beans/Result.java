package by.gsu.epamlab.beans;

import java.sql.Date;

public class Result {
	private String student;
	private String test;
	private Date date;
	private int mark;

	public Result() {
		super();
	}

	public Result(String student, String test, Date date, int mark) {
		super();
		this.student = student;
		this.test = test;
		this.date = date;
		this.mark = mark;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDate(String date) {
		this.date = Date.valueOf(date);
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public void setMark(String mark) {
		this.mark = Integer.parseInt(mark);
	}
	
	public String markToString() {
		return String.valueOf(mark);
	}
	
	@Override
	public String toString() {
		return student + ";" + test + ";" + date + ";" + markToString();
	}
	
	
}
