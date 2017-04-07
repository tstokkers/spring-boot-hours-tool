package nl.sourcelabs.repository;

import nl.sourcelabs.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tim on 6-4-2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(final String username);
}
