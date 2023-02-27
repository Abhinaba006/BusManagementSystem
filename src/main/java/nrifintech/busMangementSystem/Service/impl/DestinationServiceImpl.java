package nrifintech.busMangementSystem.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.DestinationService;
import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.DestinationRepo;

@Service
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	private DestinationRepo destinationRepo;

	@Override
	public Destination createDestination(Destination destination) {
		return destinationRepo.save(destination);
	}

	@Override
	public Destination updateDestination(Destination updatedDestination, int id) {
		Destination destination = destinationRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Destination", "id", id));
		if(updatedDestination.getName()!=null) destination.setName(updatedDestination.getName());
		if(updatedDestination.getLatitude()!=0.0) destination.setLatitude(updatedDestination.getLatitude());
		if(updatedDestination.getLongitude()!=0.0) destination.setLongitude(updatedDestination.getLongitude());
		return destinationRepo.save(destination);
	}

	@Override
	public Destination getDestination(int id) {
		return destinationRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Destination", "id", id));
	}

	@Override
	public List<Destination> getDestination() {
		return destinationRepo.findAll();
	}

	@Override
	public void deleteDestination(int id) {
		Destination destination = destinationRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Destination", "id", id));
		destinationRepo.delete(destination);	
	}
	

}
