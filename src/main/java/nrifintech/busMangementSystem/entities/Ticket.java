package nrifintech.busMangementSystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	int routeId;
	int busId;
	int userId;
	String status;
	@NotEmpty
	String date;
}
