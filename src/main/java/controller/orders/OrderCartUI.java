package controller.orders;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.users.LoginSuccessUI;
import model.CartInput;
import service.orderservice.OrderService;
import service.orderservice.impl.OrderServiceImpl;
import util.Tool;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;





public class OrderCartUI extends JFrame {
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderCartUI frame = new OrderCartUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public OrderCartUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 45, 418, 343);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea output1 = new JTextArea();
		output1.setBounds(10, 10, 398, 278);
		panel.add(output1);
		
		CartInput cartinput=Tool.readCartInput();
		
		OrderController controller = new OrderController();
		String summary = controller.buildCartSummary(cartinput);

		output1.setText(summary);
		
		
	
		
		
		
		JButton btnNewButton = new JButton("確定結帳");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String userNo = Tool.getLoginUserNo(); 
				CartInput input = Tool.readCartInput();
				OrderService service = new OrderServiceImpl();
			    String orderNo = service.checkout(userNo, input);
			    OrderConfirmUI orderConfirm=new OrderConfirmUI(orderNo);
			    orderConfirm.showLastOrder(orderNo);  //  orderNo 已經確定存在
			    orderConfirm.setVisible(true);
				dispose();
				
				
				
				
			}
		});
		btnNewButton.setBounds(76, 298, 85, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("返回商品列表");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				OrderListUI orderListUi=new OrderListUI();
				orderListUi.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(255, 298, 128, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("購物車");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 28));
		lblNewLabel.setBounds(171, 10, 103, 35);
		contentPane.add(lblNewLabel);

	}
	
	
}
