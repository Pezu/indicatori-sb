package com.xiia.indicatori.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiia.indicatori.domain.Fixed;
import com.xiia.indicatori.service.FixedService;
import com.xiia.indicatori.service.LoginService;

@RestController
@RequestMapping("/fixed")
public class FixedController {

    private final LoginService loginService;
    private final FixedService fixedService;
    
    @Autowired
    public FixedController(LoginService loginService, FixedService fixedService) {
        this.loginService = loginService;
        this.fixedService = fixedService;
    }  
	
    @RequestMapping(value = "/update",
			method = {RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Boolean updateFixed(@RequestBody Fixed fixed,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return fixedService.update(fixed);
	}
	
}
