package nrifintech.busMangementSystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrifintech.busMangementSystem.Service.interfaces.RouteService;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.payloads.ApiResponse;

@RestController
@RequestMapping("/api/v1/route")
public class RouteController {
	@Autowired
	RouteService routeService;
	 
	//get
	@GetMapping("/get")
	public ResponseEntity<List<Route>> getAllroute(){
		return ResponseEntity.ok(this.routeService.getRoute());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Route> getRouteById(@PathVariable("id") int uid){
		return ResponseEntity.ok(this.routeService.getRoute(uid));
	}
	//post
	
	@PostMapping("/create")
	ResponseEntity<Route> createRoute(List<String> destinations){
		Route createdRoute = routeService.createRoute(destinations);
		return new ResponseEntity<>(createdRoute, HttpStatus.CREATED);
	}
//	{
//		name:
//			id
//	}
	//update
	@PostMapping("/update/{routeId}")
	ResponseEntity<Route> createRoute(@RequestBody Route route, @PathVariable("routeId") int routeId){
		Route updatedRoute = routeService.updateRoute(route, routeId);
		return ResponseEntity.ok(updatedRoute);
	}
	//delete
	@DeleteMapping("/delete/{routeId}")
	public ResponseEntity<?> deleteRoute(@PathVariable("routeId") int routeId){
		routeService.deleteRoute(routeId);
		return new ResponseEntity(new ApiResponse("route deleted", true), HttpStatus.OK);
	}
}
