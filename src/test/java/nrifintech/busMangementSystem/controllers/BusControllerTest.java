package nrifintech.busMangementSystem.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.entities.Bus;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BusControllerTest {

	@Mock
	BusService busService;

	@InjectMocks
	BusController busController;

	private Bus testBus;
	private List<Bus> testBusList;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(busController).build();
	}

//    @Test
//    void testGetAllbus() {
//        // define the behavior of the mocked method
//        when(busService.getBus()).thenReturn(testBusList);
//
//        // call the method being tested
//        ResponseEntity<List<Bus>> responseEntity = busController.getAllbus();
//
//        // check the result
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(testBusList, responseEntity.getBody());
//    }
//
//    @Test
//    void testGetBusById() {
//        // define the behavior of the mocked method
//        when(busService.getBus(testBus.getId())).thenReturn(testBus);
//
//        // call the method being tested
//        ResponseEntity<Bus> responseEntity = busController.getBusById(testBus.getId());
//
//        // check the result
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(testBus, responseEntity.getBody());
//    }

	@Test
	void testCreateBus() throws Exception {
		// define the behavior of the mocked method
		testBus = new Bus();
		testBus.setBus_number("54as");
		testBus.setName("abas");
		testBus.setTotalNumberOfseats(10);
		when(busService.createBus(testBus)).thenReturn(testBus);

		mockMvc.perform(post("/create")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(testBus)))
	            .andExpect(status().isOk());
//	            .andExpect(jsonPath("$.name", is(testBus.getName())))
//	            .andExpect(jsonPath("$.bus_number", is(testBus.getBus_number())))
//	            .andExpect(jsonPath("$.totalNumberOfseats", is(testBus.getTotalNumberOfseats())));

	}


	private static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
