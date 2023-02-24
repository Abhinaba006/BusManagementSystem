package nrifintech.busMangementSystem.entities;

import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Ticket {
	@Id
	int id;
	int Bus_id;
	int Route_id;
	int user_id;
}
