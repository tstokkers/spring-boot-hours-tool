package nl.sourcelabs.repository;

import nl.sourcelabs.domain.WorkedHours;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WorkedHoursRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkedHoursRepository workedHoursRepository;

    @Test
    public void testFindWorkedHoursByUsername() throws Exception {
        entityManager.persist(new WorkedHours("username1", 1.5, LocalDate.of(2017, 4, 2)));
        entityManager.persist(new WorkedHours("username1", 1.5, LocalDate.of(2017, 4, 1)));
        entityManager.persist(new WorkedHours("username1", 1.5, LocalDate.of(2017, 4, 3)));

        List<WorkedHours> workedHoursList = workedHoursRepository.findWorkedHoursByUsernameOrderByWorkDateAsc("username1");
        assertEquals(3, workedHoursList.size());
        WorkedHours workedHours = workedHoursList.get(0);
        assertEquals("username1", workedHours.getUsername());

    }

}