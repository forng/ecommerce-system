package vo;

public class OrderDetailVO {
	  private String orderNo;
	    private String userNo;
	    private String productNo;
	    private String productName;
	    private int productPrice;
	    private int amounts;
	    private int lineTotal;
	    private String orderCreatedAt;
		public OrderDetailVO() {
			super();
			// TODO Auto-generated constructor stub
		}
		public OrderDetailVO(String orderNo, String userNo, String productNo, String productName, int productPrice,
				int amounts, int lineTotal, String orderCreatedAt) {
			super();
			this.orderNo = orderNo;
			this.userNo = userNo;
			this.productNo = productNo;
			this.productName = productName;
			this.productPrice = productPrice;
			this.amounts = amounts;
			this.lineTotal = lineTotal;
			this.orderCreatedAt = orderCreatedAt;
		}
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public String getUserNo() {
			return userNo;
		}
		public void setUserNo(String userNo) {
			this.userNo = userNo;
		}
		public String getProductNo() {
			return productNo;
		}
		public void setProductNo(String productNo) {
			this.productNo = productNo;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public int getProductPrice() {
			return productPrice;
		}
		public void setProductPrice(int productPrice) {
			this.productPrice = productPrice;
		}
		public int getAmounts() {
			return amounts;
		}
		public void setAmounts(int amounts) {
			this.amounts = amounts;
		}
		public int getLineTotal() {
			return lineTotal;
		}
		public void setLineTotal(int lineTotal) {
			this.lineTotal = lineTotal;
		}
		public String getOrderCreatedAt() {
			return orderCreatedAt;
		}
		public void setOrderCreatedAt(String orderCreatedAt) {
			this.orderCreatedAt = orderCreatedAt;
		}

	    
	   
}
