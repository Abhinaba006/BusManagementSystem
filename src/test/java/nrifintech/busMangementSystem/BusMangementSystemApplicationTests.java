package nrifintech.busMangementSystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BusMangementSystemApplicationTests {

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

}
