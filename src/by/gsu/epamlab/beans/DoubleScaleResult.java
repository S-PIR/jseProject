package by.gsu.epamlab.beans;

import java.sql.Date;

public class DoubleScaleResult extends Result {
	
	public DoubleScaleResult() {
		super();
	}

	public DoubleScaleResult(String student, String test, Date date, int mark) {
		super(student, test, date, mark);
	}

	@Override
	public void setMark(String mark) {
		super.setMark(mark.replaceAll("\\.", ""));
	}
	
	@Override
	public String markToString() {
		int mark = this.getMark();
		return mark / 10 + "." + mark % 10;
	}
}
