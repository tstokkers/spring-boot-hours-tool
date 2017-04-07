package nl.sourcelabs.repository;

import nl.sourcelabs.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by tim on 6-4-2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindUserByUsername() throws Exception {
        User foundUser = userRepository.findUserByUsername("username1");
        Assert.assertNotNull(foundUser);
        Assert.assertEquals("voornaam1", foundUser.getFirstName());

    }

}