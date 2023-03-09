package nrifintech.busMangementSystem.controllers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import nrifintech.busMangementSystem.Service.interfaces.UserService;
import nrifintech.busMangementSystem.entities.User;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest
{
	@InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testAddEmployee()
    {
    	User user = new User();
        user.setEmail("demo12@gmail.com");
        user.setEmployeeId("E012");
        user.setName("Demo user");
        user.setPassword("demo123");
        user.setType(1);

        when(userService.createUser(any(User.class))).thenReturn(new User());

        mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(Jackson2JsonObjectMapper.writeValueAsString(user)))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/1"));
    }

//    @Test
//    public void testFindAll() {
//        Employee employee1 = new Employee(1, "Lokesh", "Gupta", "howtodoinjava@gmail.com");
//        Employee employee2 = new Employee(2, "Alex", "Gussin", "example@gmail.com");
//        Employees employees = new Employees();
//        employees.setEmployeeList(Arrays.asList(employee1, employee2));
//
//        when(employeeDAO.getAllEmployees()).thenReturn(employees);
//
//        Employees result = employeeController.getEmployees();
//
//        assertThat(result.getEmployeeList().size()).isEqualTo(2);
//        assertThat(result.getEmployeeList().get(0).getFirstName()).isEqualTo(employee1.getFirstName());
//        assertThat(result.getEmployeeList().get(1).getFirstName()).isEqualTo(employee2.getFirstName());
//    }
//}
}