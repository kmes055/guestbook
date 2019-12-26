package spms.vo;

import java.util.Date;

public class Feed {
	protected int 		fno;
	protected String 	email;
	protected String	pwd;
	protected Date		gen_date;
	protected Date		mod_date;
	protected String	content;
	
	public int getFno() {
		return fno;
	}
	public String getEmail() {
		return email;
	}
	public String getPwd() {
		return pwd;
	}
	public Date getGen_date() {
		return gen_date;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public String getContent() {
		return content;
	}
	public Feed setFno(int fno) {
		this.fno = fno;
		return this;
	}
	public Feed setEmail(String email) {
		this.email = email;
		return this;
	}
	public Feed setPwd(String pwd) {
		this.pwd = pwd;
		return this;
	}
	public Feed setGen_date(Date gen_date) {
		this.gen_date = gen_date;
		return this;
	}
	public Feed setMod_date(Date mod_date) {
		this.mod_date = mod_date;
		return this;
	}
	public Feed setContent(String content) {
		this.content = content;
		return this;
	}
}
