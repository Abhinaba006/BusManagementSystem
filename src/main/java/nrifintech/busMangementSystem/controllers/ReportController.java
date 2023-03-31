package nrifintech.busMangementSystem.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrifintech.busMangementSystem.Service.impl.ReportService;

@CrossOrigin(origins = "http://localhost:5500")
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
		String headerValue = "attachment;filename=Route-Report.xlsx";
		response.setHeader(headerKey, headerValue);
		reportService.generateRouteReport(response);
	}
	
	@GetMapping("/user")
	public void generateUserReport(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=User-Usage-Report.xlsx";
		response.setHeader(headerKey, headerValue);
		reportService.generateUserReport(response);
	}
	
	@GetMapping("/month-wise-user-usage")
	public ResponseEntity<List<Integer>> getMonthWiseNumberOfUsers() {
	    List<Integer> data= reportService.getMonthWiseNumberOfUsers();
	    return ResponseEntity.ok(data);
	}
	
	@GetMapping("/currentBookings-data")
	public ResponseEntity<List<Integer>> getTicketData() {
	    List<Integer> data= reportService.getTicketData();
	    return ResponseEntity.ok(data);
	}
	
	@GetMapping("/PastBookings-data")
	public ResponseEntity<List<Integer>> getBookingsData() {
	    List<Integer> data= reportService.getBookingsData();
	    return ResponseEntity.ok(data);
	}
	
	@GetMapping("/issues-data")
	public ResponseEntity<List<Integer>> getIssuesData() {
	    List<Integer> data= reportService.getIssuesData();
	    return ResponseEntity.ok(data);
	}

}
