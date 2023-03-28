package nrifintech.busMangementSystem.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nrifintech.busMangementSystem.entities.Ticket;

@Getter
@Setter
@NoArgsConstructor
public class TicketResponse {
    private List<Ticket> content;
    private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean firstpage;
	private boolean lastPage;
}
