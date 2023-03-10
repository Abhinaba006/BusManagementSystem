package nrifintech.busMangementSystem.Service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.entities.Ticket;
import nrifintech.busMangementSystem.entities.User;
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
	
	public void generateRouteReport(HttpServletResponse response) throws IOException {

	    // create a new workbook
	    Workbook workbook = new HSSFWorkbook();

	    // create a new sheet
	    HSSFSheet sheet =   (HSSFSheet) workbook.createSheet("Route Info");

	    // create header row and add headers
	    HSSFRow headerRow =  sheet.createRow(0);
	    headerRow.createCell(0).setCellValue("Route ID");
	    headerRow.createCell(1).setCellValue("Start Destination");
	    headerRow.createCell(2).setCellValue("End Destination");
	    headerRow.createCell(3).setCellValue("Total Destination");
	    headerRow.createCell(4).setCellValue("Total Bookings");
	    headerRow.createCell(5).setCellValue("Total Overall Bookings");
	    headerRow.createCell(6).setCellValue("Route-Usage(in %)");

	    int totalBookings;
    	int overallBookings;
    	float avgBooking;
    	float avgOverallBookings;
    	int rowNum = 1;
    	float routeUsage;
	    List<Route> routes = routeRepo.findAll();
	    for(Route route:routes)
	    {
	    	HSSFRow row = sheet.createRow(rowNum++);
	    	row.createCell(0).setCellValue(route.getId());
	    	//we need to create an entry for each route.
	    	//for each route, get data for all dates from routeInfo table.
	    	try {
		    	List<RouteInfo> routeData = routeInfoRepo.getRouteData(route.getId());
		    	//add all bookings and cancellations from this routeData
		    	totalBookings = 0;
		    	overallBookings = 0;
		    	for(RouteInfo r:routeData)
		    	{
		    		totalBookings+=r.getTotal_bookings();
		    		overallBookings+=r.getOverall_bookings();
		    	}
		    	avgBooking = totalBookings/routeData.size();
		    	avgOverallBookings = overallBookings/routeData.size();
		    	routeUsage = (avgBooking/routeData.get(0).getTotal_seats()) - (avgOverallBookings/avgBooking);
		   
		    	row.createCell(1).setCellValue((destinationRepo.findById(route.getStart_destination_id())).get().getName());
		    	row.createCell(2).setCellValue((destinationRepo.findById(route.getEnd_destination_id())).get().getName());
		    	row.createCell(3).setCellValue(route.getTotal_destinations());
		    	row.createCell(4).setCellValue(totalBookings);
		    	row.createCell(5).setCellValue(overallBookings);
		    	row.createCell(6).setCellValue(routeUsage*100);
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
	    Workbook workbook = new HSSFWorkbook();

	    // create a new sheet
	    HSSFSheet sheet =   (HSSFSheet) workbook.createSheet("User Info");

	    // create header row and add headers
	    HSSFRow headerRow =  sheet.createRow(0);
	    headerRow.createCell(0).setCellValue("Emp Id");
	    headerRow.createCell(1).setCellValue("Name ");
	    headerRow.createCell(2).setCellValue("Email");
	    headerRow.createCell(3).setCellValue("Availed Bookings");
	    headerRow.createCell(4).setCellValue("Total Cancellations");


	    int availedBookings;
    	int totalCancellations;
    	int rowNum = 1;

	    List<User> users = userRepo.findAll();
	    for(User user:users)
	    {
	    	HSSFRow row = sheet.createRow(rowNum++);
	    	row.createCell(0).setCellValue(user.getEmployeeId());
	    	row.createCell(1).setCellValue(user.getName());
	    	row.createCell(2).setCellValue(user.getEmail());
	    	//we need to create an entry for each user.
	    	//for each user, get data from Ticket table.
	    	try {
	    		List<Ticket> ticketData = ticketRepo.findByUserId(user.getId());
		    	//add all bookings and cancellations from this routeData
		    	availedBookings = 0;
		    	totalCancellations = 0;
		    	for(Ticket t:ticketData)
		    	{
		    		if(t.getStatus().equals("availed"))		
		    			availedBookings+=1;
		    		else if(t.getStatus().equals("cancelled"))
		    			totalCancellations+=1;
		    	}
		    	
		    	row.createCell(3).setCellValue(availedBookings);
		    	row.createCell(4).setCellValue(totalCancellations);
	    	}
	    	catch(Exception e)
	    	{
	    		row.createCell(3).setCellValue(0);
	    		row.createCell(4).setCellValue(0);	
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
