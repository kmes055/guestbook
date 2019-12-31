package spms.vo;

import java.util.Date;

public class History {
	protected int		mno;
	protected int		fno;
	protected Date		mod_date;
	protected String	content;
	public int getMno() {
		return mno;
	}
	public int getFno() {
		return fno;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public String getContent() {
		return content;
	}
	public History setMno(int mno) {
		this.mno = mno;
		return this;
	}
	public History setFno(int fno) {
		this.fno = fno;
		return this;
	}
	public History setMod_date(Date mod_date) {
		this.mod_date = mod_date;
		return this;
	}
	public History setContent(String content) {
		this.content = content;
		return this;
	}
}
