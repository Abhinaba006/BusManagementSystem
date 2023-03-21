package nrifintech.busMangementSystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrifintech.busMangementSystem.Service.interfaces.DestinationService;
import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.payloads.ApiResponse;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/v1/")
public class DestinationController {
    @Autowired
    DestinationService destinationService;
    
    //get
    @CrossOrigin(origins = "http://localhost:5500")
    @GetMapping("/destination/get")
    public ResponseEntity<List<Destination>> getAllDestination(){
        return ResponseEntity.ok(this.destinationService.getDestination());
    }
    
    @CrossOrigin(origins = "http://localhost:5500")
    @GetMapping("/destination/get/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable("id") int destinationId){
        return ResponseEntity.ok(this.destinationService.getDestination(destinationId));
    }
    
    //post
    @PostMapping("/destination/create")
    public ResponseEntity<Destination> createDestination(@Valid @RequestBody Destination destination){
        Destination createdDestination = destinationService.createDestination(destination);
        return new ResponseEntity<>(createdDestination, HttpStatus.CREATED);
    }
    
    //update
    @CrossOrigin(origins = "http://localhost:5500")
    @PostMapping("/destination/update/{destinationId}")
    public ResponseEntity<Destination> updateDestination(@Valid @RequestBody Destination destination, @PathVariable("destinationId") int destinationId){
        Destination updatedDestination = destinationService.updateDestination(destination, destinationId);
        return ResponseEntity.ok(updatedDestination);
    }
    
    //delete
    @DeleteMapping("/destination/delete/{destinationId}")
    public ResponseEntity<?> deleteDestination(@PathVariable("destinationId") int destinationId){
        destinationService.deleteDestination(destinationId);
        ApiResponse response = new ApiResponse("Destination deleted successfully!", true);
		 return new ResponseEntity<>(response, HttpStatus.OK);
        //return new ResponseEntity(new ApiResponse("destination deleted", true), HttpStatus.OK);
    }

    
    //custom
    @GetMapping("/destination/getbyname/{name}")
    public ResponseEntity<?> getDestinationByName(@PathVariable("name") String name){
    	System.out.println(name);
    	return ResponseEntity.ok( destinationService.getDestinationByName(name));
    }

}
