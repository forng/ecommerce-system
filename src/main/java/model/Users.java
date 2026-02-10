package model;

import java.io.Serializable;

public class Users implements Serializable {
	private int id;
	private String username;
	private String password_hash;
	private String name;
	private String city;
	private String address;
	private String phone;
	private String created_at;
	private String user_no;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users( String name, String city, String address, String phone,String username, String password_hash,
			String created_at, String user_no) {
		super();
		this.username = username;
		this.password_hash = password_hash;
		this.name = name;
		this.city = city;
		this.address = address;
		this.phone = phone;
		this.created_at = created_at;
		this.user_no = user_no;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword_hash() {
		return password_hash;
	}
	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUser_no() {
		return user_no;
	}
	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}
	
	
}
