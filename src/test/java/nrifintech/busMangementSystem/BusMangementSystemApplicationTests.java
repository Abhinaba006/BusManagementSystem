package nrifintech.busMangementSystem;

<<<<<<< HEAD
=======
import static org.assertj.core.api.Assertions.assertThat;

>>>>>>> 4674ab9e20101f282f987640f6f2f04f021b0c90
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BusMangementSystemApplicationTests {

<<<<<<< HEAD
	@Test
	void contextLoads() {
	}
=======
	private Calculator c = new Calculator();
	@Test
	void contextLoads() {
	}
	
	@Test
	void testSum()
	{
		int er = 7;
		int ar = c.doSum(2, 5);
		assertThat(ar).isEqualTo(er);
	}
>>>>>>> 4674ab9e20101f282f987640f6f2f04f021b0c90

}
