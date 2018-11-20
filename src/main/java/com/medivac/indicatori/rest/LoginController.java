package com.medivac.indicatori.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medivac.indicatori.pojo.LoginRequest;
import com.medivac.indicatori.pojo.LoginResponse;
import com.medivac.indicatori.service.LoginService;

@RestController
@RequestMapping("/user")
public class LoginController {

	// folosim catalog pentru "nomenclator" cred ca asa ii zici tu. Ala de iti definesti unitati de masura, tipuri de chestii, etc
	
    private final LoginService loginService;
    
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }  
	
    @RequestMapping(value = "/login",
				method = {RequestMethod.POST},
				produces = {MediaType.APPLICATION_JSON_VALUE})
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }
    
}
