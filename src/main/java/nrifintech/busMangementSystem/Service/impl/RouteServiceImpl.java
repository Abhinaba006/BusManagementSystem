package nrifintech.busMangementSystem.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.RouteRepo;
import nrifintech.busMangementSystem.Service.interfaces.*;
@Service
public class RouteServiceImpl implements RouteService{

	@Autowired
	private RouteRepo routeRepo;
	
	@Override
	public Route createRoute(Route route) {
		// TODO Auto-generated method stub
		return routeRepo.save(route);
	}

	@Override
	public Route updateRoute(Route newRoute, int id) {
		// TODO Auto-generated method stub
		Route  route = routeRepo.findById(id).orElseThrow(() -> new ResouceNotFound("Route", "id", id));
		route.setId(newRoute.getId());
		route.setListOfDestinations(newRoute.getListOfDestinations());
		
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
