package in.sp.main.services;

import in.sp.main.entity.User;

public interface UserService {
	
	public User regsiterUser(User user);
	
	public User loginUser(String email, String password);
}
