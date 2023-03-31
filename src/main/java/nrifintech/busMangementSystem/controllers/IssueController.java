
package nrifintech.busMangementSystem.controllers;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrifintech.busMangementSystem.Service.interfaces.IssueService;
import nrifintech.busMangementSystem.entities.Issue;
import nrifintech.busMangementSystem.payloads.IssueResponse;

import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping("/unresolved")
    public ResponseEntity<IssueResponse> getAllunResolvedIssue(HttpServletRequest request,
    @RequestParam(value="pageNumber",defaultValue = "0",required=false) Integer pno,
	@RequestParam(value="pageSize",defaultValue = "3",required=false) Integer psize) { 
        IssueResponse issueResponse = issueService.getAllunResolvedIssue(pno,psize);
        return new ResponseEntity<>(issueResponse, HttpStatus.OK);
    }
    @GetMapping("/resolved")
    public ResponseEntity<IssueResponse> getAllResolvedIssue(HttpServletRequest request,
    @RequestParam(value="pageNumber",defaultValue = "0",required=false) Integer pno,
	@RequestParam(value="pageSize",defaultValue = "3",required=false) Integer psize) { 
        IssueResponse issueResponse = issueService.getAllResolvedIssue(pno,psize);
        return new ResponseEntity<>(issueResponse, HttpStatus.OK);
    }
    
    @GetMapping("{email}/unresolved")
    public ResponseEntity<IssueResponse> getUserunResolvedIssue(HttpServletRequest request,
    @PathVariable String email,
    @RequestParam(value="pageNumber",defaultValue = "0",required=false) Integer pno,
	@RequestParam(value="pageSize",defaultValue = "3",required=false) Integer psize) { 
        IssueResponse issueResponse = issueService.getUserunResolvedIssue(email,pno,psize);
        return new ResponseEntity<>(issueResponse, HttpStatus.OK);
    }
    
    @GetMapping("{email}/resolved")
    public ResponseEntity<IssueResponse> getUserResolvedIssue(HttpServletRequest request,
    @PathVariable String email,
    @RequestParam(value="pageNumber",defaultValue = "0",required=false) Integer pno,
	@RequestParam(value="pageSize",defaultValue = "3",required=false) Integer psize) { 
        IssueResponse issueResponse = issueService.getUserResolvedIssue(email,pno,psize);
        return new ResponseEntity<>(issueResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<IssueResponse> getIssuesByUserId(@PathVariable int userId,
    @RequestParam(value="pageNumber",defaultValue = "0",required=false) Integer pno,
	@RequestParam(value="pageSize",defaultValue = "3",required=false) Integer psize) {
        IssueResponse issueResponse = issueService.getIssuesByUserId(userId,pno,psize);
        return new ResponseEntity<>(issueResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Issue> addIssue(@RequestBody Issue issue){
        Issue createdIssue = issueService.addIssue(issue);
        return new ResponseEntity<>(createdIssue, HttpStatus.CREATED);
    }

    @PostMapping("/{issueId}/resolve")
    public ResponseEntity<Issue> resolveIssue(HttpServletRequest request, @PathVariable int issueId){
    	Enumeration<String> headerNames = request.getHeaderNames();
    	System.out.println("issoe resolve \n");
        while(headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }
        Issue resolvedIssue = issueService.resolveIssue(issueId);
        return new ResponseEntity<>(resolvedIssue,HttpStatus.CREATED);
    }
}

