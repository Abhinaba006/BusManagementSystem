package nrifintech.busMangementSystem.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
/*
 To be deleted
 */
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Data
public class Bus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@NotEmpty
	String name;
	@NotEmpty
	String bus_number;

	int totalNumberOfseats;
}
