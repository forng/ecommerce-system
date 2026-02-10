package service.admin;

import java.util.*;
import dao.admin.AdminOrderDaoImpl;

public class AdminOrderServiceImpl {

    private AdminOrderDaoImpl dao = new AdminOrderDaoImpl();

    public String buildAllOrdersText() {
        return buildOrdersText(dao.fetchAllLines(), null, null);
    }

    public String buildOrdersByUserNoText(String userNo) {
        return buildOrdersText(dao.fetchLinesByUserNo(userNo), null, null);
    }

    public String buildOrdersByProductNoText(String productNo) {
        return buildOrdersText(dao.fetchLinesByProductNo(productNo), null, null);
    }

    // 金額區間：用「折扣後 + 運費」的 total 去篩
    public String buildOrdersByTotalRangeText(int min, int max) {
        return buildOrdersText(dao.fetchAllLines(), min, max);
    }

    // 核心：把 lines group 成「一張張訂單」再輸出字串
    private String buildOrdersText(List<Map<String, Object>> lines, Integer minTotal, Integer maxTotal) {
        // orderNo -> list of lines
        LinkedHashMap<String, List<Map<String, Object>>> grouped = new LinkedHashMap<>();
        for (Map<String, Object> row : lines) {
            String orderNo = (String) row.get("order_no");
            grouped.computeIfAbsent(orderNo, k -> new ArrayList<>()).add(row);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== 訂單列表 ===\n\n");

        int shown = 0;

        for (Map.Entry<String, List<Map<String, Object>>> entry : grouped.entrySet()) {
            String orderNo = entry.getKey();
            List<Map<String, Object>> items = entry.getValue();

            if (items.isEmpty()) continue;

            String userNo = (String) items.get(0).get("user_no");
            String createdAt = String.valueOf(items.get(0).get("order_created_at"));

            int subtotal = 0;
            StringBuilder itemLines = new StringBuilder();

            for (Map<String, Object> it : items) {
                String name = (String) it.get("product_name");      // 你已改成中文 ✅
                int price = (int) it.get("product_price");
                int qty = (int) it.get("amounts");
                int lineTotal = price * qty;
                subtotal += lineTotal;

                itemLines.append(String.format("%s  %d x %d = %d\n", name, price, qty, lineTotal));
            }

            boolean hasDiscount = subtotal >= 1000;
            int afterDiscount = hasDiscount ? (int) Math.round(subtotal * 0.9) : subtotal;
            int shipping = (afterDiscount >= 500) ? 0 : 60;
            int total = afterDiscount + shipping;

            // 若有金額區間，就在這裡篩掉
            if (minTotal != null && total < minTotal) continue;
            if (maxTotal != null && total > maxTotal) continue;

            shown++;

            sb.append("【訂單編號】").append(orderNo).append("\n");
            sb.append("【客戶編號】").append(userNo).append("\n");
            sb.append("【訂單時間】").append(createdAt).append("\n\n");

            sb.append(itemLines);

            sb.append("\n----------------------------\n");
            sb.append("小計：").append(subtotal).append("\n");
            sb.append(hasDiscount ? "折扣：滿千打九折\n" : "折扣：未達滿千（無折扣）\n");
            sb.append("折扣後：").append(afterDiscount).append("\n");
            sb.append(shipping == 0 ? "運費：滿500免運\n" : "運費：60\n");
            sb.append("總額：").append(total).append("\n");
            sb.append("============================\n\n");
        }

        if (shown == 0) sb.append("（查無資料）\n");
        return sb.toString();
    }
}