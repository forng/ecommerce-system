package vo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.Tool;

public class OrderDetailVODaoImpl {

	 private Connection conn = Tool.getDb();
	 public List<OrderDetailVO> findByUserNo(String userNo) {
	        List<OrderDetailVO> list = new ArrayList<>();

	        String sql = "SELECT * FROM v_order_detail WHERE user_no = ? ORDER BY order_created_at DESC";

	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, userNo);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                OrderDetailVO vo = new OrderDetailVO(
	                    rs.getString("order_no"),
	                    rs.getString("user_no"),
	                    rs.getString("product_no"),
	                    rs.getString("product_name"),
	                    rs.getInt("product_price"),
	                    rs.getInt("amounts"),
	                    rs.getInt("總銷售金額"),
	                    rs.getString("order_created_at")
	                );
	                list.add(vo);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	 public String buildTextByOrderNo(String orderNo) {
		 StringBuilder sb = new StringBuilder();
		    sb.append("【訂單確認】\n");
		    sb.append("訂單編號：").append(orderNo).append("\n\n");

		    String sql = 
		    		"SELECT product_name, product_price, amounts, (product_price * amounts) AS line_total, order_created_at " +
		    			    "FROM v_order_detail " +
		    			    "WHERE order_no = ? " +
		    			    "ORDER BY product_no";
		    int subtotal = 0;
		    String orderTime = "";

		    try (java.sql.Connection conn = util.Tool.getDb();
		            java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

		           ps.setString(1, orderNo);

		           try (java.sql.ResultSet rs = ps.executeQuery()) {

		               int idx = 1;
		               boolean hasAnyItem = false;

		               while (rs.next()) {
		                   hasAnyItem = true;

		                   String name = rs.getString("product_name"); // 已是中文
		                   int price = rs.getInt("product_price");
		                   int qty = rs.getInt("amounts");
		                   int lineTotal = rs.getInt("line_total");

		                   if (orderTime == null || orderTime.isEmpty()) {
		                       orderTime = rs.getString("order_created_at"); // 同張訂單每列一樣，取第一筆即可
		                   }

		                   sb.append(idx++).append(". ")
		                     .append(name).append("  ")
		                     .append(price).append(" x ").append(qty)
		                     .append(" = ").append(lineTotal)
		                     .append("\n");

		                   subtotal += lineTotal;
		               }

		               if (!hasAnyItem) {
		                   sb.append("（查無訂單明細，可能 order_items 沒寫入）\n");
		               }
		           }

		       } catch (Exception e) {
		           e.printStackTrace();
		           return "讀取訂單失敗：" + e.getMessage();
		       }

		       // ✅ 折扣/運費規則：都在 Java 算
		       int discount = (subtotal >= 1000) ? (int) Math.round(subtotal * 0.1) : 0;
		       int afterDiscount = subtotal - discount;

		       int shipping;
		       if (subtotal == 0) shipping = 0;                 // 沒買東西就不要運費
		       else shipping = (afterDiscount >= 500) ? 0 : 60; // 免運門檻用折扣後金額（跟你購物車一致）

		       int total = afterDiscount + shipping;

		       sb.append("\n----------------------------\n");
		       if (orderTime != null && !orderTime.isEmpty()) {
		           sb.append("訂單時間：").append(orderTime).append("\n");
		       }
		       sb.append("小計：").append(subtotal).append("\n");
		       sb.append(discount > 0 ? "折扣：滿千打九折\n" : "折扣：未達滿千（無折扣）\n");
		       sb.append("折扣金額：").append(discount).append("\n");
		       sb.append("折扣後：").append(afterDiscount).append("\n");
		       sb.append(shipping == 0 ? "運費：滿500免運\n" : "運費：未達500 +60\n");
		       sb.append("總額：").append(total).append("\n");

		       return sb.toString();
	    }
	 public String buildHistoryByUserNo(String userNo) {

		    StringBuilder sb = new StringBuilder();
		    sb.append("【歷史訂單紀錄】\n");
		    sb.append("會員：").append(userNo).append("\n\n");

		    // 先找出這個 user 的所有訂單編號（新到舊）
		    String sqlOrders =
		        "SELECT DISTINCT order_no, order_created_at " +
		        "FROM v_order_detail " +
		        "WHERE user_no = ? " +
		        "ORDER BY order_created_at DESC";

		    try (java.sql.Connection conn = util.Tool.getDb();
		         java.sql.PreparedStatement ps = conn.prepareStatement(sqlOrders)) {

		        ps.setString(1, userNo);

		        try (java.sql.ResultSet rs = ps.executeQuery()) {

		            boolean hasAny = false;
		            int idx = 1;

		            while (rs.next()) {
		                hasAny = true;
		                String orderNo = rs.getString("order_no");
		                String time = rs.getString("order_created_at");

		                sb.append("====================================\n");
		                sb.append(idx++).append(") 訂單編號：").append(orderNo).append("\n");
		                sb.append("   訂單時間：").append(time).append("\n\n");

		                // ✅ 直接重用你已經完成的「單筆訂單顯示」
		                sb.append(buildTextByOrderNo(orderNo)).append("\n");
		            }

		            if (!hasAny) {
		                sb.append("（目前沒有任何歷史訂單）\n");
		            }
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		        return "讀取歷史訂單失敗：" + e.getMessage();
		    }

		    return sb.toString();
		}
	 public List<OrderDetailVO> selectAllLines() {
		    String sql = "SELECT order_no, user_no, order_created_at, product_no, product_name, product_price, amounts " +
		                 "FROM v_order_detail ORDER BY order_created_at DESC, order_no, product_no";
		    return query(sql, null);
		}

		public List<OrderDetailVO> selectLinesByUserNo(String userNo) {
		    String sql = "SELECT order_no, user_no, order_created_at, product_no, product_name, product_price, amounts " +
		                 "FROM v_order_detail WHERE user_no=? ORDER BY order_created_at DESC, order_no, product_no";
		    return query(sql, new Object[]{userNo});
		}
		public List<OrderDetailVO> selectLinesByOrderNo(String orderNo) {
			 String sql = "SELECT order_no, user_no, order_created_at, product_no, product_name, product_price, amounts " +
	                 "FROM v_order_detail WHERE order_no=? ORDER BY order_created_at DESC, order_no, product_no";
			 return query(sql, new Object[]{orderNo});

		}

		public List<OrderDetailVO> selectLinesByProductNo(String productNo) {
		    String sql = "SELECT order_no, user_no, order_created_at, product_no, product_name, product_price, amounts " +
		                 "FROM v_order_detail WHERE product_no=? ORDER BY order_created_at DESC, order_no, product_no";
		    return query(sql, new Object[]{productNo});
		}

		// 金額區間：這裡用「每行的小計」做篩選（快速、列表直覺）
		public List<OrderDetailVO> selectLinesByLineTotalRange(int min, int max) {
		    String sql = "SELECT order_no, user_no, order_created_at, product_no, product_name, product_price, amounts " +
		                 "FROM v_order_detail " +
		                 "WHERE (product_price * amounts) BETWEEN ? AND ? " +
		                 "ORDER BY order_created_at DESC, order_no, product_no";
		    return query(sql, new Object[]{min, max});
		}

		private List<OrderDetailVO> query(String sql, Object[] params) {
		    List<OrderDetailVO> list = new java.util.ArrayList<>();
		    try (java.sql.Connection conn = util.Tool.getDb();
		         java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {

		        if (params != null) {
		            for (int i = 0; i < params.length; i++) ps.setObject(i + 1, params[i]);
		        }

		        try (java.sql.ResultSet rs = ps.executeQuery()) {
		            while (rs.next()) {
		                OrderDetailVO vo = new OrderDetailVO();
		                vo.setOrderNo(rs.getString("order_no"));
		                vo.setUserNo(rs.getString("user_no"));
		                vo.setOrderCreatedAt(rs.getString("order_created_at"));
		                vo.setProductNo(rs.getString("product_no"));
		                vo.setProductName(rs.getString("product_name"));
		                vo.setProductPrice(rs.getInt("product_price"));
		                vo.setAmounts(rs.getInt("amounts"));
		                list.add(vo);
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return list;
		}
}
