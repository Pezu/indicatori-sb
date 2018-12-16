package com.xiia.indicatori.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xiia.indicatori.pojo.rapports.SummaryLine;
import com.xiia.indicatori.service.LoginService;
import com.xiia.indicatori.service.RapportsService;

@RestController
@RequestMapping("/rapports")
public class RapportsController {

    private final RapportsService rapportsService;
    private final LoginService loginService;
    
    @Autowired
    public RapportsController(RapportsService rapportsService, LoginService loginService) {
        this.rapportsService = rapportsService;
        this.loginService = loginService;
    }  
	
    
    @RequestMapping(value = "/read/{rapport}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<SummaryLine> read(@PathVariable("rapport") String rapport,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		String month = "201812";
		return rapportsService.read(rapport, month);
		
	}
   
    @RequestMapping(value = "/export/{rapport}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public String export(@PathVariable("rapport") String rapport,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return rapportsService.export(rapport);
		
	}
    
    
    @RequestMapping(value = "/test",
    		method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public void test(HttpServletResponse response) throws IOException {

    	String month = "201812";
		String rapport = "CTR";
    	
    	XSSFWorkbook wb = rapportsService.workbook(rapport, month);
    	
    	response.setHeader("Content-disposition", "attachment; filename=test.xlsx");
    	wb.write(response.getOutputStream()); 
		
	}
    
    
   
	
}
