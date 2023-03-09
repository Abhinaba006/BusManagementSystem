package nrifintech.busMangementSystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import nrifintech.busMangementSystem.entities.Destination;


//@ExtendWith(SpringExtension.class)
//@Transactional
@SpringBootTest
class DestinationRepoTest {

	@Autowired
	private DestinationRepo destinationRepo;
	
	@Test
	void contextLoads() {
	}
	
	
	@Test
    public void testFindAll() {
		//adding one more data
		Destination destination = new Destination();
		  destination.setName("Kestopur");
		  destination.setLatitude(25.45f);
		  destination.setLongitude(36.56f);
	      destinationRepo.save(destination);
        List<Destination> destinations =  destinationRepo.findAll();
        assertThat(destinations).hasSize(2);
    }
	
	@Test
	public void findbyId() {
	  Destination temp = destinationRepo.checkIfExistsByName("Salt Lake");
      Optional<Destination> result = destinationRepo.findById(temp.getId());
      assertThat(result).isPresent();
	}
	
	
	
	@Test
	public void findbyName() {
      List<Destination> result = destinationRepo.findByName("Salt");
      assertThat(result.get(0).getName()).isEqualTo("Salt Lake");
	}
	
	@Test
	public void checkIfExistsByName() {
      Destination result = destinationRepo.checkIfExistsByName("Salt Lake");
      assertThat(result.getName()).isEqualTo("Salt Lake");
	}
	
	@BeforeEach
    public void setUp() {
        // initialize resources here
        // e.g., set up database connections, create test data, etc.
		System.out.println("setting up");
		Destination destination = new Destination();
		  destination.setName("Salt Lake");
		  destination.setLatitude(23.45f);
		  destination.setLongitude(34.56f);
	      destinationRepo.save(destination);
    }
    
    @AfterEach
    public void tearDown() {
        // clean up resources here
        // e.g., delete test data, close database connections, etc.
    	System.out.println("Tearing Down");
    	this.destinationRepo.deleteAll();
    }

}
