package controller.orders;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginUI;
import util.Tool;
import vo.OrderDetailVODaoImpl;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;

import javax.swing.JScrollPane;

public class OrderConfirmUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea output;
    private String currentOrderNo;
    private String lastOrderNo;
    
    
    public void showLastOrder(String orderNo) {
        OrderDetailVODaoImpl dao = new OrderDetailVODaoImpl();   //呼叫剛剛的訂單號
        String text = dao.buildTextByOrderNo(orderNo);
        output.setText(text);
    }
    public OrderConfirmUI(String orderNo) {
    	this();                 // ⭐ 呼叫原本「有畫 UI」的建構式
        this.lastOrderNo = orderNo;
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderConfirmUI frame = new OrderConfirmUI();
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
	public OrderConfirmUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 24, 418, 382);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 398, 324);
		panel.add(scrollPane);
		
		output = new JTextArea();
		scrollPane.setViewportView(output);
		
		
		
		
		
		
		
		JButton btnNewButton = new JButton("列印");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					output.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 349, 85, 23);
		panel.add(btnNewButton);
		
		JButton btnHistory = new JButton("歷史訂單紀錄");
		btnHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String userNo = Tool.getLoginUserNo();
		        OrderDetailVODaoImpl dao = new OrderDetailVODaoImpl();

		        String text = dao.buildHistoryByUserNo(userNo);
		        output.setText(text);
				
			}
		});
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHistory.setBounds(105, 349, 134, 23);
		panel.add(btnHistory);
		
		JButton btnNewButton_2 = new JButton("繼續購買");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OrderListUI orderListUi=new OrderListUI();
				orderListUi.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(246, 349, 85, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("登出");
		btnNewButton_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI loginui=new LoginUI();
				loginui.setVisible(true);
				dispose();
				
				
			}
		});
		btnNewButton_2_1.setBounds(341, 349, 67, 23);
		panel.add(btnNewButton_2_1);

	}

}
