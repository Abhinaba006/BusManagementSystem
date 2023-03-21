package nrifintech.busMangementSystem.Service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.BusRepo;
import nrifintech.busMangementSystem.repositories.DestinationRepo;
import nrifintech.busMangementSystem.repositories.RouteInfoRepo;
import nrifintech.busMangementSystem.repositories.RouteRepo;
import nrifintech.busMangementSystem.repositories.TicketRepo;
import nrifintech.busMangementSystem.repositories.UserRepo;

@Service
public class ReportService {

	@Autowired
	RouteInfoRepo routeInfoRepo;
	
	@Autowired
	TicketRepo ticketRepo;
	
	@Autowired
	RouteRepo routeRepo;
	
	@Autowired
	DestinationRepo destinationRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	BusMapRepo busMapRepo;
	
	@Autowired
	BusRepo busRepo;
	
	public void generateRouteReport(HttpServletResponse response) throws IOException {

	    // create a new workbook
	    Workbook workbook = new XSSFWorkbook();

	    // create a new sheet
	    XSSFSheet sheet =   (XSSFSheet) workbook.createSheet("Route Info");

	    // create header row and add headers
	    XSSFRow headerRow =  sheet.createRow(0);
	    headerRow.createCell(0).setCellValue("Route ID");
	    headerRow.createCell(1).setCellValue("Start Destination");
	    headerRow.createCell(2).setCellValue("End Destination");
	    headerRow.createCell(3).setCellValue("Total Destination");
	    headerRow.createCell(4).setCellValue("Total Seats");
	    headerRow.createCell(5).setCellValue("OVERUSED");
	    headerRow.createCell(6).setCellValue("UNDERUSED");

	    int totalBookings;
    	int totalSeats = 0;
    	int rowNum = 1;
    	String routeUsage;
	    List<Route> routes = routeRepo.findAll();
	    for(Route route:routes)
	    {
	    	XSSFRow row = sheet.createRow(rowNum++);
	    	row.createCell(0).setCellValue(route.getId());
	    	//we need to create an entry for each route.
	    	//for each route, get data for all dates from routeInfo table.
	    	try {
		    	List<RouteInfo> routeData = routeInfoRepo.getRouteData(route.getId());
		    	//add all bookings and cancellations from this routeData
		    	int overused = 0,underused = 0;

		    	for(RouteInfo r:routeData)
		    	{
		    			if(r.getOverall_bookings()>=r.getTotal_seats()) overused++;
		    			else underused++;
		    	}
		    	
		    	int bus_id = busMapRepo.findByRouteId(route.getId()).getBus_id();
		    	totalSeats = busRepo.findById(bus_id).get().getTotalNumberOfseats();
		    	
		    	
		    	row.createCell(1).setCellValue((destinationRepo.findById(route.getStart_destination_id())).get().getName());
		    	row.createCell(2).setCellValue((destinationRepo.findById(route.getEnd_destination_id())).get().getName());
		    	row.createCell(3).setCellValue(route.getTotal_destinations());
		    	row.createCell(4).setCellValue(totalSeats);
		
		    	row.createCell(5).setCellValue(overused);
		    	row.createCell(6).setCellValue(underused);
	    	}
	    	catch(Exception e)
	    	{
	    		row.createCell(1).setCellValue("");
	    		row.createCell(2).setCellValue("");
	    		row.createCell(3).setCellValue("");
	    		row.createCell(4).setCellValue("");
	    		row.createCell(5).setCellValue("");
	    		row.createCell(6).setCellValue("");
	    		
	    	}
	    }
	    
	    ServletOutputStream outputStream = response.getOutputStream();
	    workbook.write(outputStream);
	    workbook.close();
	    outputStream.close();
	}
	
	public void generateUserReport(HttpServletResponse response) throws IOException {

	    // create a new workbook
	    Workbook workbook = new XSSFWorkbook();

	    // create a new sheet
	    XSSFSheet sheet =   (XSSFSheet) workbook.createSheet("User Info");

	    // create header row and add headers
	    XSSFRow headerRow =  sheet.createRow(0);
	    headerRow.createCell(0).setCellValue("Emp Id");
	    headerRow.createCell(1).setCellValue("Name ");
	    headerRow.createCell(2).setCellValue("Email");
	    headerRow.createCell(3).setCellValue("Total Bookings");
	

	    int availedBookings;
    	int rowNum = 1;

	    List<User> users = userRepo.findAll();
	    for(User user:users)
	    {
	    	XSSFRow row = sheet.createRow(rowNum++);
	    	row.createCell(0).setCellValue(user.getEmployeeId());
	    	row.createCell(1).setCellValue(user.getName());
	    	row.createCell(2).setCellValue(user.getEmail());
	   
	    	try {
	    		List<Ticket> ticketData = ticketRepo.findByUserId(user.getId());
		    	//add all bookings and cancellations from this routeData
		    	availedBookings = 0;
		    	for(Ticket t:ticketData)
		    	{
		    		if(t.getStatus().equals("AVAILED"))		
		    			availedBookings+=1;
		    	
		    	}
		    	
		    	row.createCell(3).setCellValue(availedBookings);
	    	}
	    	catch(Exception e)
	    	{
	    		row.createCell(3).setCellValue(0);	
	    	}
	    }

	    // write workbook data to response output stream
	    ServletOutputStream outputStream = response.getOutputStream();
	    workbook.write(outputStream);
	    workbook.close();
//	    outputStream.flush();
	    outputStream.close();
	}

}
