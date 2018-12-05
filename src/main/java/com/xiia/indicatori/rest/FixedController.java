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

import com.xiia.indicatori.pojo.FixedCreateRequest;
import com.xiia.indicatori.pojo.FixedMoveRequest;
import com.xiia.indicatori.pojo.FixedRequest;
import com.xiia.indicatori.pojo.FixedResponse;
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
	
    @RequestMapping(value = "/create",
			method = {RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Boolean updateFixed(@RequestBody FixedCreateRequest request,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return fixedService.create(request, user);
	}
    
    @RequestMapping(value = "/get",
			method = {RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public FixedResponse get(@RequestBody FixedRequest request,
							@RequestHeader("token") String token,
							HttpServletResponse response) throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return fixedService.getFixed(request);
	}

    @RequestMapping(value = "/move",
			method = {RequestMethod.POST},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Boolean move(@RequestBody FixedMoveRequest request,
							@RequestHeader("token") String token,
							HttpServletResponse response) throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return fixedService.move(request, user);
	}

}
