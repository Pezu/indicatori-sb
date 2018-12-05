package com.xiia.indicatori.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiia.indicatori.domain.Expense;
import com.xiia.indicatori.pojo.ExpenseRequest;
import com.xiia.indicatori.pojo.ExpenseResponse;
import com.xiia.indicatori.pojo.SplitRequest;
import com.xiia.indicatori.pojo.SplitDetails;
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
	
    
    @RequestMapping(value = "/split",
			method = {RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public SplitDetails getSplitDetails(@RequestBody SplitRequest request,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		return expenseService.getSplitDetails(request);
		
	}
    
    @RequestMapping(value = "/isHsdAvailable/{unit_id}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Boolean isHsdAvailable(@PathVariable("unit_id") Integer unitId,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return expenseService.isHsdAvailable(unitId);
		
	}
    
    @RequestMapping(value = "/split-create",
			method = {RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Boolean createSplit(@RequestBody SplitDetails request,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return expenseService.createSplit(request, user);
		
	}
    
    @RequestMapping(value = "/delete-split/{expense_id}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public void deleteSplit(@PathVariable("expense_id") String expenseId,
							@RequestHeader("token") String token,
							HttpServletResponse response) throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		expenseService.deleteSplit(expenseId, true);
	}
    
    @RequestMapping(value = "/insert",
					method = {RequestMethod.POST},
					produces = {MediaType.APPLICATION_JSON_VALUE})
	public Boolean insertExpense(@RequestBody Expense expense,
								@RequestHeader("token") String token,
								HttpServletResponse response) throws IOException {
    	Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		expense.setUpdater(user);
		
		return expenseService.insertExpense(expense);
	}
    
    @RequestMapping(value = "/delete/{expense_id}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public void insertExpense(@PathVariable("expense_id") String expenseId,
							@RequestHeader("token") String token,
							HttpServletResponse response) throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		expenseService.deleteExpense(expenseId);
	}
    
    @RequestMapping(value = "/get",
			method = {RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ExpenseResponse get(@RequestBody ExpenseRequest filter,
							@RequestHeader("token") String token,
							HttpServletResponse response) throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return expenseService.getAllExpenses(filter);
	}
	
}
