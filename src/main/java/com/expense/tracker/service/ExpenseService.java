package com.expense.tracker.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.entity.Expense;
import com.expense.tracker.exception.ExpenseNotFoundException;
import com.expense.tracker.repository.ExpenseRepository;

@Service
public class ExpenseService {
	@Autowired
	private ExpenseRepository repo;
	public Expense saveExpense(Expense expense) {
		return repo.save(expense);
	}
	public List<Expense> getallExpenses(){
		return repo.findAll();
	}
	public Expense getExpenseById(Long id) {
        return repo.findById(id)
        		.orElseThrow(()->
        		new ExpenseNotFoundException("Expense not found with id : " + id));
    }

    public void deleteExpense(Long id) {
        repo.deleteById(id);
    }
    public Expense updateExpense(Long id, Expense expense) {

        Expense existingExpense = repo.findById(id).orElse(null);

        if (existingExpense != null) {
            existingExpense.setTitle(expense.getTitle());
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setCategory(expense.getCategory());
            existingExpense.setExpenseDate(expense.getExpenseDate());

            return repo.save(existingExpense);
        }

        return null;
    }
    public List<Expense> getMonthlyExpenses(
            LocalDate start,
            LocalDate end){

        return repo.findByExpenseDateBetween(start, end);
    }
    public List<Expense> getExpensesByCategory(
            String category){

        return repo.findByCategory(category);
    }
    public List<Expense> searchExpense(
            String keyword){
    		System.out.println("Searching for: " + keyword);
        return repo.findByTitleContainingIgnoreCase(
                keyword);
        
    }
    public Double getMonthlyExpense() {
        return repo.getMonthlyExpense();
    }
    public Double getTotalExpense() {
        return repo.getTotalExpense();
    }
    public Double getFoodExpense() {
        return repo.getFoodExpense();
    }

    public Double getTravelExpense() {
        return repo.getTravelExpense();
    }

    public List<Object[]> getCategorySummary() {
        return repo.getCategorySummary();
    }

    public List<Object[]> getMonthlySummary() {
        return repo.getMonthlySummary();
    }
    public Page<Expense> getExpenses(int page, int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return repo.findAll(pageable);
    }

    public List<Expense> sortExpenses() {

        return repo.findAll(
                Sort.by("amount")
                        .descending());
    }
    public List<Expense> getUserExpenses(
            Long userId) {

        return repo.findByUserId(userId);
    }
    
    
}
