package controller.users;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



import model.Users;
import service.users.impl.UsersServiceImpl;

public class AddUsersUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField city;
	private JTextField address;
	private JTextField phone;
	private JTextField username;
	private JTextField password_hash;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUsersUI frame = new AddUsersUI();
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
	public AddUsersUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(34, 28, 473, 353);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("註冊");
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 20));
		lblNewLabel.setBounds(181, 32, 46, 33);
		panel.add(lblNewLabel);
		
				
		JLabel lblNewLabel_1_1 = new JLabel("姓名");
		lblNewLabel_1_1.setFont(new Font("新細明體", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(40, 79, 71, 26);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("地址");
		lblNewLabel_1_2.setFont(new Font("新細明體", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(40, 149, 71, 26);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("帳號");
		lblNewLabel_1_3.setFont(new Font("新細明體", Font.BOLD, 15));
		lblNewLabel_1_3.setBounds(40, 218, 71, 26);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("密碼");
		lblNewLabel_1_4.setFont(new Font("新細明體", Font.BOLD, 15));
		lblNewLabel_1_4.setBounds(40, 244, 71, 26);
		panel.add(lblNewLabel_1_4);
		
		
		
		
		
	
		
		
		JLabel lblNewLabel_1_1_1 = new JLabel("城市");
		lblNewLabel_1_1_1.setFont(new Font("新細明體", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(40, 113, 71, 26);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("電話");
		lblNewLabel_1_2_1.setFont(new Font("新細明體", Font.BOLD, 15));
		lblNewLabel_1_2_1.setBounds(40, 185, 71, 26);
		panel.add(lblNewLabel_1_2_1);
		
		name = new JTextField();
		name.setBounds(88, 82, 96, 21);
		panel.add(name);
		name.setColumns(10);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(88, 115, 96, 21);
		panel.add(city);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(88, 152, 96, 21);
		panel.add(address);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(88, 187, 96, 21);
		panel.add(phone);
		
		username = new JTextField();
		username.setColumns(10);
		username.setBounds(88, 221, 96, 21);
		panel.add(username);
		
		password_hash = new JTextField();
		password_hash.setColumns(10);
		password_hash.setBounds(88, 247, 96, 21);
		panel.add(password_hash);
		
//////////////////////////////////////////////////////////////////////////////
		JButton btnNewButton = new JButton("確定");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1.接收 username-->判斷帳號是否使用
				 * true-->AddCustomerErrorUI
				 * false->接收 註冊資料-->new Customer()-->AddCustomer方法->AddCustomerSuccessUI
				 */
				UsersServiceImpl csi=new UsersServiceImpl();
				String Username=username.getText();
				boolean x=csi.find_username(Username);
				if(x)   //布林預設false 
				{
					AddUsersErrorUI adderror=new AddUsersErrorUI();
					adderror.setVisible(true);
					dispose();
				}
				else
				{
					String Name=name.getText();
					String City=city.getText();
					String Address=address.getText();
					String Phone=phone.getText();
					String Username1=username.getText();
					String Password_hash=password_hash.getText();
					Users users=new Users(Name,City,Address,Phone,Username1,Password_hash,null,null);

					csi.AddCustomer(users);

					AddUsersSuccessUI addsuccess=new AddUsersSuccessUI();
					addsuccess.setVisible(true);
					dispose();

				}

			}
		});
		btnNewButton.setFont(new Font("新細明體", Font.BOLD, 15));
		btnNewButton.setBounds(126, 292, 185, 23);
		panel.add(btnNewButton);

	}
}