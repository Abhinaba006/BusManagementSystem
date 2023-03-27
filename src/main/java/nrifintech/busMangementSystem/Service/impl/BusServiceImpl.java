package nrifintech.busMangementSystem.Service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.exception.CustomException;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.BusRepo;
import nrifintech.busMangementSystem.repositories.RouteInfoRepo;
import nrifintech.busMangementSystem.repositories.TicketRepo;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusRepo busRepo;
	
	@Autowired
	private BusMapRepo busMapRepo;
	
	@Autowired
	private RouteInfoRepo routeInfoRepo;
	
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private TicketRepo ticketRepo;

	@Override
	public Bus createBus(Bus bus) {
		if(busRepo.findByBusNumber(bus.getBus_number()).isPresent())
			throw new CustomException("bus exists");
		return busRepo.save(bus);
	}

	@Override
	public Bus updateBus(Bus updatedBus, int id) {
//		throw new UnauthorizedAction("bus creation check, null", null);
		Bus bus = busRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Bus", "id", id));
		System.out.println("prev bus is "+ bus);
		System.out.println("updated bus is "+ updatedBus);
		if(updatedBus.getBus_number()!=null) bus.setBus_number(updatedBus.getBus_number());
		if(updatedBus.getName()!=null) bus.setName(updatedBus.getName());
		if(updatedBus.getTotalNumberOfseats()>0) bus.setTotalNumberOfseats(updatedBus.getTotalNumberOfseats());
		
		//change the total_seats in routeInfo too for route mapped with this bus, for all dates from today.
		//cause there are chances a user can book upcoming tickets too.
		Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String currentDate = formatter.format(now);
        
        BusMap busMapData = this.busMapRepo.findByBusId(id);
		List<RouteInfo> routeInfoData = this.routeInfoRepo.getAllUpcomingRouteInfo(busMapData.getRoute_id(), currentDate);
		for(RouteInfo r:routeInfoData)
		{
			r.setTotal_seats(updatedBus.getTotalNumberOfseats());
			routeInfoRepo.save(r);
		}

		System.out.println("updated bus is "+ bus);
		return busRepo.save(bus);
	}

	@Override
	public Bus getBus(int id) {
		return busRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Bus", "id", id));
	}

	@Override
	public List<Bus> getBus() {
		return busRepo.findAll();
	}

	@Override
	public void deleteBus(int id) {
		Bus bus = busRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Bus", "id", id));	
		try {
		int routeId = busMapRepo.findByBusId(id).getRoute_id();
		//delete corresponding bus data from route_info, bus_map, route, route_map
	    routeService.deleteRoute(routeId);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		busRepo.delete(bus);
	}

	@Override
	public List<Bus> getUnAllotedBus() {
		// TODO Auto-generated method stub
		return busRepo.findUnallotedBus();
	}
	


	


}
