package com.website;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	public void save(User user) {
		userRepository.save(user);
	}
	public User login(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
	public boolean isUsernameTaken(String username) {
		return userRepository.findByUsername(username) != null;
	}

}
