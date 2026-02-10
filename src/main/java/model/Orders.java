package model;

import java.time.LocalDateTime;

public class Orders {
	private int id;
	private String order_no;
	private String user_no;
	private LocalDateTime created_at;
	private int totalAmount;
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orders(String user_no, int totalAmount) {
		super();
		this.user_no = user_no;
		this.totalAmount = totalAmount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
