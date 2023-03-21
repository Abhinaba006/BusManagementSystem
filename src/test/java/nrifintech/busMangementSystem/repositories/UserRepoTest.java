package nrifintech.busMangementSystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;
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
	}

	@Test
	public void testFindAll() {
		// adding one more data
		User user = new User();
		user.setEmail("ddd1234@gmail.com");
		user.setName("demouser2");
		user.setPassword("demo1234");
		user.setEmployeeId("NRIE02");
		user.setType(0);
		userRepo.save(user);
		List<User> users = userRepo.findAll();
		assertThat(users).hasSize(2);
	}

	@Test
	public void findbyId() {
		Optional<User> temp = userRepo.findByEmail("demo123@gmail.com", 0);
		Optional<User> optionalUser = userRepo.findById(temp.get().getId());
		assertThat(optionalUser).isPresent();
	}

	@Test
	public void findbyEmail() {
		Optional<User> result = userRepo.findByEmail("demo123@gmail.com", 0);
		assertThat(result.get().getEmail()).isEqualTo("demo123@gmail.com");
	}

	@Test
	public void findByOnlyEmail() {
		Optional<User> result = userRepo.findByOnlyEmail("demo123@gmail.com");
		assertThat(result.get().getEmail()).isEqualTo("demo123@gmail.com");
	}

	@BeforeEach
	public void setUp() {
		// initialize resources here
		// e.g., set up database connections, create test data, etc.
		System.out.println("setting up");
		User user = new User();
		user.setEmail("demo123@gmail.com");
		user.setName("demouser");
		user.setEmployeeId("NRIE01");
		user.setPassword("demo123");
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
