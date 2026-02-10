package controller.orders;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.CartInput;
import service.orderservice.OrderService;
import service.orderservice.impl.OrderServiceImpl;
import util.Tool;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderListUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField wpco500g;
	private JTextField wpco1kg;
	private JTextField hpco500g;
	private JTextField hpco1kg;
	private JTextField creatine500g;
	private String summary;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderListUI frame = new OrderListUI();
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
	public OrderListUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 10, 418, 384);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("運動補品小舖");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel.setBounds(155, 10, 140, 39);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("滿千打九折,滿500免運(運費60)");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(42, 335, 366, 39);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("原味濃縮乳清500g");
		lblNewLabel_2.setBounds(26, 69, 106, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("原味濃縮乳清1kg");
		lblNewLabel_2_1.setBounds(26, 94, 106, 15);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("原味分離乳清500g");
		lblNewLabel_2_1_1.setBounds(26, 119, 106, 15);
		panel.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("原味分離乳清1kg");
		lblNewLabel_2_1_1_1.setBounds(26, 144, 106, 15);
		panel.add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("肌酸500g");
		lblNewLabel_2_1_1_1_1.setBounds(26, 169, 106, 15);
		panel.add(lblNewLabel_2_1_1_1_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("499元");
		lblNewLabel_2_2.setBounds(142, 69, 58, 15);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("900元");
		lblNewLabel_2_2_1.setBounds(142, 94, 58, 15);
		panel.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("599元");
		lblNewLabel_2_2_2.setBounds(142, 119, 58, 15);
		panel.add(lblNewLabel_2_2_2);
		
		JLabel lblNewLabel_2_2_3 = new JLabel("1050元");
		lblNewLabel_2_2_3.setBounds(142, 144, 58, 15);
		panel.add(lblNewLabel_2_2_3);
		
		JLabel lblNewLabel_2_2_4 = new JLabel("399元");
		lblNewLabel_2_2_4.setBounds(142, 169, 58, 15);
		panel.add(lblNewLabel_2_2_4);
		
		JLabel lblNewLabel_3 = new JLabel("品項");
		lblNewLabel_3.setBounds(26, 50, 46, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("單價");
		lblNewLabel_3_1.setBounds(142, 50, 46, 15);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("訂購數量");
		lblNewLabel_3_1_1.setBounds(231, 50, 64, 15);
		panel.add(lblNewLabel_3_1_1);
		
		wpco500g = new JTextField("0");            //預設為0
		wpco500g.setBounds(231, 66, 96, 21);
		panel.add(wpco500g);
		wpco500g.setColumns(10);
		
		wpco1kg = new JTextField("0");
		wpco1kg.setColumns(10);
		wpco1kg.setBounds(231, 91, 96, 21);
		panel.add(wpco1kg);
		
		hpco500g = new JTextField("0");
		hpco500g.setColumns(10);
		hpco500g.setBounds(231, 116, 96, 21);
		panel.add(hpco500g);
		
		hpco1kg = new JTextField("0");
		hpco1kg.setColumns(10);
		hpco1kg.setBounds(231, 141, 96, 21);
		panel.add(hpco1kg);
		
		creatine500g = new JTextField("0");
		creatine500g.setColumns(10);
		creatine500g.setBounds(231, 166, 96, 21);
		panel.add(creatine500g);
		
		///////////////////////////event///////////////////////////////////////
	
		JButton btnNewButton = new JButton("加入購物車");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int q1 = Integer.parseInt(wpco500g.getText());
			    int q2 = Integer.parseInt(wpco1kg.getText());
			    int q3 = Integer.parseInt(hpco500g.getText());
			    int q4 = Integer.parseInt(hpco1kg.getText());
			    int q5 = Integer.parseInt(creatine500g.getText());

			    CartInput cartinput = new CartInput(q1, q2, q3, q4, q5);

			    Tool.saveCartinput(cartinput);

			    OrderCartUI ui = new OrderCartUI();
			    ui.setVisible(true);
			    dispose();
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			}
		});
		btnNewButton.setBounds(72, 228, 116, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("全部清除");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				wpco500g.setText("0");
				wpco1kg.setText("0");
				hpco500g.setText("0");
				hpco1kg.setText("0");
				creatine500g.setText("0");
				
				
			}
		});
		btnNewButton_1.setBounds(242, 228, 116, 23);
		panel.add(btnNewButton_1);

	}
}
