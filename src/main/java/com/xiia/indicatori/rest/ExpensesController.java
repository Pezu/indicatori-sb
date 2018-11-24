package com.xiia.indicatori.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiia.indicatori.service.ExpenseService;
import com.xiia.indicatori.service.LoginService;

@RestController
@RequestMapping("/expense")
public class ExpensesController {

    private final ExpenseService expenseService;
    private final LoginService loginService;
    
    @Autowired
    public ExpensesController(ExpenseService expenseService, LoginService loginService) {
        this.expenseService = expenseService;
        this.loginService = loginService;
    }  
	
    
	
}
