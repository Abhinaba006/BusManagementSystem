package nrifintech.busMangementSystem.Service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.entities.Issue;
import nrifintech.busMangementSystem.entities.Route;
import nrifintech.busMangementSystem.entities.RouteInfo;
import nrifintech.busMangementSystem.payloads.IssueResponse;

@Service
public interface IssueService {
	
    Issue addIssue(Issue issue);
    Issue resolveIssue(int issueId);

	// List<Issue> getAllResolvedIssue();
	// List<Issue> getAllunResolvedIssue();
    // List<Issue> getIssuesByUserId(int userId);

	// List<Issue> getUserunResolvedIssue(String email);
	// List<Issue> getUserResolvedIssue(String email);

	//After Pagination
	IssueResponse getAllResolvedIssue(Integer pageNumber,Integer pageSize);
	IssueResponse getAllunResolvedIssue(Integer pageNumber,Integer pageSize);
    IssueResponse getIssuesByUserId(int userId,Integer is_resolved, Integer pageNumber,Integer pageSize);

	IssueResponse getUserunResolvedIssue(String email,Integer pageNumber,Integer pageSize);
	IssueResponse getUserResolvedIssue(String email,Integer pageNumber,Integer pageSize);

}
