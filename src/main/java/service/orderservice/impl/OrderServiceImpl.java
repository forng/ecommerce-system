package service.orderservice.impl;

import dao.orderItems.OrderItemsDao;
import dao.orderItems.OrderItemsDaoImpl;
import dao.orders.OrdersDao;
import dao.orders.OrdersDaoImpl;
import model.CartInput;
import service.orderservice.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public String buildCartSummary(CartInput in) {
        // 單價
        int p1 = 499;   // 原味濃縮乳清500g
        int p2 = 900;   // 原味濃縮乳清1kg
        int p3 = 599;   // 原味分離乳清500g
        int p4 = 1050;  // 原味分離乳清1kg
        int p5 = 399;   // 肌酸500g

        int q1 = Math.max(in.getWpco500g(), 0);
        int q2 = Math.max(in.getWpco1kg(), 0);
        int q3 = Math.max(in.getHpco500g(), 0);
        int q4 = Math.max(in.getHpco1kg(), 0);
        int q5 = Math.max(in.getCreatine500g(), 0);

        int sub1 = q1 * p1;
        int sub2 = q2 * p2;
        int sub3 = q3 * p3;
        int sub4 = q4 * p4;
        int sub5 = q5 * p5;

        int subtotal = sub1 + sub2 + sub3 + sub4 + sub5;

        int afterDiscount = subtotal;
        boolean hasDiscount = subtotal >= 1000;
        if (hasDiscount) {
            afterDiscount = (int) Math.round(subtotal * 0.9);
        }

        int shipping = (afterDiscount >= 500) ? 0 : 60;
        int total = afterDiscount + shipping;

        StringBuilder sb = new StringBuilder();
        sb.append("【購物車明細】\n\n");
        if (q1 > 0) sb.append(String.format("原味濃縮乳清500g  499 x %d = %d\n", q1, sub1));
        if (q2 > 0) sb.append(String.format("原味濃縮乳清1kg    900 x %d = %d\n", q2, sub2));
        if (q3 > 0) sb.append(String.format("原味分離乳清500g  599 x %d = %d\n", q3, sub3));
        if (q4 > 0) sb.append(String.format("原味分離乳清1kg   1050 x %d = %d\n", q4, sub4));
        if (q5 > 0) sb.append(String.format("肌酸500g          399 x %d = %d\n", q5, sub5));

        if (subtotal == 0) {
            sb.append("（你沒有選任何商品）\n");
        }
      
        sb.append("\n----------------------------\n");
        sb.append(String.format("小計：%d\n", subtotal));
        sb.append(hasDiscount ? "折扣：滿千打九折\n" : "折扣：未達滿千（無折扣）\n");
        sb.append(String.format("折扣後：%d\n", afterDiscount));
        sb.append(shipping == 0 ? "運費：滿500免運\n" : "未達500,需負擔運費：60\n");
        sb.append(String.format("總額：%d\n", total));

        return sb.toString();
    }
    private OrdersDaoImpl ordersDaoImpl = new OrdersDaoImpl();
    private OrderItemsDaoImpl ordrsItemsDao = new OrderItemsDaoImpl();
    public String checkout(String userNo, CartInput input) {
        // 1) 建立 orders，拿到 orderNo
        String orderNo = ordersDaoImpl.createOrderReturnOrderNo(userNo);
        if (orderNo == null) return null;

        // 2) 把 cart 裡「>0 的商品」寫進 order_items
        if (input.getWpco500g() > 0) ordrsItemsDao.addItem(orderNo, "P002", input.getWpco500g()); // WPC_O_500g
        if (input.getWpco1kg() > 0) ordrsItemsDao.addItem(orderNo, "P001", input.getWpco1kg()); // WPC_O_1kg
        if (input.getHpco500g() > 0) ordrsItemsDao.addItem(orderNo, "P005", input.getHpco500g()); // HPC_O_500g
        if (input.getHpco1kg() > 0) ordrsItemsDao.addItem(orderNo, "P004", input.getHpco1kg()); // HPC_O_1kg
        if (input.getCreatine500g() > 0) ordrsItemsDao.addItem(orderNo, "P003", input.getCreatine500g()); // Creatine_500g

        return orderNo;
    }
}