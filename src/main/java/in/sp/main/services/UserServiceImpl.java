package in.sp.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entity.User;
import in.sp.main.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User regsiterUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User loginUser(String email, String password) {
		User validUser = userRepository.findByEmail(email);
		if(validUser != null && validUser.getPassword().equals(password)) {
			return validUser;
		}
		return null;
	}

}
