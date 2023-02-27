package nrifintech.busMangementSystem.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Route {
	@Id
	int id;
	@ManyToMany
	Set<Destination> listOfDestinations = new HashSet<>();
	@ManyToMany
	Set<Ticket> tickets = new HashSet<>();
	String name;
}
