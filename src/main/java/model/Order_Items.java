package model;

public class Order_Items {

	private int id;
	private String order_no;
	private String product_no;
	private String product_name;
	private int price;
	private int amounts;
	public Order_Items() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order_Items(String product_no, String product_name, int price, int amounts) {
		super();
		this.product_no = product_no;
		this.product_name = product_name;
		this.price = price;
		this.amounts = amounts;
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
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmounts() {
		return amounts;
	}
	public void setAmounts(int amounts) {
		this.amounts = amounts;
	}
	
}
