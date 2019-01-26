package by.gsu.epamlab.beans;

import java.sql.Date;

public class HalfScaleResult extends Result {
	public HalfScaleResult() {
		super();
	}

	public HalfScaleResult(String student, String test, Date date, int mark) {
		super(student, test, date, mark);
	}

	@Override
	public void setMark(String mark) {
		try {
			super.setMark((mark + "0"));
		} catch (Exception e) {
			super.setMark(mark.replaceAll("\\.", ""));
		}
		
	}
	
	@Override
	public String markToString() {
		int mark = this.getMark();
		String res = String.valueOf(this.getMark() / 10);
		int mod = mark % 10;
		if (mod != 0) {
			res += "." + mod;
		}
		return res;
	}
}
