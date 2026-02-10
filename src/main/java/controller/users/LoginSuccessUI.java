package controller.users;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.orders.OrderListUI;

import model.Users;
import util.Tool;

public class LoginSuccessUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSuccessUI frame = new LoginSuccessUI();
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
	public LoginSuccessUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("歡迎您登入成功");
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel.setBounds(156, 43, 195, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("進入商品頁面");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				OrderListUI orderui=new OrderListUI();
				orderui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 16));
		btnNewButton.setBounds(60, 148, 241, 23);
		contentPane.add(btnNewButton);
		
		JLabel users = new JLabel("");
		users.setFont(new Font("新細明體", Font.BOLD, 18));
		users.setBounds(60, 43, 130, 36);
		contentPane.add(users);
		
		Object o=Tool.readObject("Users");
		Users users1=(Users) o;
		users.setText(users1.getName());

	}

}