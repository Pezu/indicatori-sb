package com.xiia.indicatori.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiia.indicatori.domain.Monthly;
import com.xiia.indicatori.domain.Unit;
import com.xiia.indicatori.pojo.MonthlyUpdate;
import com.xiia.indicatori.service.LoginService;
import com.xiia.indicatori.service.MonthlyService;

@RestController
@RequestMapping("/monthly")
public class MonthlyController {

    private final MonthlyService monthlyService;
    private final LoginService loginService;
    
    @Autowired
    public MonthlyController(MonthlyService monthlyService, LoginService loginService) {
        this.monthlyService = monthlyService;
        this.loginService = loginService;
    }  
	
    @RequestMapping(value = "/allowed/{type_id}",
						method = {RequestMethod.GET},
						produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Unit> getMonthlyAllowedUnits(@PathVariable("type_id") Integer typeId, 
    										HttpServletResponse response, 
    										@RequestHeader("token") String token) 
    												throws IOException {
    	Integer user = loginService.verifyToken(token);
    	if (user == null) {
    		response.sendError(1001, "Token invalid");
    	}
        return monthlyService.getMonthlyAllowedUnits(typeId);
    }
    
    @RequestMapping(value = "/get/{month}/{type_id}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Monthly> getMonthlyValues(@PathVariable("month") String month,
									@PathVariable("type_id") Integer typeId, 
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		return monthlyService.getMonthlyByMonthAndTypeId(month, typeId);
	}
    
    @RequestMapping(value = "/update",
			method = {RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Boolean updateMonthlyValues(@RequestBody List<MonthlyUpdate> list,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return monthlyService.update(list);
	}
	
}
