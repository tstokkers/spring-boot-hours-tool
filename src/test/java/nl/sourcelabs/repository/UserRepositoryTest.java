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
        entityManager.persist(new User("voornaam3","achternaam3","username3","a@b.c"));
        entityManager.persist(new User("voornaam4","achternaam4","username4","d@e.f"));

        User foundUser = userRepository.findUserByUsername("username3");
        Assert.assertNotNull(foundUser);
        Assert.assertEquals("voornaam3", foundUser.getFirstName());

    }

}