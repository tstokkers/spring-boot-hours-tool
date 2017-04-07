package nl.sourcelabs.repository;

import nl.sourcelabs.domain.WorkedHours;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

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
        entityManager.persist(new WorkedHours(userRepository.findUserByUsername("username1"), 1.5, LocalDate.now()));

        WorkedHours workedHours = workedHoursRepository.findWorkedHoursByUser(userRepository.findUserByUsername("username1"));
        Assert.assertNotNull(workedHours);
        Assert.assertEquals("voornaam1", workedHours.getUser().getFirstName());

    }

}