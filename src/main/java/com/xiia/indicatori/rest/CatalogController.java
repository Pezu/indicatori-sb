package com.xiia.indicatori.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xiia.indicatori.domain.Article;
import com.xiia.indicatori.domain.Category;
import com.xiia.indicatori.domain.Group;
import com.xiia.indicatori.domain.MonthlyType;
import com.xiia.indicatori.domain.Split;
import com.xiia.indicatori.domain.Unit;
import com.xiia.indicatori.pojo.AutocompleteEntry;
import com.xiia.indicatori.service.CatalogService;
import com.xiia.indicatori.service.LoginService;

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
    public List<Category> getCategories(HttpServletResponse response, @RequestHeader("token") String token) throws IOException {
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
    
    @GetMapping("/mounthly-type")
    public List<MonthlyType> getMonthlyType(HttpServletResponse response, @RequestHeader("token") String token) throws IOException {
    	Boolean allowed = loginService.verifyToken(token);
    	if (!allowed) {
    		response.sendError(1001, "Token invalid");
    	}

        return catalogService.getMonthlyType();
    }
    
    @GetMapping("/groups")
    public List<Group> getGroups(HttpServletResponse response, @RequestHeader("token") String token) throws IOException {
    	Boolean allowed = loginService.verifyToken(token);
    	if (!allowed) {
    		response.sendError(1001, "Token invalid");
    	}
        return catalogService.getGroups();
    }
    
    @GetMapping("/splits")
    public List<Split> getSplits(HttpServletResponse response, @RequestHeader("token") String token) throws IOException {
    	Boolean allowed = loginService.verifyToken(token);
    	if (!allowed) {
    		response.sendError(1001, "Token invalid");
    	}
        return catalogService.getSplits();
    }
    
    @RequestMapping(value = "/categories/{group_id}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Category> getCategoriesByGroupId(@PathVariable("group_id") Integer groupId, 
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Boolean allowed = loginService.verifyToken(token);
		if (!allowed) {
			response.sendError(1001, "Token invalid");
		}
		return catalogService.getCategoriesByGroupId(groupId);
	}
    
    @RequestMapping(value = "/articles/{category_id}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Article> getArticlesByCategoryId(@PathVariable("category_id") Integer categoryId, 
									HttpServletResponse response,
									@RequestHeader("token") String token) 
											throws IOException {
		Boolean allowed = loginService.verifyToken(token);
		if (!allowed) {
			response.sendError(1001, "Token invalid");
		}
		return catalogService.getArticlesByCategoryId(categoryId);
	}
    
    @RequestMapping(value = "/autocomplete",
 			method = {RequestMethod.GET},
 			produces = {MediaType.APPLICATION_JSON_VALUE})
 	public List<AutocompleteEntry> getArticlesForAutocomplete(HttpServletResponse response,
 																@RequestHeader("token") String token) 
 											throws IOException {
 		Boolean allowed = loginService.verifyToken(token);
 		if (!allowed) {
 			response.sendError(1001, "Token invalid");
 		}
 		return catalogService.getArticlesForAutocomplete();
 	}
    
    @RequestMapping(value = "/children/{parent_id}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Unit> getChildUnits(@PathVariable("parent_id") Integer parentId,
    								HttpServletResponse response, 
    								@RequestHeader("token") String token) throws IOException {
    	Boolean allowed = loginService.verifyToken(token);
    	if (!allowed) {
    		response.sendError(1001, "Token invalid");
    	}

        return catalogService.getChildUnits(parentId);
    }
    
}
