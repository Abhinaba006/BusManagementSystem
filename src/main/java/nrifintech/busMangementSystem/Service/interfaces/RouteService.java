package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Route;
@Service
public interface RouteService {
	Route createRoute(Route route);
	Route updateRoute(Route route, int id);
	Route getRoute(int id);
	List<Route> getRoute();
	void deleteRoute(int id);
}
