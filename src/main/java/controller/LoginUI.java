package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.admin.AdminUI;
import controller.users.AddUsersUI;
import controller.users.LoginErrorUI;
import controller.users.LoginSuccessUI;
import model.Users;
import service.users.impl.UsersServiceImpl;
import util.Tool;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
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
	public LoginUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(59, 24, 451, 73);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("會員登入");
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel.setBounds(156, 23, 79, 23);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(59, 107, 451, 193);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("帳號");
		lblNewLabel_1.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel_1.setBounds(54, 38, 46, 23);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("密碼");
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(54, 87, 46, 23);
		panel_1.add(lblNewLabel_1_1);
		
		username = new JTextField();
		username.setBounds(169, 40, 96, 21);
		panel_1.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(169, 89, 96, 21);
		panel_1.add(password);
		password.setColumns(10);
		
		
		UsersServiceImpl usersServiceImpl=new UsersServiceImpl();
		
		JButton btnNewButton = new JButton("登入");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String Username=username.getText();
				String Password=password.getText();
				
				Users users=usersServiceImpl.find_Users_by_username_and_password(Username, Password);
				if(users!=null)
				{
					Tool.saveObject(users,"Users");   //登入時存入TXT
					Tool.setLoginUserNo(users.getUser_no());  //登入成功暫存一個USER_NO
					LoginSuccessUI loginSuccess=new LoginSuccessUI();
					loginSuccess.setVisible(true);
					dispose();
				}
				else
				{
					LoginErrorUI loginerror=new LoginErrorUI();
					loginerror.setVisible(true);
					dispose();
				}
				
			}
		});
		btnNewButton.setBounds(54, 137, 87, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("註冊");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddUsersUI addusersui=new AddUsersUI();
				addusersui.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton_1.setBounds(278, 137, 87, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("進入管理者模式");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				  String u = username.getText().trim();
			        String p = password.getText().trim();

			        // 只在正確時才有反應
			        if ("AAA".equals(u) && "AAA1234".equals(p)) {
			            AdminUI adminUI = new AdminUI();
			            adminUI.setVisible(true);
			            dispose();
			        }
				
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_2.setBounds(81, 328, 152, 23);
		contentPane.add(btnNewButton_2);

	}
}