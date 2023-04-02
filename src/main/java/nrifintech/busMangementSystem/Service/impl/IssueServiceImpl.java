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
import nrifintech.busMangementSystem.payloads.IssueResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    IssueRepo issueRepo;
    
    @Autowired
    UserRepo userRepo;
    
    @Autowired
    MailService mailService;

    @Override
    public IssueResponse getAllunResolvedIssue(Integer pageNumber,Integer pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize);
        IssueResponse issueResponse=new IssueResponse();
        Page<Issue> pageissues = this.issueRepo.findAllunResolvedIssue(p);
        List<Issue> issues = pageissues.getContent();
            
        issueResponse.setContent(issues);
        issueResponse.setPageNumber(pageissues.getNumber());
        issueResponse.setPageSize(pageissues.getSize());
        issueResponse.setTotalElements(pageissues.getTotalElements());
        issueResponse.setTotalPages(pageissues.getTotalPages());
        issueResponse.setFirstpage(pageissues.isFirst());
        issueResponse.setLastPage(pageissues.isLast());
        return issueResponse;
    }
    
    @Override
    public IssueResponse getAllResolvedIssue(Integer pageNumber,Integer pageSize) {

        Pageable p = PageRequest.of(pageNumber, pageSize);
        IssueResponse issueResponse=new IssueResponse();
        Page<Issue> pageissues = this.issueRepo.findAllResolvedIssue(p);

        List<Issue> issues = pageissues.getContent();
            
        issueResponse.setContent(issues);
        issueResponse.setPageNumber(pageissues.getNumber());
        issueResponse.setPageSize(pageissues.getSize());
        issueResponse.setTotalElements(pageissues.getTotalElements());
        issueResponse.setTotalPages(pageissues.getTotalPages());
        issueResponse.setFirstpage(pageissues.isFirst());
        issueResponse.setLastPage(pageissues.isLast());
        return issueResponse;
    }

    @Override
    public IssueResponse getIssuesByUserId(int userId,Integer pageNumber,Integer pageSize) {

        Pageable p = PageRequest.of(pageNumber, pageSize);
        IssueResponse issueResponse=new IssueResponse();
        Page<Issue> pageissues = this.issueRepo.findIssuesByUserId(userId, p);
			List<Issue> issues = pageissues.getContent();
            
            issueResponse.setContent(issues);
            issueResponse.setPageNumber(pageissues.getNumber());
			issueResponse.setPageSize(pageissues.getSize());
			issueResponse.setTotalElements(pageissues.getTotalElements());
			issueResponse.setTotalPages(pageissues.getTotalPages());
			issueResponse.setFirstpage(pageissues.isFirst());
			issueResponse.setLastPage(pageissues.isLast());
        return issueResponse;
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
        issue.setResolvedDate(new Date());
        return issueRepo.save(issue);
    }

	@Override
	public IssueResponse getUserunResolvedIssue(String email,Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findByOnlyEmail(email);
        IssueResponse issueResponse=new IssueResponse();
		if(user.isPresent())
        {
            int userId =user.get().getId();
            Pageable p = PageRequest.of(pageNumber, pageSize);
            Page<Issue> pageissues = this.issueRepo.findUserunResolvedIssue(userId, p);
			List<Issue> issues = pageissues.getContent();
            
            issueResponse.setContent(issues);
            issueResponse.setPageNumber(pageissues.getNumber());
			issueResponse.setPageSize(pageissues.getSize());
			issueResponse.setTotalElements(pageissues.getTotalElements());
			issueResponse.setTotalPages(pageissues.getTotalPages());
			issueResponse.setFirstpage(pageissues.isFirst());
			issueResponse.setLastPage(pageissues.isLast());
        }
			
		else
			throw new CustomException("user does not exists");
		return issueResponse;
	}
	
	@Override
	public IssueResponse getUserResolvedIssue(String email,Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findByOnlyEmail(email);
		IssueResponse issueResponse=new IssueResponse();
		if(user.isPresent())
        {
            int userId =user.get().getId();
            Pageable p = PageRequest.of(pageNumber, pageSize);
            Page<Issue> pageissues = this.issueRepo.findUserResolvedIssue(userId, p);
            List<Issue> issues = pageissues.getContent();
            
            issueResponse.setContent(issues);
            issueResponse.setPageNumber(pageissues.getNumber());
			issueResponse.setPageSize(pageissues.getSize());
			issueResponse.setTotalElements(pageissues.getTotalElements());
			issueResponse.setTotalPages(pageissues.getTotalPages());
			issueResponse.setFirstpage(pageissues.isFirst());
			issueResponse.setLastPage(pageissues.isLast());
        }
			
		else
			throw new CustomException("user does not exists");
        return issueResponse;
	}

	

}
