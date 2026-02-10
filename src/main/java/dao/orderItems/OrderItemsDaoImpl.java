package dao.orderItems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.Tool;

public class OrderItemsDaoImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void addItem(String orderNo, String productNo, int amounts) {
	    String sql = "INSERT INTO order_items(order_no, product_no, amounts) VALUES(?,?,?)";
	    try (Connection conn = Tool.getDb();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, orderNo);
	        ps.setString(2, productNo);
	        ps.setInt(3, amounts);
	        ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
