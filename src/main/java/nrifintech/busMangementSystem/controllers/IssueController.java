// package nrifintech.busMangementSystem.controllers;


// import java.util.List;

// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import nrifintech.busMangementSystem.Service.interfaces.BusService;
// import nrifintech.busMangementSystem.Service.interfaces.IssueService;
// import nrifintech.busMangementSystem.Service.interfaces.RouteInfoService;
// import nrifintech.busMangementSystem.Service.interfaces.RouteService;
// import nrifintech.busMangementSystem.Service.interfaces.TicketService;
// import nrifintech.busMangementSystem.Service.interfaces.UserService;
// import nrifintech.busMangementSystem.entities.Bus;
// import nrifintech.busMangementSystem.entities.Issue;
// import nrifintech.busMangementSystem.entities.Ticket;
// import nrifintech.busMangementSystem.exception.ResouceNotFound;
// import nrifintech.busMangementSystem.payloads.ApiResponse;
// import nrifintech.busMangementSystem.payloads.TicketDto;
// import nrifintech.busMangementSystem.repositories.BusMapRepo;
// import nrifintech.busMangementSystem.repositories.IssueRepo;
// import nrifintech.busMangementSystem.repositories.TicketRepo;

// @CrossOrigin(origins = "http://localhost:5500")
// @RestController
// @RequestMapping("/api/v1/ticket")
// public class TicketController {
// 	@Autowired
// 	TicketService ticketService;

// 	@Autowired
// 	BusService busService;

// 	@Autowired
// 	RouteService routeService;

// 	@Autowired
// 	UserService userService;
	
// 	@Autowired
// 	RouteInfoService routeInfoService;

// 	@Autowired
// 	TicketRepo ticketRepo;
	
// 	@Autowired
// 	BusMapRepo busMapRepo;

//     @Autowired
//     IssueService issueService;

// 	@PostMapping("/create")
// 	ResponseEntity<?> createIssue(@Valid @RequestBody Issue issue){
// 		 issueService.addIssue(issue);
// 		 return new ResponseEntity(new ApiResponse("Issue created successfully!", true), HttpStatus.OK);
// 	}
// 	@PostMapping("/cancel")
// 	ResponseEntity<?> cancleTicket(@Valid @RequestBody int ticket_id){
// 		ticketService.cancelTicket(ticket_id);
// 		return new ResponseEntity(new ApiResponse("Ticket cancelled successfully!", true), HttpStatus.OK);
// 	}
// 	@GetMapping("/get/{userId}")
// 	ResponseEntity<?> getUserTickets(@PathVariable("userId") int userId){
// 		List<Ticket> tickets = ticketService.getAllTicketByPersonId(userId);
// 		return  ResponseEntity.ok(tickets);
// 	}
// }
