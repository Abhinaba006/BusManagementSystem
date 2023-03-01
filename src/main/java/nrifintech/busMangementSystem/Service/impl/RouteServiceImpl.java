package nrifintech.busMangementSystem.Service.impl;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import java.util.HashSet;
import java.util.List;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;



import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.entities.Route;

import nrifintech.busMangementSystem.entities.RouteMap;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.payloads.ApiResponse;
import nrifintech.busMangementSystem.repositories.DestinationRepo;
import nrifintech.busMangementSystem.repositories.RouteMapRepo;

import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.DestinationRepo;

import nrifintech.busMangementSystem.repositories.RouteRepo;
@Service
public class RouteServiceImpl implements RouteService{

	@Autowired
	private RouteRepo routeRepo;


	@Autowired
	private RouteMapRepo routeMapRepo;
	
	@Autowired
	private DestinationServiceImpl destinationService;
	@Override
	@Transactional
	public Route createRoute(List<String> destinations) {
		int start = 0,end = 0;
		for(int i = 0;i<destinations.size();i++) {
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
		return createdRoute;
	}
	@Override
	@Transactional
	public Route updateRoute(List<String> destinations, int id) {
//		// TODO Auto-generated method stub
//		Route  route = routeRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Route", "id", id));
//		route.setId(newRoute.getId());
////		route.setDestinations(newRoute.getDestinations());
//		
//		return this.routeRepo.save(route);
		Route  route = routeRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Route", "id", id));
	
		
		//Delete all entries from routeMap
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
		return createdRoute;
	}
		
	@Autowired
	private DestinationRepo destinationRepo;
	
//	@Override
//	public Route createRoute(Route route) {
//	    Set<Destination> destinations = new HashSet<>();
//	    for (Destination destination : route.getDestinations()) {
//	        Destination existingDestination = destinationRepo.findById(destination.getId())
//	                .orElseThrow(() -> new ResouceNotFound("Destination", "id", destination.getId()));
//	        destinations.add(existingDestination);
//	    }
//	    route.setDestinations(destinations);
//	    return routeRepo.save(route);
//	}
//	@Override
//	public Route updateRoute(Route newRoute, int id) {
//		// TODO Auto-generated method stub
//		Route  route = routeRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Route", "id", id));
//		route.setDestinations(newRoute.getDestinations());
//		
//		return this.routeRepo.save(route);
//>>>>>>> 4674ab9e20101f282f987640f6f2f04f021b0c90
//	}

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
		
		//Delete from the routeMap entry
		routeMapRepo.deleteFromRepoByRouteId(id);
		
		
		routeRepo.delete(route);	
	}

	@Override
	public List<Route> getRoutesBySourceAndDestination(int source, int destination) {
		return routeRepo.searchBySourceAndDestination(source, destination);
	}
	
	@Override
	public List<Destination> getRouteDestinations(int routeId){
		List<RouteMap> mapEntries = routeMapRepo.getByRouteIdSortedByDestinationIndex(routeId);
		List<Destination> response = new ArrayList<Destination>();
		for(int i = 0;i<mapEntries.size();i++) {
			int d_id = mapEntries.get(i).getDestination_id();
			Destination d = destinationService.getDestination(d_id);
			response.add(d);
		}
		return response;
	}
}


