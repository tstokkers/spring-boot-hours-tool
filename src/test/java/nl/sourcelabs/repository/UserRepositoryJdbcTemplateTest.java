package nl.sourcelabs.repository;

import nl.sourcelabs.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("nl.sourcelabs.repository")
public class UserRepositoryJdbcTemplateTest {

    @Autowired
    private UserRepositoryJdbcTemplate userRepository;

    @Test
    public void testFindUserByUsername() throws Exception {

        // TODO insert (create method in userRepository).

        User foundUser = userRepository.findUserByUsername("username3");
        Assert.assertNotNull(foundUser);
        Assert.assertEquals("voornaam3", foundUser.getFirstName());

    }

}