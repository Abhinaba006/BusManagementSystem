package nrifintech.busMangementSystem.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nrifintech.busMangementSystem.entities.Issue;

@Getter
@Setter
@NoArgsConstructor
public class IssueResponse {

    List<Issue> content;
    private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean firstpage;
	private boolean lastPage;
    
}
