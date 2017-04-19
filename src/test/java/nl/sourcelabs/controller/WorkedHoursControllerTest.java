package nl.sourcelabs.controller;

import nl.sourcelabs.config.WorkedHoursConfig;
import nl.sourcelabs.domain.User;
import nl.sourcelabs.repository.UserRepository;
import nl.sourcelabs.repository.WorkedHoursRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={WorkedHoursConfig.class})
@WebAppConfiguration
@DataJpaTest
public class WorkedHoursControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private WorkedHoursRepository workedHoursRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testAddWorkedHours() throws Exception{
        entityManager.persist(new User("test","testers","testuser","test@tester.test"));
        User user = userRepository.findUserByUsername("testuser");
        System.out.println(user.getUsername());
        mockMvc.perform(post("/add?{username}&{hours}&{date}","testuser", "4.5", "1-1-2017" )).andExpect(status().isAccepted());
    }
}
