package com.harris.springbootmongodb.service;

import com.harris.springbootmongodb.model.Expense;
import com.harris.springbootmongodb.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense){
        return expenseRepository.insert(expense);
    }

    public void updateExpense(Expense expense){
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(() ->
                        new RuntimeException(String.format("Expense with id %s cannot be found.", expense.getId())));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory());
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(savedExpense);
    }

    public List<Expense> getAlExpenses(){
        return expenseRepository.findAll();
    }

    public Expense getExpenseByName(String name){
        Expense expense = expenseRepository.findByName(name).orElseThrow(() ->
                new RuntimeException(String.format("Expense with name %s not found", name)));
        return expense;
    }

    public void deleteExpense(String id){
        expenseRepository.deleteById(id);
    }

}
