package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Issue;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.RouteInfo;

@Service
public interface IssueService {
	List<Issue> getAllunResolvedIssue();
    List<Issue> getIssuesByUserId(int userId);
    Issue addIssue(Issue issue);
    Issue resolveIssue(int issueId);
	List<Issue> getUserunResolvedIssue(String email);
}
