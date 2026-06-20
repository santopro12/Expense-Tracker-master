package com.expense.tracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.entity.Expense;
import com.expense.tracker.entity.User;
import com.expense.tracker.service.ExpenseService;
import com.expense.tracker.service.UserService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
@RestController
@RequestMapping("/expense")
public class ExpenseController {
	@Autowired
	private ExpenseService service;
	@Autowired
	private UserService userService;
	@PostMapping
	public Expense save(@Valid @RequestBody Expense expense) {
		return service.saveExpense(expense);
	}
	@GetMapping
	public List<Expense> getAll(){
		return service.getallExpenses();
	}
	@GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return service.getExpenseById(id);
    }

    // Delete Expense
	@DeleteMapping("/{id}")
	public String deleteExpense(@PathVariable Long id) {

	    service.deleteExpense(id);

	    return "Deleted Successfully";
	}
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id,
                                 @Valid @RequestBody Expense expense) {
        return service.updateExpense(id, expense);
    }
    @GetMapping("/month")
    public List<Expense> getMonthlyExpenses(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate){

        return service.getMonthlyExpenses(
                startDate,
                endDate);
    }
    @GetMapping("/category/{category}")
    public List<Expense> getExpensesByCategory(
            @PathVariable String category){

        return service.getExpensesByCategory(category);
    }
    @GetMapping("/search")
    public List<Expense> searchExpense(
            @RequestParam String keyword){

        return service.searchExpense(keyword);
        
    }
    @GetMapping("/page")
    public Page<Expense> getExpenses(
            @RequestParam int page,
            @RequestParam int size) {

        return service.getExpenses(
                page,
                size);
    }

    @GetMapping("/sort")
    public List<Expense> sortExpenses() {

        return service.sortExpenses();
    }
    @GetMapping("/user/{userId}")
    public List<Expense> getUserExpenses(
            @PathVariable Long userId) {

        return service.getUserExpenses(
                userId);
    }
        
}
