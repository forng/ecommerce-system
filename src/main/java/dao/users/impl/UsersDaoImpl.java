package dao.users.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.users.UsersDao;
import model.Users;
import util.Tool;

public class UsersDaoImpl implements UsersDao{

	public static void main(String[] args) {
		 UsersDao dao = new UsersDaoImpl();

		    System.out.println("=== 測試登入 ===");
		    List<Users> list = dao.selectByUsernameAndPassword("Test1", "1234");

		    if (list.size() == 0) {
		        System.out.println("登入失敗：查無此帳號或密碼錯誤");
		    } else {
		        Users u = list.get(0);
		        System.out.println("登入成功！");
		        System.out.println("id = " + u.getId());
		        System.out.println("user_no = " + u.getUser_no());
		        System.out.println("username = " + u.getUsername());
		        System.out.println("name = " + u.getName());
		        System.out.println("phone = " + u.getPhone());
		    }
		}

	
	Connection conn=Tool.getDb();
	@Override
	public void add(Users users) {
		String sql="insert into users(name,city,address,phone,username,password_hash) "
				+ "values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, users.getName());
			ps.setString(2, users.getCity());
			ps.setString(3, users.getAddress());
			ps.setString(4, users.getPhone());
			ps.setString(5, users.getUsername());
			ps.setString(6, users.getPassword_hash());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Users> selectByUsernameAndPassword(String username, String password) {
		String sql="select * from users where username=? and password_hash=?";
		List<Users> l=new ArrayList<>();
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Users u=new Users();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setCity(rs.getString("city"));
				u.setAddress(rs.getString("address"));
				u.setPhone(rs.getString("phone"));
				u.setUsername(rs.getString("username"));
				u.setPassword_hash(rs.getString("password_hash"));
				u.setUser_no(rs.getString("user_no"));
				l.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

	@Override
	public List<Users> selectByUsername(String username) {
		String sql="select * from users where username=? ";
		List<Users> l=new ArrayList<>();
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				Users u=new Users();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setCity(rs.getString("city"));
				u.setAddress(rs.getString("address"));
				u.setPhone(rs.getString("phone"));
				u.setUsername(rs.getString("username"));
				u.setPassword_hash(rs.getString("password_hash"));
				u.setUser_no(rs.getString("user_no"));
				l.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}

}
