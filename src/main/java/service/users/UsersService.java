package service.users;

import model.Users;

public interface UsersService {
	void AddCustomer(Users users);
	Users find_Users_by_username_and_password(String username,String password);
	boolean find_username(String username);  //檢查帳號
}
