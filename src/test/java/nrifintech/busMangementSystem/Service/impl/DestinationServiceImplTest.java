package nrifintech.busMangementSystem.Service.impl;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nrifintech.busMangementSystem.entities.Destination;
import nrifintech.busMangementSystem.entities.User;
import nrifintech.busMangementSystem.repositories.DestinationRepo;

@ExtendWith(MockitoExtension.class)
class DestinationServiceImplTest {

	@Mock
    private DestinationRepo destinationRepo;

	@InjectMocks
    private DestinationServiceImpl destinationService;
	
	Destination dest1,dest2,dest3;
	
    @BeforeEach
    void setup()
    {
    	System.out.println("Setting up");
    	dest1 = new Destination();
        dest1.setName("SDF");
        dest1.setLatitude(23.44f);
        dest1.setLongitude(34.45f);
        
        dest2 = new Destination();
        dest2.setName("Webel Tower");
        dest2.setLatitude(25.44f);
        dest2.setLongitude(33.45f);
        
        dest3 = new Destination();
        dest3.setName("Technopolis");
        dest3.setLatitude(29.44f);
        dest3.setLongitude(32.45f);
    }
    
	@Test
	void testCreateDestination() {
		when(destinationRepo.save(dest1)).thenReturn(dest1);
		Destination result = destinationService.createDestination(dest1);
		assertNotNull(result);
        assertNotNull(result.getId());
		assertEquals(dest1.getName(),result.getName());
	}

	@Test
	void testUpdateDestination() {
	  //update dest1 with dest2
		when(destinationRepo.findById(dest1.getId())).thenReturn(Optional.of(dest1));
		when(destinationRepo.save(dest1)).thenReturn(dest2);
		Destination result = destinationService.updateDestination(dest2, dest1.getId());
		assertEquals(dest2.getName(),result.getName());		
	}

	@Test
	void testGetDestinationInt() {
		when(destinationRepo.findById(dest3.getId())).thenReturn(Optional.of(dest3));
		Destination result = destinationService.getDestination(dest3.getId());
		assertEquals(dest3.getName(),result.getName());
	}

	@Test
	void testGetDestination() {
		//get all user
		List<Destination> dests= new ArrayList<Destination>();
		dests.add(dest1);
		dests.add(dest2);
		dests.add(dest3);
		when(destinationRepo.findAll()).thenReturn(dests);
		
		List<Destination> result = destinationService.getDestination();
		assertEquals(result.size(),dests.size());
	}

	@Test
	void testDeleteDestination() {
		when(destinationRepo.findById(dest1.getId())).thenReturn(Optional.of(dest1));		
		destinationService.deleteDestination(dest1.getId());
		verify(destinationRepo, times(1)).delete(dest1);
	}

	@Test
	void testGetDestinationByName() {
		List<Destination> dests = new ArrayList();
		dests.add(dest1);
		dests.add(dest2);
		when(destinationRepo.findByName(dest1.getName())).thenReturn(dests);		
		List<Destination> result = destinationService.getDestinationByName(dest1.getName());
		assertEquals(dests.size(),result.size());
	}

}
