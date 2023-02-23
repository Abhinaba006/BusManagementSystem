package nrifintech.busMangementSystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Ticket {
	int id;
	int Bus_id;
	int Route_id;
	int user_id;
}
