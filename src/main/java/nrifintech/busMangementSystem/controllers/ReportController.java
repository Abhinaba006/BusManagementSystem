package nrifintech.busMangementSystem.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrifintech.busMangementSystem.Service.impl.ReportService;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/route")
	public void generateRouteReport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=Route-Report.xls";
		response.setHeader(headerKey, headerValue);
		reportService.generateRouteReport(response);
	}
	
	@GetMapping("/user")
	public void generateUserReport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=User-Usage-Report.xls";
		response.setHeader(headerKey, headerValue);
		reportService.generateUserReport(response);
	}

}
