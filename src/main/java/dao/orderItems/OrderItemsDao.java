package dao.orderItems;

public interface OrderItemsDao {
	void addItem(String orderNo, String productNo, int amounts);
}
