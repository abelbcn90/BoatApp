/**
 * 
 */
package com.owt.BoatApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owt.BoatApp.entity.User;
import com.owt.BoatApp.repository.UserRepository;

/**
 * @author abelb
 *
 */
@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * 
	 * @return
	 */
	public List<User> fetchUsers() {
		return (List<User>) this.userRepository.findAll();
	}

	/**
	 * 
	 * @param username
	 * @return
	 */
	public boolean checkUserExistsByName(final String username) {
		return this.userRepository.existsByUsername(username);
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public boolean checkUserExistsByEmail(final String email) {
		return this.userRepository.existsByEmail(email);
	}

	/**
	 * 
	 * @param user
	 */
	public User createUser(final User user) {
		return this.userRepository.save(user);
	}

	/**
	 * 
	 * @param user
	 * @param id
	 * @return
	 */
	public User updateUser(final User user, final Long userId) {
		final User userDB = this.userRepository.findById(userId).get();

		if (null != user.getUsername() && !"".equalsIgnoreCase(user.getUsername())) {
			userDB.setUsername(user.getUsername());
		}

		if (null != user.getEmail() && !"".equalsIgnoreCase(user.getEmail())) {
			userDB.setEmail(user.getEmail());
		}

		if (null != user.getPassword() && !"".equalsIgnoreCase(user.getPassword())) {
			userDB.setPassword(user.getPassword());
		}

		return this.userRepository.save(userDB);
	}

	/**
	 * 
	 * @param userId
	 */
	public void deleteUserById(final Long userId) {
		this.userRepository.deleteById(userId);
	}
}
