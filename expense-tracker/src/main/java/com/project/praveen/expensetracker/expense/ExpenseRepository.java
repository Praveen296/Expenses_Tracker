package com.project.praveen.expensetracker.expense;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
	
	public Expense findByInvoice(String invoice);
	
	public List<Expense> findByUserId(int userId);
	
	public Optional<Expense> findByIdAndUserId(int id,int userId);
	
	public Expense findById(int id);

}
