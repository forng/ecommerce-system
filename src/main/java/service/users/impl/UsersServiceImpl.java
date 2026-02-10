package service.users.impl;

import model.Users;
import service.users.UsersService;
import java.util.List;

import dao.users.impl.UsersDaoImpl;

public class UsersServiceImpl implements UsersService {
	public static void main(String[] args) {
		System.out.println(new UsersServiceImpl().find_username("Test1"));

	}
	UsersDaoImpl usersDaoImpl = new UsersDaoImpl();  //service要DAO餵資料
	@Override
	public void AddCustomer(Users users) {
		// TODO Auto-generated method stub
		usersDaoImpl.add(users);
	}

	@Override
	public Users find_Users_by_username_and_password(String username, String password) {
		Users users=null;
		List<Users> l=usersDaoImpl.selectByUsernameAndPassword(username, password);
		
		if(l.size()!=0)
		{
			users=l.get(0);
		}
		
		return users;
	}

	@Override
	public boolean find_username(String username) {
		// TODO Auto-generated method stub
		List<Users> l=usersDaoImpl.selectByUsername(username);
		return !l.isEmpty();
	}

}
