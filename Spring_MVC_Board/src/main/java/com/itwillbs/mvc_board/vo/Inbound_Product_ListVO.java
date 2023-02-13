package com.itwillbs.mvc_board.vo;

import java.sql.Date;
import java.util.List;

// 입고처리 view
public class Inbound_Product_ListVO {

	private String in_schedule_cd;
	private int in_schedule_qty;
	private int in_qty;
	private Date in_date;
	private String remarks;
	private String stock_cd;
	private String in_complete;
	private String business_no;
	private String product_name;
	private String cust_name;
	private int product_cd;
	private String size_des;
	// List[] 추가..?
	private List<Inbound_Product_ListVO> DoInboundList;
	
	public Inbound_Product_ListVO() {
		super();
	}

	public Inbound_Product_ListVO(String in_schedule_cd, int in_schedule_qty, int in_qty, Date in_date, String remarks,
			String stock_cd, String in_complete, String business_no, String product_name, String cust_name,
			int product_cd, String size_des, List<Inbound_Product_ListVO> doInboundList) {
		super();
		this.in_schedule_cd = in_schedule_cd;
		this.in_schedule_qty = in_schedule_qty;
		this.in_qty = in_qty;
		this.in_date = in_date;
		this.remarks = remarks;
		this.stock_cd = stock_cd;
		this.in_complete = in_complete;
		this.business_no = business_no;
		this.product_name = product_name;
		this.cust_name = cust_name;
		this.product_cd = product_cd;
		this.size_des = size_des;
		DoInboundList = doInboundList;
	}

	public String getIn_schedule_cd() {
		return in_schedule_cd;
	}

	public void setIn_schedule_cd(String in_schedule_cd) {
		this.in_schedule_cd = in_schedule_cd;
	}

	public int getIn_schedule_qty() {
		return in_schedule_qty;
	}

	public void setIn_schedule_qty(int in_schedule_qty) {
		this.in_schedule_qty = in_schedule_qty;
	}

	public int getIn_qty() {
		return in_qty;
	}

	public void setIn_qty(int in_qty) {
		this.in_qty = in_qty;
	}

	public Date getIn_date() {
		return in_date;
	}

	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStock_cd() {
		return stock_cd;
	}

	public void setStock_cd(String stock_cd) {
		this.stock_cd = stock_cd;
	}

	public String getIn_complete() {
		return in_complete;
	}

	public void setIn_complete(String in_complete) {
		this.in_complete = in_complete;
	}

	public String getBusiness_no() {
		return business_no;
	}

	public void setBusiness_no(String business_no) {
		this.business_no = business_no;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public int getProduct_cd() {
		return product_cd;
	}

	public void setProduct_cd(int product_cd) {
		this.product_cd = product_cd;
	}

	public String getSize_des() {
		return size_des;
	}

	public void setSize_des(String size_des) {
		this.size_des = size_des;
	}

	public List<Inbound_Product_ListVO> getDoInboundList() {
		return DoInboundList;
	}

	public void setDoInboundList(List<Inbound_Product_ListVO> doInboundList) {
		DoInboundList = doInboundList;
	}

	@Override
	public String toString() {
		return "Inbound_Product_ListVO [in_schedule_cd=" + in_schedule_cd + ", in_schedule_qty=" + in_schedule_qty
				+ ", in_qty=" + in_qty + ", in_date=" + in_date + ", remarks=" + remarks + ", stock_cd=" + stock_cd
				+ ", in_complete=" + in_complete + ", business_no=" + business_no + ", product_name=" + product_name
				+ ", cust_name=" + cust_name + ", product_cd=" + product_cd + ", size_des=" + size_des
				+ ", DoInboundList=" + DoInboundList + "]";
	}


}
