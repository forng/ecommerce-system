package service.orderservice;
import model.CartInput;
public interface OrderService {
	 String buildCartSummary(CartInput input);
	 String checkout(String userNo, CartInput input);
}
