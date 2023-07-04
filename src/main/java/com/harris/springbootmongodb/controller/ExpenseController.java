package com.harris.springbootmongodb.controller;

import com.harris.springbootmongodb.model.Expense;
import com.harris.springbootmongodb.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/save")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense){
        Expense resp = expenseService.addExpense(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/update")
    public ResponseEntity updateExpense(@RequestBody Expense expense){
        expenseService.updateExpense(expense);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Expense>> getAlExpenses(){
        List<Expense> allExpenses = expenseService.getAlExpenses();
        return ResponseEntity.status(HttpStatus.OK).body(allExpenses);
    }

    @PostMapping("/fetch/{name}")
    public ResponseEntity<Expense> getExpenseByName(@PathVariable("name") String name){
        Expense expenseByName = expenseService.getExpenseByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(expenseByName);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteExpense(@PathVariable("id") String id){
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
