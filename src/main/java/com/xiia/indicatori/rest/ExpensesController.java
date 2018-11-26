package com.xiia.indicatori.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiia.indicatori.domain.Percentage;
import com.xiia.indicatori.pojo.PercentageRequest;
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
	
    @RequestMapping(value = "/percentage",
			method = {RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Percentage> getPercentages(@RequestBody PercentageRequest request,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Boolean allowed = loginService.verifyToken(token);
		if (!allowed) {
			response.sendError(1001, "Token invalid");
		}
		
		return expenseService.getPercentages(request);
	}
	
}
