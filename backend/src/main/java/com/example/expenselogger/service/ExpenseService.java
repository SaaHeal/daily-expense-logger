package com.example.expenselogger.service;

import com.example.expenselogger.model.Expense;
import com.example.expenselogger.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ExpenseService {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);

    @Autowired
    private ExpenseRepository repository;

    public Expense saveExpense(Expense expense) {
        logger.info("VALIDATION: Processing expense: {}", expense);

        if (expense.getAmount() == null) {
            logger.warn("VALIDATION FAILED: Amount is null. Rejecting.");
            throw new IllegalArgumentException("Amount cannot be null");
        }

        try {
            if (expense.getDate() == null) {
                expense.setDate(LocalDate.now());
            }
            Expense saved = repository.save(expense);
            logger.info("Successfully saved transaction: ID {}", saved.getId());
            return saved;
        } catch (Exception e) {
            logger.error("SYSTEM ERROR: Failed to save expense", e);
            throw e;
        }
    }

    public List<Expense> getAllExpenses() {
        logger.info("DB: Fetching all transactions");
        try {
            return repository.findAll();
        } catch (Exception e) {
            logger.error("DB: Failed to fetch transactions", e);
            throw e;
        }
    }

    public Map<String, Double> getSummary() {
        logger.info("ANALYTICS: Re-calculating summaries");
        try {
            LocalDate today = LocalDate.now();

            Map<String, Double> summary = new HashMap<>();
            summary.put("today", calculateSum(repository.findByDateBetween(today, today)));

            LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            summary.put("week", calculateSum(repository.findByDateBetween(startOfWeek, today)));

            LocalDate startOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
            summary.put("month", calculateSum(repository.findByDateBetween(startOfMonth, today)));

            LocalDate startOfYear = today.with(TemporalAdjusters.firstDayOfYear());
            summary.put("year", calculateSum(repository.findByDateBetween(startOfYear, today)));

            return summary;
        } catch (Exception e) {
            logger.error("ANALYTICS: Summary calculation failed", e);
            throw e;
        }
    }

    private double calculateSum(List<Expense> expenses) {
        return expenses.stream()
                .map(Expense::getAmount)
                .filter(Objects::nonNull) // Prevent NullPointerException
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
