package com.expense.tracker.repository;



import java.time.LocalDate;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expense.tracker.entity.Expense;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	List<Expense> findByExpenseDateBetween(
	        LocalDate startDate,
	        LocalDate endDate);
	List<Expense> findByCategory(String category);
	List<Expense> findByTitleContainingIgnoreCase(
	        String keyword);
	
	
	@Query("SELECT SUM(e.amount) FROM Expense e")
	Double getTotalExpense();

	@Query("""
	SELECT e.category, SUM(e.amount)
	FROM Expense e
	GROUP BY e.category
	""")
	List<Object[]> getCategorySummary();

	@Query("""
	SELECT MONTH(e.expenseDate), SUM(e.amount)
	FROM Expense e
	GROUP BY MONTH(e.expenseDate)
	""")
	List<Object[]> getMonthlySummary();
	@Query("SELECT SUM(e.amount) FROM Expense e WHERE e.category='Food'")
	Double getFoodExpense();

	@Query("SELECT SUM(e.amount) FROM Expense e WHERE e.category='Travel'")
	Double getTravelExpense();
	@Query("""
			SELECT COALESCE(SUM(e.amount),0)
			FROM Expense e
			WHERE MONTH(e.expenseDate) = MONTH(CURRENT_DATE)
			AND YEAR(e.expenseDate) = YEAR(CURRENT_DATE)
			""")
			Double getMonthlyExpense();
	
	List<Expense> findByUserId(Long userId);
	
}
