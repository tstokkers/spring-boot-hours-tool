import com.fasterxml.jackson.databind.ObjectMapper;
import nl.sourcelabs.WorkedHoursResource;
import nl.sourcelabs.domain.User;
import nl.sourcelabs.domain.WorkedHours;
import nl.sourcelabs.repository.UserRepository;
import nl.sourcelabs.repository.WorkedHoursRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class WorkedHoursResourceTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private WorkedHoursRepository workedHoursRepository;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Configuration
    @EnableAutoConfiguration
    public static class Config {
        @Bean
        public WorkedHoursResource workedHoursResource() {
            return new WorkedHoursResource();
        }
    }

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetAllWorkedHours() throws Exception {
        mockMvc.perform(get("/worked-hours").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testPostWorkedHoursWithNonExistingUser() throws Exception {
        WorkedHours workedHours = new WorkedHours("username1", new Double(8), LocalDate.now());
        mockMvc.perform(post("/worked-hours", workedHours).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPostWorkedHoursWithExistingUser() throws Exception {
        given(this.userRepository.findUserByUsername("username1")).willReturn(new User("user", "user", "username1", "user@us.er"));

        WorkedHours workedHours = new WorkedHours("username1", new Double(8), LocalDate.now());
        mockMvc.perform(post("/worked-hours").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(asJsonString(workedHours)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
