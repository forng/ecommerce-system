package controller.orders;

import model.CartInput;
import service.orderservice.OrderService;
import service.orderservice.impl.OrderServiceImpl;


public class OrderController {

    private OrderService orderService = new OrderServiceImpl();

    public String buildCartSummary(CartInput input) {
        return orderService.buildCartSummary(input);
    }
}
