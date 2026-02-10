package model;

public class Products {
	private int id;
	private String Product_no;
	private String Product_name;
	private int Product_price;
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Products(String product_no, String product_name, int product_price) {
		super();
		Product_no = product_no;
		Product_name = product_name;
		Product_price = product_price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct_no() {
		return Product_no;
	}
	public void setProduct_no(String product_no) {
		Product_no = product_no;
	}
	public String getProduct_name() {
		return Product_name;
	}
	public void setProduct_name(String product_name) {
		Product_name = product_name;
	}
	public int getProduct_price() {
		return Product_price;
	}
	public void setProduct_price(int product_price) {
		Product_price = product_price;
	}

}
