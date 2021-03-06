package com.xiia.indicatori.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiia.indicatori.pojo.LoginRequest;
import com.xiia.indicatori.pojo.LoginResponse;
import com.xiia.indicatori.service.LoginService;

@RestController
@RequestMapping("/user")
public class LoginController {

    private final LoginService loginService;
    
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }  
	
    @RequestMapping(value = "/login",
				method = {RequestMethod.POST},
				produces = {MediaType.APPLICATION_JSON_VALUE})
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletResponse response) throws IOException {
    	LoginResponse lr = loginService.login(request);
    	if (lr == null) {
    		response.sendError(1000, "Date de conectare eronate");
    	}
        return lr;
    }
    
}
