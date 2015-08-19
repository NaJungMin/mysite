package com.bit2015.mysite.vo;

public class BoardVo {

	private long no;
	private String title;
	private String content;
	private long member_no;
	private String member_name;
	private long view_cnt;
	private String reg_date;
	private long dap;
	private long my_no;
	private long order_no;
	
	public void BoardVo(){}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getMember_no() {
		return member_no;
	}

	public void setMember_no(long member_no) {
		this.member_no = member_no;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public long getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(long view_cnt) {
		this.view_cnt = view_cnt;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public long getDap() {
		return dap;
	}

	public void setDap(long dap) {
		this.dap = dap;
	}

	public long getMy_no() {
		return my_no;
	}

	public void setMy_no(long my_no) {
		this.my_no = my_no;
	}

	public long getOrder_no() {
		return order_no;
	}

	public void setOrder_no(long order_no) {
		this.order_no = order_no;
	}
	
	
	
}
