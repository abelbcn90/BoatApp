/**
 * 
 */
package com.owt.BoatApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.owt.BoatApp.entity.User;
import com.owt.BoatApp.service.UserService;

import jakarta.validation.Valid;

/**
 * @author abelb
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/users")
	public User saveUser(@Valid @RequestBody User user) {
		return this.userService.createUser(user);
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/users")
	public List<User> fetchUsers() {
		return this.userService.fetchUsers();
	}
	
	/**
	 * 
	 * @param user
	 * @param userId
	 * @return
	 */
	@PutMapping("/users/{id}")
	public User updateDepartment(@RequestBody User user, @PathVariable("id") Long userId) {
		return this.userService.updateUser(user, userId);
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	@DeleteMapping("/users/{id}")
	public String deleteUserById(@PathVariable("id") Long userId) {
		this.userService.deleteUserById(userId);
		return "Deleted Successfully";
	}
}
