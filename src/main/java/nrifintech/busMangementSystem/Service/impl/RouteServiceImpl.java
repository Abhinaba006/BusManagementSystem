package nrifintech.busMangementSystem.Service.impl;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nrifintech.busMangementSystem.Service.interfaces.BusMapService;
import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.BusMap;
import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.entities.RouteMap;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.BusMapRepo;
import nrifintech.busMangementSystem.repositories.BusRepo;
import nrifintech.busMangementSystem.repositories.RouteInfoRepo;
import nrifintech.busMangementSystem.repositories.RouteMapRepo;
import nrifintech.busMangementSystem.repositories.RouteRepo;
import nrifintech.busMangementSystem.repositories.TicketRepo;
@Service
public class RouteServiceImpl implements RouteService{

	@Autowired
	private RouteRepo routeRepo;


	@Autowired
	private RouteMapRepo routeMapRepo;
	
	
	@Autowired
	private DestinationServiceImpl destinationService;
	
	@Autowired
	private BusMapService busMapService;

	@Autowired
	private BusMapRepo busMapRepo;

	@Autowired
	private RouteInfoRepo routeInfoRepo;

	@Autowired
	private BusRepo busRepo;
	
	@Autowired
	private TicketRepo ticketRepo;
	
	@Override
	@Transactional
	public Route createRoute(List<String> destinations,int bus_id) {
		int start = 0,end = 0;
		for(int i = 0;i<destinations.size();i++) {
			String dest = destinations.get(i);
			String [] dest_id_time = dest.split("_");
			System.out.println(dest_id_time[1]);
			//System.out.println("Testing: "+dest_id_time[0]);
			if(i == 0) {
				start = Integer.parseInt(dest_id_time[0]);
			}
			else if(i == destinations.size()-1) {
				end = Integer.parseInt(dest_id_time[0]);
			}
		}
		Route r = new Route();
		r.setTotal_destinations(destinations.size());
		r.setStart_destination_id(start);
		r.setEnd_destination_id(end);
		
		Route createdRoute = routeRepo.save(r);
		for(int i = 0;i<destinations.size();i++) {
			String dest = destinations.get(i);
			String [] dest_id_time = dest.split("_");
			RouteMap rm = new RouteMap();
			rm.setRoute_id(createdRoute.getId());
			rm.setDestination_id(Integer.parseInt(dest_id_time[0]));
			rm.setDestination_index(i);
			rm.setTime(dest_id_time[2]);
			routeMapRepo.save(rm);
		}
		busMapService.addBusMap(createdRoute.getId(), bus_id);
		return createdRoute;
	}
	
	
	@Override
	@Transactional
	public Route updateRoute(List<String> destinations, int id,int bus_id) {
		Route  route = routeRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Route", "id", id));
		routeMapRepo.deleteFromRepoByRouteId(route.getId());
		
		//Update the route
		int start = 0,end = 0;
		for(int i = 0;i<destinations.size();i++) {
			System.out.println(destinations.get(i));
			String dest = destinations.get(i);
			String [] dest_id_time = dest.split("_");
			System.out.println(dest_id_time[1]);
			if(i == 0) {
				start = Integer.parseInt(dest_id_time[0]);
			}
			else if(i == destinations.size()-1) {
				end = Integer.parseInt(dest_id_time[0]);
			}
		}
		route.setTotal_destinations(destinations.size());
		route.setStart_destination_id(start);
		route.setEnd_destination_id(end);
		
		Route createdRoute = routeRepo.save(route);
		for(int i = 0;i<destinations.size();i++) {
			String dest = destinations.get(i);
			String [] dest_id_time = dest.split("_");
			RouteMap rm = new RouteMap();
			rm.setRoute_id(createdRoute.getId());
			rm.setDestination_id(Integer.parseInt(dest_id_time[0]));
			rm.setDestination_index(i);
			rm.setTime(dest_id_time[2]);
			routeMapRepo.save(rm);
		}
		busMapService.updateBusByRoute(id, bus_id);
		return createdRoute;
		
	}


	@Override
	public Route getRoute(int id) {
		// TODO Auto-generated method stub
		 return this.routeRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Route", "id", id));
	}

	@Override
	public List<Route> getRoute() {
		// TODO Auto-generated method stub
		return  this.routeRepo.findAll();
	}

	@Override
	@Transactional
	public void deleteRoute(int id) {
		// TODO Auto-generated method stub
		Route route = this.routeRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Route", "id", id));
		//delete from route_info entry
		routeInfoRepo.deleteRouteInfo(id);
		Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String currentDate = formatter.format(now);
		//delete upcoming tickets for this route too.
		ticketRepo.DeleteUpcomingTicketsByRouteId(id, currentDate);
		//delete from routeInfo entry.
		routeInfoRepo.deleteRouteInfo(id);
		//Delete from the routeMap entry
		routeMapRepo.deleteFromRepoByRouteId(id);
		//Delete from busMap entry
		busMapService.deleteByRouteId(id);
		
		//Delete the route
		routeRepo.delete(route);	
	}

	@Override
	public List<Route> getRoutesBySourceAndDestination(int source, int destination) {
		//return routeRepo.searchBySourceAndDestination(source, destination);
		//use route_map db
		//find all route_ids from route_map where source = destination_id.(returns R1, R2)
		List<Integer> routes1 = routeMapRepo.getByDestinationId(source);
		//find all route_ids from route_map where destination = destination_id(returns R2, R3)
		List<Integer> routes2 = routeMapRepo.getByDestinationId(destination);
		//find common of both (return R2)
		List<Integer> commonroutes = new ArrayList<>(routes1);
		commonroutes.retainAll(routes2);
		
//		System.out.println(com);
		System.out.println(commonroutes);
		//check if destination_index of source in R2 < destination_index of destination in R2
		List<Route> result = new ArrayList<>();
		
		if(commonroutes.size()>0)
		{
			//check for all route in commonroutes if it satisfies above given criteria, if yes put it in result.
			for(Integer route_id:commonroutes)
			{
				//find routeObject from routeMap where route_id = route_id and destination_id = source
				System.out.println();
				if(routeMapRepo.getByRouteIdAndDestinationId(route_id, source).get().getDestination_index()<routeMapRepo.getByRouteIdAndDestinationId(route_id, destination).get().getDestination_index())
					System.out.println("error genera rote id "+route_id);
					result.add(this.getRoute(route_id));		
			}
		}
	    return  result;	
	}
	
	@Override
	public List<Map<String, Object>> getRouteDestinations(int routeId) {
		List<RouteMap> mapEntries = routeMapRepo.getByRouteIdSortedByDestinationIndex(routeId);
		List<Map<String, Object>> response = new ArrayList<>();
		for (int i = 0; i < mapEntries.size(); i++) {
			int d_id = mapEntries.get(i).getDestination_id();
			String time = mapEntries.get(i).getTime();
			Destination d = destinationService.getDestination(d_id);
			Map<String, Object> destinationInfo = new HashMap<String, Object>();
			destinationInfo.put("destination", d);
			destinationInfo.put("time", time);
			response.add(destinationInfo);
		}
		return response;
	}


	@Override
	public List<RouteInfo> getRouteReport(int route_id) {
		return routeInfoRepo.getRouteData(route_id);
	}
	@Override
	public Bus getBusFromRouteId(int route_id){
		BusMap b = busMapRepo.findByRouteId(route_id);
		int bus_id = b.getBus_id();
		Optional<Bus> bus = busRepo.findById(bus_id);
		return bus.get();
	}

}


