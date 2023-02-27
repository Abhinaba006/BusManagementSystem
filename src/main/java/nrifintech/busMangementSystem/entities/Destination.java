package nrifintech.busMangementSystem.entities;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Destination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	float lattitude;
	float longitude;
	@ManyToMany
	Set <Route> routes = new TreeSet<>();
}
