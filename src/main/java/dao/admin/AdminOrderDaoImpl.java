package dao.admin;

import java.sql.*;
import java.util.*;

import util.Tool;

public class AdminOrderDaoImpl {

    private Connection conn = Tool.getDb();

    // 讀全部明細（每一列是一個 order item）
    public List<Map<String, Object>> fetchAllLines() {
        String sql = "SELECT order_no, user_no, order_created_at, product_no, product_name, product_price, amounts " +
                     "FROM v_order_detail ORDER BY order_created_at DESC, order_no, product_no";
        return query(sql, Collections.emptyList());
    }

    public List<Map<String, Object>> fetchLinesByUserNo(String userNo) {
        String sql = "SELECT order_no, user_no, order_created_at, product_no, product_name, product_price, amounts " +
                     "FROM v_order_detail WHERE user_no=? " +
                     "ORDER BY order_created_at DESC, order_no, product_no";
        return query(sql, Arrays.asList(userNo));
    }

    public List<Map<String, Object>> fetchLinesByProductNo(String productNo) {
        String sql = "SELECT order_no, user_no, order_created_at, product_no, product_name, product_price, amounts " +
                     "FROM v_order_detail WHERE product_no=? " +
                     "ORDER BY order_created_at DESC, order_no, product_no";
        return query(sql, Arrays.asList(productNo));
    }

    private List<Map<String, Object>> query(String sql, List<Object> params) {
        List<Map<String, Object>> rows = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.size(); i++) ps.setObject(i + 1, params.get(i));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> m = new HashMap<>();
                    m.put("order_no", rs.getString("order_no"));
                    m.put("user_no", rs.getString("user_no"));
                    m.put("order_created_at", rs.getString("order_created_at"));
                    m.put("product_no", rs.getString("product_no"));
                    m.put("product_name", rs.getString("product_name"));
                    m.put("product_price", rs.getInt("product_price"));
                    m.put("amounts", rs.getInt("amounts"));
                    rows.add(m);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }
}