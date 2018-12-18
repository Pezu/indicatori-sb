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
	
    
    @RequestMapping(value = "/read/summary/{month}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<SummaryLine> readSummary(@PathVariable("month") String month,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return rapportsService.readSummary(month);
		
	}
   
    @RequestMapping(value = "/export/summary/{month}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public void export(@PathVariable("month") String month,
									HttpServletResponse response) 
											throws IOException {
		XSSFWorkbook wb = rapportsService.workbookSummary(month);
    	
    	response.setHeader("Content-disposition", "attachment; filename=centralizator.xlsx");
    	wb.write(response.getOutputStream()); 
		
	}

    @RequestMapping(value = "/read/fixed-balance/{month}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<SummaryLine> readFixedBalance(@PathVariable("month") String month,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return rapportsService.readSummary(month);
		
	}
   
    @RequestMapping(value = "/read/fixed-stock/{month}",
			method = {RequestMethod.GET},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<SummaryLine> readFixedStock(@PathVariable("month") String month,
									HttpServletResponse response, 
									@RequestHeader("token") String token) 
											throws IOException {
		Integer user = loginService.verifyToken(token);
		if (user == null) {
			response.sendError(1001, "Token invalid");
		}
		
		return rapportsService.readSummary(month);
		
	}
   

}
