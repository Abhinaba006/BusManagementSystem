package nrifintech.busMangementSystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
