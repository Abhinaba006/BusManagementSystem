package nrifintech.busMangementSystem.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import nrifintech.busMangementSystem.Service.interfaces.IssueService;
import nrifintech.busMangementSystem.payloads.IssueResponse;

@ExtendWith(MockitoExtension.class)
public class IssueControllerTest {

    @Mock
    private IssueService issueService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private IssueController issueController;

    @Test
    public void testGetAllUnresolvedIssue() {
        IssueResponse issueResponse = new IssueResponse();

        when(issueService.getAllunResolvedIssue(0, 1)).thenReturn(issueResponse);

        ResponseEntity<IssueResponse> responseEntity = issueController.getAllunResolvedIssue(request, 0, 1);

        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() == issueResponse;
    }
    
    @Test
    public void testGetAllResolvedIssue() {
        // Set up test data
        Integer pageNumber = 0;
        Integer pageSize = 1;
        IssueResponse mockIssueResponse = new IssueResponse();

        // Set up mock behavior
        when(issueService.getAllResolvedIssue(pageNumber, pageSize)).thenReturn(mockIssueResponse);

        // Call the method being tested
        ResponseEntity<IssueResponse> responseEntity = issueController.getAllResolvedIssue(request, pageNumber, pageSize);

        // Verify the result
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockIssueResponse, responseEntity.getBody());
    }
    
    
}
