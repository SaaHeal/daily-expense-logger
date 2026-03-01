package com.example.expenselogger.controller;

import java.util.List;
import java.util.Map;

import com.example.expenselogger.model.Expense;
import com.example.expenselogger.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService service;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        logger.info("REST: POST request to add expense: {}", expense);
        try {
            return service.saveExpense(expense);
        } catch (Exception e) {
            logger.error("REST: Error in POST /api/expenses", e);
            throw e;
        }
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        logger.info("REST: GET request for all expenses");
        try {
            return service.getAllExpenses();
        } catch (Exception e) {
            logger.error("REST: Error in GET /api/expenses", e);
            throw e;
        }
    }

    @GetMapping("/summary")
    public Map<String, Double> getSummary() {
        logger.info("REST: GET request for summary");
        try {
            return service.getSummary();
        } catch (Exception e) {
            logger.error("REST: Error in GET /api/expenses/summary", e);
            throw e;
        }
    }
}
