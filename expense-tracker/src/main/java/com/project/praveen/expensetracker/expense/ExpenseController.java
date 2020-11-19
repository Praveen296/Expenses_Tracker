package com.project.praveen.expensetracker.expense;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/user/{userId}/expenses")
	public List<Expense> getexpenses(@PathVariable("userId") int userId) throws ResourceNotFoundException {
	
		List<Expense> expenses = expenseService.getAllExpenses(userId);
		return expenses;
	}
	
	@GetMapping("/user/{userId}/expense/{id}")
	public Expense getExpense(@PathVariable("userId") int userId,@PathVariable("id") int id) throws ResourceNotFoundException {
		
		return expenseService.getExpenseById(id,userId);
		
	}
	
	/*@GetMapping("/user/{userId}/expense/invoice/{invoice}")
	public Expense getExpenseByInvoice(@PathVariable String invoice) {
	
		return expenseService.getExpenseByInvoice(invoice);
		
	}*/
	
	@PostMapping("/user/{userId}/expenses")
	public Expense addExpense(@PathVariable("userId") int userId,@Valid @RequestBody Expense expense) throws ResourceNotFoundException {
		
		return expenseService.addExpense(userId,expense);
	}
	
	@PutMapping("/user/{userId}/expense/{id}")
	public Expense updateExpense(@PathVariable("userId") int userId,@RequestBody Expense expense,@PathVariable("id") int id) throws ResourceNotFoundException {
	
		return expenseService.updateExpense(id,userId,expense);
		
	}
	
	@DeleteMapping("/user/{userId}/expense/{id}")
	public ResponseEntity<?> deleteExpense(@PathVariable("userId") int userId,@PathVariable("id") int id) throws ResourceNotFoundException {
	
		return expenseService.deleteExpense(id,userId);
		
	}

}
