package com.medivac.indicatori.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medivac.indicatori.domain.Categorie;
import com.medivac.indicatori.domain.Unit;
import com.medivac.indicatori.service.CatalogService;
import com.medivac.indicatori.service.LoginService;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;
    private final LoginService loginService;
    
    @Autowired
    public CatalogController(CatalogService catalogService, LoginService loginService) {
        this.catalogService = catalogService;
        this.loginService = loginService;
    }  
	
    @GetMapping("/categories")
    public List<Categorie> getCategories(HttpServletResponse response, @RequestHeader("token") String token) throws IOException {
    	Boolean allowed = loginService.verifyToken(token);
    	if (!allowed) {
    		response.sendError(1001, "Token invalid");
    	}
        return catalogService.getCategories();
    }
    
    @GetMapping("/units")
    public List<Unit> getUnits(HttpServletResponse response, @RequestHeader("token") String token) throws IOException {
    	Boolean allowed = loginService.verifyToken(token);
    	if (!allowed) {
    		response.sendError(1001, "Token invalid");
    	}

        return catalogService.getUnits();
    }
    
}
