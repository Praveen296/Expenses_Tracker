package com.project.praveen.expensetracker.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.praveen.ResourceNotFoundException;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getusers() {
	
		List<User> users = userService.getAllUsers();
		return users;
	}
	
	@GetMapping("/user/{id}")
	public User getuser(@PathVariable("id") int id) throws ResourceNotFoundException {
		
		return userService.getUserById(id);
		
	}
	
	
	@PostMapping("/users")
	public void addUser(@RequestBody User user) {
		
		userService.addUser(user);
	}
	
	@PutMapping("/user/{id}")
	public User updateuser(@RequestBody User user,@PathVariable("id") int id) throws ResourceNotFoundException {
	
		return userService.updateUser(id,user);
		
	}
	
	@DeleteMapping("/user/{id}")
	public User deleteUser(@PathVariable("id") int id) throws ResourceNotFoundException {
	
		return userService.deleteUser(id);
		
	}

}
