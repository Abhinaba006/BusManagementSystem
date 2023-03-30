package nrifintech.busMangementSystem.Service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.IssueService;
import nrifintech.busMangementSystem.entities.Issue;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.exception.CustomException;
import nrifintech.busMangementSystem.repositories.IssueRepo;
import nrifintech.busMangementSystem.repositories.UserRepo;


@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    IssueRepo issueRepo;
    
    @Autowired
    UserRepo userRepo;
    
    @Autowired
    MailService mailService;

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
    	issue.setDate(new Date());
        return issueRepo.save(issue);
    }

    @Override
    public Issue resolveIssue(int issueId){
        Optional<Issue> _issue = issueRepo.findById(issueId);
        Issue issue = _issue.get();
        issue.setIsResolved(1);
        return issueRepo.save(issue);
    }

	@Override
	public List<Issue> getUserunResolvedIssue(String email) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findByOnlyEmail(email);
		List<Issue> issue = new ArrayList<Issue>();
		if(user.isPresent())
			issue =  issueRepo.getUserunResolvedIssue(user.get().getId());
		else
			throw new CustomException("user does not exists");
		return issue;
	}

	

}
