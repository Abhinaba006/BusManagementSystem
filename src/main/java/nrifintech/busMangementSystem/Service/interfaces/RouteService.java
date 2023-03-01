package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.entities.Route;
@Service
public interface RouteService {
	Route updateRoute(List<String> destinations, int id,int bus_id);
	Route getRoute(int id);
	List<Route> getRoute();
	void deleteRoute(int id);
	Route createRoute(List<String> destinations,int bus_id);
	List<Route> getRoutesBySourceAndDestination(int source, int destination);
	List<Destination> getRouteDestinations(int routeId);
	
}
