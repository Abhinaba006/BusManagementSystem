package nrifintech.busMangementSystem.Service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.DestinationRepo;
import nrifintech.busMangementSystem.repositories.RouteRepo;
@Service
public class RouteServiceImpl implements RouteService{

	@Autowired
	private RouteRepo routeRepo;
	@Autowired
	private DestinationRepo destinationRepo;
	
	@Override
	public Route createRoute(Route route) {
	    Set<Destination> destinations = new HashSet<>();
	    for (Destination destination : route.getListOfDestinations()) {
	        Destination existingDestination = destinationRepo.findById(destination.getId())
	                .orElseThrow(() -> new ResouceNotFound("Destination", "id", destination.getId()));
	        destinations.add(existingDestination);
	    }
	    route.setListOfDestinations(destinations);
	    return routeRepo.save(route);
	}
	@Override
	public Route updateRoute(Route newRoute, int id) {
		// TODO Auto-generated method stub
		Route  route = routeRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Route", "id", id));
		route.setId(newRoute.getId());
		route.setDestinations(newRoute.getDestinations());
		
		return this.routeRepo.save(route);
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
	public void deleteRoute(int id) {
		// TODO Auto-generated method stub
		Route route = this.routeRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Route", "id", id));
		routeRepo.delete(route);	
	}

}
