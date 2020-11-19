package com.project.praveen.expensetracker.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.praveen.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public User getUserById(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserId " + id + " not found"));
	}

	

	public void addUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
		
	}

	public User updateUser(int id, User newuser) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserId " + id + " not found"));
		user.setFirstName(newuser.getFirstName());
		user.setLastName(newuser.getLastName());
		userRepository.save(user);
		return newuser;
	}

	public User deleteUser(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserId " + id + " not found"));;
		userRepository.delete(user);
		return user;
	}
	
	
	
	

	
}
