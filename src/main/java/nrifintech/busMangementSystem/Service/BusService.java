package nrifintech.busMangementSystem.Service;

import java.util.List;

import nrifintech.busMangementSystem.entities.Bus;

public interface BusService {
	Bus createBus(Bus bus);
	Bus updateBus(Bus bus, int id);
	Bus getBus(int id);
	List<Bus> getBus();
	void deleteBus(int id);
}