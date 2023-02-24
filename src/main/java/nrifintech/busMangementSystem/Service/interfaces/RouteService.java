package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;

import nrifintech.busMangementSystem.entities.Route;

public interface RouteService {
	Route createRoute(Route route);
	Route updateRoute(Route route, int id);
	Route getRoute(int id);
	List<Route> getRoute();
	void deleteRoute(int id);
}
