package nrifintech.busMangementSystem.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.DestinationService;
import nrifintech.busMangementSystem.Service.interfaces.IssueService;
import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.entities.Issue;
import nrifintech.busMangementSystem.exception.ResouceNotFound;

import nrifintech.busMangementSystem.exception.UnauthorizedAction;


import nrifintech.busMangementSystem.repositories.DestinationRepo;
import nrifintech.busMangementSystem.repositories.IssueRepo;


@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    IssueRepo issueRepo;

    @Override
    public List<Issue> getAllunResolvedIssue() {
       return issueRepo.getAllunResolvedIssue();
    }

    @Override
    public List<Issue> getIssuesByUserId(int userId) {
        return issueRepo.getIssuesByUserId(userId);
    }

    @Override
    public Issue addIssue(Issue issue){
        return issueRepo.save(issue);
    }

    @Override
    public Issue resolveIssue(int issueId){
        Optional<Issue> _issue = issueRepo.findById(issueId);
        Issue issue = _issue.get();

        issue.setIsResolved(1);
        return issueRepo.save(issue);
    }

	

}
