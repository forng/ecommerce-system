package controller.users;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginUI;


import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginErrorUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginErrorUI frame = new LoginErrorUI();
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
	public LoginErrorUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("登入失敗");
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel.setBounds(201, 131, 108, 40);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("若無帳號請先註冊");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddUsersUI addusersui=new AddUsersUI();
				addusersui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(144, 294, 193, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton1 = new JButton("請重新登入");
		btnNewButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUI loginui=new LoginUI();
				loginui.setVisible(true);
				dispose();
			}
		});
		btnNewButton1.setBounds(144, 215, 193, 50);
		contentPane.add(btnNewButton1);
		
		JLabel lblNewLabel_1 = new JLabel("   無此帳號或密碼錯誤");
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel_1.setBounds(144, 175, 193, 40);
		contentPane.add(lblNewLabel_1);

	}
}