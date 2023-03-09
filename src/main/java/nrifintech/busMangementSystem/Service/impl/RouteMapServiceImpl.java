package nrifintech.busMangementSystem.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import nrifintech.busMangementSystem.Service.interfaces.RouteMapService;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.repositories.RouteMapRepo;

public class RouteMapServiceImpl implements RouteMapService {
	@Autowired
	private RouteMapRepo routeMapRepo;

	@Override
	public void addRouteMap(int route_id, int destination_id, int index, String time) {
	}



	

	
}
