package nrifintech.busMangementSystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Destination {
	String name;
	int id;
	float lattitude;
	float longitude;
}
