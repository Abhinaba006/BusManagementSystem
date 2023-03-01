package nrifintech.busMangementSystem.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.exception.ResouceNotFound;
import nrifintech.busMangementSystem.repositories.BusRepo;

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	private BusRepo busRepo;

	@Override
	public Bus createBus(Bus bus) {
		return busRepo.save(bus);
	}

	@Override
	public Bus updateBus(Bus updatedBus, int id) {
		Bus bus = busRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Bus", "id", id));
		if(updatedBus.getName()!=null) bus.setName(updatedBus.getName());
		if(updatedBus.getNumberOfSeats()>0) bus.setNumberOfSeats(updatedBus.getNumberOfSeats());
		return busRepo.save(bus);
	}

	@Override
	public Bus getBus(int id) {
		return busRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Bus", "id", id));
	}

	@Override
	public List<Bus> getBus() {
		return busRepo.findAll();
	}

	@Override
	public void deleteBus(int id) {
		Bus bus = busRepo.findById(id)
			.orElseThrow(() -> new ResouceNotFound("Bus", "id", id));
		busRepo.delete(bus);
	}


}
