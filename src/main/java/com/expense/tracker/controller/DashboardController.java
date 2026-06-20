package com.expense.tracker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.expense.tracker.service.ExpenseService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ExpenseService service;

    @GetMapping("/total-expense")
    public Map<String, Double> totalExpense() {

        Map<String, Double> response =
                new HashMap<>();

        response.put(
                "totalExpense",
                service.getTotalExpense());

        return response;
    }

    @GetMapping("/category-summary")
    public List<Object[]> categorySummary() {
        return service.getCategorySummary();
    }

    @GetMapping("/monthly-summary")
    public List<Object[]> monthlySummary() {
        return service.getMonthlySummary();
    }
    @GetMapping("/stats")
    public Map<String, Double> getStats() {

        Map<String, Double> stats =
                new HashMap<>();

        stats.put(
                "totalExpense",
                service.getTotalExpense());

        stats.put(
                "foodExpense",
                service.getFoodExpense());

        stats.put(
                "travelExpense",
                service.getTravelExpense());
        stats.put(
                "monthlyExpense",
                service.getMonthlyExpense());

        return stats;
    }
}