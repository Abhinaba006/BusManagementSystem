package nrifintech.busMangementSystem.controllers;

import java.util.List;

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
    public ResponseEntity<List<Issue>> getAllunResolvedIssue() {
        List<Issue> issues = issueService.getAllunResolvedIssue();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Issue>> getIssuesByUserId(@PathVariable int userId) {
        List<Issue> issues = issueService.getIssuesByUserId(userId);
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> addIssue(@RequestBody Issue issue){
        issueService.addIssue(issue);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{issueId}/resolve")
    public ResponseEntity<Void> resolveIssue(@PathVariable int issueId){
        issueService.resolveIssue(issueId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
