package nrifintech.busMangementSystem.Service.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.RouteInfo;

@Service
public interface RouteInfoService {
	void preCheck(int route_id);
	void changeTotalBooking(int route_id,int doit);
	RouteInfo getRouteInfo(int route_id,String date);
	void incrementOverallBooking(int route_id);
}
