package dao.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Tool;

public class OrdersDaoImpl {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public String createOrderReturnOrderNo(String userNo) {
	    String insertSql = "INSERT INTO orders(user_no) VALUES(?)";
	    String selectSql = "SELECT order_no FROM orders WHERE id = ?";
	    
	    try (Connection conn = Tool.getDb();
	         PreparedStatement ps = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

	        ps.setString(1, userNo);
	        ps.executeUpdate();

	        // 拿到剛新增的 orders.id
	        int newId = -1;
	        try (ResultSet rs = ps.getGeneratedKeys()) {
	            if (rs.next()) newId = rs.getInt(1);
	        }

	        // 查出 order_no (trigger 已經補上)
	        try (PreparedStatement ps2 = conn.prepareStatement(selectSql)) {
	            ps2.setInt(1, newId);
	            try (ResultSet rs2 = ps2.executeQuery()) {
	                if (rs2.next()) return rs2.getString("order_no");
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
