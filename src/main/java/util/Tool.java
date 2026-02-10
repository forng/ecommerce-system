package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.CartInput;



public class Tool {
	public static void main(String[] args) {
		//System.out.println(Tool.getDb());
		//Tool.saveObject(new UsersServiceImpl().find_Users_by_username_and_password("GD", "a1234"), "Users");
		System.out.println(Tool.readObject("Users"));
		
	}
	//連線
		public static Connection getDb()
		{
			String url="jdbc:mysql://localhost:3306/ecommerce";
			String user="root";
			String password="1234";
			Connection conn=null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				System.out.println("no Driver");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("no Connection");
				e.printStackTrace();
			}
			
			return conn;
		}
		///存USERNO
		private static String loginUserNo;

		public static void setLoginUserNo(String userNo) {
		    loginUserNo = userNo;
		}

		public static String getLoginUserNo() {
		    return loginUserNo;
		}
		//要存檔 先在model implements Serializable序列化
				//存檔-->writeObject    
						public static void saveObject(Object object, String saveFileName)
						{
							try {
								FileOutputStream fos=new FileOutputStream(saveFileName+".txt");
								ObjectOutputStream oos=new ObjectOutputStream(fos);
								oos.writeObject(object);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						//讀檔-->readObject
						public static Object readObject(String fileName)
						{
							Object object=null;
							try {
								FileInputStream fis=new FileInputStream(fileName+".txt");
								ObjectInputStream ois=new ObjectInputStream(fis);
								
								object=ois.readObject();
								
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							return object;
						}
						//存檔-->writeObject    要在CartInput import Serializable  讓物件序列化
						public static void saveCartinput(CartInput cartinput)
						{
							try {
								FileOutputStream fos=new FileOutputStream("CartInput.txt");
								ObjectOutputStream oos=new ObjectOutputStream(fos);
								oos.writeObject(cartinput);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						//讀檔-->readObject
						public static CartInput readCartInput()
						{
							CartInput cartinput=null;
							try {
								FileInputStream fis=new FileInputStream("CartInput.txt");
								ObjectInputStream ois=new ObjectInputStream(fis);
								
								cartinput=(CartInput)(ois.readObject());
								
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							return cartinput;
						}
						
		}