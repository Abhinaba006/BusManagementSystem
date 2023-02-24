package nrifintech.busMangementSystem.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Route {
	int id;
	List<Destination> listOfDestinations = new ArrayList<>();
}
