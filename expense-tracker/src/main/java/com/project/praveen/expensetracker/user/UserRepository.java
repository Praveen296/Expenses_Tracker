package com.project.praveen.expensetracker.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
	
	//public User findById(int id);

}
