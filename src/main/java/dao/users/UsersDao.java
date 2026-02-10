package dao.users;

import java.util.List;


import model.Users;

public interface UsersDao {
	//create
		void add(Users users);
		
		//read
		List<Users> selectByUsernameAndPassword(String username,String password);
		List<Users> selectByUsername(String username);
}
