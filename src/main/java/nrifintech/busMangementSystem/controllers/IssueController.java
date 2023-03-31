
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

@CrossOrigin(origins = "http://localhost:5500")
@RestController
@RequestMapping("/api/v1/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping("/unresolved")
    public ResponseEntity<List<Issue>> getAllunResolvedIssue(HttpServletRequest request) { 
        List<Issue> issues = issueService.getAllunResolvedIssue();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }
    @GetMapping("/resolved")
    public ResponseEntity<List<Issue>> getAllResolvedIssue(HttpServletRequest request) { 
        List<Issue> issues = issueService.getAllResolvedIssue();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }
    
    @GetMapping("{email}/unresolved")
    public ResponseEntity<List<Issue>> getUserunResolvedIssue(HttpServletRequest request,@PathVariable String email) { 
        List<Issue> issues = issueService.getUserunResolvedIssue(email);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }
    
    @GetMapping("{email}/resolved")
    public ResponseEntity<List<Issue>> getUserResolvedIssue(HttpServletRequest request,@PathVariable String email) { 
        List<Issue> issues = issueService.getUserResolvedIssue(email);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Issue>> getIssuesByUserId(@PathVariable int userId) {
        List<Issue> issues = issueService.getIssuesByUserId(userId);
        return new ResponseEntity<>(issues, HttpStatus.OK);
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

