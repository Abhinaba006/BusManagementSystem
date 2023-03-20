//package nrifintech.busMangementSystem.controllers;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import nrifintech.busMangementSystem.Service.interfaces.BusService;
//import nrifintech.busMangementSystem.entities.Bus;
//
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//class BusControllerTest {
//
//	@Mock
//	BusService busService;
//
//	@InjectMocks
//	BusController busController;
//
//	private Bus testBus;
//	private List<Bus> testBusList;
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Before
//	public void setUp() {
//		mockMvc = MockMvcBuilders.standaloneSetup(busController).build();
//	}
//
////    @Test
////    void testGetAllbus() {
////        // define the behavior of the mocked method
////        when(busService.getBus()).thenReturn(testBusList);
////
////        // call the method being tested
////        ResponseEntity<List<Bus>> responseEntity = busController.getAllbus();
////
////        // check the result
////        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
////        assertEquals(testBusList, responseEntity.getBody());
////    }
////
////    @Test
////    void testGetBusById() {
////        // define the behavior of the mocked method
////        when(busService.getBus(testBus.getId())).thenReturn(testBus);
////
////        // call the method being tested
////        ResponseEntity<Bus> responseEntity = busController.getBusById(testBus.getId());
////
////        // check the result
////        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
////        assertEquals(testBus, responseEntity.getBody());
////    }
//
//	@Test
//	void testCreateBus() throws Exception {
//		// define the behavior of the mocked method
//		testBus = new Bus();
//		testBus.setBus_number("54as");
//		testBus.setName("abas");
//		testBus.setTotalNumberOfseats(10);
//		when(busService.createBus(testBus)).thenReturn(testBus);
//
//		mockMvc.perform(post("/create")
//	            .contentType(MediaType.APPLICATION_JSON)
//	            .content(asJsonString(testBus)))
//	            .andExpect(status().isOk());
////	            .andExpect(jsonPath("$.name", is(testBus.getName())))
////	            .andExpect(jsonPath("$.bus_number", is(testBus.getBus_number())))
////	            .andExpect(jsonPath("$.totalNumberOfseats", is(testBus.getTotalNumberOfseats())));
//
//	}
//
//
//	private static String asJsonString(final Object obj) {
//		try {
//			final ObjectMapper mapper = new ObjectMapper();
//			final String jsonContent = mapper.writeValueAsString(obj);
//			return jsonContent;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//}

package nrifintech.busMangementSystem.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import nrifintech.busMangementSystem.Service.interfaces.BusService;
import nrifintech.busMangementSystem.entities.Bus;
import nrifintech.busMangementSystem.payloads.ApiResponse;

@ExtendWith(MockitoExtension.class)
public class BusControllerTest {
    
    @Mock
    private BusService busService;
    
    @InjectMocks
    private BusController busController;
    
    @Test
    public void testGetAllBus() {
        List<Bus> busList = new ArrayList<>();
        Bus bus1 = new Bus();
        bus1.setBus_number("B01");
        bus1.setName("Howrah-SaltLake");
        bus1.setTotalNumberOfseats(30);
        Bus bus2 = new Bus();
        bus2.setBus_number("B02");
        bus2.setName("Sealdah-Babughat");
        bus2.setTotalNumberOfseats(40);
        busList.add(bus1);
        busList.add(bus2);
        when(busService.getBus()).thenReturn(busList);
        
        // when
        ResponseEntity<List<Bus>> response = busController.getAllbus();
        
        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody().get(0).getName()).isEqualTo("Howrah-SaltLake");
        assertThat(response.getBody().get(1).getName()).isEqualTo("Sealdah-Babughat");
        assertThat(response.getBody().get(0).getBus_number()).isEqualTo("B01");
        assertThat(response.getBody().get(1).getBus_number()).isEqualTo("B02");
        assertThat(response.getBody().get(0).getTotalNumberOfseats()).isEqualTo(30);
        assertThat(response.getBody().get(1).getTotalNumberOfseats()).isEqualTo(40);
    }
    
    @Test
    public void testGetBusById() {
        // given
    	Bus bus1 = new Bus();
        bus1.setBus_number("B01");
        bus1.setName("Howrah-SaltLake");
        bus1.setTotalNumberOfseats(30);
        when(busService.getBus(1)).thenReturn(bus1);
        
        // when
        ResponseEntity<Bus> response = busController.getBusById(1);
        
        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("Howrah-SaltLake");
        assertThat(response.getBody().getBus_number()).isEqualTo("B01");
        assertThat(response.getBody().getTotalNumberOfseats()).isEqualTo(30);
    }
    
    @Test
    public void testCreateBus() {
        // given
    	Bus bus1 = new Bus();
        bus1.setBus_number("B01");
        bus1.setName("Howrah-SaltLake");
        bus1.setTotalNumberOfseats(30);
        when(busService.createBus(bus1)).thenReturn(bus1);
        
        // when
        ResponseEntity<Bus> response = busController.createBus(bus1);
        
        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getName()).isEqualTo("Howrah-SaltLake");
        assertThat(response.getBody().getBus_number()).isEqualTo("B01");
        assertThat(response.getBody().getTotalNumberOfseats()).isEqualTo(30);
    }
    
    public void testUpdateBus() throws Exception {
        // Create a test bus
        Bus bus = new Bus();
        bus.setBus_number("Test Bus");
        bus.setName("Test Bus");
        bus.setTotalNumberOfseats(50);

        // Convert bus to JSON format
        ObjectMapper objectMapper = new ObjectMapper();
        String busJson = objectMapper.writeValueAsString(bus);

        // Mock the bus service's updateBus method
        when(busService.updateBus(eq(bus), eq(1))).thenReturn(bus);

        // Setup the MockMvc instance
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(busController).build();

        // Perform the update request
        mockMvc.perform(post("/api/v1/bus/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(busJson))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").doesNotExist())
                .andExpect(jsonPath("$.bus_umber").value("Test Bus"))
                .andExpect(jsonPath("$.name").value("Test Bus"))
                .andExpect(jsonPath("$.total_number_ofseats").value(50));
    }

    
    @Test
    public void testDeleteBus() {
        int busId = 1;
        ResponseEntity<?> response = busController.deleteBus(busId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ApiResponse apiResponse = (ApiResponse) response.getBody();
        assertTrue(apiResponse.isSuccess());
        assertEquals("bus deleted", apiResponse.getMessage());
        // verify that the bus was actually deleted
        assertNull(busController.getBusById(busId).getBody());
    }
}
