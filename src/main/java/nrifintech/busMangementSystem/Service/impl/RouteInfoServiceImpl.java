package nrifintech.busMangementSystem.Service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.RouteInfoService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.BusRepo;
import nrifintech.busMangementSystem.repositories.RouteInfoRepo;

@Service
public class RouteInfoServiceImpl implements RouteInfoService {
	
	@Autowired
	RouteInfoRepo routeInfoRepo;
	
	@Autowired
	BusMapRepo busMapRepo;
	
	@Autowired
	BusRepo busRepo;



	@Override
	public void preCheck(int route_id) {
		LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        String formattedDate = date.format(formatter);
		RouteInfo routeInfo = routeInfoRepo.getRouteByPresentDate(route_id,formattedDate);
		if(routeInfo == null){
			System.out.println("Reaching here...");
			//Get the bus id for this route
			BusMap busMap = busMapRepo.findByRouteId(route_id);
			int busId = busMap.getBus_id();
			Optional<Bus> busObj = busRepo.findById(busId);
			Bus bus = busObj.get();
			routeInfo= new RouteInfo();
			routeInfo.setDate(formattedDate);
			routeInfo.setRoute_id(route_id);
			routeInfo.setTotal_seats(bus.getTotalNumberOfseats());
			routeInfo.setTotal_bookings(0);
			routeInfo.setOverall_bookings(0);
			routeInfoRepo.save(routeInfo);
		}
		else{
			//Do nothing
		}

	}
	public void changeTotalBooking(int route_id,int doit){
		preCheck(route_id);
		LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        String formattedDate = date.format(formatter);
		RouteInfo routeInfo = routeInfoRepo.getRouteByPresentDate(route_id,formattedDate);
		routeInfo.setTotal_bookings(routeInfo.getTotal_bookings() + doit);
		routeInfoRepo.save(routeInfo);
	}
	public void incrementOverallBooking(int route_id){
		preCheck(route_id);
		LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        String formattedDate = date.format(formatter);
		RouteInfo routeInfo = routeInfoRepo.getRouteByPresentDate(route_id,formattedDate);
		routeInfo.setOverall_bookings(routeInfo.getOverall_bookings() + 1);
		routeInfoRepo.save(routeInfo);
	}
	public RouteInfo getRouteInfo(int route_id,String date){
		preCheck(route_id);
		return routeInfoRepo.getRouteByPresentDate(route_id,date);
	}

}
