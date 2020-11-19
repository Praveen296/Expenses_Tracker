package com.project.praveen.expensetracker.expense;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.praveen.ResourceNotFoundException;
import com.project.praveen.expensetracker.user.UserRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Expense> getAllExpenses(int userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		if(!userRepository.existsById(userId)) {
			throw new ResourceNotFoundException("userId not found");
		}
		return expenseRepository.findByUserId(userId);
	}

	public Expense getExpenseById(int id,int userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return expenseRepository.findByIdAndUserId(id,userId).orElseThrow(() -> new ResourceNotFoundException("Expense not found with expenseId " + id + " and user id " + userId));
	}

	/*public Expense getExpenseByInvoice(String invoice) {
		// TODO Auto-generated method stub
		return expenseRepository.findByInvoice(invoice);
	}*/

	public Expense addExpense(int userId, Expense expense) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
		return userRepository.findById(userId).map(user -> {
			expense.setUser(user);
			return expenseRepository.save(expense);
		}).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
		
		
	}

	public Expense updateExpense(int id, int userId, Expense newExpense) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		
		if(!userRepository.existsById(userId)) {
			throw new ResourceNotFoundException("userId not found");
		}
		
		return expenseRepository.findByIdAndUserId(id,userId).map(expense -> {
			expense.setAmount(newExpense.getAmount());
			expense.setDescription(newExpense.getDescription());
			return expenseRepository.save(expense);
		}).orElseThrow(() -> new ResourceNotFoundException("expenseId " + id + " not found"));
		
	}

	public ResponseEntity < ? > deleteExpense(int id, int userId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		if(!userRepository.existsById(userId)) {
			throw new ResourceNotFoundException("userId not found");
		}
		
		return expenseRepository.findByIdAndUserId(id,userId).map(expense -> {
			expenseRepository.delete(expense);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("expenseId " + id + " not found"));
		
		
	}
	
}
