package nrifintech.busMangementSystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import nrifintech.busMangementSystem.entities.User;

//@ExtendWith(SpringExtension.class)
//@Transactional
@SpringBootTest
class UserRepoTest {

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	@Test
	public void testFindAll() {
		// adding one more data
		User user = new User();
		user.setEmail("abhinabad@trainee.nrifintech.com");
		user.setName("Abhinaba Das");
		user.setPassword("abhi123");
		user.setEmployeeId("NRIE02");
		user.setType(0);
		userRepo.save(user);
		List<User> users = userRepo.findAll();
		assertThat(users).hasSize(2);
	}

	@Test
	public void findbyId() {
		Optional<User> temp = userRepo.findByEmail("keshabhk@trainee.nrifintech.com", 0);
		Optional<User> optionalUser = userRepo.findById(temp.get().getId());
		assertThat(optionalUser).isPresent();
	}

	@Test
	public void findbyEmail() {
		Optional<User> result = userRepo.findByEmail("keshabhk@trainee.nrifintech.com", 0);
		assertThat(result.get().getEmail()).isEqualTo("keshabhk@trainee.nrifintech.com");
	}

	@Test
	public void findByOnlyEmail() {
		Optional<User> result = userRepo.findByOnlyEmail("keshabhk@trainee.nrifintech.com");
		assertThat(result.get().getEmail()).isEqualTo("keshabhk@trainee.nrifintech.com");
	}

	@BeforeEach
	public void setUp() {
		// initialize resources here
		// e.g., set up database connections, create test data, etc.
		System.out.println("setting up");
		User user = new User();
		user.setEmail("keshabhk@trainee.nrifintech.com");
		user.setName("Keshabh Kedia");
		user.setEmployeeId("NRIE01");
		user.setPassword("keshabhk123");
		user.setType(0);
		userRepo.save(user);
	}

	@AfterEach
	public void tearDown() {
		// clean up resources here
		// e.g., delete test data, close database connections, etc.
		System.out.println("Tearing Down");
		this.userRepo.deleteAll();
	}

}
